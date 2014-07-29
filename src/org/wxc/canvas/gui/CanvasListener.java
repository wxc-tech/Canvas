package org.wxc.canvas.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;

public interface CanvasListener {
  public void setBrush(int type);
  public void setColor(Color color);
  public void setStroke(float width);
  public void clear();
  public void fireMousePressedEvent(MouseEvent me);
  public void fireMouseReleasedEvent(MouseEvent me);
  public void fireMouseDraggedEvent(MouseEvent me);
  public int getBrush();
  public Color getColor();
  public float getStroke();
  public boolean getIsCleared();
}
