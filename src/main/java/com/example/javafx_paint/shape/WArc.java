package com.example.javafx_paint.shape;

import com.example.javafx_paint.Paint;
import com.example.javafx_paint.plate.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class WArc extends Arc implements WShape {
    private Canvas canvas;
    public WArc(MouseEvent e, Color color, Color stroke, double strokeWidth, Canvas canvas){
        super();
        this.canvas = canvas;
        setCenterX(e.getX());
        setCenterY(e.getY());
        setRadiusX(0);
        setRadiusY(0);
        setStartAngle(0);
        setLength(180);
        setType(ArcType.ROUND);
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
    public void setLengthW(double l) {
        setLength(l);
    }

    @Override
    public void setStartAngleW(double s) {
        setStartAngle(s);
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
    public double iGetStrokeWidth() {
        return getStrokeWidth();
    }

    @Override
    public String toString() {
        return "Arc";
    }
}
