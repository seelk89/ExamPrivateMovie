/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;


import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
    private ChoiceBox<Category> genre1;
    @FXML
    private ChoiceBox<Category> genre2;
    @FXML
    private ChoiceBox<Category> genre3;
    @FXML
    private ChoiceBox<Category> genre4;
    
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

    MovieModel model = new MovieModel();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        int maxChar = 3;
        
        //restricts txtPersonalRating to decimal numbers and limits to 3 characters
        txtPersonalRating.textProperty().addListener(new ChangeListener<String>() 
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}([\\.]\\d{0,9})?") || newValue.length() > maxChar) {
                    txtPersonalRating.setText(oldValue);
                }
            }
        });
        
        //restricts txtImdbRating to decimal numbers and limits to 3 characters
        txtImdbRating.textProperty().addListener(new ChangeListener<String>() 
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}([\\.]\\d{0,9})?") || newValue.length() > maxChar) {
                    txtPersonalRating.setText(oldValue);
                }
            }
        });
        
        //genre1 choicebox options
        genre1.setItems(FXCollections.observableArrayList(model.getAllCategories()));
        
        //genre2 choicebox options
        genre2.setItems(FXCollections.observableArrayList(model.getAllCategories()));
        
        //genre3 choicebox options
        genre3.setItems(FXCollections.observableArrayList(model.getAllCategories()));
        
        //genre4 choicebox options
        genre4.setItems(FXCollections.observableArrayList(model.getAllCategories()));
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
        Movie m = new Movie();
        
        m.setName(txtMovieTitle.getText());
        m.setPersonalRating(txtPersonalRating.getText());
        m.setIMDBRating(txtImdbRating.getText());
        m.setFilelink(txtFileLocation.getText());
        
        String fileLocation = txtFileLocation.getText();
        
        if(fileLocation.endsWith("mp4") || fileLocation.endsWith("mpeg4"))
        {
            model.addMovie(m);
        
            boolean genre1Empty = genre1.getSelectionModel().isEmpty();

            if(genre1Empty != true)
            {
                String genre1Value = genre1.getValue().getName();
                model.matchMovie(genre1Value);
            }

            boolean genre2Empty = genre2.getSelectionModel().isEmpty();

            if(genre2Empty != true)
            {
                String genre2Value = genre2.getValue().getName();
                model.matchMovie(genre2Value);
            }

            boolean genre3Empty = genre3.getSelectionModel().isEmpty();

            if(genre3Empty != true)
            {
                String genre3Value = genre3.getValue().getName();
                model.matchMovie(genre3Value);
            }

            boolean genre4Empty = genre4.getSelectionModel().isEmpty();

            if(genre4Empty != true)
            {
                String genre4Value = genre4.getValue().getName();
                model.matchMovie(genre4Value);
            }

            Stage window = (Stage) btnSave.getScene().getWindow();
            window.close();
        }
    }
}
