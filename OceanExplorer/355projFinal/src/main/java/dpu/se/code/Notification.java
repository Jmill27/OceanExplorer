package dpu.se.code;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Notification {
	
	public void win() {
		// notify the user when they win
		Alert alert = new Alert(
				AlertType.INFORMATION, 
				"Winner !!!", 
				ButtonType.YES);
		alert.showAndWait();
	}

}
