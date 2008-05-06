package limelight.ui;

import junit.framework.TestCase;

public class ScreenableStyleTest extends TestCase
{
  private ScreenableStyle style;
  private RichStyle style2;
  private RichStyle style3;

  public void setUp() throws Exception
  {
    style = new ScreenableStyle();
    style2 = new RichStyle();
    style3 = new RichStyle();
  }

  public void testNoScreenAtFirst() throws Exception
  {
    assertEquals(null, style.getScreen());
  }

  public void testAddingEmptyScreenDoesntRegisterChanges() throws Exception
  {
    style.setWidth("100");
    style.flushChanges();
    style.applyScreen(style2);

    assertSame(style2, style.getScreen());
    assertEquals("100", style.getWidth());
    assertEquals(false, style.changed());
    assertEquals(0, style.getChangedCount());
  }
  
  public void testAddingFullScreenAppliesChanges() throws Exception
  {
    style.setWidth("100");
    style.flushChanges();
    style2.setWidth("123");
    style2.flushChanges();

    style.applyScreen(style2);

    assertEquals("123", style.getWidth());
    assertEquals(1, style.getChangedCount());
    assertEquals(true, style.changed());
    assertEquals(true, style.changed(Style.WIDTH));
  }
  
  public void testRemovingEmptyScreenDoesntAffectChanges() throws Exception
  {
    style.setWidth("100");
    style.flushChanges();
    style.applyScreen(style2);
    style.flushChanges();

    style.removeScreen();

    assertSame(null, style.getScreen());
    assertEquals("100", style.getWidth());
    assertEquals(false, style.changed());
    assertEquals(0, style.getChangedCount());
  }


  public void testRemovingFullScreenAppliesChanges() throws Exception
  {
    style.setWidth("100");
    style.flushChanges();
    style2.setWidth("123");
    style2.flushChanges();
    style.applyScreen(style2);
    style.flushChanges();

    style.removeScreen();

    assertEquals("100", style.getWidth());
    assertEquals(1, style.getChangedCount());
    assertEquals(true, style.changed());
    assertEquals(true, style.changed(Style.WIDTH));
  }

  public void testCantApplyMultipleScreens() throws Exception
  {
    style.applyScreen(style2);

    try
    {
      style.applyScreen(style3);
      fail("Should have throws an exception");
    }
    catch(Exception e)
    {
      assertEquals("Screen already applied", e.getMessage());  
    }
  }
}
