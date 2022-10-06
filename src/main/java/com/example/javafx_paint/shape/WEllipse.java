package com.example.javafx_paint.shape;

import com.example.javafx_paint.Paint;
import com.example.javafx_paint.plate.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class WEllipse extends Ellipse implements WShape {
    private Canvas canvas;
    public WEllipse(MouseEvent e, Color color, Color stroke, double strokeWidth, Canvas canvas){
        super(e.getX(), e.getY(), 0, 0);
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
        setRadiusX(getRadiusX()+e.getDeltaY()/3);
        setRadiusY(getRadiusY()+e.getDeltaY()/3);
    }

    @Override
    public void setText(String text) {

    }

    @Override
    public void drag(MouseEvent e) {
        setRadiusX(Math.abs(getCenterX()-e.getX()));
        setRadiusY(Math.abs(getCenterY()-e.getY()));
    }

    @Override
    public void move(MouseEvent e) {
        if(getRotate()==0){
            setCenterX(e.getX());
            setCenterY(e.getY());
        }
    }

    @Override
    public void remove() {
        canvas.remove(this);
    }

    @Override
    public double iGetArea() {
        return Math.PI*getRadiusX()*getRadiusY();
    }

    @Override
    public double iGetWidth() {
        return getRadiusX();
    }

    @Override
    public double iGetHeight() {
        return getRadiusY();
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
    public javafx.scene.paint.Paint iGetColor() {
        return getFill();
    }

    @Override
    public javafx.scene.paint.Paint iGetStroke() {
        return getStroke();
    }

    @Override
    public double iGetCirc() {
        return 2*Math.PI*Math.sqrt((Math.pow(getRadiusX(),2)+Math.pow(getRadiusY(),2))/2);
    }

    @Override
    public double iGetStrokeWidth() {
        return getStrokeWidth();
    }

    @Override
    public String toString() {
        return "Ellipse";
    }
}
