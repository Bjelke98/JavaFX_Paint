package com.example.javafx_paint.shape;

import com.example.javafx_paint.Paint;
import com.example.javafx_paint.State;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public interface WShape {

    // Setters
    // // Øker eller senker størrelse basert på scroll
    default void deltaSize(ScrollEvent e){}

    // After Creation
    // // Klikk på individuell figur
    default void selectPress(MouseEvent e){
        if(State.isSelect()){
            Paint.setSelected(this);
        }
    }
    // // Dra på individuell figur
    default void selectDrag(MouseEvent e) {
        if(State.isSelect()){
            move(e);
        }
    }

    // Metoder for å konfigurere ARC
    default void setLengthW(double l){}
    default void setStartAngleW(double s){}

    void setText(String text);

    // Before Creation
    void drag(MouseEvent e);
    void move(MouseEvent e);
    void remove();
    default void newPoint(){}

    // Shape Info
    // Henter figur info
    default double iGetArea(){return 0;}
    default double iGetCirc(){return 0;}
    default double iGetWidth(){return 0;}
    default double iGetHeight(){return 0;}
    default double iGetX(){return 0;}
    default double iGetY(){return 0;}
    default double iGetLineWidth(){return 0;}
    default double iGetStrokeWidth(){return 0;}
    javafx.scene.paint.Paint iGetColor();
    javafx.scene.paint.Paint iGetStroke();

}
