package org.wxc.canvas.gui;

import org.wxc.canvas.model.CanvasModel;
import org.wxc.canvas.model.CanvasLogger;

public class CanvasController {

  private void launch() {
     CanvasModel model = new CanvasModel();
     new CanvasView(model);
     new CanvasView(model);
     new CanvasView(model);
     new CanvasView(model);
     new CanvasView(model);
     new CanvasView(model);
     new CanvasView(model);
     new CanvasView(model);
     new CanvasLogger(model);
  }
  
  public static void main(String[] args) {
	new CanvasController().launch();
  }
}
