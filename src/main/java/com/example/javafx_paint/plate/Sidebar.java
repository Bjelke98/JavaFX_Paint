package com.example.javafx_paint.plate;

import com.example.javafx_paint.Paint;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Sidebar = standardopsett for ekstra vinduer
 */

public class Sidebar extends Stage implements Plate {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 800;
    public static final int DEFAULT_PADDING = 10;
    public static final int DEFAULT_SPACING = 5;
    private VBox box;
    private Scene scene;
    public Sidebar(String name){
        box = new VBox();
        box.setPadding(new Insets(DEFAULT_PADDING));
        box.setSpacing(DEFAULT_SPACING);
        scene = new Scene(box, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle(name);
        setScene(scene);
        getIcons().add(new Image("file:"+ Paint.DEFAULT_ICON_PATH));
        show();
    }

    @Override
    public void add(Node node) {
        box.getChildren().add(node);
    }

    @Override
    public void addAll(Node... nodes) {
        box.getChildren().addAll(nodes);
    }

    @Override
    public void addAll(ObservableList<Node> nodes) {
        box.getChildren().addAll(nodes);
    }

    @Override
    public void clear(){
        box.getChildren().clear();
    }
}
