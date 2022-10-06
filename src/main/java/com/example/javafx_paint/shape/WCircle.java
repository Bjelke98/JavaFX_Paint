package com.example.javafx_paint.shape;

import com.example.javafx_paint.Paint;
import com.example.javafx_paint.plate.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class WCircle extends Circle implements WShape {
    private final Canvas canvas;
    public WCircle(MouseEvent e, Color color, Color stroke, double strokeWidth, Canvas canvas){
        super(e.getX(), e.getY(), 20);
        this.canvas = canvas;
        setStroke(stroke);
        setFill(color);
        setStrokeWidth(strokeWidth);
        setOnMousePressed(this::selectPress);
        setOnMouseDragged(this::selectDrag);
        Paint.setCurrent(this);
        Paint.setSelected(this);
        canvas.add(this);
    }
    @Override
    public void deltaSize(ScrollEvent e) {
        double deltaY = e.getDeltaY()/3;
        setRadius(getRadius()+deltaY);
    }

    @Override
    public void setText(String text) {

    }

    @Override
    public void drag(MouseEvent e) {
        setRadius(
            Math.sqrt(
                (e.getX() - getCenterX()) * (e.getX() - getCenterX())+
                (e.getY() - getCenterY()) * (e.getY() - getCenterY())
            )
        );
    }

    @Override
    public void move(MouseEvent e) {
        setCenterX(e.getX());
        setCenterY(e.getY());
    }

    @Override
    public void remove() {
        canvas.remove(this);
    }

    @Override
    public double iGetArea() {
        return Math.PI*getRadius()*getRadius();
    }

    @Override
    public double iGetWidth() {
        return getRadius();
    }

    @Override
    public double iGetX() {
        return getCenterX();
    }

    @Override
    public double iGetY() {
        return getCenterY();
    }

    @Override
    public double iGetStrokeWidth() {
        return getStrokeWidth();
    }

    @Override
    public javafx.scene.paint.Paint iGetColor() {
        return getFill();
    }

    @Override
    public javafx.scene.paint.Paint iGetStroke() {
        return getStroke();
    }

    @Override
    public double iGetCirc() {
        return Math.PI*2*getRadius();
    }

    @Override
    public String toString() {
        return "Circle";
    }
}
