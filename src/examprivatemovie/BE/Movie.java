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

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    
    public String getLastview() {
        return lastview.get();
    }

    public void setLastview(String value) {
        lastview.set(value);
    }

    public StringProperty lastviewProperty() {
        return lastview;
    }

    
    public String getFilelink() {
        return filelink.get();
    }

    public void setFilelink(String value) {
        filelink.set(value);
    }

    public StringProperty filelinkProperty() {
        return filelink;
    }
    

    public String getIMDBRating() {
        return IMDBRating.get();
    }

    public void setIMDBRating(String value) {
        IMDBRating.set(value);
    }

    public StringProperty IMDBRatingProperty() {
        return IMDBRating;
    }
    
    
    public String getPersonalRating() {
        return PersonalRating.get();
    }

    public void setPersonalRating(String value) {
        PersonalRating.set(value);
    }

    public StringProperty PersonalRatingProperty() {
        return PersonalRating;
    }
   
    
    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    
    
}
