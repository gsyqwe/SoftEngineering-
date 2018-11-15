package ui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Log extends Application{
	public static void main(String[]args){
		launch(args);
	}
	 private static ArrayList<String>userlist=new ArrayList<String>();
	 private static ArrayList<String>passwordlist=new ArrayList<String>();
	 //生成2个ArrayList来保存密码和用户名
	 private PasswordField pb = new PasswordField();
	 private TextField textfield=new TextField();
	@Override
	public void start(final Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String>userlist1=new ArrayList<String>();
		Register ri=new Register();
		userlist1=ri.getUserID();
		//得到register的userlist
		for(int i=0;i<userlist1.size();i++){
			userlist.add(userlist1.get(i));
		}
		//将register中的userlist添加到userlist中
		ArrayList<String>passwordlist1=new ArrayList<String>();
		passwordlist1=ri.getPassword();
		for(int i=0;i<passwordlist1.size();i++){
			passwordlist.add(passwordlist1.get(i));
		}
		//讲register中的Password中的密码加到密码中
	    Label label = new Label("Password"); 
	    Label label2=new Label("UserID     ");
		Button button=new Button("           Log          ");
		Button button1=new Button("        register       ");
		Button button2=new Button("forget passrword");
		//利用VBox进行布局
		VBox vb = new VBox();  
        vb.setPadding(new Insets(10, 0, 0, 10));  
        vb.setSpacing(10);  
        HBox hb = new HBox();  
        hb.setSpacing(10);  
        hb.setAlignment(Pos.CENTER_LEFT); 
        HBox hb1 = new HBox();  
        hb1.setSpacing(10);  
        hb1.setAlignment(Pos.CENTER_LEFT); 
		 hb.getChildren().addAll(label, pb);  
		 hb1.getChildren().addAll(label2,textfield);
	     vb.getChildren().addAll(hb1,hb,button,button1,button2);  
	     //给Log的button添加事件，查看ArrayList中的userlist和password是否有相对应的用户名和密码
	     button.setOnAction(new EventHandler<ActionEvent>() {
             public void handle(ActionEvent e) {
            	 String code = textfield.getText();
            	 String password=pb.getText();
            	 MainFrame mainframe=new MainFrame();
     			 try {
     				primaryStage.close();
     				boolean difference=false;
     				boolean truth=false;
     				int m=0;
     				for(int i=0;i<userlist.size()-1;i++){
     					if(userlist.get(i).equals(code)){
     						m=i;
     					}
     				}
     				if(userlist.get(m).equals(code)){
     					difference=true;
     				}
     				if(passwordlist.get(m).equals(password)){
     					truth=true;
     				}
     				if(difference==true&&truth==true){
     					primaryStage.close();
     					mainframe.start(new Stage());
     				}else{
     					primaryStage.show();
     				}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
             }
              });
	     //给register的button添加事件，进行注册
	     button1.setOnAction(new EventHandler<ActionEvent>(){
	    	public void handle(ActionEvent e){
	    		Register register=new Register();
	    		try {
	    			primaryStage.close();
					register.start(new Stage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	     });
	     //给forgetPassword添加事件，忘记密码后可修改
	     button2.setOnAction(new EventHandler<ActionEvent>(){
		    	public void handle(ActionEvent e){
		    		ForgetPassword forget=new ForgetPassword();
		    		try {
		    			primaryStage.close();
						forget.start(new Stage());
						passwordlist=forget.getpassword();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
		     });
	     Scene s  = new Scene(vb,300,200);  
	     primaryStage.setScene(s);  
	     primaryStage.show();
	}
	//将Log的Userlist传出去，传给ForgetPassword进行修改密码
	public ArrayList<String> getUserID(){
		return userlist;
	}
}
