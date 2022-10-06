package com.example.javafx_paint;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new Paint(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}