package ui;
import java.rmi.RemoteException;
import java.util.ArrayList;

import rmi.RemoteHelper;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ForgetPassword extends Application{
    private TextField textfield=new TextField();
    private PasswordField pb = new PasswordField();
    private TextField textfield1=new TextField();
    private static ArrayList<String> userID=new ArrayList<String>();
    private static ArrayList<String> password=new ArrayList<String>();
    public ArrayList<String> getpassword(){
 	   return password;
    }
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
		Label label1=new Label("UserID            ");
		Label label2=new Label("New Password");
		hb.getChildren().addAll(label2, pb);  
		 hb1.getChildren().addAll(label1,textfield);
	     vb.getChildren().addAll(hb1,hb,textfield1,button,button1);  
	     Scene s  = new Scene(vb,350,200);  
	     primaryStage.setScene(s);  
	     primaryStage.show();
	     button.setOnAction(new EventHandler<ActionEvent>() {
             public void handle(ActionEvent e) {
            	 String code=textfield.getText();
            	 String code1=pb.getText();
            	 Log log=new Log();
                 userID=log.getUserID();
                 int m=0;
                 for(int i=0;i<userID.size()-1;i++){
                	 if(userID.get(i).equals(code)){
                		 m=i;
                	 }
                 }
                 if(userID.contains(code)){
                	 password.add(code1);
     			 try {
     				primaryStage.close();
					log.start(new Stage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
             }
             else{
            	 textfield.clear();
            	 pb.clear();
            	 textfield1.setText("no user");
             }
             }
              });
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
}
