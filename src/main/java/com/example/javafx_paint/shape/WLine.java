package com.example.javafx_paint.shape;

import com.example.javafx_paint.Paint;
import com.example.javafx_paint.plate.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class WLine extends Line implements WShape {
    private Canvas canvas;
    public WLine(MouseEvent e, Color color, Color stroke, double strokeWidth, Canvas canvas){
        super(e.getX(), e.getY(), e.getX(), e.getY());
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
    public void setText(String text) {

    }

    @Override
    public void drag(MouseEvent e) {
        setEndX(e.getX());
        setEndY(e.getY());
    }

    @Override
    public void move(MouseEvent e) {
        if(getRotate()==0){
            double width = getEndX()-getStartX();
            double height = getEndY()-getStartY();
            setStartX(e.getX()-width/2);
            setStartY(e.getY()-height/2);
            setEndX(getStartX()+width);
            setEndY(getStartY()+height);
        }
    }

    @Override
    public void remove() {
        canvas.remove(this);
    }

    @Override
    public double iGetX() {
        return getStartX();
    }

    @Override
    public double iGetY() {
        return getStartY();
    }

    @Override
    public javafx.scene.paint.Paint iGetColor() {
        return this.getFill();
    }

    @Override
    public javafx.scene.paint.Paint iGetStroke() {
        return this.getStroke();
    }

    @Override
    public double iGetLineWidth() {
        return Math.sqrt((getEndX() - getStartX()) * (getEndX() - getStartX()) + (getEndY() - getStartY()) * (getEndY() - getStartY()));
    }

    @Override
    public double iGetStrokeWidth() {
        return getStrokeWidth();
    }

    @Override
    public String toString() {
        return "Line";
    }
}
