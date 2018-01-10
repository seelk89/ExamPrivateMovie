/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;

import examprivatemovie.BE.CatMovie;
import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import examprivatemovie.BLL.BLLManager;
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
    
       public List<Movie> getAllMovies()
       {
           return bllm.getAllMovies();
       }
       
       private ObservableList<Category> cList = FXCollections.observableArrayList();
    
       public List<Category> getAllCategories()
       {
           return bllm.getAllCategories();
       }
       
       private ObservableList<CatMovie> catmList = FXCollections.observableArrayList();
    
       public List<CatMovie> getAllMoviesInCategory()
       {
           return bllm.getAllMoviesInCategory();
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
       
       public ObservableList<CatMovie> getMoviesInCategoryList()
       {
        return catmList;
       }
       
    /**
     * Clears the list and searches in column title and artist for text in txtFilter.
     */
    public void search(String title, String imdbRating)
    {
        mList.clear();
        mList.addAll(bllm.getAllMoviesBySearching(title, imdbRating));
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
    
       public void removeMovie(Movie selectedMovie)
       {
           bllm.removeMovieFromDb(selectedMovie);
           mList.remove(selectedMovie);
       }
       
       public void loadMovie()
       {
           mList.clear();
           mList.addAll(bllm.getAllMovies());
       }
       
              
       public void loadCategory()
       {
           cList.clear();
           cList.addAll(bllm.getAllCategories());
       }
       
<<<<<<< HEAD
       public void editDate(int selectedId)
       {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.now();
            String formattedDateTime = dateTime.format(formatter);
            
            System.out.println(formattedDateTime);
            
            bllm.editDate(formattedDateTime, selectedId);
=======
       public void loadMoviesInCategory()
       {
           catmList.clear();
           catmList.addAll(bllm.getAllMoviesInCategory());
>>>>>>> 429dae175577498adcfec13326a80291d23b82b0
       }
}
