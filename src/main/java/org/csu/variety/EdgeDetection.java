package org.csu.variety;

import java.io.File;
import org.csu.utils.PathUtil;
import org.opencv.core.Mat;
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
    Imgproc.Sobel(src,dst,-1, 0,1);

    String outputFilePath = PathUtil.outputTempFilePath(file, "sobel");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }
}
