package ui;

import java.rmi.RemoteException;
import java.util.ArrayList;
import rmi.RemoteHelper;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainFrame extends Application{
	static int count=0;
	private String s="";
    static ArrayList<String>mylist=new ArrayList<String>();//用来保存输入的代码
    static ArrayList<String>mylist1=new ArrayList<String>();//用来保存输入的数据
    static ArrayList<String>mylist2=new ArrayList<String>();//用来保存输出的数据
    //创建2个ArrayList用来实现undo和redo
    static int mk=0;
    static int sk=0;
    static int sin=0;
    static ArrayList<String>mylist3=new ArrayList<String>();
    static ArrayList<String>mylist4=new ArrayList<String>();
    static ArrayList<String>mylist5=new ArrayList<String>();
    //实现undochar和redochar
    //mk用来保存CodeTextfield中的运行数
    //sk用来保存InputTextfield中的运行数
    //sin用来指示此时改修改CodeTextfield还是InputTextfield
    static CodeTextField textfield=new CodeTextField();
    static InputTextField textfield1=new InputTextField();
    static OutputTextField textfield2=new OutputTextField();
    Thread Play=new Thread(new MusicPlay(getClass().getClassLoader().getResource("1.wav").getPath()));
    //利用线程来进行播放wav格式的音频
	@Override
	public void start(final Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//给textfield添加一个改变事件，用来记录输入的改变并将其传入ArrayList中
		textfield.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(mk>(mylist5.size()-1)&&sk>(mylist3.size()-1)){
                	mylist3.add("0");
                	mylist5.add("0");
                }
                //进行比较，比较undochar结束后的数是否和现在ArrayList指向的数相同，如果相同就不添加
                if(mylist3.get(sk).equals(textfield.getText())){
                	
                }else{
            	mk=mk+1;
                sk=sk+1;
                mylist3.set(sk-1,textfield.getText());
                mylist5.set(mk-1,"0");
            }
            }
        });
		//对inputfield也进行changge事件绑定
		textfield1.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if(mk>(mylist5.size()-1)&&sin>(mylist4.size()-1)){
                	mylist4.add("0");
                	mylist5.add("0");
                }
                if(mylist4.get(sin).equals(textfield1.getText())){
                     	
                }else{
                mk=mk+1;
                sin=sin+1;
                mylist4.set(sin-1,textfield1.getText());
                mylist5.set(mk-1,"1");
            }
            }
        });
		//对文件进行读取操作
		String code=RemoteHelper.getInstance().getIOService().readFile("admin", "Code");
		String Param=RemoteHelper.getInstance().getIOService().readFile("admin", "Param");
	    String Output=RemoteHelper.getInstance().getIOService().readFile("admin","OutPut");
	    textfield.setText(code);
	    textfield1.setText(Param);
	    textfield2.setText(Output);
		   //初始化
		   BorderPane border = new BorderPane();
		   GridPane grid=new GridPane();//网格布局
		   MenuBar menubar=new MenuBar();
		   Menu File=new Menu("File");
		   Menu voidclose=new Menu("Voice");
		   MenuItem voidopen=new Menu("On");
		   MenuItem voiddown=new Menu("Off");
		   voidclose.getItems().addAll(voidopen,voiddown);
		   Menu Run=new Menu("Run");
		   Menu Version=new Menu("Version");
		   Menu Undo=new Menu("Undo");
		   Menu Redo=new Menu("Redo");
		   Menu LogOut=new Menu("LogOut");
		   MenuItem newitem=new MenuItem("New");
		   //打开一个新的mainFrame
		   newitem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				primaryStage.close();
				MainFrame gui=new MainFrame();
				try {
					gui.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		   });    
		   NewOpenItem openitem=new NewOpenItem("Open");
		   SaveMenuItem saveitem=new SaveMenuItem("Save");
		   NewExitItem exititem=new NewExitItem("Exit");
		   ExecuteItem executeitem=new ExecuteItem("Execute");
		   VersionItem versionitem1=new VersionItem("first");
		   VersionItem1 versionitem2=new VersionItem1("second");
		   VersionItem2 versionitem3=new VersionItem2("third");
		   VersionItem3 versionitem4=new VersionItem3("fourth");
		   MenuItem undoitem1=new MenuItem("undochar");
		   exititem.setMnemonicParsing(true);
		   exititem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
		   saveitem.setMnemonicParsing(true);
		   saveitem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
		   openitem.setMnemonicParsing(true);
		   openitem.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN));
		   undoitem1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(mk<1){
					textfield.clear();
					textfield1.clear();
					textfield2.setText("no more undochar");
				}else{
				mk=mk-1;
				String s=mylist5.get(mk);
				if(s.equals("0")){
					if(sk<1){
						//比较是否数组越界
						textfield.clear();
						textfield1.clear();
						textfield2.setText("no more undochar");
					}else{
					sk=sk-1;
					String code=mylist3.get(sk);
					textfield.setText(code);
					}
				}else{
					//进行比较判断是否有更多的位置
					if(sin<1){
						textfield.clear();
						textfield1.clear();
						textfield2.setText("no more undochar");
					}else{
					sin=sin-1;
					String param=mylist4.get(sin);
					textfield1.setText(param);
					}
				}
				}
			}
		   });
		   UndoItem undoitem=new UndoItem("undo");
		   RedoItem redoitem=new RedoItem("redo");
		   MenuItem redoitem1=new MenuItem("redochar");
		   //设定快捷键
		   executeitem.setMnemonicParsing(true);
		   executeitem.setAccelerator(new KeyCodeCombination(KeyCode.F11, KeyCombination.SHORTCUT_DOWN));
		   redoitem.setMnemonicParsing(true);
		   redoitem.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.SHORTCUT_DOWN));
		   redoitem1.setMnemonicParsing(true);
		   redoitem1.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
		   undoitem1.setMnemonicParsing(true);
		   undoitem1.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN));
		   undoitem.setMnemonicParsing(true);
		   undoitem.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));
		   redoitem1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(mk>(mylist5.size()-1)){
					//比较数组越界问题
					textfield2.setText("no more redo char");
				}else{
					mk=mk+1;
					String s=mylist5.get(mk-1);
					if(s.equals("0")){
						if(sk>(mylist3.size()-1)){
							//数组越界问题
							textfield.clear();
							textfield1.clear();
							textfield2.setText("no more redochar");
						}else{
							sk=sk+1;
							String code=mylist3.get(sk-1);
							textfield.setText(code);
						}
					}else{
						if(sin>(mylist4.size()-1)){
							textfield.clear();
							textfield1.clear();
							textfield2.setText("no more redochar");
						}else{
							sin=sin+1;
							String param=mylist4.get(sin-1);
							textfield1.setText(param);
						}
					}
				}
			}
			   
		   });
		   //LogOut的事件，返回上一级界面
		   MenuItem logoutitem=new MenuItem("LogOut");
		   logoutitem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				primaryStage.close();
				Log log=new Log();
				try {
					log.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		   });
	       primaryStage.setTitle("Welcome to Use");
		   Play.start();//开始音乐播放
	       File.getItems().addAll(newitem,openitem,saveitem,exititem);//添加items
	       LogOut.getItems().add(logoutitem);
	       Run.getItems().add(executeitem);
	       Version.getItems().addAll(versionitem1,versionitem2,versionitem3,versionitem4);
	       Undo.getItems().addAll(undoitem,undoitem1);
	       Redo.getItems().addAll(redoitem,redoitem1);
	       menubar.getMenus().addAll(File,Run,Version,Undo,Redo,voidclose,LogOut);//添加menu
	       textfield.setMinSize(300,240);//设置其大小
	       textfield.setMaxSize(500,300);
	       textfield1.setMinSize(225,220);
	       textfield1.setMaxSize(300, 200);
	       textfield2.setMinSize(225, 220);
	       textfield2.setMaxSize(300, 200);
	       grid.setHgap(50);
	       grid.setVgap(50);
	       grid.setPadding(new Insets(0, 0, 0, 10));
	       border.setTop(menubar);
	       border.setCenter(grid);
	       grid.add(textfield, 0,0,3,5);
	       grid.add(textfield1,0,5);
	       grid.add(textfield2,1,5);
	       StackPane root = new StackPane();
	       root.getChildren().add(border);
	       Scene scene=new Scene(root,550,550);
	       primaryStage.setScene(scene);
	       voidopen.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Play=new Thread(new MusicPlay(getClass().getClassLoader().getResource("1.wav").getPath()));
				Play.start();
			}
			});
	       voiddown.setOnAction(new EventHandler<ActionEvent>(){
				@SuppressWarnings("deprecation")
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
				Play.stop();//音乐停止播放
				}
				});
	       primaryStage.show();  
	    }
	public ArrayList<String> getCodeList(){
		return mylist;
	}
	public ArrayList<String> getInputList(){
		return mylist1;
	}
	public ArrayList<String> getOutputList(){
		return mylist2;
	}
	public int getCount(){
		return count;
	}
	class ExecuteItem extends MenuItem{
	    public ExecuteItem(String str){
	 	   super(str);
	 	   this.setOnAction(new EventHandler<ActionEvent>() {
		              public void handle(ActionEvent e) {
		            	  count++;
		            	  String code = textfield.getText();
			              String param=textfield1.getText();
			            try{
			            	s=RemoteHelper.getInstance().getExecuteService().execute(code,param);
			            	textfield2.setText(s);
			            }catch(RemoteException e1){
			            	e1.printStackTrace();
			            }
			            String t1=textfield.getText();
					   	   String t2=textfield1.getText();
					   	   String t3=textfield2.getText();
					   	   mylist.add(t1);
					   	   mylist1.add(t2);
					   	   mylist2.add(t3);
		               }
		    	   });
	    }
	    public ArrayList<String> getmylist(){
	    	return mylist;
	    }
	    public ArrayList<String> getmylist1(){
	    	return mylist1;
	    }
	    public ArrayList<String> getmylist2(){
	    	return mylist2;
	    }
	}
	class SaveMenuItem extends MenuItem{
		MainFrame mainframe=new MainFrame();
		public SaveMenuItem(String str){
	    	  super(str);
	    	  this.setOnAction(new EventHandler<ActionEvent>() {
	             public void handle(ActionEvent e) {
	            	 String code = textfield.getText();
	            	 String param=textfield1.getText();
	     			try {
	     				String Output=RemoteHelper.getInstance().getExecuteService().execute(code,param);
	     				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "Code");
	     				RemoteHelper.getInstance().getIOService().writeFile(param, "admin", "Param");
	     				RemoteHelper.getInstance().getIOService().writeFile(Output,"admin","OutPut");
	     				mylist.add(code);
	     				mylist1.add(param);
	     				mylist2.add(Output);
	     				textfield2.setText("you have save the file");
	     			} catch (RemoteException e1) {
	     				e1.printStackTrace();
	     		    }
	               }
	              });
	 }
	}
	 class NewOpenItem extends MenuItem{
	       public NewOpenItem(String str){
	    	   super(str);
	    	   this.setOnAction(new EventHandler<ActionEvent>() {
	              public void handle(ActionEvent e) {
	            	  try{
	            		  String code=RemoteHelper.getInstance().getIOService().readFile("admin", "Code");
	            		  String param=RemoteHelper.getInstance().getIOService().readFile("admin", "Param");
	            		  String output=RemoteHelper.getInstance().getIOService().readFile("admin","OutPut");
	            		  textfield.setText(code);
	            		  textfield1.setText(param);
	            		  textfield2.setText(output);
	            	  }catch(RemoteException e2){
	            		  e2.printStackTrace();
	            	  }
	               }
	    	   });
	       }
	}
    class RedoItem extends MenuItem{
       public RedoItem(String str){
    	   super(str);
    	   this.setOnAction(new EventHandler<ActionEvent>() {
	              public void handle(ActionEvent e) {
	              if(count>(mylist.size()-1)){
	            	  textfield.clear();
	            	  textfield1.clear();
	            	  textfield2.setText("no more redo");
	              }else {
	              count=count+1;
	              String code=mylist.get(count-1);
	           	  String param=mylist1.get(count-1);
	           	  String result=mylist2.get(count-1);
	              textfield.setText(code);
	              textfield1.setText(param);
	              textfield2.setText(result);
	              }
	              }
	    	   });
       }
}
    class UndoItem extends MenuItem{
       public UndoItem(String str){
    	   super(str);
    	   this.setOnAction(new EventHandler<ActionEvent>() {
	              public void handle(ActionEvent e) {
	            	  if(count<1){
	            		  textfield.clear();
	            		  textfield1.clear();
	            		  textfield2.setText("no more undo");
	            	  }else{
	               count=count-1;
	               String code=mylist.get(count);
	           	   String param=mylist1.get(count);
	           	   String result=mylist2.get(count);
	               textfield.setText(code);
	               textfield1.setText(param);
	               textfield2.setText(result);
	            	  }
	              }
	    	   });
       }
}
    class VersionItem extends MenuItem{
        public VersionItem(String str){
     	   super(str);
     	   this.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub	
				if(mylist.size()>=1){
					String code=mylist.get(0);
					String param=mylist1.get(0);
					String result=mylist2.get(0);
					textfield.setText(code);
					textfield1.setText(param);
					textfield2.setText(result);
				}else{
					textfield.clear();
					textfield1.clear();
					textfield2.setText("no this history version");
				}
			}
     	   });
        }
 }
    class VersionItem1 extends MenuItem{
        public VersionItem1(String str){
     	   super(str);
     	  this.setOnAction(new EventHandler<ActionEvent>(){
  			public void handle(ActionEvent arg0) {
  				// TODO Auto-generated method stub	
  				if(mylist.size()>=2){
  					String code=mylist.get(1);
  					String param=mylist1.get(1);
  					String result=mylist2.get(1);
  					textfield.setText(code);
  					textfield1.setText(param);
  					textfield2.setText(result);
  				}else{
  					textfield.clear();
  					textfield1.clear();
  					textfield2.setText("no this history version");
  				}
  			}
       	   });
        }
 }
    class VersionItem2 extends MenuItem{
    	public VersionItem2(String str){
    		super(str);
    		this.setOnAction(new EventHandler<ActionEvent>(){
    			public void handle(ActionEvent arg0) {
    				// TODO Auto-generated method stub	
    				if(mylist.size()>=3){
    					String code=mylist.get(2);
    					String param=mylist1.get(2);
    					String result=mylist2.get(2);
    					textfield.setText(code);
    					textfield1.setText(param);
    					textfield2.setText(result);
    				}else{
    					textfield.clear();
    					textfield1.clear();
    					textfield2.setText("no this history version");
    				}
    			}
         	   });
    	}

    }
    class VersionItem3 extends MenuItem{
        public VersionItem3(String str){
     	   super(str);
     	  this.setOnAction(new EventHandler<ActionEvent>(){
  			public void handle(ActionEvent arg0) {
  				// TODO Auto-generated method stub	
  				if(mylist.size()>=4){
  					String code=mylist.get(3);
  					String param=mylist1.get(3);
  					String result=mylist2.get(3);
  					textfield.setText(code);
  					textfield1.setText(param);
  					textfield2.setText(result);
  				}else{
  					textfield.clear();
  					textfield1.clear();
  					textfield2.setText("no this history version");
  				}
  			}
       	   });
        }
 }
}