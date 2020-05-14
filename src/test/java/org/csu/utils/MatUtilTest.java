package org.csu.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/14 21:41
 */
public class MatUtilTest {
  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  @Test
  public void showMat() {
    Mat mat=Mat.zeros(new int[]{2,3}, CvType.CV_32FC1);
    MatUtil.showMat(mat);

    mat.put(0,0,new double[]{1});
    mat.put(0,2,new double[]{20});
    mat.put(1,1,new double[]{1});
    mat.put(1,2,new double[]{20});
    MatUtil.showMat(mat);
  }

  @Test
  public void test(){
    String s="";

    Double d= Double.valueOf(s);
  }
}