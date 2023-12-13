package com.example.practice1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ColorItemViewModel extends ViewModel {
    MutableLiveData<List<ColorItem>> colorList = new MutableLiveData<>();

    public LiveData<List<ColorItem>> getColorList(){
        if(colorList == null){
            colorList = new MutableLiveData<>();
        }
        return colorList;
    }
    public void loadColorList(List<ColorItem> _colorList){
        colorList.setValue(_colorList);
    }

}
