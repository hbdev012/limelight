package limelight.ui.model.inputs;

import limelight.styles.Style;
import limelight.util.Box;

import java.awt.*;

public interface TextContainer
{
  Style getStyle();

  Point getAbsoluteLocation();

  int getWidth();

  int getHeight();

  Box getBoundingBox();

  boolean hasFocus();

  int getLastKeyPressed();

  void setLastKeyPressed(int keyCode);
}