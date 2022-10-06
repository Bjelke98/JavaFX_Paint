package com.example.javafx_paint;

import com.example.javafx_paint.option.OptionSet;
import com.example.javafx_paint.option.RadioOption;
import com.example.javafx_paint.option.StandardFields;
import com.example.javafx_paint.plate.Canvas;
import com.example.javafx_paint.plate.Sidebar;
import com.example.javafx_paint.shape.WFactory;
import com.example.javafx_paint.shape.WShape;
import com.example.javafx_paint.shape.WShapeList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Paint {

    public static final String DEFAULT_ICON_PATH = System.getProperty("user.dir")+"\\images\\icon.png";

    public static final String APPLICATION_NAME = "Paint";
    public static final String OPTION_BAR_NAME = "Options";
    public static final String SELECT_BAR_NAME = "Select";
    public static final int DEFAULT_SCREEN_WIDTH = 800;
    public static final int DEFAULT_SCREEN_HEIGHT = 800;
    public static final int DEFAULT_PANE_OFFSET = 10;

    public static final Color DEFAULT_FILL = Color.TRANSPARENT;
    public static final Color DEFAULT_STROKE = Color.BLACK;
    public static final int DEFAULT_STROKE_WIDTH = 15;
    public static final int DEFAULT_STROKE_WIDTH_MIN = 0;
    public static final int DEFAULT_STROKE_WIDTH_MAX = 100;

    // Setter opp vinduer og paneler
    private final Canvas canvas = new Canvas();
    private final Sidebar option = new Sidebar(OPTION_BAR_NAME);
    private final Sidebar select = new Sidebar(SELECT_BAR_NAME);

    // Henter tegne alternativer
    private final ToggleGroup shapeOptionGroup = new ToggleGroup();
    private final ObservableList<Node> radioOptions = RadioOption.getOptions(shapeOptionGroup);

    // Henter Felles valg for begge sidepanelene
    private final StandardFields standardOption = new StandardFields(false);
    private final StandardFields standardOptionSelect = new StandardFields(true);

    // Knapper for ekstra funksjonalitet
    private final Button wUP = new Button("UP");
    private final Button wDOWN = new Button("DOWN");
    private final Button wTOP = new Button("TOP");
    private final Button wBOTTOM = new Button("BOTTOM");
    private final Button save = new Button("Save");
    private final Button clear = new Button("Clear");

    private final ObservableList<Node> optionCollection = OptionSet.collect(
            standardOption,
            wUP,
            wDOWN,
            wTOP,
            wBOTTOM,
            save,
            clear
    );

    // Holder på nåværende figur og valgte figur
    private static WShape current;
    private static WShape selected;

    public Paint(Stage stage) throws Exception {
        // Vindu oppsett
        Scene scene = new Scene(canvas, DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(APPLICATION_NAME);
        stage.show();
        option.setX(stage.getX()- Sidebar.DEFAULT_WIDTH-DEFAULT_PANE_OFFSET);
        select.setX(stage.getX()+DEFAULT_SCREEN_WIDTH+DEFAULT_PANE_OFFSET);
        stage.getIcons().add(new Image("file:"+DEFAULT_ICON_PATH));
        // Legg til innhold
        option.addAll(radioOptions);
        option.addAll(optionCollection);
        select.add(standardOptionSelect);
        // Events
        //  // Window
        stage.setOnCloseRequest(this::close);
        //  // Canvas
        canvas.setOnMousePressed(this::pressed);
        canvas.setOnMouseDragged(this::dragged);
        canvas.setOnMouseReleased(this::released);
        canvas.setOnScroll(e->{
            if(selected!=null)selected.deltaSize(e);
            standardOptionSelect.updateShapeInfo();
        });
        // // KeyPress
        scene.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.DELETE){
                if(selected!=null)selected.remove();
            } else if(e.getCode() == KeyCode.Z){
                canvas.removeLast();
            } else if(e.getCode() == KeyCode.SPACE){
                selected.newPoint();
            }
        });
        // // Knapp klikk
        wUP.setOnAction(e->canvas.up((Shape)selected));
        wDOWN.setOnAction(e->canvas.down((Shape)selected));
        wTOP.setOnAction(e->canvas.top((Shape)selected));
        wBOTTOM.setOnAction(e->canvas.bottom((Shape)selected));
        save.setOnAction(e->canvas.save());
        clear.setOnAction(e->canvas.clear());
    }

    // Klikk i canvas
    public void pressed(MouseEvent e){
        if(!State.isSelect()){
            current = WFactory.create(e, (WShapeList)shapeOptionGroup.getSelectedToggle().getUserData(), standardOption.getColor(), standardOption.getStroke(), standardOption.getStrokeWidth(), canvas, standardOption.getText());
        }
        if(current!=null){
            standardOptionSelect.updateShapeInfo();
        }
    }
    // Dra i canvas
    public void dragged(MouseEvent e){
        if(!State.isSelect()){
            current.drag(e);
        }
    }
    // Slipp i canvas
    public void released(MouseEvent e){
        if(current!=null){
            standardOptionSelect.updateShapeInfo();
        }
    }
    // Lukker alle vinduer når hovedvindu lukkes
    private void close(WindowEvent e){
        try {
            option.close();
            select.close();
        } catch (Exception err){
            err.printStackTrace();
        }
    }

    // Statiske funksjoner brukt av andre klasser
    public static void setCurrent(WShape shape){
        current = shape;
    }
    public static void setSelected(WShape shape){
        selected = shape;
    }
    public static WShape getSelected(){
        return selected;
    }

}
