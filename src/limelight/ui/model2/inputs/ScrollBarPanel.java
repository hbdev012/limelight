package limelight.ui.model2.inputs;

import limelight.styles.Style;
import limelight.ui.model2.BasePanel;
import limelight.ui.model2.PropPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScrollBarPanel extends BasePanel
{
  public static final int VERTICAL = JScrollBar.VERTICAL;
  public static final int HORIZONTAL = JScrollBar.HORIZONTAL;
  private JScrollBar scrollBar;
  private int preferredGirth;
  private boolean configuring;

  public ScrollBarPanel(int orientation)
  {
    scrollBar = new JScrollBar(orientation);
    scrollBar.addAdjustmentListener(new AdjustmentListener()
    {
      public void adjustmentValueChanged(AdjustmentEvent e)
      {
        if(!configuring)
        {
          ((PropPanel)getParent()).markAsChanged();
          markAsChanged();
        }
      }
    });
    if(getOrientation() == VERTICAL)
      preferredGirth = width = scrollBar.getPreferredSize().width;
    else
      preferredGirth = height = scrollBar.getPreferredSize().height;
  }

  public int getOrientation()
  {
    return scrollBar.getOrientation();
  }

  public JScrollBar getScrollBar()
  {
    return scrollBar;
  }

  public limelight.util.Box getChildConsumableArea()
  {
    return getBoundingBox();
  }

  public limelight.util.Box getBoxInsidePadding()
  {
    return getBoundingBox();
  }

  public Style getStyle()
  {
    return getParent().getStyle();
  }

  public void setSize(int width, int height)
  {
    if(getOrientation() == VERTICAL)
      width = preferredGirth;
    else
      height = preferredGirth;
    super.setSize(width, height);
    scrollBar.setSize(width, height);
  }

  public void setHeight(int height)
  {
    setSize(preferredGirth, height);
  }

  public void setWidth(int width)
  {
    setSize(width, preferredGirth);
  }

  public void mousePressed(MouseEvent e)
  {
    for(MouseListener mouseListener : scrollBar.getMouseListeners())
      mouseListener.mousePressed(translatedEvent(e));
  }

  public void mouseReleased(MouseEvent e)
  {
    for(MouseListener mouseListener : scrollBar.getMouseListeners())
      mouseListener.mouseReleased(translatedEvent(e));
  }

  public void mouseClicked(MouseEvent e)
  {
    for(MouseListener mouseListener : scrollBar.getMouseListeners())
      mouseListener.mouseClicked(translatedEvent(e));
  }

  public void mouseDragged(MouseEvent e)
  {
    for(MouseMotionListener mouseListener : scrollBar.getMouseMotionListeners())
      mouseListener.mouseDragged(translatedEvent(e));
  }

  public void paintOn(Graphics2D graphics)
  {
    scrollBar.paint(graphics);
  }

  //TODO MDM This is a bit too optimistic.  Scrollbars maybe covered by floaters so we can't just paint it directly
  public void repaint()
  {
    limelight.util.Box ab = getAbsoluteBounds();
    Graphics2D g = (Graphics2D) getRoot().getGraphics().create(ab.x, ab.y, ab.width, ab.height);
    paintOn(g);
    g.dispose();
  }

  public void setValue(int value)
  {
    scrollBar.setValue(value);
  }

  public int getValue()
  {
    return scrollBar.getValue();
  }

  public int getMaximumValue()
  {
    return scrollBar.getMaximum();
  }

  public int getVisibleAmount()
  {
    return scrollBar.getVisibleAmount();
  }

  public int getUnitIncrement()
  {
    return scrollBar.getUnitIncrement();
  }

  public int getBlockIncrement()
  {
    return scrollBar.getBlockIncrement();
  }

  public void configure(int visible, int maximum)
  {
    configuring = true;
    scrollBar.setMaximum(maximum);
    scrollBar.setVisibleAmount(visible);
    scrollBar.setUnitIncrement((int) (visible * 0.1));
    scrollBar.setBlockIncrement((int) (visible * 0.9));
    configuring = false;
  }
}
