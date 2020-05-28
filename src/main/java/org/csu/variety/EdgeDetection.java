package org.csu.variety;

import java.io.File;
import org.csu.utils.PathUtil;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/22 9:00
 */
public class EdgeDetection {
  /**
   * canny 边缘检测
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String canny(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Imgproc.Canny(src,dst,40.0,200.0);

    String outputFilePath = PathUtil.outputTempFilePath(file, "canny");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  /**
   * canny 边缘检测
   * 低于阈值1的像素点会被认为不是边缘；
   * 高于阈值2的像素点会被认为是边缘；
   * 在阈值1和阈值2之间的像素点,若与第2步得到的边缘像素点相邻，则被认为是边缘，否则被认为不是边缘。
   * @param  file 输入文件
   * @param threshold1 阈值1
   * @param threshold2 阈值2
   * @return 结果路径
   */
  public static String canny(File file,double threshold1,double threshold2) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Imgproc.Canny(src,dst,threshold1,threshold2);

    String outputFilePath = PathUtil.outputTempFilePath(file, "canny");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  /**
   * sobel 边缘检测
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String sobel(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Mat grad_x=new Mat(),grad_y=new Mat();
    Mat grad_x_abs=new Mat(),grad_y_abs=new Mat();

    Imgproc.Sobel(src,grad_y,-1, 0,1);
    Imgproc.Sobel(src,grad_x,-1, 1,0);
    Core.convertScaleAbs(grad_x, grad_x_abs);
    Core.convertScaleAbs(grad_y, grad_y_abs);

    Core.addWeighted(grad_x_abs, 0.5, grad_y_abs, 0.5, 10, dst);

    String outputFilePath = PathUtil.outputTempFilePath(file, "sobel");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  /**
   * 角点检测
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String detectConers(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    final int maxCorners = 50,blockSize =3;
    final double qualityLevel = 0.01,minDistance = 20.0,k=0.04;
    final boolean userHarrisDetector = false;
    MatOfPoint corners = new MatOfPoint();
    Mat gray = new Mat();

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Imgproc.cvtColor(src,gray,Imgproc.COLOR_BGR2GRAY);//转成灰度图像
    Mat dst = src.clone();
    //计算角点
    //        image：8位或32位浮点型输入图像，单通道
    //        corners：保存检测出的角点
    //        maxCorners：角点数目最大值，如果实际检测的角点超过此值，则只返回前maxCorners个强角点
    //        qualityLevel：角点的品质因子
    //        minDistance：对于初选出的角点而言，如果在其周围minDistance范围内存在其他更强角点，则将此角点删除
    //        mask：指定感兴趣区，如不需在整幅图上寻找角点，则用此参数指定ROI。也可以new Mat()来代替，这样就是没有mask.
    //        blockSize：计算协方差矩阵时的窗口大小
    //        useHarrisDetector：指示是否使用Harris角点检测，如不指定，则计算shi-tomasi角点
    //        harrisK：Harris角点检测需要的k值
    Imgproc.goodFeaturesToTrack(gray,corners,maxCorners,qualityLevel,minDistance,new Mat(),blockSize,userHarrisDetector,k);
    Point[] pCorners = corners.toArray();
    for (int i = 0; i < pCorners.length; i++) {
      Imgproc.circle(dst,pCorners[i],2,new Scalar(0,255,255),1);
    }

    String outputFilePath = PathUtil.outputTempFilePath(file, "detectConers");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  /**
   * 模块化
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String mozaic(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Imgproc.resize(src,dst,new Size(),0.2,0.2,Imgproc.INTER_NEAREST);
    Imgproc.resize(src,dst,new Size(),1/0.2,1/0.2,Imgproc.INTER_NEAREST);


    String outputFilePath = PathUtil.outputTempFilePath(file, "mozaic");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

}
