package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.controllers;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50.MyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.MailsMani;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.MyApp;
import eg.edu.alexu.csd.datastructure.queue.IQueue;
import eg.edu.alexu.csd.datastructure.queue.cs06_cs04_cs19.MyLinkedBased;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ComposeController {

	private Stage myStage;
	private MyApp app;
	private IContact contact;
	private ObservableList<String> atts;
	private IMail mail;

	public void setup(Stage myStage, MyApp app, IContact ccntact) {
		this.myStage = myStage;
		this.app = app;
		this.contact = ccntact;
		atts = FXCollections.observableArrayList();
		mail = new IMail();

	}

	public void setup(Stage myStage, MyApp app, IContact ccntact, IMail mail) {
		this.myStage = myStage;
		this.app = app;
		this.contact = ccntact;
		atts = FXCollections.observableArrayList();
		this.mail = mail;
		name.setText(contact.getName());

	}

	@FXML
	private ComboBox<String> attachments;

	@FXML
	private ImageView drft;

	@FXML
	private Label name;

	@FXML
	private Button signoutButton;

	@FXML
	private Button back;

	@FXML
	private TextField to;

	@FXML
	private ImageView attach;

	@FXML
	private ImageView delete;

	@FXML
	private Button send;

	@FXML
	private TextField subjekt;

	@FXML
	private TextArea mesg;

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
	void addAttachment(MouseEvent event) {
		FileChooser chooser = new FileChooser();
		File attachment = chooser.showOpenDialog(attach.getScene().getWindow());
		if(attachment!=null)
		{
		atts.add(attachment.getPath());
		attachments.setItems(atts);
		}

	}

	@FXML
	void deleteAttachments(MouseEvent event) {
		atts.remove(attachments.getValue());
		attachments.setItems(atts);
	}

	@FXML
	void sent(ActionEvent event) {
                 if(!to.getText().equals("")||!subjekt.getText().equals("")
                		 ||!mesg.getText().equals(""))
                 {
                	 getReady();
                	 app.compose(mail);
                 }
	}

	@FXML
	void bak(ActionEvent event) {
		app.setViewingOptions(null, null, new ISort("date"));
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/mails.fxml"));
		Parent root;

		try {
			root = fxmlLoader.load();
			myStage.setTitle("Cosmos Mail");
			myStage.setScene(new Scene(root, 879.0D, 564.0D));
			myStage.setResizable(false);
			MailsController mailsController = fxmlLoader.getController();
			mailsController.setup(myStage, app, contact);
			myStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void draft(MouseEvent event) {
		getReady();
		System.out.println(mail.getSubject()+"sasa");
		MailsMani mailsMani = new MailsMani();
		mail.setFolder("draft");
		mailsMani.sendMail(mail, "accounts/" + contact.getName() + "/" + contact.getEmail() + "/draft");
	}

	public void getReady() {
		mail.setSubject(subjekt.getText());
		String[] splited = to.getText().split(",");
		IQueue rec = new MyLinkedBased();
		for (int i = 0; i < splited.length; i++) {
			rec.enqueue(splited[i]);
		}
		mail.setRecivers(rec);
		ILinkedList list = new MyLinkedList();
		for (int i = 0; i < atts.size(); i++) {
			list.add(atts.get(i));
		}
		mail.setAttachmets(list);
		mail.setSender(contact.getEmail());
		mail.setText(mesg.getText());
		mail.setTime(LocalDateTime.now());
		mail.setPriority(1);
	}

}
