package org.wxc.canvas.test;

import org.wxc.canvas.model.CanvasModel;
import org.wxc.canvas.gui.CanvasView;
import org.wxc.canvas.gui.CanvasListener;

import java.awt.Color;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CanvasTest {
  private CanvasModel cm;
  
  @Before
  public void setUp() {
    cm = new CanvasModel();
    new CanvasView(cm, false);
    new CanvasView(cm, false);
    new CanvasView(cm, false);
  }
  
  @Test
  public void testChooseBrush() {
    cm.chooseBrush(1);
    List<CanvasListener> clList = cm.getListeners();
    for(CanvasListener cl : clList) {
      assertEquals(1, cl.getBrush());
    } 
  }
  
  @Test
  public void testClearBoard() {
    cm.clearBoard();
    List<CanvasListener> clList = cm.getListeners();
    for(CanvasListener cl : clList) {
      assertTrue(cl.getIsCleared());
    }
  }
  
  @Test
  public void testChooseColor() {
    cm.chooseColor(Color.red);
    List<CanvasListener> clList = cm.getListeners();
	for(CanvasListener cl : clList) {
      assertEquals(Color.red, cl.getColor());
	}  	  
  }
  
  @Test
  public void testChooseStroke() {
    cm.chooseStroke(2.0f);
	List<CanvasListener> clList = cm.getListeners();
	for(CanvasListener cl : clList) {
	  assertEquals(2.0f, cl.getStroke(), 0.0f);
	}  	  
  }
  
  @Test
  public void testAddListener() {
	CanvasListener cl = new CanvasView(cm);
	List<CanvasListener> listeners = cm.getListeners();
	assertTrue(cl==listeners.get(listeners.size() - 1));
  }
  
  @Test
  public void testToString() {
    CanvasModel tmp = new CanvasModel();
    new CanvasView(tmp);
    new CanvasView(tmp);
    new CanvasView(tmp);
    cm.chooseBrush(1);
    cm.chooseColor(Color.red);
    cm.chooseStroke(1.0f);
    cm.clearBoard();
    tmp.chooseBrush(1);
    tmp.chooseColor(Color.red);
    tmp.chooseStroke(1.0f);
    tmp.clearBoard();
    assertTrue(cm.toString().equals(tmp.toString()));
  }
  
  @Test
  public void testHashCode() {
    CanvasModel tmp = new CanvasModel();
	new CanvasView(tmp);
	new CanvasView(tmp);
	new CanvasView(tmp);
	cm.chooseBrush(1);
	cm.chooseColor(Color.red);
	cm.chooseStroke(1.0f);
	cm.clearBoard();
	tmp.chooseBrush(1);
	tmp.chooseColor(Color.red);
	tmp.chooseStroke(1.0f);
	tmp.clearBoard();
	assertTrue(cm.hashCode()==tmp.hashCode()); 
  }
  
  @Test
  public void testEquals() {
    CanvasModel tmp = new CanvasModel();
	new CanvasView(tmp);
	new CanvasView(tmp);
	new CanvasView(tmp);
	cm.chooseBrush(1);
	cm.chooseColor(Color.red);
	cm.chooseStroke(1.0f);
	cm.clearBoard();
	tmp.chooseBrush(1);
	tmp.chooseColor(Color.red);
	tmp.chooseStroke(1.0f);
	tmp.clearBoard();
	assertTrue(cm.equals(tmp)); 
  }
  
}
