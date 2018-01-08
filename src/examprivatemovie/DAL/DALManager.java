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
                m.setName(rs.getString("name"));
                m.setPersonalRating(rs.getInt("personalRating"));
                m.setIMDBRating(rs.getInt("IMDBRating"));
                m.setFilelink(rs.getString("filelink"));
                m.setLastview(rs.getString("lastview"));


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
      
//    /**
//     * 
//     * Gets each and every song in the Playlist table where the songs playlist_id is equal the selected playlists playlists_id.
//     */
//    public List<Category> getAllMoviesInCategory(int CategoryId)
//    {
//        List<Category> allMoviesInCategory = new ArrayList();
//
//        try (Connection con = cm.getConnection())
//        {
//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Category WHERE CategoryId = ? ORDER BY playlistOrder");
//
//            stmt.setInt(1, CategoryId);
//
//            ResultSet rs = stmt.executeQuery();
//            
//            while (rs.next())
//            {
//                Category c = new Category();
//                c.setId(rs.getInt("id"));
//                c.setPlaylistsId(rs.getInt("playlists_id"));
//                c.setPlaylistOrder(rs.getInt("playlistOrder"));
//                c.setSongsTitle(rs.getString("songs_title"));
//                c.setSongsArtist(rs.getString("songs_artist"));
//                c.setSongsGenre(rs.getString("songs_genre"));
//                c.setSongsTime(rs.getString("songs_time"));
//                c.setSongsFileLocation(rs.getString("songs_fileLocation"));
//
//                allMoviesInCategory.add(c);
//            }
//        } catch (SQLException ex)
//        {
//            Logger.getLogger(DALManager.class.getName()).log(
//                    Level.SEVERE, null, ex);
//        }
//        return allMoviesInCategory;
//    }

   public void addMovieToDB(Movie m)
   {
       try (Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT INTO Movie"
                    + "(name, personalRating, IMDBRating, filelink, lastview) "
                    + "VALUES(?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            pstmt.setString(1, m.getName());
            pstmt.setInt(2, m.getPersonalRating());
            pstmt.setInt(3, m.getIMDBRating());
            pstmt.setString(4, m.getFilelink());
            pstmt.setString(5, m.getLastview());

            

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
        
   public void editMovieInDb(Movie m)
   {
       try (Connection con = cm.getConnection())
        {
            String sql
                    = "UPDATE Movie SET "
                    + "name=?, personalRating=?, IMDBRating=?, filelink=?, lastview=? "
                    + "WHERE name=?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, m.getName());
            pstmt.setInt(2, m.getPersonalRating());
            pstmt.setInt(3, m.getIMDBRating());
            pstmt.setString(4, m.getFilelink());
            pstmt.setString(5, m.getLastview());
            

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
   

   public void deleteCharFromDb(Movie selectedMovie)
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
            String name)
        {

        List<Movie> allMovies = new ArrayList();

        try (Connection con = cm.getConnection())
        {

            String query
                    = "SELECT * FROM Movie "
                    + "WHERE name LIKE ? "
//                    + "OR "
//                    + "element LIKE ? "
                    + "ORDER BY name ";

            PreparedStatement pstmt
                    = con.prepareStatement(query);
            pstmt.setString(1, "%" + name + "%");
//            pstmt.setString(2, "%" + element + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {

                Movie m = new Movie();
                m.setName(rs.getString("name"));
                m.setPersonalRating(rs.getInt("personalRating"));
                m.setIMDBRating(rs.getInt("IMDBRating"));
                m.setFilelink(rs.getString("filelink"));
                m.setLastview(rs.getString("lastview"));
                allMovies.add(m);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allMovies;

    }
}
