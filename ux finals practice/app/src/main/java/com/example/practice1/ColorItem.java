package com.example.practice1;

public class ColorItem {
     String colorName;
     int colorVal;
    public void setColorDesc(String _colorName) {
        colorName = _colorName;
    }
    public void setColorVal(int _colorVal) {
        colorVal = _colorVal;
    }
    public String getColorDesc() {
        return colorName;
    }
    public int getColorVal() {
        return colorVal;
    }
    public ColorItem(String _colorName, int _colorVal) {
        colorName = _colorName;
        colorVal = _colorVal;
    }
}
