package org.wxc.canvas.model;

import java.util.List;
import java.util.Iterator;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.concurrent.CopyOnWriteArrayList;
import org.wxc.canvas.gui.CanvasListener;

/**
 * This class is the canvas model, every canvas app will be stored in the model.
 * And for every listener, when it made a paint move, the model will display the
 * effect on other listeners in the meanwhile log the paint move info.
 * @author Xiaocong Wang
 *
 */
public class CanvasModel {

  private List<CanvasListener> listeners = 
      new CopyOnWriteArrayList<CanvasListener>();
  
  public CanvasModel() {  
  }
  
  /**
   * Invoked by the listener to change the brush type of all listeners
   * @param type
   */
  public void chooseBrush(int type) {
    fireBrushSetEvent(type);
  }
  
  /**
   * Inform listeners to change the brush type
   * @param type
   */
  private void fireBrushSetEvent(int type) {
    for (CanvasListener listener : listeners) {
      listener.setBrush(type);
    }
  }
  
  /**
   * Invoked by the listener to clear the canvas of all listeners
   */
  public void clearBoard() {
    fireClearBoardEvent();
  }
  
  /**
   * Inform listeners to clear the canvas
   */
  private void fireClearBoardEvent() {
    for (CanvasListener listener : listeners) {
      listener.clear();
    }
  }
  
  /**
   * Invoked by the listener to change the color of all listeners
   * @param color
   */
  public void chooseColor(Color color) {
    fireColorSetEvent(color);
  }
  
  /**
   * Inform listeners to change the color
   * @param color
   */
  private void fireColorSetEvent(Color color) {
    for (CanvasListener listener : listeners) {
      listener.setColor(color);
    }
  }
  
  /**
   * Invoked by the listener to change the line width of all listeners
   * @param width
   */
  public void chooseStroke(float width) {
	fireStrokeSetEvent(width);
  }
  
  /**
   * Inform listeners to change the line width
   * @param width
   */
  private void fireStrokeSetEvent(float width) {
    for(CanvasListener listener : listeners) {
      listener.setStroke(width);
    }
  }
  
  /**
   * Invoked by the listener to paint on the canvas of every listener
   * @param sort
   * @param me
   */
  public void paint(int sort, MouseEvent me) {
    switch(sort) {
      case 0: {
        fireMousePressedEvent(me); 
        break;
      }
      case 1: {
        fireMouseReleasedEvent(me); 
        break;
      }
      case 2: {
        fireMouseDraggedEvent(me); 
        break;
      }
      default: {
        throw new RuntimeException("Wrong event");
      }
    }
  }
  
  /**
   * Inform listeners to fire the mouse pressed event handler
   * @param me
   */
  private void fireMousePressedEvent(MouseEvent me) {
    for(CanvasListener listener : listeners) {
      listener.fireMousePressedEvent(me);
    }
  }
  
  /**
   * Inform listeners to fire the mouse released event handler
   * @param me
   */
  private void fireMouseReleasedEvent(MouseEvent me) {
    for(CanvasListener listener : listeners) {
      listener.fireMouseReleasedEvent(me);
    }
  }
  
  /**
   * Inform listeners to fire the mouse dragged event handler
   * @param me
   */
  private void fireMouseDraggedEvent(MouseEvent me) {
    for(CanvasListener listener : listeners) {
      listener.fireMouseDraggedEvent(me);
    }
  }
  
  /**
   * Add the listener in the list of the model
   * @param listener
   */
  public void addListener(CanvasListener listener) {
    listeners.add(listener);
  }
  
  @Override
  public String toString() {
    return listeners.toString();
  }
  
  @Override
  public int hashCode() {
    return listeners.hashCode();
  }
  
  @Override
  public boolean equals(Object obj) {
    if(obj instanceof CanvasModel) {
      CanvasModel cm = (CanvasModel) obj;
      return this.listeners.equals(cm.getListeners());
    }else {
      return false;
    }
  }
  
  /**
   * Just for test
   */
  public List<CanvasListener> getListeners() {
    return listeners;
  }
}
