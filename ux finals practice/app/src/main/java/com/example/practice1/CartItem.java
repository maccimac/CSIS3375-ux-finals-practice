package com.example.practice1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CartItem implements Parcelable {
    SaleItem saleItem;
    int amount=0;

    public CartItem() {
        this.saleItem = new SaleItem();
    }

    public CartItem(SaleItem saleItem) {
        this.saleItem = saleItem;
    }

    protected CartItem(Parcel in) {
        saleItem = in.readParcelable(SaleItem.class.getClassLoader());
        amount = in.readInt();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public SaleItem getSaleItem() {
        return saleItem;
    }

    public void setSaleItem(SaleItem saleItem) {
        this.saleItem = saleItem;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(saleItem, i);
        parcel.writeInt(amount);
    }
}
