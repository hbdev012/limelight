//- Copyright © 2008-2011 8th Light, Inc. All Rights Reserved.
//- Limelight and all included source files are distributed under terms of the MIT License.

package limelight.styles.attributes;

import org.junit.Before;
import org.junit.Test;

public class TopLeftBorderColorAttributeTest extends AbstractStyleAttributeTestBase
{
  @Before
  public void setUp() throws Exception
  {
    attribute = new TopLeftBorderColorAttribute();
  }

  @Test
  public void shouldCreation() throws Exception
  {
    assertEquals("Top Left Border Color", attribute.getName());
    assertEquals("color", attribute.getCompiler().type);
    assertEquals("#000000ff", attribute.getDefaultValue().toString());
  }
}
