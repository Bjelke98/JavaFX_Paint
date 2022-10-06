package com.example.javafx_paint;

public class State {
    public static boolean select = false;

    public static boolean isSelect() {
        return select;
    }

    public static void setSelect(boolean select) {
        State.select = select;
    }
}
