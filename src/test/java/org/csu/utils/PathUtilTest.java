package org.csu.utils;

import static org.junit.Assert.*;

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
  public void testOutputFilePath() {
    File file=new File("I://new.jpg");
    System.out.println(PathUtil.outputFilePath(file,"txczyzh"));
  }
}