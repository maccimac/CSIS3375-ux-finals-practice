package com.example.practice1;

import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;

public class SaleItem {
    String name = "Name";
    String description = "Description";
    double price = 0.00;
    String url = "https://";
    LocalDate itemDate;
    Integer imageId;

    public SaleItem(){

    }

    public SaleItem(String name, String description, double price, String url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
    }

    public SaleItem(String name, String description, double price, String url, LocalDate itemDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
        this.itemDate = itemDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getItemDate() {
        return itemDate;
    }

    public void setItemDate(LocalDate itemDate) {
        this.itemDate = itemDate;
    }

    public Integer getImageId() {return imageId;}

    public void setImageId(Integer imgId) {this.imageId = imgId;}
}
