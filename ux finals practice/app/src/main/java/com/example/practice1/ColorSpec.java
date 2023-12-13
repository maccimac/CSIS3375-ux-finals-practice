package com.example.practice1;

public class ColorSpec {
    private String ColorDesc;
    private int ColorVal;
    public void setColorDesc(String colorDesc) {
        ColorDesc = colorDesc;
    }
    public void setColorVal(int colorVal) {
        ColorVal = colorVal;
    }
    public String getColorDesc() {
        return ColorDesc;
    }
    public int getColorVal() {
        return ColorVal;
    }
    public ColorSpec(String colorDesc, int colorVal) {
        ColorDesc = colorDesc;
        ColorVal = colorVal;
    }
}
