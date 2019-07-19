package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.controllers;

import java.io.IOException;

import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.MyApp;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {

	@FXML
	private Hyperlink nomailsignup;

	@FXML
	private TextField mailField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private CheckBox remembermeBox;

	@FXML
	private Button signinButton;

	@FXML
	private Label errorLabel;
	private MyApp app;
	private Stage myStage;
	private Validator validate;

	public SignInController() {
		app = new MyApp();
	     validate =new Validator();
	}

	@FXML
	void signUp(ActionEvent event) {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/sample.fxml"));
		Parent root;
		try {
			root = fxmlLoader.load();
			myStage.setTitle("Cosmos Mail");
			myStage.setScene(new Scene(root, 879.0D, 564.0D));
			myStage.setResizable(false);
			SignUpController signUpController = fxmlLoader.getController();
			signUpController.setup(myStage, app);
			myStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void signIn(ActionEvent event) {
		String email = mailField.getText();
		String password = passwordField.getText();
		if (app.signin(email, password)) {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/mails.fxml"));
			Parent root;

			try {
				root = fxmlLoader.load();
				myStage.setTitle("Cosmos Mail");
				myStage.setScene(new Scene(root, 879.0D, 564.0D));
				myStage.setResizable(false);
				MailsController mailsController = fxmlLoader.getController();
				IContact contact=validate.validateOnsignIN(email, password);
				mailsController.setup(myStage, app,contact);
				myStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			this.errorLabel.setVisible(true);
			this.mailField.clear();
			this.passwordField.clear();
		}

	}

	public void setup(Stage primaryStage) {
		this.myStage = primaryStage;
	}

	public MyApp getApp() {
		return app;
	}

	public void setApp(MyApp app) {
		this.app = app;
	}

}
