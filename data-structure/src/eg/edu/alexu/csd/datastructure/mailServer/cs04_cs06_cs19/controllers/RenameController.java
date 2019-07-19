package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.controllers;

import java.io.File;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50.MyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.FilesManpulator;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.MyApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RenameController {
	@FXML
	private TextField newName;

	@FXML
	private ComboBox<String> chooseFolder;

	@FXML
	private Button renameButton;

	private IContact contact;
	private ObservableList<String> names;
    private MyApp app;
	public void setup(IContact contact,MyApp app)
	{
		this.contact=contact;
		getFolders();
		this.app=app;
	}
	@FXML
	void rename(ActionEvent event) {
		if(!names.contains(newName.getText()))
		{
          IFolder folder = new IFolder(chooseFolder.getValue(), contact);
          IFolder newFolder = new IFolder(newName.getText(), contact);
          newFolder.createDirectory();
          newFolder.makeIndex();
          IMail[] mails=folder.getAllMails();
          ILinkedList list  =new MyLinkedList();
          for(int i = 0 ;i<mails.length;i++)
          {
        	  list.add(mails[i]);
          }
          app.moveEmails(list, newFolder);
          FilesManpulator manpulator = new FilesManpulator();
          manpulator.deleteDirectory(new File("accounts/"+contact.getName()+
        		  "/"+contact.getEmail()+"/"+chooseFolder.getValue()));

		}


	}
	private void getFolders()
    {
    	File file = new File ("accounts/"+contact.getName()+"/"+contact.getEmail());
    	File[] files =file.listFiles();
    	//IFolder[] folders = new IFolder[files.length];
    	 names = FXCollections.observableArrayList();

    	for(int i=0;i<files.length;i++)
    	{
    		if(!files[i].getName().equals("inbox")&&!files[i].getName().equals("sent")
    				&&!files[i].getName().equals("draft")&&!files[i].getName().equals("starred")
    				&&!files[i].getName().equals("trash"))
    		{

    			names.add(files[i].getName());
    		}
    	}
    	chooseFolder.setItems(names);

    }
}
