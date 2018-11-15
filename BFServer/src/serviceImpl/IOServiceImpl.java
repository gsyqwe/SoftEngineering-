package serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import service.IOService;

public class IOServiceImpl implements IOService{
	
	public boolean writeFile(String file, String userId, String fileName) {
		File f = new File(userId + "_" + fileName);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public String readFile(String userId, String fileName) {
		// TODO Auto-generated method stub
		File f=new File(userId+"_"+fileName);
		String s="";
		char []buf=new char[1024];
		try {
			FileReader fr=new FileReader(f);
			try {
				fr.read(buf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s=String.valueOf(buf);
		return s;
	}

	public String readFileList(String userId) {
		// TODO Auto-generated method stub
		String s="";
		char []buf=new char[1024];
		File f=new File("admin_Code");
		try {
			FileReader fr=new FileReader(f);
			try {
				fr.read(buf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s=String.valueOf(buf);
		return s;
	}
	
}
