/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI.CONTROLLER;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Jesper
 */
public class MainMovieViewController implements Initializable
{
    
    private Label label;
    @FXML
    private TableView<?> TableCategoryView;
    @FXML
    private TableColumn<?, ?> ColumnCategory;
    @FXML
    private TableView<?> TableMovieView;
    @FXML
    private TableColumn<?, ?> columnTitle;
    @FXML
    private TableColumn<?, ?> ColumnPersonalRating;
    @FXML
    private TableColumn<?, ?> ColumnIMDBRating;
    @FXML
    private TableColumn<?, ?> ColumnLastViewed;
    @FXML
    private Button ClickEditMovie;
    @FXML
    private Button ClickAddMovie;
    @FXML
    private Button ClickRemoveMovie;
    @FXML
    private Button clickPlayMovie;
    @FXML
    private TextField txtSearchFilter;
    @FXML
    private Label labelSearch;
    
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
