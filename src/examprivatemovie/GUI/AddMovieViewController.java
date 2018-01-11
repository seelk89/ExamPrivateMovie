/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;

import examprivatemovie.BE.CatMovie;
import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
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
    private ChoiceBox<String> genre2;
    @FXML
    private ChoiceBox<String> genre3;
    @FXML
    private ChoiceBox<String> genre4;
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
        
        //genre1 choicebox options
        genre1.setItems(FXCollections.observableArrayList(model.getAllCategories()));
        
        //genre2 choicebox options
        genre2.getItems().add("Action");
        genre2.getItems().add("Adventure");
        genre2.getItems().add("Animation");
        genre2.getItems().add("Biography");
        genre2.getItems().add("Comedy");
        genre2.getItems().add("crime");
        genre2.getItems().add("Documentary");
        genre2.getItems().add("Drama");
        genre2.getItems().add("Family");
        genre2.getItems().add("Fantasy");
        genre2.getItems().add("Film noir");
        genre2.getItems().add("History");
        genre2.getItems().add("Horror");
        genre2.getItems().add("Music");
        genre2.getItems().add("Musical");
        genre2.getItems().add("Mystery");
        genre2.getItems().add("Romance");
        genre2.getItems().add("Sci-fi");
        genre2.getItems().add("Short");
        genre2.getItems().add("Sport");
        genre2.getItems().add("Superhero");
        genre2.getItems().add("Thriller");
        genre2.getItems().add("War");
        genre2.getItems().add("Western");
        
        //genre3 choicebox options
               
        //genre4 choicebox options
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
        }
        
        //String genre1Value = genre1.getValue();
        
        String genre2Value = genre2.getValue();
        
        if(genre2Value != null)
        {
            model.matchMovie(genre2Value);
        }
        
        String genre3Value = genre3.getValue();
        
        if(genre3Value != null)
        {
            model.matchMovie(genre3Value);
        }
        
        String genre4Value = genre4.getValue();
        
        if(genre4Value != null)
        {
            model.matchMovie(genre4Value);
        }
        
        Stage window = (Stage) btnSave.getScene().getWindow();
        window.close();
    }
}
