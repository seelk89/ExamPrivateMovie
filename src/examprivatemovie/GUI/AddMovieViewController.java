/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;

import examprivatemovie.BE.Movie;
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
    private ChoiceBox<String> genre1;
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
        genre1.getItems().add("Action");
        genre1.getItems().add("Adventure");
        genre1.getItems().add("Animation");
        genre1.getItems().add("Biography");
        genre1.getItems().add("Comedy");
        genre1.getItems().add("crime");
        genre1.getItems().add("Documentary");
        genre1.getItems().add("Drama");
        genre1.getItems().add("Family");
        genre1.getItems().add("Fantasy");
        genre1.getItems().add("Film noir");
        genre1.getItems().add("History");
        genre1.getItems().add("Horror");
        genre1.getItems().add("Music");
        genre1.getItems().add("Musical");
        genre1.getItems().add("Mystery");
        genre1.getItems().add("Romance");
        genre1.getItems().add("Sci-fi");
        genre1.getItems().add("Short");
        genre1.getItems().add("Sport");
        genre1.getItems().add("Superhero");
        genre1.getItems().add("Thriller");
        genre1.getItems().add("War");
        genre1.getItems().add("Western");
        
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
        genre3.getItems().add("Action");
        genre3.getItems().add("Adventure");
        genre3.getItems().add("Animation");
        genre3.getItems().add("Biography");
        genre3.getItems().add("Comedy");
        genre3.getItems().add("crime");
        genre3.getItems().add("Documentary");
        genre3.getItems().add("Drama");
        genre3.getItems().add("Family");
        genre3.getItems().add("Fantasy");
        genre3.getItems().add("Film noir");
        genre3.getItems().add("History");
        genre3.getItems().add("Horror");
        genre3.getItems().add("Music");
        genre3.getItems().add("Musical");
        genre3.getItems().add("Mystery");
        genre3.getItems().add("Romance");
        genre3.getItems().add("Sci-fi");
        genre3.getItems().add("Short");
        genre3.getItems().add("Sport");
        genre3.getItems().add("Superhero");
        genre3.getItems().add("Thriller");
        genre3.getItems().add("War");
        genre3.getItems().add("Western");
       
        //genre4 choicebox options
        genre4.getItems().add("Action");
        genre4.getItems().add("Adventure");
        genre4.getItems().add("Animation");
        genre4.getItems().add("Biography");
        genre4.getItems().add("Comedy");
        genre4.getItems().add("crime");
        genre4.getItems().add("Documentary");
        genre4.getItems().add("Drama");
        genre4.getItems().add("Family");
        genre4.getItems().add("Fantasy");
        genre4.getItems().add("Film noir");
        genre4.getItems().add("History");
        genre4.getItems().add("Horror");
        genre4.getItems().add("Music");
        genre4.getItems().add("Musical");
        genre4.getItems().add("Mystery");
        genre4.getItems().add("Romance");
        genre4.getItems().add("Sci-fi");
        genre4.getItems().add("Short");
        genre4.getItems().add("Sport");
        genre4.getItems().add("Superhero");
        genre4.getItems().add("Thriller");
        genre4.getItems().add("War");
        genre4.getItems().add("Western");
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
        
       
        
        // set category id, get movie id, 
        
        
        model.addMovie(m);
        //model.addMovieToCategory(cm);
        
        Stage window = (Stage) btnSave.getScene().getWindow();
        window.close();
    }
}
