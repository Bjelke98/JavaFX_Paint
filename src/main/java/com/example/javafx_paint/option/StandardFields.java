package com.example.javafx_paint.option;

import com.example.javafx_paint.Paint;
import com.example.javafx_paint.shape.WShape;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class StandardFields extends VBox {

    private boolean enableSelected;

    private final Label textLabel = new Label("Text: ");
    private final HBox textBox = new HBox();
    private final TextField text = new TextField();
    private final Button textButton = new Button("SET");

    private final Label fillLabel = new Label("Fill: ");
    private final ColorPicker fill = new ColorPicker(Paint.DEFAULT_FILL);

    private final Label strokeLabel = new Label("Stroke: ");
    private final ColorPicker stroke = new ColorPicker(Paint.DEFAULT_STROKE);

    private final Label strokeWidthLabel = new Label("Stroke Width: ");
    private final HBox strokeWidthBox = new HBox();
    private final StrokeWidthField strokeWidthValue = new StrokeWidthField();
    private final Button confirmStrokeWidthValue = new Button("SET");
    private final StrokeSlider strokeWidth = new StrokeSlider(Paint.DEFAULT_STROKE_WIDTH_MIN, Paint.DEFAULT_STROKE_WIDTH_MAX, Paint.DEFAULT_STROKE_WIDTH);

    private final Label rotateLabel = new Label("Rotate: ");
    private final Label rotateDeg = new Label("Deg: 0");
    private final RotateSlider rotate = new RotateSlider();

    private final Label arcSizeLabel = new Label("ArcSize: ");
    private final Label arcSizeDeg = new Label("Size: 0");
    private final ArcSlider arcSize = new ArcSlider();

    private final Label arcStartLabel = new Label("ArcStart: ");
    private final Label arcStartDeg = new Label("Size: 0");
    private final ArcSlider arcStart = new ArcSlider();

    private final Label shapeTextLabel = new Label("Shape info: ");
    private final TextArea shapeText = new TextArea();

    private final Label tutorialTextLabel = new Label("Tutorial: ");
    private final TextArea tutorialText = new TextArea();

    public StandardFields(boolean enableSelected){
        this.enableSelected = enableSelected;
        textBox.getChildren().add(text);
        if(enableSelected){
            textBox.getChildren().add(textButton);
        }

        strokeWidthBox.getChildren().addAll(strokeWidthValue, confirmStrokeWidthValue);
        getChildren().addAll(textLabel, textBox, fillLabel, fill, strokeLabel, stroke, strokeWidthLabel, strokeWidthBox, strokeWidth);
        confirmStrokeWidthValue.setOnAction(e->manualStrokeWidth());
        strokeWidthValue.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.ENTER){
                manualStrokeWidth();
            }
        });
        strokeWidth.setOnMouseClicked(e->strokeWidthDrag());
        strokeWidth.setOnMouseDragged(e->strokeWidthDrag());
        if(enableSelected){
            getChildren().addAll(rotateLabel, rotateDeg, rotate);
            getChildren().addAll(arcSizeLabel, arcSizeDeg, arcSize);
            getChildren().addAll(arcStartLabel, arcStartDeg, arcStart);
            fill.setOnAction(e->setColor());
            stroke.setOnAction(e->setStroke());
            rotate.setOnMouseClicked(e->rotateDrag());
            rotate.setOnMouseDragged(e->rotateDrag());
            arcSize.setOnMouseClicked(e->arcSizeDrag());
            arcSize.setOnMouseDragged(e->arcSizeDrag());
            arcStart.setOnMouseClicked(e->arcStartDrag());
            arcStart.setOnMouseDragged(e->arcStartDrag());
            textButton.setOnAction(e->setText());
            text.setOnKeyPressed(e->{
                if(e.getCode()== KeyCode.ENTER){
                    setText();
                }
            });
            shapeText.setEditable(false);
            tutorialText.setEditable(false);
            tutorialText.setText("""
                    Controls:
                    Key: Z = Delete from top
                    Key: DELETE = Delete selected /
                    \tlast clicked (Option: Select)
                    Action: Scroll = Scale shape
                    Action: Click+Drag = Make and adjust shape
                    Polygon: When creating a polygon
                    \tuse SPACE to set a new point.
                    Note: Some rotated shapes can not be moved.
                    \tSome shapes can't be scaled
                    Tip: A shape is automaticly
                    \tselected after creating it
                    """);
            getChildren().addAll(shapeTextLabel, shapeText, tutorialTextLabel, tutorialText);
        }
    }

    public String getText(){
        return text.getText();
    }
    public void setText(){
        Paint.getSelected().setText(getText());
    }

    private void rotateDrag(){
        Shape shape = (Shape) Paint.getSelected();
        shape.setRotate(rotate.getValue());
        rotateDeg.setText("Deg: "+Math.round(rotate.getValue()*100.0)/100.0);
    }

    private void arcSizeDrag(){
        WShape shape = Paint.getSelected();
        shape.setLengthW(arcSize.getValue());
        arcSizeDeg.setText("Size: "+Math.round(arcSize.getValue()*100.0)/100.0);
    }

    private void arcStartDrag(){
        WShape shape = Paint.getSelected();
        shape.setStartAngleW(arcStart.getValue());
        arcStartDeg.setText("Start: "+Math.round(arcStart.getValue()*100.0)/100.0);
    }

    private void strokeWidthDrag(){
        strokeWidthValue.setValue(strokeWidth.getValue());
        if(enableSelected){
            setStrokeWidth();
        }
    }

    private void manualStrokeWidth(){
        strokeWidth.adjustValue(strokeWidthValue.getValue());
        if(enableSelected){
            setStrokeWidth();
        }
    }

    public Color getColor(){
        return fill.getValue();
    }
    public Color getStroke(){
        return stroke.getValue();
    }
    public double getStrokeWidth(){
        return strokeWidth.getValue();
    }

    public void setColor(){
        Shape shape = (Shape) Paint.getSelected();
        shape.setFill(getColor());
        updateShapeInfo();
    }
    public void setStroke(){
        Shape shape = (Shape) Paint.getSelected();
        shape.setStroke(getStroke());
        updateShapeInfo();
    }
    public void setStrokeWidth(){
        Shape shape = (Shape) Paint.getSelected();
        shape.setStrokeWidth(getStrokeWidth());
        updateShapeInfo();
    }
    public void setSelected(){
        Shape shape = (Shape) Paint.getSelected();
        strokeWidthValue.setText(""+shape.getStrokeWidth());
        strokeWidth.adjustValue(shape.getStrokeWidth());
        fill.setValue(Color.valueOf(shape.getFill().toString()));
        stroke.setValue(Color.valueOf(shape.getStroke().toString()));
    }
    public void setColors(javafx.scene.paint.Paint color, javafx.scene.paint.Paint stroke){
        this.fill.setValue(Color.valueOf(color.toString()));
        this.stroke.setValue(Color.valueOf(stroke.toString()));
    }
    public void updateShapeInfo(){
        // Get and show info
        WShape current = Paint.getSelected();
        if(current!=null){
            String sb = "Type: " + current + "\n";
            sb+=(current.iGetArea()!=0)?"Area: " + r2(current.iGetArea()) + "\n":"";
            sb+=(current.iGetCirc()!=0)?"Circumference: " + r2(current.iGetCirc()) + "\n":"";
            sb+=(current.iGetLineWidth()!=0)?"LineWidth: " + r2(current.iGetLineWidth()) + "\n":"";
            sb+="X: " + r2(current.iGetX()) + "\n";
            sb+="Y: " + r2(current.iGetY()) + "\n";
            sb+=(current.iGetWidth()!=0)?"Width: " + r2(current.iGetWidth()) + "\n":"";
            sb+=(current.iGetHeight()!=0)?"Height: " + r2(current.iGetHeight()) + "\n":"";
            sb+="Color: " + Color.valueOf(current.iGetColor().toString()) + "\n";
            sb+="Stroke: " + Color.valueOf(current.iGetStroke().toString()) + "\n";
            sb+=(current.iGetStrokeWidth()!=0)?"Stroke Width: " + r2(current.iGetStrokeWidth()):"";
            shapeText.setText(sb);
            setColors(current.iGetColor(), current.iGetStroke());
        }
    }
    private double r2(double n){
        return Math.round(n*100.0)/100.0;
    }
}
