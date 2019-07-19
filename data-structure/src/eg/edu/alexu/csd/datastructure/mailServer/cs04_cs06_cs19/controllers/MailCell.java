package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MailCell extends ListCell<IMail> {
	@FXML
	private Label date;

	@FXML
	private ImageView star;

	@FXML
	private Label sender;

	@FXML
	private Label subject;

	@FXML
	private ImageView attach;
	@FXML
    private AnchorPane anchorPane;

	private FXMLLoader mLLoader;

	@Override
	protected void updateItem(IMail mail, boolean empty) {
		super.updateItem(mail, empty);
		if (empty || mail == null) {

			setText(null);
			setGraphic(null);

		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/FXMLS/MailListItem.fxml"));
				mLLoader.setController(this);

				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = mail.getTime();
			String timeString = dateTime.format(formatter);
			date.setText(timeString);
			sender.setText(mail.getSender());
			subject.setText(mail.getSubject());
			if(!mail.isStarred())
			{
				star.setVisible(false);
			}
			if(mail.getAttachmets().size()==0)
			{
				attach.setVisible(false);
			}
			setText(null);
			setGraphic(anchorPane);

		}

	}

}
