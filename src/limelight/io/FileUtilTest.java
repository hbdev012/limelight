package limelight.io;

import junit.framework.TestCase;
import limelight.util.TestUtil;

import java.io.File;

public class FileUtilTest extends TestCase
{
  public void testBuildPathEmpty() throws Exception
	{
		assertEquals("", FileUtil.buildPath(new String[]{}));
	}

	public void testBuildPathOneElement() throws Exception
	{
		assertEquals("a", FileUtil.buildPath(new String[]{"a"}));
	}

	public void testBuildPathThreeElements() throws Exception
	{
		String separator = System.getProperty("file.separator");
		assertEquals("a" + separator + "b" + separator + "c", FileUtil.buildPath(new String[]{"a", "b", "c"}));
	}

  public void testCurrentPath() throws Exception
  {
    String currentPath = FileUtil.currentPath();
    File currentPathFile = new File(FileUtil.buildPath(new String[] {currentPath, "."} ));
    File dotFile = new File(".");
    assertEquals(dotFile.getAbsolutePath(), currentPathFile.getAbsolutePath());
  }

  public void testCreateDir() throws Exception
	{
		File dir = FileUtil.makeDir(FileUtil.pathTo(TestUtil.TMP_DIR, "temp"));
		assertTrue(dir.exists());
		assertTrue(dir.isDirectory());
		FileUtil.deleteFileSystemDirectory(dir);
	}

  public void testCreateFile() throws Exception
  {
    String filePath = FileUtil.pathTo(TestUtil.TMP_DIR, "test.txt");

    FileUtil.createFile(filePath, "blah");
    assertEquals(true, new File(filePath).exists());
    assertEquals("blah", FileUtil.getFileContent(filePath));

    FileUtil.deleteFile(filePath);
    assertEquals(false, new File(filePath).exists());
  }
}
