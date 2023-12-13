package com.example.practice1;

public class CartItem {
    SaleItem saleItem;
    int amount=0;

    public CartItem() {
        this.saleItem = new SaleItem();
    }

    public CartItem(SaleItem saleItem) {
        this.saleItem = saleItem;
    }

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
}
