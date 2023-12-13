package com.example.practice1;
// THIS IS IMPORTANT FOR DATA BINDING
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.practice1.databinding.LayoutCartItemBinding;
import java.util.List;

public class CartItemAdapter extends BaseAdapter {
    List<CartItem> cartItemList;
    LayoutCartItemBinding cartItemBinding;

    public CartItemAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @Override
    public int getCount() {
        return cartItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return cartItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            cartItemBinding = LayoutCartItemBinding.inflate(
                    LayoutInflater.from(viewGroup.getContext()),
                    viewGroup, false
            );
        }
        cartItemBinding.textViewCartitemName.setText(
                cartItemList.get(i).saleItem.name
        );
        cartItemBinding.editTextCartitemAmount.setText(
                String.valueOf(cartItemList.get(i).amount)
        );
        return cartItemBinding.getRoot();
    }
}
