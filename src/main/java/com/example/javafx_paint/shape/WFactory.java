package com.example.javafx_paint.shape;

import com.example.javafx_paint.plate.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

// Static
public class WFactory {
    // Statisk fabrikk for å lage diverse figurer
    public static WShape create(MouseEvent e, WShapeList shapeName, Color color, Color stroke, double strokeWidth, Canvas canvas, String text){
        if     (shapeName.equals(WShapeList.Marker))    return new WMarker(e, color, strokeWidth, canvas);
        else if(shapeName.equals(WShapeList.Arc))       return new WArc(e, color, stroke, strokeWidth, canvas);
        else if(shapeName.equals(WShapeList.Circle))    return new WCircle(e, color, stroke, strokeWidth, canvas);
        else if(shapeName.equals(WShapeList.Ellipse))   return new WEllipse(e, color, stroke, strokeWidth, canvas);
        else if(shapeName.equals(WShapeList.Line))      return new WLine(e, color, stroke, strokeWidth, canvas);
        else if(shapeName.equals(WShapeList.Polygon))   return new WPolygon(e, color, stroke, strokeWidth, canvas);
        else if(shapeName.equals(WShapeList.Rectangle)) return new WRectangle(e, color, stroke, strokeWidth, canvas);
        else if(shapeName.equals(WShapeList.Text))      return new WText(e, color, stroke, strokeWidth, canvas, text);
        else return null;
    }
}
