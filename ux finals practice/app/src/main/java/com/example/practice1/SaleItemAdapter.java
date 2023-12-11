package com.example.practice1;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// THIS IS IMPORTANT FOR DATA BINDING
import com.example.practice1.databinding.LayoutCartItemBinding;

import java.util.List;

// [] 1. Create SaleItemHolder
// [] 1.1. Create interface OnItemClickListener at the end of method
// [] 2. Alt enter implement methods
// [] 3. Setup method: SaleItemHolder>onCreateViewHolder
// [] 4. Setup onBindViewHolder

public class SaleItemAdapter extends RecyclerView.Adapter<SaleItemAdapter.SaleItemHolder> {
    List<SaleItem> adapterSaleList;
    // SHOULD THIS NOT HAVE 'AdapterView'
    OnItemClickListener onItemClickListener;

    public SaleItemAdapter(List<SaleItem> adapterSaleList) {
        this.adapterSaleList = adapterSaleList;
    }

    public SaleItemAdapter(List<SaleItem> adapterSaleList, OnItemClickListener onItemClickListener) {
        this.adapterSaleList = adapterSaleList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SaleItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        LayoutCartItemBinding binding =  LayoutCartItemBinding.inflate(
                LayoutInflater.from(
                        parent.getContext()),
                parent,
                false
        );
        SaleItemHolder holder = new SaleItemHolder(binding.getRoot(), binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaleItemHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.holderBinding.textViewName.setText(
                adapterSaleList.get(position).name
        );
        holder.holderBinding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
//        return 0;
        return adapterSaleList.size();
    }

    // CREATE THIS FIRST BEFORE IMPLEMENTING METHODS?
    public class SaleItemHolder extends RecyclerView.ViewHolder{ // THEN CREATE CONSTRUCTOR

        LayoutCartItemBinding holderBinding;
        public SaleItemHolder(@NonNull View itemView) {
            super(itemView);
        }
        public SaleItemHolder(@NonNull View itemView, LayoutCartItemBinding holderBinding) {
            super(itemView);
            this.holderBinding = holderBinding;
//            this.holderBinding.getRoot().setOnClickListener(
            this.holderBinding.btnReadMore.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onItemClickListener.onItemClick(
                                    SaleItemHolder.this.getAdapterPosition()
                            );
                        }
                    }
            );

        }

//
    }

    public interface OnItemClickListener{
        public void onItemClick(int i);
    }


}

