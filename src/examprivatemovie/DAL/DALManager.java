/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.DAL;

import examprivatemovie.BE.Category;
import examprivatemovie.BE.Movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anni
 */
public class DALManager
{

    /**
     * Selects all from Movie.
     */
    private ConnectionManager cm = new ConnectionManager();

    public List<Movie> getAllMovies()
    {
        System.out.println("Getting all Movies.");

        List<Movie> allMovies = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Movie");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Movie m = new Movie();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setPersonalRating(rs.getString("personalRating"));
                m.setIMDBRating(rs.getString("IMDBRating"));
                m.setLastview(rs.getString("lastview"));
                m.setFilelink(rs.getString("filelink"));

                allMovies.add(m);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allMovies;
    }
    
    /**
     * Counts all movies.
     */
    public int getMovieCount()
    {
        int count = 0;
        
        try (Connection con = cm.getConnection())
        { 
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Movie");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                count = count + 1;
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     * Selects all from Category
     *
     * @return
     */
    public List<Category> getAllCategories()
    {
        System.out.println("Getting all Categories.");

        List<Category> allCategories = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Category");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Category c = new Category();
                c.setName(rs.getString("name"));
                c.setId(rs.getInt("id"));

                allCategories.add(c);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allCategories;
    }

    /**
     *
     * Gets all movies from selected Category.Id
     */
    public List<Movie> getAllMoviesInCategory(int selectedId)
    {
        List<Movie> allMoviesInCategory = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement(
                    " SELECT Movie.name, Movie.personalRating, Movie.imdbRating, Movie.lastview, Movie.filelink "
                    + " FROM ((CatMovie "
                    + " INNER JOIN Category ON CatMovie.CategoryId = Category.id) "
                    + " INNER JOIN Movie ON CatMovie.MovieId = Movie.id) "
                    + " WHERE Category.Id = ? "
            );

            stmt.setInt(1, selectedId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Movie m = new Movie();
                m.setName(rs.getString("name"));
                m.setPersonalRating(rs.getString("personalRating"));
                m.setIMDBRating(rs.getString("imdbRating"));
                m.setFilelink(rs.getString("filelink")); //dunno if works
                m.setLastview(rs.getString("lastview"));

                allMoviesInCategory.add(m);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allMoviesInCategory;
    }

    /**
     * Adds an entry to Movie
     *
     * @param m
     */
    public void addMovieToDB(Movie m)
    {
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT INTO Movie"
                    + "(name, personalRating, IMDBRating, filelink) "
                    + "VALUES(?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, m.getName());
            pstmt.setString(2, m.getPersonalRating());
            pstmt.setString(3, m.getIMDBRating());
            pstmt.setString(4, m.getFilelink());

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Movie could not be added");
            }

            // Get database generated id, probs not necessary
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next())
            {
                //char.setId(rs.getInt(1));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds a category to Category.
     *
     * @param c
     */
    public void addCategoryToDB(Category c)
    {
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT INTO Category"
                    + "(name) "
                    + "VALUES(?)";

            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, c.getName());

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Category could not be added");
            }

            // Get database generated id, probs not necessary
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next())
            {
                //char.setId(rs.getInt(1));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     * edits a movie in db
     *
     * @param m
     */
    public void editMovieInDb(Movie m)
    {
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "UPDATE Movie SET "
                    + "name=?, personalRating=?, IMDBRating=?, filelink=? "
                    + "WHERE name=?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, m.getName());
            pstmt.setString(2, m.getPersonalRating());
            pstmt.setString(3, m.getIMDBRating());
            pstmt.setString(4, m.getFilelink());

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Movie could not be edited");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     * Removes selected movie from Movie and CatMovie.
     *
     * @param selectedMovie
     * @param selectedMovieId
     */
    public void removeMovieFromDb(Movie selectedMovie, Movie selectedMovieId)
    {
        //also delete from CatMovie where id=?
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "DELETE FROM Movie WHERE name=? ";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, selectedMovie.getName());
            pstmt.execute();

            String sql2
                    = "DELETE FROM CatMovie WHERE MovieId=? ";
            PreparedStatement pstmt2 = con.prepareStatement(sql2);

            pstmt2.setInt(1, selectedMovieId.getId());
            pstmt2.execute();

        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     * Removes selected category from Category and CatMovie
     *
     * @param selectedCategory
     */
    public void removeCategoryFromDb(Category selectedCategory)
    {
        //also delete from CatMovie where id=?
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "DELETE FROM Category WHERE name=? ";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, selectedCategory.getName());
            pstmt.execute();

            String sql2
                    = "DELETE FROM CatMovie WHERE CategoryId=? ";
            PreparedStatement pstmt2 = con.prepareStatement(sql2);

            pstmt2.setInt(1, selectedCategory.getId());
            pstmt2.execute();

        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets all Movies where name or imdbRating = whatever text is in the
     * searchFilter
     *
     * @param name
     * @param imdbRating
     * @return
     */
    public List<Movie> getAllMoviesBySearching(
            String name, String imdbRating)
    {

        List<Movie> allMovies = new ArrayList();

        try (Connection con = cm.getConnection())
        {

            String query
                    = "SELECT * FROM Movie "
                    + "WHERE name LIKE ? "
                    + "OR "
                    + "imdbRating LIKE ? "
                    + "ORDER BY name ";

            PreparedStatement pstmt
                    = con.prepareStatement(query);
            pstmt.setString(1, "%" + name + "%");
            pstmt.setString(2, "%" + imdbRating + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {

                Movie m = new Movie();
                m.setName(rs.getString("name"));
                m.setPersonalRating(rs.getString("personalRating"));
                m.setIMDBRating(rs.getString("imdbRating")); //imdb capital letters or no?
                m.setFilelink(rs.getString("filelink"));
                allMovies.add(m);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allMovies;

    }

    /**
     * Gets all movies by title, why is this one here, not used !!!!!!!!!!
     * Instead look at the method beneath SAM, you can use that one.
     *
     * @return
     */
    public List<String> getAllMoviesByTitle()
    {
        List<String> allMoviesByTitle = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT name FROM Movie");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                allMoviesByTitle.add(rs.getString("name"));
                //m.setPersonalRating(rs.getString("personalRating"));
                //m.setIMDBRating(rs.getString("imdbRating"));
                //m.setFilelink(rs.getString("filelink"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allMoviesByTitle;
    }

    /**
     * Edits the lastview in Movie
     *
     * @param d
     * @param selectedId
     */
    public void editDate(String d, int selectedId)
    {
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "UPDATE Movie SET "
                    + "lastview=? "
                    + "WHERE id=?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, d);
            pstmt.setInt(2, selectedId);

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Date could not be updated");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     * Combines the id of the movie and the selected category
     * in the CatMovie table in the database.
     * @param categoryName
     */
    public void matchMovieCat(String categoryName)
    {
        try (Connection con = cm.getConnection())
        {
            int movieId = 0;
            int catId = 0;

            //Gets the newly added Movies id
            PreparedStatement pstmt1 = con.prepareStatement("SELECT MAX(id) FROM Movie");

            ResultSet rsMovie = pstmt1.executeQuery();

            if (rsMovie.next())
            {
                movieId = rsMovie.getInt(1);
            }

            System.out.println(movieId);

            //Gets the id of the category that has been selected
            PreparedStatement pstmt2 = con.prepareStatement("SELECT id FROM Category WHERE name = ?");

            pstmt2.setString(1, categoryName);

            ResultSet rsCat = pstmt2.executeQuery();

            if (rsCat.next())
            {
                catId = rsCat.getInt(1);
            }

            System.out.println(catId);

            //Inserts the movieId along with the categoryId into CatMovie
            String sql = "INSERT INTO CatMovie "
                    + "(CategoryId, MovieId) "
                    + "VALUES (?, ?)";

            PreparedStatement pstmt3 = con.prepareStatement(sql);
            pstmt3.setInt(1, catId);
            pstmt3.setInt(2, movieId);

            int affected = pstmt3.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Date could not be updated");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the lastViewed date of selected movie.
     *
     */
    public String selectedMovieLastView(int movieId) throws SQLException
    {
        String movieLastView = "";

        try (Connection con = cm.getConnection())
        {
            //Gets the lastview date of the selected movie
            PreparedStatement pstmt1 = con.prepareStatement("SELECT lastview FROM Movie WHERE id = ?");

            pstmt1.setInt(1, movieId);

            ResultSet rsLastView = pstmt1.executeQuery();

            if (rsLastView.next())
            {
                movieLastView = rsLastView.getString(1);
            }
        }

        return movieLastView;
    }
    
    /**
     * Gets the selected movies personalRating and converts it to a float.
     *
     */
    public float selectedPersRating(int movieId) throws SQLException
    {
        String moviePersRating = "";

        try (Connection con = cm.getConnection())
        {
            //Gets the personal rating of the selected movie
            PreparedStatement pstmt1 = con.prepareStatement("SELECT personalRating FROM Movie WHERE id = ?");

            pstmt1.setInt(1, movieId);

            ResultSet rsPersRating = pstmt1.executeQuery();

            if (rsPersRating.next())
            {
                moviePersRating = rsPersRating.getString(1);
            }
        }

        float floatPersRating = Float.parseFloat(moviePersRating);

        return floatPersRating;
    }
}
