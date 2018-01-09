/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.GUI.CONTROLLER;

import examprivatemovie.GUI.CONTROLLER.MainMovieViewController;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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

    @FXML
    private MediaView mediaView;
    
    private MainMovieViewController parent;
    
    private MediaPlayer mp;
    private Media me;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        String path = new File ("C:/Users/Jesper/Desktop/NetbeansProjects/ExamPrivateMovie/SampleVideo_1280x720_2mb.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mediaView.setMediaPlayer(mp);
        
    }    
    
    public void setParentWindowController(MainMovieViewController parent)
    {
        this.parent = parent;
    }
}