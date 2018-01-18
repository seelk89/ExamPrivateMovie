/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI.MODEL;

import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import examprivatemovie.BLL.BLLManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Anni
 */
public class MovieModel {

    BLLManager bllm = new BLLManager();

    private ObservableList<Movie> mList = FXCollections.observableArrayList();
    private ObservableList<Category> cList = FXCollections.observableArrayList();
    private ObservableList<Movie> catmList = FXCollections.observableArrayList();

    /**
     * get all movies from db
     *
     * @return
     */
    public List<Movie> getAllMovies() {
        return bllm.getAllMovies();
    }

    /**
     * get all categories from db
     *
     * @return
     */
    public List<Category> getAllCategories() {
        return bllm.getAllCategories();
    }

    /**
     * Gets all movies from the database by searching.
     *
     * @param name
     * @param imdbRating
     * @return
     */
    public List<Movie> getAllMoviesBySearching(String name, String imdbRating) {
        return bllm.getAllMoviesBySearching(name, imdbRating);
    }

    /**
     * gets all movies by title.
     *
     * @return
     */
    public List<String> getAllMoviesByTitle() {
        return bllm.getAllMoviesByTitle();
    }

    /**
     * gets the movie list.
     *
     * @return
     */
    public ObservableList<Movie> getMoviesList() {
        return mList;
    }

    /**
     * gets the categories
     *
     * @return
     */
    public ObservableList<Category> getCategoriesList() {
        return cList;
    }

    /**
     * gets the movies inside each category
     *
     * @return
     */
    public ObservableList<Movie> getMoviesInCategoryList() {
        return catmList;
    }

    /**
     * Clears the list and searches in column title and artist for text in
     * txtFilter.
     */
    public void search(String title, String imdbRating) {
        catmList.clear();
        catmList.addAll(bllm.getAllMoviesBySearching(title, imdbRating));
    }

    /**
     * adds movies
     *
     * @param m
     */
    public void addMovie(Movie m) {
        bllm.addMovieToDb(m);
        mList.addAll(bllm.getAllMovies());
    }

    /**
     * adds categories
     *
     * @param c
     */
    public void addCategory(Category c) {
        bllm.addCategoryToDb(c);
        cList.addAll(bllm.getAllCategories());
    }

    /**
     * This method edits the movies.
     *
     * @param m
     */
    public void editMovie(Movie m) {
        bllm.editMovieInDb(m);
    }

    /**
     * This method removes the movie choosen.
     *
     * @param selectedMovie
     * @param selectedMovieId
     */
    public void removeMovie(Movie selectedMovie, Movie selectedMovieId) {
        bllm.removeMovieFromDb(selectedMovie, selectedMovieId);
        catmList.remove(selectedMovie);
    }

    /**
     * This method removes the category choosen.
     *
     * @param selectedCategory
     * @param selectedCategoryId
     */
    public void removeCategory(Category selectedCategory, Category selectedCategoryId) {
        bllm.removeCategoryFromDb(selectedCategory);
        cList.remove(selectedCategory);
    }

    /**
     * This method loads the movie.
     */
    public void loadMovie() {
        catmList.clear();
        catmList.addAll(bllm.getAllMovies());
    }

    /**
     * This method loads category.
     */
    public void loadCategory() {
        cList.clear();
        cList.addAll(bllm.getAllCategories());
    }

    /**
     * This method gets all movies inside categories
     *
     * @param selectedId
     * @return
     */
    public List<Movie> getAllMoviesInCategory(int selectedId) {
        return bllm.getAllMoviesInCategory(selectedId);
    }

    /**
     * Converts the lastView to a LocalDateTime object so that it can be
     * compared to the current date, to see if it was last played two years ago.
     *
     */
    public boolean twoYearWarning(int movieId) throws SQLException {
        String lastView = bllm.selectedMovieLastView(movieId);
        LocalDateTime currentDateMinusTwoYears = LocalDateTime.now().minusYears(2);

        //Gets the String format of the date specified in the selected movie and converts it to LocalDateFormat
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate lastViewDate = LocalDate.parse(lastView, formatter);
        LocalDateTime localLastViewDate = LocalDateTime.of(lastViewDate, LocalDateTime.now().toLocalTime());

        boolean afterTwoYears = localLastViewDate.isBefore(currentDateMinusTwoYears);

        return afterTwoYears;
    }

    /**
     * This method return the "lasview" parameter from the choosen movie.
     *
     * @param movieId
     * @return
     * @throws SQLException
     */
    public String selectedMovieLastView(int movieId) throws SQLException {
        return bllm.selectedMovieLastView(movieId);
    }

    public float selectedMoviePersRating(int movieId) throws SQLException {
        return bllm.selectedMoviePersRating(movieId);
    }

    /**
     * This method load movies inside categories with the selected id.
     *
     * @param selectedId
     */
    public void loadMoviesInCategory(int selectedId) {
        catmList.clear();
        catmList.addAll(bllm.getAllMoviesInCategory(selectedId));
    }

    /**
     * This method load movies inside a category list.
     *
     * @param cats
     */
    public void loadMoviesInCategory(List<Category> cat) {
        catmList.setAll(bllm.getAllMoviesInCategory(cat));
    }

    /**
     * Set the current date as the lastView date of the movie being played
     *
     * @param selectedId
     */
    public void editDate(int selectedId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);

        System.out.println(formattedDateTime);

        bllm.editDate(formattedDateTime, selectedId);
    }

    /**
     * This method matchs movie
     *
     * @param categoryName
     */
    public void matchMovie(String categoryName) {
        bllm.matchMovieCat(categoryName);
    }

    /**
     * This method counts the movie.
     *
     * @return
     */
    public int movieCount() {
        return bllm.movieCount();
    }
}
