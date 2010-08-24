package limelight.ui.events;

import limelight.ui.Panel;

import java.awt.*;

public abstract class MouseEvent extends ModifiableEvent
{
  public static final int BUTTON1_MASK = 1 << 4;
  public static final int BUTTON2_MASK = ModifiableEvent.ALT_MASK;
  public static final int BUTTON3_MASK = ModifiableEvent.COMMAND_MASK;

  private Point location;
  private int clickCount;

  public MouseEvent(Panel source, int modifiers, Point location, int clickCount)
  {
    super(source, modifiers);
    this.location = location;
    this.clickCount = clickCount;
  }

  @Override
  public boolean isInheritable()
  {
    return true;
  }

  public Point getAbsoluteLocation()
  {
    return location;
  }

  public int getClickCount()
  {
    return clickCount;
  }

  public Point getLocation()
  {
    return new Point(location.x - getSource().getX(), location.y - getSource().getY());
  }

  public boolean isButton1()
  {
    return (getModifiers() & BUTTON1_MASK) != 0;
  }

  public boolean isButton2()
  {
    return (getModifiers() & BUTTON2_MASK) != 0;
  }

  public boolean isButton3()
  {
    return (getModifiers() & BUTTON3_MASK) != 0;
  }
}
