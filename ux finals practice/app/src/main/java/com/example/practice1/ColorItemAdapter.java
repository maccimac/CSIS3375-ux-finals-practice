package com.example.practice1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;
import com.example.practice1.databinding.LayoutSpinnerItemBinding;

public class ColorItemAdapter extends BaseAdapter {
    List<ColorItem> colorList;
    LayoutSpinnerItemBinding spinnerItemBinding;

    public ColorItemAdapter(List<ColorItem> _colorList) {
        colorList = _colorList;
    }

    @Override
    public int getCount() {
        return colorList.size();
    }

    @Override
    public Object getItem(int i) {
        return colorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        return null;
        if(view == null){
            spinnerItemBinding = LayoutSpinnerItemBinding.
                    inflate(
                        LayoutInflater.from(viewGroup.getContext()),
                        viewGroup,
                        false
                    );
        }
        spinnerItemBinding.textViewSpinnerName.setText(
                colorList.get(i).colorName
        );
        spinnerItemBinding.textViewSpinnerName.setBackgroundColor(
                colorList.get(i).colorVal
        );
        return spinnerItemBinding.getRoot();
    }
}
