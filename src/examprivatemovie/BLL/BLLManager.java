/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.BLL;

import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import examprivatemovie.DAL.DALManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Anni
 */
public class BLLManager {

    DALManager dalm = new DALManager();

    /**
     * Returns a list of all movies
     *
     * @return
     */
    public List<Movie> getAllMovies() {
        return dalm.getAllMovies();
    }

    /**
     * Returns a list of all categories
     *
     * @return
     */
    public List<Category> getAllCategories() {
        return dalm.getAllCategories();
    }

    /**
     * Returns all the movies from the selected categories
     *
     * @param selectedId
     * @return
     */
    public List<Movie> getAllMoviesInCategory(int selectedId) {
        return dalm.getAllMoviesInCategory(selectedId);
    }

    /**
     * Gets all movies from selected categories.
     *
     * @param cats
     * @return
     */
    public List<Movie> getAllMoviesInCategory(List<Category> cats) {

        HashMap<Integer, Movie> hm = new HashMap();

        if (!cats.isEmpty()) {
            for (Category cat : cats) {
                List<Movie> allMovies = dalm.getAllMoviesInCategory(cat.getId());
                for (Movie allMovie : allMovies) {
                    if (!hm.containsKey(allMovie.getId())) {
                        hm.put(allMovie.getId(), allMovie);
                    }
                }
            }
        } else {
            return dalm.getAllMovies();
        }
        return new ArrayList<>(hm.values());
    }

    /**
     * Gets all movies by searching for name and imdbRating
     *
     * @param name
     * @param imdbRating
     * @return
     */
    public List<Movie> getAllMoviesBySearching(String name, String imdbRating) {
        return dalm.getAllMoviesBySearching(name, imdbRating);
    }

    public String selectedMovieLastView(int movieId) throws SQLException {
        return dalm.selectedMovieLastView(movieId);
    }

    public float selectedMoviePersRating(int movieId) throws SQLException {
        return dalm.selectedPersRating(movieId);
    }

    /**
     * returns all movies by title.
     *
     * @return
     */
    public List<String> getAllMoviesByTitle() {
        return dalm.getAllMoviesByTitle();
    }

    /**
     * Adds a new Movie to db table Movie.
     *
     * @param m
     */
    public void addMovieToDb(Movie m) {
        dalm.addMovieToDB(m);
    }

    /**
     * Adds a new category to db table Category.
     *
     * @param c
     */
    public void addCategoryToDb(Category c) {
        dalm.addCategoryToDB(c);
    }

    /**
     * Edits Movie in db.
     *
     * @param m
     */
    public void editMovieInDb(Movie m) {
        dalm.editMovieInDb(m);
    }

    /**
     * Removes movie from db tables Movie and Catmovie
     *
     * @param selectedMovie
     * @param selectedMovieId
     */
    public void removeMovieFromDb(Movie selectedMovie, Movie selectedMovieId) {
        dalm.removeMovieFromDb(selectedMovie, selectedMovieId);
    }

    /**
     * Removes category from db tables Category and CatMovie
     *
     * @param selectedCategory
     */
    public void removeCategoryFromDb(Category selectedCategory) {
        dalm.removeCategoryFromDb(selectedCategory);
    }

    /**
     * Edits date of movie.
     *
     * @param d
     * @param selectedId
     */
    public void editDate(String d, int selectedId) {
        dalm.editDate(d, selectedId);
    }

    /**
     * This method matchs the movie category based on the String "categoryName"
     *
     * @param categoryName
     */
    public void matchMovieCat(String categoryName) {
        dalm.matchMovieCat(categoryName);
    }

    /**
     *
     * @return This method returns the "movieCount" parameter as an integer.
     */
    public int movieCount() {
        return dalm.getMovieCount();
    }
}
