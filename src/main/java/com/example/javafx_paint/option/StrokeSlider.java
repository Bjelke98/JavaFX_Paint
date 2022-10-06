package com.example.javafx_paint.option;

import javafx.scene.control.Slider;

public class StrokeSlider extends Slider {
    public static final double DEFAULT_DECIMAL_COUNT_MULTIPLIER = Math.pow(10, 2);
    public StrokeSlider(int min, int max, int start){
        super(min, max, start);
    }

    @Override
    public void adjustValue(double v) {
        super.adjustValue(Math.round(v* DEFAULT_DECIMAL_COUNT_MULTIPLIER)/ DEFAULT_DECIMAL_COUNT_MULTIPLIER);
    }
}
