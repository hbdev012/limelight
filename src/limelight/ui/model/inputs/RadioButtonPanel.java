package limelight.ui.model.inputs;

import java.awt.*;

public class RadioButtonPanel extends InputPanel
{
  private RadioButton radioButton;

  public RadioButtonPanel()
  {
    super();
    setSize(radioButton.getPreferredSize().width, radioButton.getPreferredSize().height);
  }

  protected Component createComponent()
  {
    return radioButton = new RadioButton(this);
  }

  public RadioButton getRadioButton()
  {
    return radioButton;
  }

  public boolean canBeBuffered()
  {
    return false;
  }
}
