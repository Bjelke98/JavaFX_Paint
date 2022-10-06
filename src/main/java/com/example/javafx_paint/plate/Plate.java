package com.example.javafx_paint.plate;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public interface Plate {
    void add(Node node);
    void addAll(Node... nodes);
    void addAll(ObservableList<Node> nodes);
    void clear();
}
