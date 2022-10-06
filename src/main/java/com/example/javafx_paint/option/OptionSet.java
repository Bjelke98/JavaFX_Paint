package com.example.javafx_paint.option;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class OptionSet extends HBox {
    public OptionSet(Node... nodes){
        getChildren().addAll(nodes);
    }
    public static ObservableList<Node> collect(Node... nodes){
        return FXCollections.observableArrayList(nodes);
    }
}
