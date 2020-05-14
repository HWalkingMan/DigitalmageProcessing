package org.csu.variety;

import java.io.File;
import java.util.Arrays;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/11 23:24
 */
public class TrySomething {


  public void trysomething1() {
    String filename="src/main/resources/sourceImg/1568700218300.jpeg";
    File imgFile=new File(filename);
    if (!imgFile.exists()){
      System.out.println("error for img path");
    }
    else {
      Mat srcImg= Imgcodecs.imread(filename);
      if (srcImg.empty()){
        System.out.println("加载图片失败！");
      }else{
        HighGui.imshow("image",srcImg); //显示
        HighGui.waitKey(1000);
        Imgcodecs.imwrite("src/main/resources/outputImg/1.jpeg",srcImg);
      }
    }
  }

  public  void trysomething2() {
      //以灰度图像的方式读取图像.
      Mat src = Imgcodecs.imread("src/main/resources/outputImg/1.jpeg", Imgcodecs.IMREAD_GRAYSCALE);
      Mat dst = new Mat();

      //对一个数组应用一个自适应阈值。
      //            该函数根据公式将灰度图像转换为二进制图像：
      if (src.empty()){
        System.out.println("加载图片失败！");
      return;
      }
      Imgproc.adaptiveThreshold(src, dst, 200,
          Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 7, 8);

      HighGui.imshow("d",dst);
      HighGui.waitKey(1000);

  }

  public static void playMat(){
    Mat mat=Mat.zeros(new Size(3,6), CvType.CV_32FC4);
//    for (int i=0;i<mat.height();i++){
//
//      for (int j=0;j<mat.width();j++){
//        System.out.print(Arrays.toString(mat.get(i, j)) +"\t");
//      }
//      System.out.println();
//    }

    System.out.println(mat.channels());
  }

}
