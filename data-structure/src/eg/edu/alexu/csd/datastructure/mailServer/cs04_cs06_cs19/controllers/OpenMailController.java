package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50.MyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.FilesManpulator;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.MailsMani;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.MyApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OpenMailController {

    @FXML
    private TextArea date;

    @FXML
    private TextArea msg;

    @FXML
    private ImageView move;

    @FXML
    private HBox attachmentz;

    @FXML
    private ImageView star;
    @FXML
    private ImageView unStar;

    @FXML
    private TextArea subject;

    @FXML
    private ComboBox<String> foldersChoose;

    @FXML
    private Button back;

    @FXML
    private Button changePri;

    @FXML
    private ScrollBar textScroll;

    @FXML
    private ImageView delete;

    @FXML
    private StackPane str;

    @FXML
    private ComboBox<Integer> prior;


    @FXML
    private Button signoutButton;

    @FXML
    private TextArea from;

    @FXML
    private TextArea to;

    @FXML
    private ComboBox<String> chooseAttach;

    @FXML
    private Button openAttac;
    @FXML
    private Label name;

    private Stage myStage;
    private MyApp app;
    private IContact contact;
    private MailsMani mailsMani;
    private FilesManpulator filesManpulator;
    private IMail mail;
    public void setup(Stage myStage,MyApp app,IContact contact,IMail mail)
    {
    	this.myStage= myStage;
    	this.app=app;
    	this.contact =contact;
    	filesManpulator= new FilesManpulator();
    	mailsMani= new MailsMani();
    	this.mail=mail;
    	 ObservableList<Integer> data = FXCollections.observableArrayList(1,2,3,4);
    	 prior.setItems(data);
    	 prior.setPromptText(Integer.toString(mail.getPriority()));
    	 getFolders();
         setupAttachment();
         from.setText(mail.getSender());
         subject.setText(mail.getSubject());
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
 		LocalDateTime dateTime = mail.getTime();
 		String timeString = dateTime.format(formatter);
        date.setText(timeString);
        StringBuilder str =new StringBuilder();
        for(int i=0 ;i<mail.getRecivers().size();i++)
	    {
	    	String reciever= mail.getRecivers().dequeue().toString();
	    	mail.getRecivers().enqueue(reciever);
	    	str.append(reciever);
	    	if(i!=mail.getRecivers().size()-1)
	    	{
	    		str.append(",");
	    	}

	    }
        to.setText(str.toString());
        msg.setText(mail.getText());
        msg.setEditable(false);
        from.setEditable(false);
        to.setEditable(false);
        subject.setEditable(false);
        date.setEditable(false);
        name.setText(contact.getName());
        if (mail.isStarred())
    	{
        	unStar.setVisible(false);
    		star.setDisable(false);
    		star.setVisible(true);
    		unStar.setDisable(true);

    	}
    	else
    	{
    		star.setVisible(false);
    		star.setDisable(true);
    		unStar.setVisible(true);
            unStar.setDisable(false);
    	}

    }

    @FXML
    void signout(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/theFXML.fxml"));
		Parent root;
		try {
			root = fxmlLoader.load();
			myStage.setTitle("Cosmos Mail");
			myStage.setScene(new Scene(root, 844.0D, 550.0D));
			myStage.setResizable(false);
			SignInController signInController = fxmlLoader.getController();
			signInController.setup(myStage);
			myStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }


    @FXML
    void setstar(MouseEvent event) {



    		star(mail);
    		star.setVisible(true);
    		star.setDisable(false);
    		unStar.setVisible(false);
            unStar.setDisable(false);



    }
    @FXML
    void setUnStar(MouseEvent event) {

		unstar(mail);
		unStar.setVisible(true);
		star.setDisable(true);
		star.setVisible(false);
		unStar.setDisable(false);

    }

    @FXML
    void trsh(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this mail?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) {
    	    //do stuff

    	ILinkedList list = new MyLinkedList();
    	list.add(mail);
    	app.deleteEmails(list);
    	app.setViewingOptions(null, null, new ISort("date"));
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/mails.fxml"));
		Parent root;

		try {
			root = fxmlLoader.load();
			myStage.setTitle("Cosmos Mail");
			myStage.setScene(new Scene(root, 879.0D, 564.0D));
			myStage.setResizable(false);
			MailsController mailsController = fxmlLoader.getController();
			mailsController.setup(myStage, app,contact);
			myStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
    }


    @FXML
    void changPri(ActionEvent event) {
    	if(mail.getPriority()!=prior.getValue())
        {
        	mail.setPriority(prior.getValue());
        	mailsMani.deleteFromIndex(mail, "accounts/"+contact.getName()+"/"+contact.getEmail()+"/"+mail.getFolder());
        	String mailName=mail.getTime().toString().replaceAll(":", ".");
        	filesManpulator.deleteDirectory(new File("accounts/"+contact.getName()+"/"+contact.getEmail()+"/"+mail.getFolder()+"/"+mailName));
        	moveMail(mail, mail.getFolder());

        }
    }

    @FXML
    void mov(MouseEvent event) {
    	if(!mailsMani.existInInFolder(mail, "accounts/"+contact.getName()+"/"+contact.getEmail()+"/"+foldersChoose.getValue()))
    	{
    		moveMail(mail,foldersChoose.getValue());
    	}

    }
    @FXML
    void bk(ActionEvent event) {
    	app.setViewingOptions(null, null, new ISort("date"));
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/mails.fxml"));
		Parent root;

		try {
			root = fxmlLoader.load();
			myStage.setTitle("Cosmos Mail");
			myStage.setScene(new Scene(root, 879.0D, 564.0D));
			myStage.setResizable(false);
			MailsController mailsController = fxmlLoader.getController();
			mailsController.setup(myStage, app,contact);
			myStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    private void moveMail(IMail mail ,String folderName)
    {

    	IFolder folder = new IFolder(folderName, contact);
    	ILinkedList list = new MyLinkedList();
    	list.add(mail);
    	app.moveEmails(list, folder);
    }
    private void star(IMail mail)
    {

    	mail.setStarred(true);

    	mailsMani.deleteFromIndex(mail,"accounts/"+contact.getName()+"/"+contact.getEmail()+"/"+mail.getFolder());
    	String mailName=mail.getTime().toString().replaceAll(":", ".");
    	filesManpulator.deleteDirectory(new File("accounts/"+contact.getName()+"/"+contact.getEmail()+"/"+mail.getFolder()+"/"+mailName));
    	String folderName=mail.getFolder();
    	moveMail(mail, mail.getFolder());
    	moveMail(mail, "starred");
    	mail.setFolder(folderName);




    }
    private void unstar(IMail mail)
    {
    	mail.setStarred(false);

    	mailsMani.deleteFromIndex(mail, "accounts/"+contact.getName()+"/"+contact.getEmail()+"/"+mail.getFolder());
    	String mailName=mail.getTime().toString().replaceAll(":", ".");
    	filesManpulator.deleteDirectory(new File("accounts/"+contact.getName()+"/"+contact.getEmail()+"/"+mail.getFolder()+"/"+mailName));
    	moveMail(mail, mail.getFolder());
    	mailsMani.deleteFromIndex(mail, "accounts/"+contact.getName()+"/"+contact.getEmail()+"/starred");

    	filesManpulator.deleteDirectory(new File("accounts/"+contact.getName()+"/"+contact.getEmail()+"/starred/"+mailName));

    }
    private void getFolders()
    {
    	File file = new File ("accounts/"+contact.getName()+"/"+contact.getEmail());
    	File[] files =file.listFiles();
    	//IFolder[] folders = new IFolder[files.length];
    	 ObservableList<String> names = FXCollections.observableArrayList();

    	for(int i=0;i<files.length;i++)
    	{
    		if(!files[i].getName().equals("inbox")&&!files[i].getName().equals("sent")
    				&&!files[i].getName().equals("draft")&&!files[i].getName().equals("starred")
    				&&!files[i].getName().equals("trash"))
    		{
    			names.add(files[i].getName());
    		}
    	}
    	foldersChoose.setItems(names);

    }
    @FXML
    void openAttachments(ActionEvent event) {
    	String attachName =chooseAttach.getValue();
    	String mailName=mail.getTime().toString().replaceAll(":", ".");
    	try {
			Desktop.getDesktop().open(new File("accounts/"+contact.getName()+
					"/"+contact.getEmail()+"/"+mail.getFolder()+"/"+mailName+"/attachments/"+attachName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    private void setupAttachment()
    {
    	ILinkedList list =mail.getAttachmets();
    	 ObservableList<String> names = FXCollections.observableArrayList();
    	 for(int i=0;i<list.size();i++)
    	 {
    		 String attachName=list.get(i).toString().substring(list.get(i).toString().lastIndexOf("/")+1);
    		 names.add(attachName);
    	 }
    	chooseAttach.setItems(names);
    }

}
