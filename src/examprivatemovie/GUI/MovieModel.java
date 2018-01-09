/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;

import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import examprivatemovie.BLL.BLLManager;
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
       
    /**
     * Clears the list and searches in column title and artist for text in txtFilter.
     */
    public void search(String title, String imdbRating)
    {
        mList.clear();
        mList.addAll(bllm.getAllMoviesBySearching(title, imdbRating));
    }
       
       public void AddMovie(Movie m)
       {
           bllm.addMovieToDb(m);
           mList.addAll(bllm.getAllMovies());
       }

       public void editMovie(Movie m)
       {
           bllm.editMovieInDb(m);
           //cList.addAll(bllm.getAllChars());
       }
    
       public void deleteMovie(Movie selectedMovie)
       {
           bllm.deleteMovieFromDb(selectedMovie);
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
}
