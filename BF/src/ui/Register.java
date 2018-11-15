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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class Register extends Application{
	 private static ArrayList<String>userlist=new ArrayList<String>();
	 private static ArrayList<String>passwordlist=new ArrayList<String>();
	 private PasswordField pb = new PasswordField();
	 private TextField textfield=new TextField();
	@Override
	public void start(final Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		VBox vb = new VBox();  
        vb.setPadding(new Insets(10, 0, 0, 10));  
        vb.setSpacing(10);  
        HBox hb = new HBox();  
        hb.setSpacing(10);  
        hb.setAlignment(Pos.CENTER_LEFT); 
        HBox hb1 = new HBox();  
        hb1.setSpacing(10);  
        hb1.setAlignment(Pos.CENTER_LEFT); 
		Button button=new Button("OK");
		Button button1=new Button("Cancle");
		Label label1=new Label("UserID    ");
		Label label2=new Label("Password");
		hb.getChildren().addAll(label2, pb);  
		 hb1.getChildren().addAll(label1,textfield);
	     vb.getChildren().addAll(hb1,hb,button,button1);  
	     Scene s  = new Scene(vb,300,200);  
	     primaryStage.setScene(s);  
	     primaryStage.show();
	     //点击OK后
	     button.setOnAction(new EventHandler<ActionEvent>() {
             public void handle(ActionEvent e) {
            	 String code=textfield.getText();
            	 String code1=pb.getText();
	     		 userlist.add(code);
	     		 passwordlist.add(code1);
     			 try {
     				primaryStage.close();
     				Log log=new Log();
					log.start(new Stage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
             }
              });
	     //点击cancle后
	     button1.setOnAction(new EventHandler<ActionEvent>(){
	    	 public void handle(ActionEvent e){
	    		 primaryStage.close();
	    		 Log log=new Log();
	    		 try {
					log.start(new Stage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	 }
	     });
	}
	public ArrayList<String> getUserID(){
		return userlist;
	}
	public ArrayList<String>getPassword(){
		return passwordlist;
	}
}