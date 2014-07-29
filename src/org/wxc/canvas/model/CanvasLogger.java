package org.wxc.canvas.model;

import java.awt.Color;
import java.awt.event.MouseEvent;

import org.wxc.canvas.gui.CanvasListener;

public class CanvasLogger implements CanvasListener{
  CanvasModel model;	
  public CanvasLogger(CanvasModel model) {
    model.addListener(this);
  }
  
  @Override
  public void setBrush(int type) {
    switch(type) {
      case 0: System.out.println("Change the brush to line"); break;
      case 1: System.out.println("Change the brush to curve"); break;
      case 2: System.out.println("Change the brush to rectangle"); break;
      case 3: System.out.println("Change the brush to oval"); break;
      case 4: System.out.println("Change the brush to triangle"); break;
    }
  }
  
  @Override
  public void setColor(Color color) {
	if(color == Color.black) {
      System.out.println("Change the color to black");
	}else if(color == Color.blue) {
	  System.out.println("Change the color to blue");
	}else if(color == Color.red) {
	  System.out.println("Change the color to red");
	}else if(color == Color.green) {
	  System.out.println("Change the color to green");
	}else if(color == Color.yellow) {
	  System.out.println("Change the color to yellow");
	}
  }
  
  @Override
  public void setStroke(float width) {
    System.out.println("Change the line width to " + width);
  }
  
  @Override
  public void clear() {
    System.out.println("Clear the canvas");  
  }
  
  @Override
  public void fireMousePressedEvent(MouseEvent me) {
    System.out.println("Fire mouse pressed event");
  }
  
  @Override
  public void fireMouseReleasedEvent(MouseEvent me) {
    System.out.println("Fire mouse released event"); 
  }
  
  @Override
  public void fireMouseDraggedEvent(MouseEvent me) {
    System.out.println("Fire mouse dragged event");	  
  }
  
  @Override
  public int getBrush() {
    System.out.println("Get the brush");
    return 0;
  }
 
  @Override
  public float getStroke() {
    System.out.println("Get the stroke");
    return 0.0f;
  }
  
  @Override
  public Color getColor() {
    System.out.println("Get the color");
    return null;
  }
  
  @Override 
  public boolean getIsCleared() {
    System.out.println("Get the isCleared");
    return true;
  }
  
}
