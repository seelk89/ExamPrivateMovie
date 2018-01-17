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
public class MovieModel {
     BLLManager bllm = new BLLManager();
    
       private ObservableList<Movie> mList = FXCollections.observableArrayList();
    
       public List<Movie> getAllMovies()
       {
           return bllm.getAllMovies();
       }
       
       private ObservableList<Category> cList = FXCollections.observableArrayList();
    
       public List<Category> getAllCategories()
       {
           return bllm.getAllCategories();
       }
       
       private ObservableList<Movie> catmList = FXCollections.observableArrayList();
    
       public List<Movie> getAllMoviesInCategory(int selectedId)
       {
           return bllm.getAllMoviesInCategory(selectedId);
       }
 
       public List<Movie> getAllMoviesBySearching(String name, String imdbRating)
       {
           return bllm.getAllMoviesBySearching(name, imdbRating);
       }
       
       public ObservableList<Movie> getMoviesList()
       {
        return mList;
       }
       
       public ObservableList<Category> getCategoriesList()
       {
        return cList;
       }
       
       public ObservableList<Movie> getMoviesInCategoryList()
       {
        return catmList;
       }
       
    /**
     * Clears the list and searches in column title and artist for text in txtFilter.
     */
    public void search(String title, String imdbRating)
    {
        catmList.clear();
        catmList.addAll(bllm.getAllMoviesBySearching(title, imdbRating));
    }
       
       public void addMovie(Movie m)
       {
           bllm.addMovieToDb(m);
           mList.addAll(bllm.getAllMovies());
       }

       public void editMovie(Movie m)
       {
           bllm.editMovieInDb(m);
        
       }
    
       public void removeMovie(Movie selectedMovie, Movie selectedMovieId)
       {
           bllm.removeMovieFromDb(selectedMovie, selectedMovieId);
           catmList.remove(selectedMovie);
       }
       
       public void loadMovie()
       {
           catmList.clear();
           catmList.addAll(bllm.getAllMovies());
       }
       
              
       public void loadCategory()
       {
           cList.clear();
           cList.addAll(bllm.getAllCategories());
       }
       
       public void editDate(int selectedId)
       {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.now();
            String formattedDateTime = dateTime.format(formatter);
            
            Date date = new Date();
            //System.out.println(date);
            
            Instant date2 = date.toInstant();
            
            //System.out.println(date2);
            
            Instant time = LocalDateTime.now().minusYears(2).toInstant(ZoneOffset.UTC);
            
            //System.out.println(time);
            
            boolean withinLast2Years = date2.isAfter(time);
           
            
            
            bllm.editDate(formattedDateTime, selectedId);
       }
       
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

       public void loadMoviesInCategory(int selectedId)
       {
           catmList.clear();
           catmList.addAll(bllm.getAllMoviesInCategory(selectedId));

       }
       
       public void matchMovie(String categoryName)
       {
           bllm.matchMovieCat(categoryName);
       }
}
