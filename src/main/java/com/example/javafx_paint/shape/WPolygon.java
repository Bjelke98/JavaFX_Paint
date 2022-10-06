package com.example.javafx_paint.shape;

import com.example.javafx_paint.Paint;
import com.example.javafx_paint.plate.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class WPolygon extends Polygon implements WShape {
    private Canvas canvas;
    public WPolygon(MouseEvent e, Color color, Color stroke, double strokeWidth, Canvas canvas){
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
        int lastPoint = getPoints().size();
        getPoints().set(lastPoint-2, e.getX());
        getPoints().set(lastPoint-1, e.getY());
    }

    @Override
    public void move(MouseEvent e) {
        if(getRotate()==0){
            double deltaX = e.getX()-getPoints().get(0);
            double deltaY = e.getY()-getPoints().get(1);
            for (int i = 0; i<getPoints().size();i+=2){
                getPoints().set(i, getPoints().get(i)+deltaX);
                getPoints().set(i+1, getPoints().get(i+1)+deltaY);
            }
        }
    }

    @Override
    public void remove() {
        canvas.remove(this);
    }

    @Override
    public double iGetX() {
        return getPoints().get(0);
    }

    @Override
    public double iGetY() {
        return getPoints().get(1);
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
        double l = 0.0;
        double xPrev = getPoints().get(getPoints().size()-2);
        double yPrev = getPoints().get(getPoints().size()-1);
        for (int i = 2; i<getPoints().size(); i+=2){
            double x = getPoints().get(i);
            double y = getPoints().get(i+1);
            l+= Math.sqrt((xPrev - x) * (xPrev - x) + (yPrev - y) * (yPrev - y));
            xPrev = x;
            yPrev = y;
        }
        return l;
    }

    @Override
    public double iGetStrokeWidth() {
        return getStrokeWidth();
    }

    @Override
    public void newPoint() {
        int lastPoint = getPoints().size();
        getPoints().addAll(getPoints().get(lastPoint-2), getPoints().get(lastPoint-1));
    }

    @Override
    public String toString() {
        return "Polygon";
    }
}
