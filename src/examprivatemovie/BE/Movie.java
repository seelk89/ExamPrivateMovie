/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Anni
 */
public class Movie {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty PersonalRating = new SimpleStringProperty();
    private final StringProperty IMDBRating = new SimpleStringProperty();
    private final StringProperty filelink = new SimpleStringProperty();
    private final StringProperty lastview = new SimpleStringProperty();
    private final IntegerProperty id = new SimpleIntegerProperty();

    /**
     *
     * @return This method gets the id.
     */
    public int getId() {
        return id.get();
    }

    /**
     *
     * @param value This method sets the id value.
     */
    public void setId(int value) {
        id.set(value);
    }

    /**
     *
     * @return This method returns the id as IntegerProperty.
     */

    public IntegerProperty idProperty() {
        return id;
    }

    /**
     *
     * @return This method gets the "Lastview" parameter.
     */
    public String getLastview() {
        return lastview.get();
    }

    /**
     *
     * @param value This method sets the "Lastview" parameter.
     */
    public void setLastview(String value) {
        lastview.set(value);
    }
/**
 * 
 * @return This method returns the "Lastview" parameter as StringProperty.
 */
    public StringProperty lastviewProperty() {
        return lastview;
    }
/**
 * 
 * @return This method gets the "FileLink" parameter.
 */
    public String getFilelink() {
        return filelink.get();
    }
/**
 * 
 * @param value This method sets the "FileLink" parameter.
 */
    public void setFilelink(String value) {
        filelink.set(value);
    }
/**
 * 
 * @return This method returns the "Filelink" as StringProperty.
 */
    public StringProperty filelinkProperty() {
        return filelink;
    }
/**
 * 
 * @return This method gets the rating from the page IMDB.
 */
    public String getIMDBRating() {
        return IMDBRating.get();
    }
/**
 * 
 * @param value This method set the rating from the page IMDB.
 */
    public void setIMDBRating(String value) {
        IMDBRating.set(value);
    }
/**
 * 
 * @return This method sets the rating from the page IMDB as a StringProperty.
 */
    public StringProperty IMDBRatingProperty() {
        return IMDBRating;
    }
/**
 * 
 * @return This method gets the personal rating.
 */
    public String getPersonalRating() {
        return PersonalRating.get();
    }
/**
 * 
 * @param value This method sets the personal rating.
 */
    public void setPersonalRating(String value) {
        PersonalRating.set(value);
    }
/**
 * 
 * @return This method returns the personal rating as a StringProperty.
 */
    public StringProperty PersonalRatingProperty() {
        return PersonalRating;
    }
/**
 * 
 * @return This method gets the name.
 */
    public String getName() {
        return name.get();
    }
/**
 * 
 * @param value This method sets the name.
 */
    public void setName(String value) {
        name.set(value);
    }
/**
 * 
 * @return This method returns the name as a StringProperty.
 */
    public StringProperty nameProperty() {
        return name;
    }

}
