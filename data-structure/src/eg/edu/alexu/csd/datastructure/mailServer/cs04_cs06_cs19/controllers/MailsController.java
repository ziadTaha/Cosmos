package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.controllers;

import java.io.File;
import java.io.IOException;

import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.MyApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MailsController {

	public void setup(Stage myStage, MyApp app, IContact contact) {
		this.myStage = myStage;
		this.app = app;
		this.contact = contact;
		this.currentPage = 1;
		ObservableList<String> typeFilter = FXCollections.observableArrayList("sender","subject","priority");
		filter.setItems(typeFilter);
		getFolders();
		app.setViewingOptions(null, null, new ISort("date"));
		loadMails(currentPage);
		prev.setDisable(true);
		folder= null;
		name.setText(contact.getName());

	}

	@FXML
	private Button addfolder;

	@FXML
	private ListView<String> foldersList;

	@FXML
	private Label pnum;

	@FXML
	private Button cancelf;

	@FXML
	private Button prev;

	@FXML
	private ImageView refresh;

	@FXML
	private TextField searchfield;

	@FXML
	private Button nxt;

	@FXML
	private ComboBox<String> filter;

	@FXML
	private ImageView search;

	@FXML
	private Button compose;

	@FXML
	private Button newf;

	@FXML
	private ImageView background;

	@FXML
	private Label name;

	@FXML
	private Button signoutButton;

	@FXML
	private TextField newfoldername;

	@FXML
	private ListView<IMail> mailsList;

	@FXML
	private VBox newfolderBox;
	private Stage myStage;
	private MyApp app;
	private int currentPage;
	private IContact contact;
	private IFolder folder;
	private ObservableList<String> folders;
	private IMail[] mails;

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
	void ref(MouseEvent event) {
		app.signin(contact.getEmail(), contact.getPassword());
		loadMails(currentPage);
		getFolders();

	}

	@FXML
	void compoze(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/compose.fxml"));
		Parent root;
		try {
			root = fxmlLoader.load();
			myStage.setTitle("Cosmos Mail");
			myStage.setScene(new Scene(root, 879.0D, 564.0D));
			myStage.setResizable(false);
			ComposeController composeController = fxmlLoader.getController();
			composeController.setup(myStage, app, contact);
			myStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void newfolder(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/NewFolder.fxml"));
		Parent root;
		try {
			root = fxmlLoader.load();
			Stage newStage = new Stage();
			newStage.setTitle("create new folder");
			newStage.setScene(new Scene(root, 240.0D, 240.0D));
			newStage.setResizable(false);
			NewFolderController controller= fxmlLoader.getController();
			controller.setup(contact);
			newStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getFolders();

	}

	@FXML
	void prevpage(ActionEvent event) {
         currentPage--;
         loadMails(currentPage);
         nxt.setDisable(false);
         if(currentPage==1)
         {
        	 prev.setDisable(true);
         }
	}

	@FXML
	void nxtpage(ActionEvent event) {
		currentPage++;
		prev.setDisable(false);
		loadMails(currentPage);
		int next = currentPage+1;
		if(app.listEmails(next).length==0)
		{
			nxt.setDisable(true);
		}

	}

	@FXML
	void srch(MouseEvent event) {
		if(searchfield.getText().equals(""))
		{
			IFilter f =null;


			folder=null;
			app.setViewingOptions(folder,f ,new  ISort("date"));
			loadMails(currentPage);
		}
		else
		{
			/*try
			{*/
			IFilter f =null;
			if(!filter.getValue().equals(""))
			{
				f=new IFilter();
				f.setTopic(filter.getValue());
				f.setValue(searchfield.getText());
			}
			app.setViewingOptions(folder,f ,new  ISort(filter.getValue()));
			loadMails(currentPage);
			//}
			/*catch (Exception e)
			{
				   Alert alert = new Alert(AlertType.ERROR);
	               alert.setContentText("fill search type");
	               alert.showAndWait();
			}*/
		}

	}

	@FXML
	void checkfoldername(ActionEvent event) {

	}

	@FXML
	void cancelfolder(ActionEvent event) {

	}

	@FXML
	void addfo(ActionEvent event) {




	}
	 private void getFolders()
	    {
	    	File file = new File ("accounts/"+contact.getName()+"/"+contact.getEmail());
	    	File[] files =file.listFiles();
	    	//IFolder[] folders = new IFolder[files.length];
	    	 ObservableList<String> names = FXCollections.observableArrayList();
            names.add("all mails");
	    	for(int i=0;i<files.length;i++)
	    	{

	    			names.add(files[i].getName());

	    	}
	    	foldersList.setItems(names);
	    	foldersList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

				@Override
				public ListCell<String> call(ListView<String> param) {

					return new FolderCell();
				}
			});
	    	foldersList.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if(event.getClickCount()==2)
					{
						String selected=foldersList.getSelectionModel().getSelectedItem();
						if(selected.equals("all mails"))
						{
							folder=null;
						}
						else
						{
							folder=new IFolder(selected, contact);

						}
						app.setViewingOptions(folder,null ,new  ISort("date"));
						currentPage=1;
						loadMails(currentPage);
					}

				}
			});

	    }
	 private void loadMails(int page)
	 {
		 mails=app.listEmails(page);
		 ObservableList<IMail> items = FXCollections.observableArrayList();
		 for(int i=0;i<mails.length;i++)
		 {
			 items.add(mails[i]);
		 }
		 mailsList.setItems(items);
		 mailsList.setCellFactory(new Callback<ListView<IMail>, ListCell<IMail>>() {

			@Override
			public ListCell<IMail> call(ListView<IMail> arg0) {
				// TODO Auto-generated method stub
				return new MailCell();
			}
		});
		 mailsList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()==2)
				{
				IMail selected=mailsList.getSelectionModel().getSelectedItem();
				if(!selected.getFolder().equals("draft"))
				{
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/openmail.fxml"));
				Parent root;
				try {
					root = fxmlLoader.load();
					myStage.setTitle("Cosmos Mail");
					myStage.setScene(new Scene(root, 879.0D, 564.0D));
					myStage.setResizable(false);
					OpenMailController openController = fxmlLoader.getController();
					openController.setup(myStage, app, contact, selected);
					myStage.show();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				else
				{
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/compose.fxml"));
					Parent root;
					try {
						root = fxmlLoader.load();
						myStage.setTitle("Cosmos Mail");
						myStage.setScene(new Scene(root, 879.0D, 564.0D));
						myStage.setResizable(false);
						ComposeController composeController = fxmlLoader.getController();
						composeController.setup(myStage, app, contact,selected);
						myStage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			}
		});

	 }
	   @FXML
	    void rename(ActionEvent event) {
		   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/RenameFolder.fxml"));
			Parent root;
			try {
				root = fxmlLoader.load();
				Stage newStage = new Stage();
				newStage.setTitle("rename folder");
				newStage.setScene(new Scene(root, 320.0D, 320.0D));
				newStage.setResizable(false);
				RenameController controller= fxmlLoader.getController();
				controller.setup(contact,app);
				newStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
}
