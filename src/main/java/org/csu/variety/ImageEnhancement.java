package org.csu.variety;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.csu.utils.PathUtil;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.CLAHE;
import org.opencv.imgproc.Imgproc;

/**
 * @Description
 * https://blog.csdn.net/u012525096/article/details/88135284
 * @Author WM
 * @Date 2020/5/22 8:05
 */
public class ImageEnhancement {

//-------------------对比度增强--图片太亮、太暗等问题-----------------------------
  /**
   * 对比度受限直方图均衡化CLAHE
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String autoHistEqualize(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGR2YCrCb);
    List<Mat> list1 = new ArrayList<>();
    Core.split(dst, list1);
    CLAHE clahe = Imgproc.createCLAHE();
    clahe.setClipLimit(4);
    clahe.apply(list1.get(0), list1.get(0));
    //        Core.normalize(list1.get(0),list1.get(0),0,255,Core.NORM_MINMAX);
    Core.merge(list1, dst);
    Imgproc.cvtColor(dst, dst, Imgproc.COLOR_YCrCb2BGR);

    String outputFilePath = PathUtil.outputTempFilePath(file, "autoHistEqualize");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  /**
   * 直方图增强
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String histEqualize(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGR2YCrCb);
    List<Mat> list1 = new ArrayList<>();
    Core.split(dst, list1);
    Imgproc.equalizeHist(list1.get(0), list1.get(0));
    Core.normalize(list1.get(0), list1.get(0), 0, 255, Core.NORM_MINMAX);
    Core.merge(list1, dst);
    Imgproc.cvtColor(dst, dst, Imgproc.COLOR_YCrCb2BGR);

    String outputFilePath = PathUtil.outputTempFilePath(file, "histEqualize");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  /**
   * 拉普拉斯算子增强
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String laplaceEnhance(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    float[] kernel = {0, 0, 0, -1, 5f, -1, 0, 0, 0};
    Mat kernelMat = new Mat(3, 3, CvType.CV_32FC1);
    kernelMat.put(0, 0, kernel);
    Imgproc.filter2D(dst, dst, CvType.CV_8UC3, kernelMat);


    String outputFilePath = PathUtil.outputTempFilePath(file, "laplaceEnhance");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  /**
   * 对数变换
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String logEnhance(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Mat imageResult = new Mat(dst.size(), CvType.CV_32FC3);
    Core.add(dst, new Scalar(5, 5, 5), dst);
    dst.convertTo(dst, CvType.CV_32F);
    Core.log(dst, imageResult);
    //        Core.multiply(imageLog, new Scalar(3,3,3), imageLog);
    Core.normalize(imageResult, imageResult, 0, 255, Core.NORM_MINMAX);
    Core.convertScaleAbs(imageResult, imageResult);

    String outputFilePath = PathUtil.outputTempFilePath(file, "logEnhance");
    Imgcodecs.imwrite(outputFilePath,imageResult);

    return outputFilePath;
  }

  /**
   * 伽马变换
   * γ大于1可强调高灰度值，γ小于1可强调低灰度值
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String gammaEnhance(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    dst.convertTo(dst, CvType.CV_32F);

    Core.pow(dst, 0.5, dst);

    Core.normalize(dst, dst, 0, 255, Core.NORM_MINMAX);
    Core.convertScaleAbs(dst, dst);

    String outputFilePath = PathUtil.outputTempFilePath(file, "gammaEnhance2");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  public static String gammaEnhance(File file,double γ) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    dst.convertTo(dst, CvType.CV_32F);

    Core.pow(dst, γ, dst);

    Core.normalize(dst, dst, 0, 255, Core.NORM_MINMAX);
    Core.convertScaleAbs(dst, dst);

    String outputFilePath = PathUtil.outputTempFilePath(file, "gammaEnhance2");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  //-----------图像平滑----------------------------------------------

  /**
   * 拉普拉斯平滑
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String laplacianSmooth(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Imgproc.Laplacian(src,dst,-1);

    String outputFilePath = PathUtil.outputTempFilePath(file, "laplacianSmooth");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  /**
   * 均值平滑
   * @param  file 输入文件
   * @return 结果路径
   */
  private final  static int MAX_KERNEL_LENGTH = 3;
  public static String medianFiltering(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath(),Imgcodecs.IMREAD_GRAYSCALE);

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();

    for ( int i = 1; i < MAX_KERNEL_LENGTH; i = i + 2 )
    {
      Imgproc.medianBlur(src,dst,i);
    }

    String outputFilePath = PathUtil.outputTempFilePath(file, "medianFiltering3-2");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  public static String medianFiltering(File file,int kernelLegth) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath(),Imgcodecs.IMREAD_GRAYSCALE);

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();

    for ( int i = 1; i < kernelLegth; i = i + 2 )
    {
      Imgproc.medianBlur(src,dst,i);
    }

    String outputFilePath = PathUtil.outputTempFilePath(file, "medianFiltering3-2");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  //-------------图像锐化----------------------------------------





  //-----------------图像模糊------------------------------

  /**
   * 高斯模糊
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String gaussianBlur(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Imgproc.GaussianBlur(src,dst,new Size(0,0),31,0);

    String outputFilePath = PathUtil.outputTempFilePath(file, "gaussianBlur31");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  public static String gaussianBlur(File file,double sigmaX) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Imgproc.GaussianBlur(src,dst,new Size(0,0),sigmaX,0);

    String outputFilePath = PathUtil.outputTempFilePath(file, "gaussianBlur31");
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }



}
