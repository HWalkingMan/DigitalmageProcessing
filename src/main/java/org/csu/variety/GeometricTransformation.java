package org.csu.variety;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.csu.utils.MatUtil;
import org.csu.utils.PathUtil;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
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

  public static String spin(File file) throws Exception {
    Mat src=Imgcodecs.imread(file.getAbsolutePath());
    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst=src.clone();

    Mat affineTrans = Mat.zeros(new int[]{2,3},CvType.CV_32FC1);
    affineTrans.put(0,0,new double[]{1});
    affineTrans.put(0,2,new double[]{20});//水平偏移量
    affineTrans.put(1,1,new double[]{1});
    affineTrans.put(1,2,new double[]{20});//垂直偏移量

    String outputFilePath = PathUtil.outputFilePath(file, "spin");
    Imgproc.warpAffine(src,dst,affineTrans,dst.size());
    Imgcodecs.imwrite(outputFilePath,dst);
    return outputFilePath;
  }

  public static String spin(File file,double xoff,double yoff) throws Exception {
    Mat src=Imgcodecs.imread(file.getAbsolutePath());
    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }
    Mat dst=src.clone();

    Mat affineTrans = Mat.zeros(new int[]{2,3},CvType.CV_32FC1);
    affineTrans.put(0,0,new double[]{1});
    affineTrans.put(0,2,new double[]{xoff});//水平偏移量
    affineTrans.put(1,1,new double[]{1});
    affineTrans.put(1,2,new double[]{yoff});//垂直偏移量

    String outputFilePath = PathUtil.outputFilePath(file, "spin");
    Imgproc.warpAffine(src,dst,affineTrans,dst.size());
    Imgcodecs.imwrite(outputFilePath,dst);
    return outputFilePath;
  }


  public static String fourierTransform(File file) throws Exception {
    Mat src=Imgcodecs.imread(file.getAbsolutePath(),Imgcodecs.IMREAD_GRAYSCALE);
    if (src.empty()) {
      throw new Exception("加载图片失败！");
    }

    int newHeight = Core.getOptimalDFTSize(src.rows()); // 获取纵向扩充后的距离（高度）
    int newWidth = Core.getOptimalDFTSize(src.cols()); // 获取横向扩充后的距离（宽度）
    Mat padded = new Mat();
    // 扩充图像边界
    Core.copyMakeBorder(src,padded, 0,newHeight - src.rows(), 0, newWidth - src.cols() , Core.BORDER_CONSTANT , Scalar
        .all(0));

    List<Mat> paddedMat_channels = new ArrayList<Mat>();

    //转float
    padded.convertTo(padded,CvType.CV_32F);
    paddedMat_channels.add(padded);
    paddedMat_channels.add(Mat.zeros(padded.size(),CvType.CV_32F));

    //合并通道
    Mat complexImage = new Mat();
    Core.merge(paddedMat_channels,complexImage);
    //离散傅里叶变换
    Core.dft(complexImage,complexImage);
    //分割通道
    Core.split(complexImage,paddedMat_channels);

    //将复数值转化为副值
    Mat temp = new Mat();
    Core.magnitude(paddedMat_channels.get(0),paddedMat_channels.get(1),temp);
    Core.add(temp,Scalar.all(0),temp);
    Core.log(temp, temp);
    temp = new Mat(temp,new Rect(0,0,temp.cols()&-2,temp.rows()&-2));

    int cx = src.cols() / 2;
    int cy = src.rows() / 2;

    Mat q0 = new Mat(temp, new Rect(0, 0, cx, cy));
    Mat q1 = new Mat(temp, new Rect(cx, 0, cx, cy));
    Mat q2 = new Mat(temp, new Rect(0, cy, cx, cy));
    Mat q3 = new Mat(temp, new Rect(cx, cy, cx, cy));

    Mat tmp = new Mat();
    q0.copyTo(tmp);
    q3.copyTo(q0);
    tmp.copyTo(q3);
    q1.copyTo(tmp);
    q2.copyTo(q1);
    tmp.copyTo(q2);

    Core.normalize(temp, temp, 0, 255, Core.NORM_MINMAX);


    String outputFilePath = PathUtil.outputFilePath(file, "fourierTransform");
    Imgcodecs.imwrite(outputFilePath,temp);

    return outputFilePath;
  }



}
