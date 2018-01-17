/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;

import examprivatemovie.GUI.AddMovieViewController;
import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import examprivatemovie.GUI.MovieModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML
    private Button btnAddCategory;
    @FXML
    private Button btnRemoveCategory;
    @FXML
    private TextField txtAddCategory;

    MovieModel model = new MovieModel();
    private ObservableList<Category> masterData = FXCollections.observableArrayList();
    private TableView<Category> myTable = new TableView<>(masterData);

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        editingCells(); //is it supposed to be above the cells?
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
        TableMovieView.setItems(model.getMoviesInCategoryList());
        TableCategoryView.setItems(model.getCategoriesList());
        TableCategoryView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        search();

    }
    
    //get Category.id, get Catmovie.id get Movie.id, only add Movie from CatMovie.id with all chosen Category.id
    
    /**
     * Gets selected Movie from list
     */
    public Movie getSelectedMovie()
    {
        return TableMovieView.getSelectionModel().getSelectedItem();
    }

    /**
     * Gets selected Category from left list
     */
    public Category getSelectedCategory()
    {
        return TableCategoryView.getSelectionModel().getSelectedItem();
    }

    /**
     * etiher this one or the one above needs to be deleted by Anni !!!!!!!
     * @return 
     */
    public Category getSelectedCategoryId()
    {
        return TableCategoryView.getSelectionModel().getSelectedItem();
    }

    /**
     * 
     */
    private void editingCells()
    {
        TableMovieView.setEditable(true);
        columnPersonalRating.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPersonalRating.setOnEditCommit(new EventHandler<CellEditEvent<Movie, String>>()
        {
            @Override
            public void handle(CellEditEvent<Movie, String> t)
            {
                ((Movie) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPersonalRating(t.getNewValue());
            }
        });
        //model.editMovie(m);
    }

    /**
     * opens a new window and loads the list of movies when the window is closed
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickAddMovie(ActionEvent event) throws IOException
    {

        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddMovieView.fxml"));

        Parent root = fxLoader.load();

        AddMovieViewController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        newWindow.setTitle("Add Movie");
        newWindow.setScene(scene);
        newWindow.showAndWait();

        model.loadMovie();
    }

    /**
     * Removes a movie from db Movie and catmovie!!!!!!!!
     * @param event 
     */
    @FXML
    private void clickRemoveMovie(ActionEvent event)
    {

        Movie selectedMovie = getSelectedMovie();
        Movie selectedMovieId = getSelectedMovie();
        model.removeMovie(selectedMovie, selectedMovieId);
    }

    /**
     * Plays movie in out own mediaplayer and updates lastview.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickPlayMovie(ActionEvent event) throws IOException
    {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("PlayView.fxml"));

        Parent root = fxLoader.load();

        PlayViewController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        newWindow.setScene(scene);

        //writes the date and time the movie was played
        int selectedMovieId = TableMovieView.getSelectionModel().getSelectedItem().getId();
        System.out.println(selectedMovieId);
        model.editDate(selectedMovieId);
        
        newWindow.showAndWait();

        model.loadMovie();
    }
    
    private void twoYearWarning() throws SQLException
    {
        int selectedMovieId = TableMovieView.getSelectionModel().getSelectedItem().getId();
        
        if(model.twoYearWarning(selectedMovieId) == true && model.selectedMoviePersRating(selectedMovieId) < 6)
        {
        
        }
    }

    /**
     * doesn't really do anything, btnEdit might need to be deleted !!!!!!!!!
     * @param event 
     */
    @FXML
    private void clickEditMovie(ActionEvent event)
    {

    }

    /**
     * Adds a category by getting the text, adding and loading categories.
     * @param event 
     */
    @FXML
    private void clickAddCategory(ActionEvent event)
    {
        Category c = new Category();
        c.setName(txtAddCategory.getText());

        model.addCategory(c);
        model.loadCategory();
    }

    /**
     * Removes the selected category and loads the list !!!!!!!
     * @param event 
     */
    @FXML
    private void clickRemoveCategory(ActionEvent event)
    {
        Category selectedCategory = TableCategoryView.getSelectionModel().getSelectedItem();
        Category selectedCategoryId = new Category();
        model.removeCategory(selectedCategory, selectedCategoryId);
        model.loadCategory();
    }

    /**
     * Uses txtSearchFilter to search for movies by name or imdb rating.
     */
    private void search()
    {
        txtSearchFilter.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                model.search(newValue, newValue);
            }
        });
    }

    /**
     * Makes it possible to select and deselect multiple items in TableCategoryView
     * @param event 
     */
    @FXML
    private void clkSelectedItems(MouseEvent event)
    {
        List<Category> cat = TableCategoryView.getSelectionModel().getSelectedItems();

                System.out.println(cat);
                model.loadMoviesInCategory(cat);
    }

}
