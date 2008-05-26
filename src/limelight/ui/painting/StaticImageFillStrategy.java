//- Copyright 2008 8th Light, Inc.
//- Limelight and all included source files are distributed under terms of the GNU LGPL.

package limelight.ui.painting;

import java.awt.*;

public class StaticImageFillStrategy implements ImageFillStrategy
{
	public void fill(Graphics2D graphics, Image image)
	{
		limelight.ui.Rectangle area = new limelight.ui.Rectangle(graphics.getClipBounds());
		Graphics innerGraphics = graphics.create(area.x, area.y, area.width, area.height);
		innerGraphics.drawImage(image, 0, 0, null);
	}
}
