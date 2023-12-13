package com.example.practice1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;

public class SaleItem implements Parcelable {
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


    protected SaleItem(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
        url = in.readString();
        if (in.readByte() == 0) {
            imageId = null;
        } else {
            imageId = in.readInt();
        }
    }

    public static final Creator<SaleItem> CREATOR = new Creator<SaleItem>() {
        @Override
        public SaleItem createFromParcel(Parcel in) {
            return new SaleItem(in);
        }

        @Override
        public SaleItem[] newArray(int size) {
            return new SaleItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeDouble(price);
        parcel.writeString(url);
        if (imageId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(imageId);
        }
    }
}
