package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.controllers;

import java.io.IOException;

import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.MyApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {
	private Stage myStage;
	private MyApp app;

	public void setup(Stage myStage, MyApp app) {
		this.myStage = myStage;
        this.app = app;

	}
	@FXML
    private PasswordField passField;

    @FXML
    private Button signupButton;

    @FXML
    private Label addressError;

    @FXML
    private Label passwordError;

    @FXML
    private Hyperlink signinLink;

    @FXML
    private TextField nameField;

    @FXML
    private TextField mailField;

    @FXML
    void signIn(ActionEvent event) {
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
    void signUp(ActionEvent event) {
    	IContact c = new IContact(this.nameField.getText(), this.passField.getText(), this.mailField.getText());
        if (this.passField.getText().length() < 8) {
            this.passwordError.setVisible(true);
            this.nameField.clear();
            this.mailField.clear();
            this.passField.clear();
        } else if (app.signup(c)) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/mails.fxml"));
            Parent root;
			try {
				root = fxmlLoader.load();
				myStage.setTitle("Cosmos Mail");
	            myStage.setScene(new Scene(root, 879.0D, 564.0D));
	            myStage.setResizable(false);
	            MailsController mailsController = fxmlLoader.getController();
	            mailsController.setup(myStage,app,c);
	            myStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }

    }

}
