package org.csu.variety;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/14 19:02
 */
public class TrySomethingTest {

  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  @Test
  public void playMat() {
    TrySomething.playMat();
  }
}