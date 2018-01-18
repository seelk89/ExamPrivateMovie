/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI.CONTROLLER;

import examprivatemovie.BE.Movie;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Jesper
 */
public class PlayViewController implements Initializable
{

    /**
     *
     */
    @FXML
    private MediaView mediaView;

    private MainMovieViewController parent;
    private Movie movie;

    private MediaPlayer mp;
    private Media me;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    /**
     * Plays a video with given absolute path.
     * 
     */
    public void play(String path)
    { 
        String filmPath = new File(path).getAbsolutePath();

        me = new Media(new File(filmPath).toURI().toString());
        mp = new MediaPlayer(me);

        mediaView.setMediaPlayer(mp);
        mediaView.setMediaPlayer(mp);
        mp.setAutoPlay(true);

        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
    }

    /**
     *
     * @param parent
     */
    public void setParentWindowController(MainMovieViewController parent)
    {
        this.parent = parent;
    }

}
