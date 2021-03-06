//- Copyright © 2008-2011 8th Light, Inc. All Rights Reserved.
//- Limelight and all included source files are distributed under terms of the MIT License.

package limelight.ui.model.inputs;

import limelight.Log;
import limelight.events.Event;
import limelight.events.EventAction;
import limelight.styles.Style;
import limelight.ui.PaintablePanel;
import limelight.ui.Painter;
import limelight.ui.events.panel.MousePressedEvent;
import limelight.ui.events.panel.MouseReleasedEvent;
import limelight.ui.events.panel.PanelEvent;
import limelight.ui.images.Images;
import limelight.ui.model.*;
import limelight.ui.ninepatch.NinePatch;
import limelight.util.Box;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonPanel extends AbstractButtonPanel
{
  private String text;
  private boolean isBeingPressed;

  public ButtonPanel()
  {
    isBeingPressed = false;
    getEventHandler().add(MousePressedEvent.class, MousePressedAction.instance);
    getEventHandler().add(MouseReleasedEvent.class, MouseReleasedAction.instance);
  }

  @Override
  protected void setDefaultStyles(Style style)
  {
    style.setDefault(Style.WIDTH, 128);
    style.setDefault(Style.HEIGHT, 27);
    style.setDefault(Style.HORIZONTAL_ALIGNMENT, "center");
    style.setDefault(Style.VERTICAL_ALIGNMENT, "center");
    style.setDefault(Style.FONT_FACE, "Arial");
    style.setDefault(Style.FONT_SIZE, 12);
    style.setDefault(Style.FONT_STYLE, "bold");
    style.setDefault(Style.TEXT_COLOR, "black");
  }

  @Override
  protected Painter getPropPainter(PropPanel prop)
  {
    return BottonPropPainter.instance;
  }

  public void setText(String text)
  {
    if(text.equals(this.text))
      return;

    this.text = text;
    markAsDirty();
    valueChanged();
  }

  public String getText()
  {
    return text;
  }

  private static class MousePressedAction implements EventAction
  {
    public static MousePressedAction instance = new MousePressedAction();

    public void invoke(Event e)
    {
      PanelEvent event = (PanelEvent) e;
      ButtonPanel panel = (ButtonPanel) event.getRecipient();
      panel.isBeingPressed = true;
      panel.markAsDirty();
    }
  }

  private static class MouseReleasedAction implements EventAction
  {
    public static MouseReleasedAction instance = new MouseReleasedAction();

    public void invoke(Event e)
    {
      PanelEvent event = (PanelEvent) e;
      ButtonPanel panel = (ButtonPanel) event.getRecipient();
      panel.isBeingPressed = false;
      panel.markAsDirty();
    }
  }

  // TODO MDM - Change color when space bar is pressed

  public static class BottonPropPainter implements Painter
  {
    public static BottonPropPainter instance = new BottonPropPainter();

    private static Drawable normalPatch;
    private static Drawable selectedPatch;
    private static Drawable focusPatch;

    static
    {
      normalPatch = NinePatch.load(Images.load("button.9.png"));
      selectedPatch = NinePatch.load(Images.load("button_selected.9.png"));
      focusPatch = NinePatch.load(Images.load("button_focus.9.png"));
    }

    public void paint(Graphics2D graphics, PaintablePanel panel)
    {
      final Box bounds = panel.getMarginedBounds();
      if(panel.hasFocus())
        focusPatch.draw(graphics, bounds.x, bounds.y, bounds.width, bounds.height);

      final ButtonPanel button = (ButtonPanel) ((PropPanel) panel).getTextAccessor(); // TODO MDM Yuk!
      if(button.isBeingPressed)
        selectedPatch.draw(graphics, bounds.x, bounds.y, bounds.width, bounds.height);
      else
        normalPatch.draw(graphics, bounds.x, bounds.y, bounds.width, bounds.height);
    }
  }
}
