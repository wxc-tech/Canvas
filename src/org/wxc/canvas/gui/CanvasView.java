package org.wxc.canvas.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Label;
import java.awt.BasicStroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import org.wxc.canvas.model.CanvasModel;

/**
 * This class is the listener which implements the functions of the canvas app including
 * choose line width, choose line color, choose brush type(line,curve,triangle,oval,rectangle).
 * And when the user made an operation on this canvas, it will ask the model 
 * to inform other listeners to do the same operations.
 * @author Xiaocong Wang
 *
 */
public class CanvasView implements CanvasListener, ActionListener, ItemListener{
  private JButton line, curve, triangle, rectangle, oval;
  private JButton clear;
  private Choice color, size; 
  private CanvasModel model;
  private SimpleCanvas canvas;
  private JFrame frame;
  private JPanel canvasPanel;
  private JPanel controlPanel;
  private boolean isCleared = false;
	
  /**
   * The constructor: initialize the canvas
   * @param model
   */
  public CanvasView(CanvasModel model) {
    this.model = model;
    frame = new JFrame("Canvas");
    frame.setBounds(500, 500, 800, 500);
    frame.setLayout(new BorderLayout());
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent me) {
        System.exit(0);
      }
    });
     
    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());
    line = addButton(controlPanel, "Line");
    curve = addButton(controlPanel, "Curve");
    triangle = addButton(controlPanel, "Triangle");
    oval = addButton(controlPanel, "Oval");
    rectangle = addButton(controlPanel, "Rectangle");
    clear = addButton(controlPanel, "Clear");
   
    color = addChoice(controlPanel, "Color");
    color.add("Black");
    color.add("Blue");
    color.add("Red");
    color.add("Yellow");
    color.add("Green");
    color.select(0);
    size = addChoice(controlPanel, "Size");
    size.add("1");
    size.add("2");
    size.add("3");
    size.add("4");
    size.add("5");
    size.select(0);
    
    frame.add(controlPanel, BorderLayout.NORTH);
    
    canvasPanel = new JPanel();
    canvas = new SimpleCanvas();
    canvasPanel.add(canvas);
    canvasPanel.setBounds(0, 70, 800, 430);
    canvas.setBounds(0,0,canvasPanel.getWidth(),canvasPanel.getHeight());
    frame.add(canvasPanel, BorderLayout.CENTER);
   
    model.addListener(this);
    frame.setVisible(true);
  }
  
  /**
   * This constructor is just used to code test
   * @param model
   */
  public CanvasView(CanvasModel model, boolean visible) {
    this(model);
    frame.setVisible(visible);
  }
  
  /**
   * Add the control buttons to the canvas
   * @param panel
   * @param name
   * @return
   */
  private JButton addButton(JPanel panel, String name) {
    JButton button = new JButton(name);
    button.addActionListener(this);
    panel.add(button);
    return button;
  }
  
  /**
   * Add the choice buttons to the canvas
   * @param panel
   * @param name
   * @return
   */
  private Choice addChoice(JPanel panel, String name) {
    Choice choice = new Choice();
    choice.addItemListener(this);
    panel.add(new Label(name));
    panel.add(choice);
    return choice;
  }
  
  /**
   * The listener for choice component event
   */
  @Override
  public void itemStateChanged(ItemEvent ie) {
    Object source = ie.getSource();
    if(source == color) {
      switch(color.getSelectedIndex()) {
      case 0: model.chooseColor(Color.black); break;
      case 1: model.chooseColor(Color.blue); break;
      case 2: model.chooseColor(Color.red); break;
      case 3: model.chooseColor(Color.yellow); break;
      case 4: model.chooseColor(Color.green); break;
      }
    }else if(source == size) {
      switch(size.getSelectedIndex()) {
      case 0: model.chooseStroke(1.0f); break;
      case 1: model.chooseStroke(2.0f); break;
      case 2: model.chooseStroke(3.0f); break;
      case 3: model.chooseStroke(4.0f); break;
      case 4: model.chooseStroke(5.0f); break;
      }
    }else {
      throw new RuntimeException("Wrong source");
    }
  }
  
  /**
   * The listener for button component event
   */
  @Override
  public void actionPerformed(ActionEvent event) {
	Object source = event.getSource();
	int tmp = 0;
    if(source == line) {
      tmp = 0;
    }else if(source == curve) {
      tmp = 1;
    }else if(source == rectangle) {
      tmp = 2;
    }else if(source == oval) {
      tmp = 3;
    }else if(source == triangle) {
      tmp = 4;
    }else if(source == clear) {
      model.clearBoard();
      return;
    }else {
      throw new RuntimeException("Wrong source");
    }
    model.chooseBrush(tmp);
  }
  
  /**
   * To clear the canvas
   */
  @Override
  public void clear() {
    Graphics g = canvas.getGraphics();
    int width = (int) canvas.getWidth();
    int height = (int) canvas.getHeight();
    g.clearRect(0, 0, width, height);
    isCleared = true;
  }
  
  /**
   * To set the brush type
   */
  @Override
  public void setBrush(int type) {
    canvas.setBrush(type);
  }
  
  /**
   * To set the color
   */
  @Override
  public void setColor(Color lineColor) {
    canvas.setColor(lineColor);
  }
  
  /**
   * To set the line width
   */
  @Override
  public void setStroke(float width) {
    canvas.setStroke(width);
  }
  
  /**
   * To fire the mouse pressed event handler
   */
  @Override
  public void fireMousePressedEvent(MouseEvent me) {
	setIsCleared();
    canvas.mousePressedEventHandler(me);
  }
  
  /**
   * To fire the mouse released event handler
   */
  @Override
  public void fireMouseReleasedEvent(MouseEvent me) {
	setIsCleared();
    canvas.mouseReleasedEventHandler(me);
  }
  
  /**
   * To fire the mouse dragged event handler
   */
  @Override
  public void fireMouseDraggedEvent(MouseEvent me) {
	setIsCleared();
    canvas.mouseDraggedEventHandler(me);
  }
  
  /**
   * Set the isCleared to false if it is true
   */
  public void setIsCleared() {
    if(isCleared) {
      isCleared = false;
    }
  }
  
  /**
   * To get the brush type
   */
  @Override
  public int getBrush() {
    return canvas.getBrush();
  }
  
  /**
   * To get the color
   */
  @Override
  public Color getColor() {
    return canvas.getColor();
  }
  
  /**
   * To get the stroke
   */
  @Override
  public float getStroke() {
    return canvas.getStroke();
  }
  
  /**
   * Return true if the board is cleared
   */
   public boolean getIsCleared() {
     return isCleared;
   }
   
   /**
    * toString()
    */
   @Override
   public String toString() {
     return "isCleared:" + isCleared
       + "&" + canvas.toString() + "&";
   }
   
   /**
    * equals()
    */
   @Override
   public boolean equals(Object obj) {
     if(obj instanceof CanvasView) {
       CanvasView cv = (CanvasView) obj;
       return this.toString().equals(cv.toString());
     }else {
       return false;
     }
   }
   
   /**
    * hashCode()
    */
   @Override
   public int hashCode() {
     return toString().hashCode();   
   }
  
  /**
   * This inner class implements the canvas function. The user can paint
   * image on the canvas by the mouse.
   * @author Xiaocong Wang
   *
   */
  public class SimpleCanvas extends Canvas {
	private Point[] dots = new Point[10000];
	private boolean pressed = false;
	private int startX, startY, endX, endY;
	private int index = 0;
	private int width = 0;
	private int height = 0;
	private int type = 1;
	private Color lineColor = Color.black;
	private BasicStroke stroke = new BasicStroke(1.0f);
	private MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent me) {
          model.paint(0, me);
        }
        @Override
        public void mouseReleased(MouseEvent me) {
          model.paint(1, me);	
        }
    };
    
    private MouseMotionAdapter mma = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent me) {
          model.paint(2, me); 
        }
      };
      
    public void mousePressedEventHandler(MouseEvent me) {
      if(!pressed) {
        startX = me.getX();
        startY = me.getY();
        pressed = true;
      }
    }
    
    public void mouseReleasedEventHandler(MouseEvent me) {
      if(pressed) {
      	if(type == 1) {
      	  index = 0;
      	}else {
          endX = me.getX();
          endY = me.getY();
          if(type == 2 || type == 3) {
            width = Math.abs(startX - endX);
            height = Math.abs(startY - endY);
            if(endX<=startX) {
              startX = endX;
            }
            if(endY<=startY) {
              startY = endY;
            }
          }
       }
        pressed = false;
        repaint();
      }    	
    }
    
    public void mouseDraggedEventHandler(MouseEvent me) {
      if(type == 1) {
        dots[index] = new Point(me.getX(), me.getY());
        index ++;
        repaint();
      }  
    }
	  
    public SimpleCanvas() {
      setBackground(Color.white);
      addMouseListener(ma);
      addMouseMotionListener(mma);
    }
    
    public void setColor(Color lineColor) {
      this.lineColor = lineColor;
    }
    
    public void setBrush(int type) {
      this.type = type;
    }
    
    public void setStroke(float width) {
      this.stroke = new BasicStroke(width);
    }
    
    public void paint(Graphics g) {
      paintUpdate(g);
    }
    
    public void update(Graphics g) {
      paintUpdate(g);
    }
    
    public int getBrush() {
      return type;
    }
    
    public Color getColor() {
      return lineColor;
    }
    
    public float getStroke() {
      return stroke.getLineWidth();
    }
    
    @Override
    public String toString() {
      return "brush:" + type + "&" + "color:"
          + lineColor + "&" + "stroke:" + stroke.getLineWidth(); 
    }
    
    public void paintUpdate(Graphics gra) {
      Graphics2D g = (Graphics2D)gra;
      g.setColor(lineColor);
      g.setStroke(stroke);
      switch(type) {
        case 0: {
          g.draw(new Line2D.Double(startX, startY, endX, endY));
          break;
        }
        case 1: {
          for(int i=0; i<index-1; i++) {
            g.draw(new Line2D.Double(dots[i], dots[i+1]));
          }
          break;
        }
        case 2: {
          g.draw(new Rectangle2D.Double(startX, startY, width, height));
          break;
        }
        case 3: {
          g.draw(new Ellipse2D.Double(startX, startY, width, height));
          break;
        }
        case 4: {
          g.drawPolygon(new int[]{startX, startX, endX}, new int[] {startY, endY, startY}, 3);
          break;
        }
      }
    }
  }
}
