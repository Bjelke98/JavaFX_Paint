package com.example.javafx_paint.option;

import com.example.javafx_paint.State;
import com.example.javafx_paint.shape.WShapeList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;

public class RadioOption extends RadioButton {
    public RadioOption(String name, ToggleGroup tg){
        super(name);
        setToggleGroup(tg);
    }
    public RadioOption(String name, ToggleGroup tg, Boolean selected){
        this(name, tg);
        setSelected(selected);
    }
    public static ObservableList<Node> getOptions(ToggleGroup tg){
        ArrayList<Node> nodes = new ArrayList<>();
        for(WShapeList v : WShapeList.values()){
            if(v.toString().equals("Select")){
                RadioOption ro = new RadioOption(v.toString(), tg);
                ro.setOnAction(e-> State.setSelect(true));
                ro.setUserData(v);
                nodes.add(ro);
            } else if(v.toString().equals("Rectangle")) {
                RadioOption ro = new RadioOption(v.toString(), tg, true);
                ro.setOnAction(e->State.setSelect(false));
                ro.setUserData(v);
                nodes.add(ro);
            } else {
                RadioOption ro = new RadioOption(v.toString(), tg);
                ro.setOnAction(e->State.setSelect(false));
                ro.setUserData(v);
                nodes.add(ro);
            }
        }
        return FXCollections.observableArrayList(nodes);
    }
}
