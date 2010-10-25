package limelight.util;

import limelight.Context;
import limelight.io.FakeFileSystem;
import limelight.io.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class ResourceLoaderTest
{
  private static String rootPath = "/limelight/resource_loader/test";
  private ResourceLoader loader;
  private FakeFileSystem fs;

  @Before
  public void setUp() throws Exception
  {
    fs = new FakeFileSystem();
    Context.instance().fs = fs;

    loader = ResourceLoader.forRoot(rootPath);
  }

  @Test
  public void hasRoot() throws Exception
  {
    assertEquals(rootPath, loader.getRoot());
  }

  @Test
  public void pathToAbsolutePath() throws Exception
  {
    assertEquals("/", loader.pathTo("/"));  
    assertEquals("/Users/micahmartin", loader.pathTo("/Users/micahmartin"));
  }

  @Test
  public void pathToRelativePath() throws Exception
  {
    assertEquals(rootPath + "/foo", loader.pathTo("foo"));
    assertEquals(rootPath + "/foo/bar.gif", loader.pathTo("foo/bar.gif"));
  }

  @Test
  public void knowsIfTheFileExists() throws Exception
  {
    fs.createTextFile(FileUtil.join(rootPath, "foo.txt"), "blah");

    assertEquals(true, loader.exists("foo.txt"));
    assertEquals(false, loader.exists("bar.txt"));
  }

  @Test
  public void readsText() throws Exception
  {
    fs.createTextFile(FileUtil.join(rootPath, "foo.txt"), "blah");

    assertEquals("blah", loader.readText("foo.txt"));
  }
}
