package com.example.javafx_paint.option;

import com.example.javafx_paint.Paint;
import javafx.scene.control.TextField;

public class StrokeWidthField extends TextField {
    public static final int DEFAULT_COL_COUNT = 5;
    public static final double DEFAULT_DECIMAL_COUNT_MULTIPLIER = Math.pow(10, 2);
    public StrokeWidthField(){
        setPrefColumnCount(DEFAULT_COL_COUNT);
        setText(""+ Paint.DEFAULT_STROKE_WIDTH);
    }
    public void setValue(double value){
        super.setText(Double.toString(Math.round(value* DEFAULT_DECIMAL_COUNT_MULTIPLIER)/ DEFAULT_DECIMAL_COUNT_MULTIPLIER));
    }
    public double getValue(){
        return Math.round(Double.parseDouble(getText())* DEFAULT_DECIMAL_COUNT_MULTIPLIER)/ DEFAULT_DECIMAL_COUNT_MULTIPLIER;
    }
}
