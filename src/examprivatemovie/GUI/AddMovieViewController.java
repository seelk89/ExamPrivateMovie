/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Jesper
 */
public class AddMovieViewController implements Initializable
{

    private MainMovieViewController parent;
    private TextField fileLocation;
    @FXML
    private ChoiceBox<?> genre1;
    @FXML
    private ChoiceBox<?> genre2;
    @FXML
    private ChoiceBox<?> genre3;
    @FXML
    private ChoiceBox<?> genre4;
    @FXML
    private Button btnFileLocation;
    
    private Window stage;
    @FXML
    private TextField txtPersonalRating;
    @FXML
    private TextField txtFileLocation;
    @FXML
    private TextField txtImdbRating;
    @FXML
    private TextField txtMovieTitle;
    @FXML
    private Button btnSave;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    public void setParentWindowController(MainMovieViewController parent)
    {
        this.parent = parent;
    }

    @FXML
    private void clickFileLocation(ActionEvent event)
    {
        String absolutePath = null;

        final FileChooser fileChooser = new FileChooser();

        File song = fileChooser.showOpenDialog(stage);
        if (song != null) {
            absolutePath = song.getAbsolutePath();
        }

        txtFileLocation.setText(absolutePath);
    }

    @FXML
    private void clickSave(ActionEvent event)
    {
    }
}
