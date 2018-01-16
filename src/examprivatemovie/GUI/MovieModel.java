/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI;


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
       
       private ObservableList<Movie> catmList = FXCollections.observableArrayList();
    
       public List<Movie> getAllMoviesInCategory(int selectedId)
       {
           return bllm.getAllMoviesInCategory(selectedId);
       }
 
       public List<Movie> getAllMoviesBySearching(String name, String imdbRating)
       {
           return bllm.getAllMoviesBySearching(name, imdbRating);
       }
       public List<String> getAllMoviesByTitle(){
           return bllm.getAllMoviesByTitle();
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
       
       public void addCategory(Category c)
       {
           bllm.addCategoryToDb(c);
           cList.addAll(bllm.getAllCategories()); //maybe wrong list
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
       
       public void removeCategory(Category selectedCategory, Category selectedCategoryId)
       {
           bllm.removeCategoryFromDb(selectedCategory);
           cList.remove(selectedCategory);
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
            
            System.out.println(formattedDateTime);
            
            bllm.editDate(formattedDateTime, selectedId);
       }

        
       public void loadMoviesInCategory(int selectedId)
       {
           catmList.clear();
           catmList.addAll(bllm.getAllMoviesInCategory(selectedId));

       }
       
       public void loadMoviesInCategory(List<Category> cats)
       {
           catmList.clear();
           catmList.addAll(bllm.getAllMoviesInCategory(cats));
       }
       
       public void matchMovie(String categoryName)
       {
           bllm.matchMovieCat(categoryName);
       }
}
