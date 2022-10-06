package com.example.javafx_paint.plate;

import com.example.javafx_paint.Paint;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klasse for degnebrettet
 */

public class Canvas extends Pane implements Plate {
    private static final Rectangle item2 = new Rectangle(0, 0, 0, 0);
    @Override
    public void add(Node node) {
        getChildren().add(node);
    }

    @Override
    public void addAll(Node... nodes) {
        getChildren().addAll(nodes);
    }

    @Override
    public void addAll(ObservableList<Node> nodes) {
        getChildren().addAll(nodes);
    }

    @Override
    public void clear() {
        getChildren().clear();
    }

    public void remove(Node o){
        getChildren().remove(o);
        Paint.setSelected(null);
    }

    public void remove(Node o, boolean keepSelect){
        if(keepSelect){
            remove(o);
        } else {
            getChildren().remove(o);
        }
    }

    public void removeLast(){
        if(getChildren().size()>0){
            getChildren().remove(getChildren().size()-1);
        }
    }
    // Flytter figur
    public void up(Node o){
        if(o!=null){
            int index = getChildren().indexOf(o);
            if(index<getChildren().size()-1){
                Node item1 = getChildren().get(index+1);
                getChildren().set(index, item2);
                getChildren().set(index+1, o);
                getChildren().set(index, item1);
            }
        }
    }
    public void down(Node o){
        if(o!=null) {
            int index = getChildren().indexOf(o);
            if (index > 0) {
                Node item1 = getChildren().get(index - 1);
                getChildren().set(index, item2);
                getChildren().set(index - 1, o);
                getChildren().set(index, item1);
            }
        }
    }
    public void top(Node o){
        if(o!=null) {
            remove(o, false);
            add(o);
        }
    }
    public void bottom(Node o){
        if(o!=null) {
            remove(o, false);
            getChildren().add(0, o);
        }
    }
    // Lagrer bildet i mappen images
    public void save(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MMddyy_HHmmss");
        String filename = time.format(timeFormat);
        WritableImage image = this.snapshot(new SnapshotParameters(), null);
        File file = new File(System.getProperty("user.dir")+"\\images\\"+filename+".png");
//        try {
//            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("Save not supported for this JavaFX version.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
