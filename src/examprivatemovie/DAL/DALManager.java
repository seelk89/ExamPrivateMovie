/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.DAL;

import examprivatemovie.BE.CatMovie;
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
public class DALManager {
     private ConnectionManager cm = new ConnectionManager();
   
     public List<Movie> getAllMovies() {
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
    
      public List<Category> getAllCategories() {
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
     * Shows all movies in the selected category
     */
    public List<CatMovie> getAllMoviesInCategory()
    {
        List<CatMovie> allMoviesInCategory = new ArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement
        

        ( " SELECT Movie.name, Movie.personalRating, Movie.imdbRating, Movie.lastview " 
        + " FROM ((CatMovie " 
        + " INNER JOIN Category ON CatMovie.CategoryId = Category.id) " 
        + " INNER JOIN Movie ON CatMovie.MovieId = Movie.id) "
        );   
            

//            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
//                Movie c = new Movie();
//                c.setName(rs.getString("name"));
//                c.setPersonalRating(rs.getString("personalRating"));
//                c.setIMDBRating(rs.getString("imdbRating"));
//                c.setFilelink(rs.getString("filelink"));
//                c.setLastview(rs.getString("lastview"));
//          
                CatMovie cm = new CatMovie();
                cm.setId(rs.getInt("id"));
                cm.setCategoryId(rs.getInt("CategoryId"));
                cm.setMovieId(rs.getInt("MovieId"));
               

                allMoviesInCategory.add(cm);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allMoviesInCategory;
    }

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
        
   public void addMovieToCategory(CatMovie catm)
   {
       try (Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT INTO CatMovie"
                    + "(id, CategoryId, MovieId) "
                    + "VALUES(?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            pstmt.setInt(1, catm.getId());
            pstmt.setInt(2, catm.getCategoryId());
            pstmt.setInt(3, catm.getMovieId());

            

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Movie could not be added to category/ies");
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
   

   public void removeMovieFromDb(Movie selectedMovie)
   {
       try (Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM Movie WHERE name=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, selectedMovie.getName());
            pstmt.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
   }   
    
   
    public List<Movie> getAllMoviesBySearching
        (
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

}
