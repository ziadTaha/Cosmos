package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.controllers;

import java.io.File;

import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class NewFolderController {
	@FXML
	private Button create;

	@FXML
	private TextField folderName;
	private IContact contact;
	public void setup(IContact contact)
	{
		this.contact=contact;
	}

	@FXML
	void createNewFolder(ActionEvent event) {
		if(folderName.equals(""))
		{
			Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("fill search type");
            alert.showAndWait();
		}
		else
		{
		File file = new File ("accounts/"+contact.getName()+"/"+contact.getEmail());
    	File[] files =file.listFiles();
    	int flag=0;
    	for(File f:files)
    	{
    		if(folderName.equals(f.getName()))
    		{
    			flag=1;
    			break;
    		}
    	}
    	if(flag==0)
    	{
    		IFolder folder = new IFolder(folderName.getText(), contact);
    		folder.createDirectory();
    		folder.makeIndex();
    	}
		}
		Stage stage  = (Stage) create.getScene().getWindow();
		stage.close();

	}

}
