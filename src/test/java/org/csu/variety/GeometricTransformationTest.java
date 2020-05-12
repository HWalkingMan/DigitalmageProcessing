package org.csu.variety;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.Test;
import org.opencv.core.Core;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/12 11:00
 */
public class GeometricTransformationTest {
  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  @Test
  public void pan() {
    File file=new File("src/main/resources/inputImg/1568700218300.jpeg");
    try {
      GeometricTransformation.pan(file);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void zoom(){
    File file=new File("src/main/resources/inputImg/1568700218300.jpeg");
    try {
      GeometricTransformation.zoom(file);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}