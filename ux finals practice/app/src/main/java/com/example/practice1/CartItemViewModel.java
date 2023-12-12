package com.example.practice1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CartItemViewModel extends ViewModel {
    MutableLiveData<List<CartItem>> cartItemList = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCartItemList(){
        if(cartItemList == null){
            cartItemList = new MutableLiveData<>();
        }
        return cartItemList;
    }

    public void loadCartItemList(List<CartItem> _cartItemList){
        cartItemList.setValue(_cartItemList);
    }
}
