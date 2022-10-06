package com.example.javafx_paint.shape;

import com.example.javafx_paint.Paint;
import com.example.javafx_paint.plate.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WText extends Text implements WShape {
    private Canvas canvas;
    public WText(MouseEvent e, Color color, Color stroke, double strokeWidth, Canvas canvas, String text){
        super(text);
        setX(e.getX());
        setY(e.getY());
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
        setFont(Font.font(getFont().getSize()+e.getDeltaY()/3));
    }

    @Override
    public void drag(MouseEvent e) {
        move(e);
    }

    @Override
    public void move(MouseEvent e) {
        setX(e.getX());
        setY(e.getY());
    }

    @Override
    public void remove() {
        canvas.remove(this);
    }

    @Override
    public double iGetHeight() {
        return getFont().getSize();
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
    public String toString() {
        return "Text";
    }
}
