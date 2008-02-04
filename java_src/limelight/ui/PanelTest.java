package limelight.ui;

import junit.framework.TestCase;
import limelight.LimelightError;

import java.awt.*;

public class PanelTest extends TestCase
{
  private TestablePanel panel;
  private MockPanel parent;
  private MockPanel child;
  private Panel sibling;
  private MockPanel grandChild;

  private class TestablePanel extends Panel
  {

    public void paint(Rectangle clip)
    {
    }

    public void snapToSize()
    {
    }
  }

  public void setUp() throws Exception
  {
    panel = new TestablePanel();
  }

  public void testPanelHasDefaultSize() throws Exception
  {
    assertEquals(50, panel.getHeight());
    assertEquals(50, panel.getWidth());
  }

  public void testLocationDefaults() throws Exception
  {
    assertEquals(0, panel.getX());
    assertEquals(0, panel.getY());
  }

  public void testCanSetSize() throws Exception
  {
    panel.setSize(100, 200);
    assertEquals(100, panel.getWidth());
    assertEquals(200, panel.getHeight());

    panel.setWidth(300);
    assertEquals(300, panel.getWidth());

    panel.setHeight(400);
    assertEquals(400, panel.getHeight());
  }

  public void testCanSetLocation() throws Exception
  {
    panel.setLocation(123, 456);
    assertEquals(123, panel.getX());
    assertEquals(456, panel.getY());

    panel.setX(234);
    assertEquals(234, panel.getX());

    panel.setY(567);
    assertEquals(567, panel.getY());
  }

  public void testContainsPoint() throws Exception
  {
    panel.setLocation(100, 200);
    panel.setSize(300, 400);

    assertFalse(panel.containsPoint(new Point(0, 0)));
    assertFalse(panel.containsPoint(new Point(1000, 1000)));
    assertFalse(panel.containsPoint(new Point(99, 400)));
    assertFalse(panel.containsPoint(new Point(400, 400)));
    assertFalse(panel.containsPoint(new Point(200, 199)));
    assertFalse(panel.containsPoint(new Point(200, 600)));

    assertTrue(panel.containsPoint(new Point(200, 400)));
    assertTrue(panel.containsPoint(new Point(100, 400)));
    assertTrue(panel.containsPoint(new Point(399, 400)));
    assertTrue(panel.containsPoint(new Point(200, 200)));
    assertTrue(panel.containsPoint(new Point(200, 599)));
  }

  public void testIsAncestor() throws Exception
  {
    createFamilyTree();

    assertTrue(child.isAncestor(parent));
    assertTrue(sibling.isAncestor(parent));
    assertTrue(grandChild.isAncestor(parent));
    assertTrue(grandChild.isAncestor(child));

    assertFalse(child.isAncestor(sibling));
    assertFalse(child.isAncestor(grandChild));
  }

  private void createFamilyTree() throws SterilePanelException
  {
    parent = new MockPanel();
    child = new MockPanel();
    parent.add(child);
    grandChild = new MockPanel();
    child.add(grandChild);
    sibling = new MockPanel();
    parent.add(sibling);
  }

  public void testGetCommonAncestor() throws Exception
  {
    createFamilyTree();

    assertSame(parent, sibling.getClosestCommonAncestor(child));
    assertSame(parent, child.getClosestCommonAncestor(sibling));
    assertSame(parent, child.getClosestCommonAncestor(grandChild));
    assertSame(parent, grandChild.getClosestCommonAncestor(child));
    assertSame(parent, sibling.getClosestCommonAncestor(grandChild));
    assertSame(parent, grandChild.getClosestCommonAncestor(sibling));
    assertSame(child, grandChild.getClosestCommonAncestor(grandChild));
  }

  public void testGetClosestCommonAncestorExceptionCase() throws Exception
  {
    createFamilyTree();

    try
    {
      parent.getClosestCommonAncestor(child);
      fail("An exception is expected");
    }
    catch(LimelightError e)
    {  
    }
  }

  public void testGetAbsoluteLocation() throws Exception
  {
    createFamilyTree();

    parent.setLocation(1, 10);
    child.setLocation(2, 20);
    grandChild.setLocation(5, 50);

    assertEquals(new Point(1, 10), parent.getAbsoluteLocation());
    assertEquals(new Point(3, 30), child.getAbsoluteLocation());
    assertEquals(new Point(8, 80), grandChild.getAbsoluteLocation());
  }
}

