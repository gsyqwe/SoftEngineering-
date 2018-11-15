package ui;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class MusicPlay implements Runnable {
	private String file;//文件
   public MusicPlay(String s) {
	   file=s;
      }      
   public void run() {
	    File soundFile =new File(file);
	    //音乐文件
        AudioInputStream ad = null;
        //调用API中的AudioInputStream类
        //音频输入流，从音频输入流写入外部文件
        try {
          ad = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e1) {
         e1.printStackTrace();
         return;
          }
        //利用AudioFormat类
        AudioFormat format = ad.getFormat();
        //AudioFormat是一个音频格式类，解析音频的格式
        SourceDataLine a = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format); 
        //SourceDataLine提供将音频数据写入数据行的缓冲区中的方法
        //DataLine.Info继承的嵌套类
        //除了继承自其超类的类信息之外，DataLine.Info 还提供特定于数据行的其他信息。
        try {
        a = (SourceDataLine) AudioSystem.getLine(info);
        a.open(format);
        //open(AudioFormat format) 
        //打开具有指定格式和建议缓冲区大小的行，这样可使该行获得所有所需系统资源并变得可操作。
        } catch (Exception e) {
              e.printStackTrace();
        return;
        } 
        a.start();
        int nBytesRead = 0;
            byte[] abData = new byte[512]; 
            try {
            while (nBytesRead != -1) {
            nBytesRead = ad.read(abData, 0, abData.length);
            //从音频输入流读取数据下一个字节。音频输入流的帧大小必须是一个字节，否则将抛出 IOException。
            if (nBytesRead >= 0)
            a.write(abData, 0, nBytesRead);
            }
               } catch (IOException e) {
               e.printStackTrace();
               return;
               } finally {
               a.drain();
             //通过在清空数据行的内部缓冲区之前继续数据 I/O，排空数据行中的列队数据。
               a.close();
               //关闭此音频输入流并释放与该流关联的所有系统资源。
        }
       } 
}
