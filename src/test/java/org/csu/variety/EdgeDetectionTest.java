package org.csu.variety;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.Test;
import org.opencv.core.Core;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/22 9:00
 */
public class EdgeDetectionTest {

  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  @Test
  public void canny() throws Exception {
    EdgeDetection.canny(new File("src/main/resources/inputImg/lena.jpg"));
  }

  @Test
  public void sobel() throws Exception {
    EdgeDetection.sobel(new File("src/main/resources/inputImg/lena.jpg"));
  }

  @Test
  public void detectConers() throws Exception {
    EdgeDetection.detectConers(new File("src/main/resources/inputImg/test2.bmp"));
  }

  @Test
  public void mozaic() throws Exception {
    EdgeDetection.mozaic(new File("src/main/resources/inputImg/lena.jpg"));
  }

}