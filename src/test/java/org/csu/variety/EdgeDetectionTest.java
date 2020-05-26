package org.csu.variety;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.Test;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/22 9:00
 */
public class EdgeDetectionTest {

  @Test
  public void canny() throws Exception {
    EdgeDetection.canny(new File("src/main/resources/inputImg/lena.jpg"));
  }

  @Test
  public void sobel() throws Exception {
    EdgeDetection.sobel(new File("src/main/resources/inputImg/lena.jpg"));
  }

}