package ui;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;


public class NewExitItem extends MenuItem{
       public NewExitItem(String str){
    	   super(str);
    	   this.setOnAction(new EventHandler<ActionEvent>() {

             public void handle(ActionEvent e) {
            System.exit(0);
           }
         });
       }
}
