/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;

import examprivatemovie.BE.CatMovie;
import examprivatemovie.GUI.AddMovieViewController;
import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import examprivatemovie.GUI.MovieModel;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Jesper
 */
public class MainMovieViewController implements Initializable {

    private Label label;
    @FXML
    private TableView<Category> TableCategoryView;
    @FXML
    private TableColumn<Movie, String> columnCategory;
    @FXML
    private TableView<CatMovie> TableMovieView;
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
    public void initialize(URL url, ResourceBundle rb) {
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

//         TableMovieView.setItems(model.getMoviesList());
        TableCategoryView.setItems(model.getCategoriesList());

        TableCategoryView.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                lastFocus = "Category";
            }
        }
        );

        TableMovieView.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                lastFocus = "Movie";
            }
        }
        );

        TableMovieView.setItems(model.getMoviesInCategoryList());

        TableCategoryView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
            @Override
            public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {
                model.loadMoviesInCategory(newValue.getId());
            }
        }
        );

        setCellEditables();
        autoEditCell();

        columnPersonalRating.setOnEditCommit(new EventHandler<CellEditEvent<Movie, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Movie, String> change) {
                ((Movie) change.getTableView().getItems().get(change.getTablePosition().getRow())).setPersonalRating(change.getNewValue());

            }

        });
    }

    
    private void setCellEditables() {
        columnPersonalRating.setCellFactory(TextFieldTableCell.forTableColumn());
        TableMovieView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (TableMovieView.isFocused() && event.getClickCount() == 1) {
                    if (!TableMovieView.isEditable()) {
                        TableMovieView.setEditable(true);
                    }
                }
            }
        });
    }

    private void autoEditCell() {

        TableMovieView.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {

                }
            }
        });

    }

    /**
     * Gets selected Movie from list
     */
    private CatMovie getSelectedMovie() {
        return TableMovieView.getSelectionModel().getSelectedItem();
    }

    /**
     * Gets selected Category from left list
     */
    private Category getSelectedMovieInCategory() {
        return TableCategoryView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void clickAddMovie(ActionEvent event) throws IOException {

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

    @FXML
    private void clickRemoveMovie(ActionEvent event) {

//        Movie selectedMovie = getSelectedMovie();
//        model.removeMovie(selectedMovie);
    }

    @FXML
    private void clickPlayMovie(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("PlayView.fxml"));

        Parent root = fxLoader.load();

        PlayViewController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        newWindow.setTitle("Play view");
        newWindow.setScene(scene);

        int selectedMovieId = TableMovieView.getSelectionModel().getSelectedItem().getId();
        System.out.println(selectedMovieId);
        model.editDate(selectedMovieId);

        newWindow.showAndWait();

        model.loadMovie();

    }

    @FXML
    private void writeSearchForMovieKeyTyped(KeyEvent event) {
        System.out.println("Searching for Movie by title and imdb rating");
        model.search(txtSearchFilter.getText(), txtSearchFilter.getText());
    }

    @FXML
    private void clickEditMovie(ActionEvent event) {
    }

}
