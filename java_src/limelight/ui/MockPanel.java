package limelight.ui;

import limelight.ui.Rectangle;
import limelight.ui.Panel;
import limelight.ui.MockProp;

public class MockPanel extends Panel
{
  public Rectangle rectangleInsideMargin;
  public Rectangle rectangleInsidePadding;

  public MockPanel()
	{
		super(new MockProp());
	}

  public Rectangle getRectangleInsideMargins()
  {
    if(rectangleInsideMargin != null)
      return rectangleInsideMargin;
    return super.getRectangleInsideMargins();
  }

  public Rectangle getRectangleInsidePadding()
  {
    if(rectangleInsidePadding != null)
      return rectangleInsidePadding;
    return super.getRectangleInsidePadding();
  }
}
