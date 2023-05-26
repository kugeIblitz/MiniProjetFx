package com.rayen.miniprojetfx;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.IntegerExpression;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleDoubleProperty price;
    private SimpleStringProperty name;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty image;
    private SimpleStringProperty Description;
    private SimpleStringProperty YouSaved;

    public String getDescription() {
        return Description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return Description;
    }

    public void setDescription(String description) {
        if (this.Description == null) {
            this.Description = new SimpleStringProperty();
        }
        this.Description.set(description);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getYouSaved() {
        return YouSaved.get();
    }

    public SimpleStringProperty youSavedProperty() {
        return YouSaved;
    }

    public void setYouSaved(String youSaved) {
        if (this.YouSaved == null) {
            this.YouSaved = new SimpleStringProperty();
        }
        this.YouSaved.set(youSaved);
    }

    public Product(String name, String image) {
        this.name = new SimpleStringProperty(name);
        this.image = new SimpleStringProperty(image);
    }

    public Product(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public Product(String name, int quantity) {
        this.quantity = new SimpleIntegerProperty(quantity);
        this.name = new SimpleStringProperty(name);
    }

    public Product(String name, int quantity, String Description, String YouSaved) {
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.Description = new SimpleStringProperty(Description);
        this.YouSaved = new SimpleStringProperty(YouSaved);
    }

    public Product() {
    }

    Product(int id,String name,String description,double price,String image){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.image = new SimpleStringProperty(image);
        this.Description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
    }

    public Product(int id, String name, int quantity) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getImage() {
        return image.get();
    }

    public SimpleStringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }


}
