/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.BLL;

import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import examprivatemovie.DAL.DALManager;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Anni
 */
public class BLLManager {

    DALManager dalm = new DALManager();

    public List<Movie> getAllMovies() {
        return dalm.getAllMovies();
    }

    public List<Category> getAllCategories() {
        return dalm.getAllCategories();
    }

    public List<Movie> getAllMoviesInCategory(int selectedId) {
        return dalm.getAllMoviesInCategory(selectedId);
    }

    public List<Movie> getAllMoviesInCategory(List<Category> cats) {
        List<Movie> movs = new ArrayList<>();

        if (cats.size() > 0) {

            for (int i = 0; i < cats.size(); i++) {
                Category catty = cats.get(i);
                movs.addAll(dalm.getAllMoviesInCategory(catty.getId()));
            }
        } else {
            return dalm.getAllMovies();
        }

        return movs;
    }

    public List<Movie> getAllMoviesBySearching(String name, String imdbRating) {
        return dalm.getAllMoviesBySearching(name, imdbRating);
    }

    public List<String> getAllMoviesByTitle() {
        return dalm.getAllMoviesByTitle();
    }

    public void addMovieToDb(Movie m) {
        dalm.addMovieToDB(m);
    }

    public void addCategoryToDb(Category c) {
        dalm.addCategoryToDB(c);
    }

    public void editMovieInDb(Movie personalRating) {
        dalm.editMovieInDb(personalRating);
    }

    public void removeMovieFromDb(Movie selectedMovie, Movie selectedMovieId) {
        dalm.removeMovieFromDb(selectedMovie, selectedMovieId);
    }

    public void removeCategoryFromDb(Category selectedCategory) {
        dalm.removeCategoryFromDb(selectedCategory);
    }

    public void editDate(String d, int selectedId) {
        dalm.editDate(d, selectedId);
    }

    public void matchMovieCat(String categoryName) {
        dalm.matchMovieCat(categoryName);
    }

}
