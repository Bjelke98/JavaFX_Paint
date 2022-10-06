package com.example.javafx_paint.shape;

import com.example.javafx_paint.plate.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class WMarker extends Circle implements WShape {
    Color color;
    double strokeWidth;
    Canvas canvas;
    public WMarker(MouseEvent e, Color color, double strokeWidth, Canvas canvas){
        super(e.getX(), e.getY(), strokeWidth, color);
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.canvas = canvas;
        canvas.add(this);
    }

    @Override
    public void selectPress(MouseEvent e) {

    }

    @Override
    public void selectDrag(MouseEvent e) {

    }

    @Override
    public void setText(String text) {

    }

    @Override
    public void drag(MouseEvent e) {
        new WMarker(e, this.color, this.strokeWidth, this.canvas);
    }

    @Override
    public void move(MouseEvent e) {

    }

    @Override
    public void remove() {

    }

    @Override
    public Paint iGetColor() {
        return null;
    }

    @Override
    public Paint iGetStroke() {
        return null;
    }
}
