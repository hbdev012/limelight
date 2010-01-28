package limelight.ui.model.inputs.keyProcessors;

import limelight.ui.model.inputs.KeyProcessor;
import limelight.ui.model.inputs.TextModel;

public class KPAlt extends KeyProcessor
{
  public KPAlt(TextModel boxInfo)
  {
    super(boxInfo);
  }

  public void processKey(int keyCode)
  {
    if(isMoveRightEvent(keyCode)){
      boxInfo.setCursorIndex(findNearestWordToTheRight());
    }
    else if(isMoveLeftEvent(keyCode)){
      boxInfo.setCursorIndex(findNearestWordToTheLeft());
    }
  }

}
