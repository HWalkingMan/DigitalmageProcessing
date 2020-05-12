package org.csu.variety;

import java.io.File;
import org.csu.utils.PathUtil;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/12 10:50
 */
public class GeometricTransformation {

  /**
   * 图像的旋转操作
   * @param  file 输入文件
   * @return 结果路径
   */
  public static String pan(File file) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    //center : 原图像的旋转中心
    Point center = new Point(src.width()/2.0, src.height());

    double angle=90.0;
    double scale=1.0;

    //angle : 旋转角的度。正值意味着逆时针旋转（坐标原点被假定为左上角）
    //scale : 各向同性的比例因子
    //计算一个二维旋转的仿射矩阵
    Mat affineTrans = Imgproc.getRotationMatrix2D(center, angle, scale);

    //src ： 输入的图像
    //dst :  输出的图像
    //affineTrans : 变换矩阵。
    //dsize : 输出图象的大小
    String outputFilePath = PathUtil.outputFilePath(file, "pan");
    Imgproc.warpAffine(src, dst, affineTrans, dst.size(), Imgproc.INTER_NEAREST);
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  public static String pan(File file,double angle,double scale ) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    //center : 原图像的旋转中心
    Point center = new Point(src.width()/2.0, src.height());
    
    //angle : 旋转角的度。正值意味着逆时针旋转（坐标原点被假定为左上角）
    //scale : 各向同性的比例因子
    //计算一个二维旋转的仿射矩阵
    Mat affineTrans = Imgproc.getRotationMatrix2D(center, angle, scale);

    //src ： 输入的图像
    //dst :  输出的图像
    //affineTrans : 变换矩阵。
    //dsize : 输出图象的大小
    String outputFilePath = PathUtil.outputFilePath(file, "pan");
    Imgproc.warpAffine(src, dst, affineTrans, dst.size(), Imgproc.INTER_NEAREST);
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  public static String pan(File file,double centerX,double centerY,double angle,double scale ) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst = src.clone();
    Point center = new Point(centerX,centerY);

    Mat affineTrans = Imgproc.getRotationMatrix2D(center, angle, scale);

    String outputFilePath = PathUtil.outputFilePath(file, "pan");
    Imgproc.warpAffine(src, dst, affineTrans, dst.size(), Imgproc.INTER_NEAREST);
    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }
  
  /** 
  * 
  * @param file
  * @return  结果路径
  * @exception Exception 图片路径错误
  */
  public static String zoom(File file) throws Exception {

    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }

    Mat dst = src.clone();

    float scale = 0.5f;
    float width = src.width();
    int height = src.height();

    String outputFilePath = PathUtil.outputFilePath(file, "zoom");

    Imgproc.resize(src,dst,new Size(width*scale,height*scale));

    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }

  public static String zoom(File file,float scale) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }

    Mat dst = src.clone();

    float width = src.width();
    int height = src.height();

    String outputFilePath = PathUtil.outputFilePath(file, "zoom");

    Imgproc.resize(src,dst,new Size(width*scale,height*scale));

    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }


  public static String zoom(File file,float width,float height) throws Exception {
    Mat src = Imgcodecs.imread(file.getAbsolutePath());

    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }

    Mat dst = src.clone();

    String outputFilePath = PathUtil.outputFilePath(file, "zoom");

    Imgproc.resize(src,dst,new Size(width,height));

    Imgcodecs.imwrite(outputFilePath,dst);

    return outputFilePath;
  }



}
