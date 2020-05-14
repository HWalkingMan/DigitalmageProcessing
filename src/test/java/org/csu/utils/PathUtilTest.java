package org.csu.utils;

import java.io.File;
import org.junit.Test;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/12 10:12
 */
public class PathUtilTest {

  @Test
  public void outputFilePath() {
  }

  @Test
  public void testOutputTempFilePath() {
    File file=new File("I://new.jpg");
    System.out.println(PathUtil.outputTempFilePath(file,"txczyzh"));
  }

  @Test
  public void testOutputFilePath() {
    File file=new File("I://new-zoom.jpg");
    System.out.println(PathUtil.outputFilePath(file));
  }
}