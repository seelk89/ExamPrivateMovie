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
public class Category {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();

    /**
     *
     * @return This method gets the id linked with categories
     */
    public int getId() {
        return id.get();
    }

    /**
     *
     * @return This method sets the id linked with categories
     */
    public void setId(int value) {
        id.set(value);
    }
    /**
     * 
     * @return This method returns the id as a IntegerProperty. It takes the id.
     */
    public IntegerProperty idProperty() {
        return id;
    }
    /**
     * 
     * @return This method gets the name as a String.
     */
    public String getName() {
        return name.get();
    }
    /**
     * 
     * @param value This method sets the name as String.
     */
    public void setName(String value) {
        name.set(value);
    }
    /**
     * 
     * @return This method returns the name as a StringProperty. It takes the name. 
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * 
     * @return the value of "name".
     */
    @Override
    public String toString() {
        return name.getValue();
    }
}
