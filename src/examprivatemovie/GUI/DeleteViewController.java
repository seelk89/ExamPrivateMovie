/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;

import examprivatemovie.BE.Movie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jesper
 */
public class DeleteViewController implements Initializable
{

    @FXML
    private Button btnAccept;
    @FXML
    private Button btnCancel;
    @FXML
    private Label labelMovieName;
    
    private MainMovieViewController parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {   
        //String movieName = parent.getSelectedMovie().getName();
        //labelMovieName.setText(movieName);
    }    

    public void setParentWindowController(MainMovieViewController parent)
    {
        this.parent = parent;
    }
    
    @FXML
    private void clickAccept(ActionEvent event)
    {
        this.parent = parent;
        MovieModel model = new MovieModel();
        
        Movie selectedMovieName = parent.getSelectedMovie();
        Movie selectedId = parent.getSelectedMovie();

        model.removeMovie(selectedMovieName, selectedId);

        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.close();
    }

    @FXML
    private void clickCancel(ActionEvent event)
    {
        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.close();
    }
    
}
