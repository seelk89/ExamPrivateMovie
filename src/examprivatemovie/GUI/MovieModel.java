/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;

import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import examprivatemovie.BLL.BLLManager;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Anni
 */
public class MovieModel
{

    BLLManager bllm = new BLLManager();

    private ObservableList<Movie> mList = FXCollections.observableArrayList();
    private ObservableList<Category> cList = FXCollections.observableArrayList();
    private ObservableList<Movie> catmList = FXCollections.observableArrayList();

    /**
     * get all movies from db
     *
     * @return
     */
    public List<Movie> getAllMovies()
    {
        return bllm.getAllMovies();
    }

    /**
     * get all categories from db
     *
     * @return
     */
    public List<Category> getAllCategories()
    {
        return bllm.getAllCategories();
    }

    /**
     *
     * @param name
     * @param imdbRating
     * @return
     */
    public List<Movie> getAllMoviesBySearching(String name, String imdbRating)
    {
        return bllm.getAllMoviesBySearching(name, imdbRating);
    }

    /**
     *
     * @return
     */
    public List<String> getAllMoviesByTitle()
    {
        return bllm.getAllMoviesByTitle();
    }

    /**
     *
     * @return
     */
    public ObservableList<Movie> getMoviesList()
    {
        return mList;
    }

    /**
     *
     * @return
     */
    public ObservableList<Category> getCategoriesList()
    {
        return cList;
    }

    /**
     *
     * @return
     */
    public ObservableList<Movie> getMoviesInCategoryList()
    {
        return catmList;
    }

    /**
     * Clears the list and searches in column title and artist for text in
     * txtFilter.
     */
    public void search(String title, String imdbRating)
    {
        catmList.clear();
        catmList.addAll(bllm.getAllMoviesBySearching(title, imdbRating));
    }

    /**
     *
     * @param m
     */
    public void addMovie(Movie m)
    {
        bllm.addMovieToDb(m);
        mList.addAll(bllm.getAllMovies());
    }

    /**
     *
     * @param c
     */
    public void addCategory(Category c)
    {
        bllm.addCategoryToDb(c);
        cList.addAll(bllm.getAllCategories()); //maybe wrong list
    }

    /**
     *
     * @param m
     */
    public void editMovie(Movie m)
    {
        bllm.editMovieInDb(m);
    }

    /**
     *
     * @param selectedMovie
     * @param selectedMovieId
     */
    public void removeMovie(Movie selectedMovie, Movie selectedMovieId)
    {
        bllm.removeMovieFromDb(selectedMovie, selectedMovieId);
        catmList.remove(selectedMovie);
    }

    /**
     *
     * @param selectedCategory
     * @param selectedCategoryId
     */
    public void removeCategory(Category selectedCategory, Category selectedCategoryId)
    {
        bllm.removeCategoryFromDb(selectedCategory);
        cList.remove(selectedCategory);
    }

    /**
     *
     */
    public void loadMovie()
    {
        catmList.clear();
        catmList.addAll(bllm.getAllMovies());
    }

    /**
     *
     */
    public void loadCategory()
    {
        cList.clear();
        cList.addAll(bllm.getAllCategories());
    }

    /**
     *
     * @param selectedId
     * @return
     */
    public List<Movie> getAllMoviesInCategory(int selectedId)
    {
        return bllm.getAllMoviesInCategory(selectedId);
    }

    /**
     * Converts the lastView to a LocalDateTime object so that it can
     * be compared to the current date, to see if it was last played two years ago.
     *
     */
    public boolean twoYearWarning(int movieId) throws SQLException
    {
        String lastView = bllm.selectedMovieLastView(movieId);
        LocalDateTime currentDateMinusTwoYears = LocalDateTime.now().minusYears(2);

        //Gets the String format of the date specified in the selected movie and converts it to LocalDateFormat
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate lastViewDate = LocalDate.parse(lastView, formatter);
        LocalDateTime localLastViewDate = LocalDateTime.of(lastViewDate, LocalDateTime.now().toLocalTime());

        boolean afterTwoYears = localLastViewDate.isBefore(currentDateMinusTwoYears);

        return afterTwoYears;
    }

    public String selectedMovieLastView(int movieId) throws SQLException
    {
        return bllm.selectedMovieLastView(movieId);
    }

    public float selectedMoviePersRating(int movieId) throws SQLException
    {
        return bllm.selectedMoviePersRating(movieId);
    }

    /**
     *
     * @param selectedId
     */
    public void loadMoviesInCategory(int selectedId)
    {
        catmList.clear();
        catmList.addAll(bllm.getAllMoviesInCategory(selectedId));
    }

    /**
     * changes cats to cat, might need to change back !!!!!!!!!!
     *
     * @param cats
     */
    public void loadMoviesInCategory(List<Category> cat)
    {
        catmList.setAll(bllm.getAllMoviesInCategory(cat));
        // if (catmList.contains(delete all movies once. then you only have 1 of the dupes. perfect result
    }

    /**
     * Set the current date as the lastView date of the movie being played
     * 
     * @param selectedId
     */
    public void editDate(int selectedId)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);

        System.out.println(formattedDateTime);

        bllm.editDate(formattedDateTime, selectedId);
    }

    /**
     *
     * @param categoryName
     */
    public void matchMovie(String categoryName)
    {
        bllm.matchMovieCat(categoryName);
    }
    
    public int movieCount()
    {
        return bllm.movieCount();
    }
}
