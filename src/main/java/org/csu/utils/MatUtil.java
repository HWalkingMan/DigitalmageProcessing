package org.csu.utils;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/14 20:49
 */
public class MatUtil {
  public static void showMat(Mat mat){
    double[][][] matdoule =new double[mat.width()][mat.height()][mat.channels()];
    for (double[][] matt:matdoule){
      matt=new double[mat.width()][mat.height()];
      for(double[] mato:matt){
        mato=new double[mat.width()];
      }
    }
    for (int i=0;i<mat.width();i++)
      for (int j=0;j<mat.height();j++)
        for (int k=0;k<mat.channels();k++)
          matdoule[i][j][k]=mat
              .get(j,i)
              [k];

    for (int k=0;k<mat.channels();k++){
      System.out.println("-----channel:"+(k+1)+"-----");
      for (int i=0;i<mat.width();i++){
        for (int j = 0; j < mat.height(); j++) {
          System.out.print(matdoule[i][j][k]+"\t");
        }
        System.out.println();
      }

      System.out.println();
    }
  }

}
