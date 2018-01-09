/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprivatemovie.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Anni
 */
public class CatMovie {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty CategoryId = new SimpleIntegerProperty();
    private final IntegerProperty MovieId = new SimpleIntegerProperty();    

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getCategoryId() {
        return CategoryId.get();
    }

    public void setCategoryId(int value) {
        CategoryId.set(value);
    }

    public IntegerProperty CategoryIdProperty() {
        return CategoryId;
    }

    public int getMovieId() {
        return MovieId.get();
    }

    public void setMovieId(int value) {
        MovieId.set(value);
    }

    public IntegerProperty MovieIdProperty() {
        return MovieId;
    }
    
    
}
