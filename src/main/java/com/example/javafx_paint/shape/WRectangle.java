package com.example.javafx_paint.shape;

import com.example.javafx_paint.Paint;
import com.example.javafx_paint.plate.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WRectangle extends Rectangle implements WShape {
    public final double INITIAL_X;
    public final double INITIAL_Y;
    private final Canvas canvas;
    public WRectangle(MouseEvent e, Color color, Color stroke, double strokeWidth, Canvas canvas){
        super(e.getX(), e.getY(), 0, 0);
        this.canvas = canvas;
        INITIAL_X = e.getX();
        INITIAL_Y = e.getY();
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
        setX(getX()-deltaY);
        setY(getY()-deltaY);
        setWidth(getWidth()+deltaY*2);
        setHeight(getHeight()+deltaY*2);
    }

    @Override
    public void setText(String text) {

    }

    @Override
    public void drag(MouseEvent e) {
        double deltaX = e.getX() - INITIAL_X;
        double deltaY = e.getY() - INITIAL_Y;
        if(deltaX < 0) {
            setX(e.getX());
            setWidth(-deltaX);
        } else {
            setX(INITIAL_X);
            setWidth(e.getX() - INITIAL_X);
        }
        if(deltaY < 0) {
            setY( e.getY() );
            setHeight(-deltaY);
        } else {
            setY(INITIAL_Y);
            setHeight(e.getY() - INITIAL_Y);
        }
    }

    @Override
    public void move(MouseEvent e) {
        if(getRotate()==0){
            setX(e.getX()-getWidth()/2);
            setY(e.getY()-getHeight()/2);
        }
    }

    @Override
    public void remove() {
        canvas.remove(this);
    }

    @Override
    public double iGetArea() {
        return getHeight()*getWidth();
    }

    @Override
    public double iGetWidth() {
        return getWidth();
    }

    @Override
    public double iGetHeight() {
        return getHeight();
    }

    @Override
    public double iGetX() {
        return getY();
    }

    @Override
    public double iGetY() {
        return getX();
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
    public double iGetCirc() {
        return getWidth()*2+getHeight()*2;
    }

    @Override
    public String toString() {
        return "Rectangle";
    }
}
