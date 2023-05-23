package com.rayen.miniprojetfx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class Product {


    private  SimpleIntegerProperty id;
    private  SimpleStringProperty name;
    private  SimpleIntegerProperty quantity;
    private  SimpleDoubleProperty price;
    private Image photo;

    public Product(String name, Image photo) {
        this.name = new SimpleStringProperty(name);
        this.photo = photo;
    }

    public Product(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public Product(String name, int quantity) {
        this.quantity = new SimpleIntegerProperty(quantity);
        this.name = new SimpleStringProperty(name);
    }

    public Product() {
    }

    public Product(int id, String name, int quantity, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
    }


    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }


    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public double getPrice() {
        return price.get();
    }

    // Setter methods (if needed)
    public void setId(int id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}
