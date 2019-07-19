package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class FolderCell extends ListCell<String>{

    @FXML
    private Label folderName;
    @FXML
    private AnchorPane anchorPane;
    private FXMLLoader mLLoader;
    @Override
    protected void updateItem(String folder, boolean empty) {
        super.updateItem(folder, empty);
        if(empty || folder == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/FXMLS/FolderCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            folderName.setText(folder);
            setText(null);
            setGraphic(anchorPane);

        }

    }



}
