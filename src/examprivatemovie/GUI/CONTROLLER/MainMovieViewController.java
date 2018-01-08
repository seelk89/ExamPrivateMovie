/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI.CONTROLLER;

import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import examprivatemovie.GUI.MovieModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Jesper
 */
public class MainMovieViewController implements Initializable
{
    
    private Label label;
    @FXML
    private TableView<Category> TableCategoryView;
    @FXML
    private TableColumn<Movie, String> columnCategory;
    @FXML
    private TableView<Movie> TableMovieView;
    @FXML
    private TableColumn<Movie, String> columnTitle;
    @FXML
    private TableColumn<Movie, String> columnPersonalRating;
    @FXML
    private TableColumn<Movie, String> columnIMDBRating;
    @FXML
    private TableColumn<Movie, String> columnLastViewed;
    @FXML
    private TextField txtSearchFilter;
    @FXML
    private Label labelSearch;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnPlay;
    private String lastFocus = "";
    MovieModel model = new MovieModel();
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
         columnCategory.setCellValueFactory(
                new PropertyValueFactory("name"));
     
         columnTitle.setCellValueFactory(
                new PropertyValueFactory("name"));
         columnPersonalRating.setCellValueFactory(
                new PropertyValueFactory("personalRating"));
         columnIMDBRating.setCellValueFactory(
                 new PropertyValueFactory("IMDBRating"));
         columnLastViewed.setCellValueFactory(
                new PropertyValueFactory("lastview"));

     
         model.loadMovie();
         model.loadCategory();
        
         TableMovieView.setItems(model.getMoviesList());
         TableCategoryView.setItems(model.getCategoriesList()); 
        
         TableCategoryView.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> 
                    observable, Boolean oldValue, Boolean newValue) {
                lastFocus = "Movie";
            }
        }
        );
    }    

    @FXML
    private void ClickEditMovie(ActionEvent event) {
    }

    @FXML
    private void ClickAddMovie(ActionEvent event) {
    }

    @FXML
    private void ClickRemoveMovie(ActionEvent event) {
    }

    @FXML
    private void clickPlayMovie(ActionEvent event) {
    }
    
}
