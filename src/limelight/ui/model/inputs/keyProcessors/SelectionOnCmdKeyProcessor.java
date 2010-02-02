package limelight.ui.model.inputs.keyProcessors;

import limelight.ui.model.inputs.KeyProcessor;
import limelight.ui.model.inputs.TextModel;

import java.awt.event.KeyEvent;

public class SelectionOnCmdKeyProcessor extends KeyProcessor
{
  public SelectionOnCmdKeyProcessor(TextModel boxInfo)
  {
    super(boxInfo);
  }

  public void processKey(KeyEvent event)
  {
    switch (event.getKeyCode())
    {
      case KeyEvent.VK_A:
        selectAll();
        break;
      case KeyEvent.VK_V:
        boxInfo.deleteSelection();
        boxInfo.pasteClipboard();
        boxInfo.selectionOn = false;
        break;
      case KeyEvent.VK_C:
        boxInfo.copySelection();
        break;
      case KeyEvent.VK_X:
        boxInfo.copySelection();
        boxInfo.deleteSelection();
        boxInfo.selectionOn = false;
        break;
      case KeyEvent.VK_RIGHT:
        boxInfo.setCursorIndex(boxInfo.text.length());
        boxInfo.selectionOn = false;
        break;
      case KeyEvent.VK_LEFT:
        boxInfo.setCursorIndex(0);
        boxInfo.selectionOn = false;
        break;
    }
  }

}