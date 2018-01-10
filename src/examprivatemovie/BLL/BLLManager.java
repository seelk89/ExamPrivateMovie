/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.BLL;

import examprivatemovie.BE.CatMovie;
import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import examprivatemovie.DAL.DALManager;
import java.util.List;

/**
 *
 * @author Anni
 */
public class BLLManager {
    
    
    DALManager dalm = new DALManager();
    
       public List<Movie> getAllMovies()
       {
           return dalm.getAllMovies();
       }
       
        public List<Category> getAllCategories()
       {
           return dalm.getAllCategories();
       }
 
        public List<CatMovie> getAllMoviesInCategory()
        {
            return dalm.getAllMoviesInCategory();
        }
        
       public List<Movie> getAllMoviesBySearching(String name, String imdbRating)
       {
           return dalm.getAllMoviesBySearching(name, imdbRating);
       }
       
       public void addMovieToDb(Movie m)
       {
           dalm.addMovieToDB(m);
       }
       
//       public void addMovieToCategory(CatMovie catm)
//       {
//           dalm.addMovieToCategory(catm);
//       }

       public void editMovieInDb(Movie m)
       {
           dalm.editMovieInDb(m);
       }
    
       public void removeMovieFromDb(Movie selectedMovie)
       {
           dalm.removeMovieFromDb(selectedMovie);
       }

       public void editDate(String d, int selectedId)
       {
           dalm.editDate(d, selectedId);
       }
}
