package org.csu.service;
import com.sun.javafx.geom.Edge;
import java.io.File;
import org.csu.controller.MainController;
import org.csu.utils.PathUtil;
import org.csu.variety.EdgeDetection;
import org.csu.variety.GeometricTransformation;
import org.csu.variety.ImageEnhancement;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/12 10:00
 */
public class ProcessService {
  private MainController controller;
  private File file;
  private String outputPath="";


  public ProcessService(MainController controller) {
    this.controller = controller;
  }

  public MainController getController() {
    return controller;
  }

  public void setController(MainController controller) {
    this.controller = controller;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
    outputPath=file.getAbsolutePath();
  }

  public String getOutputPath() {
    return outputPath;
  }

  public void txzjyzh(){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 图像直接阈值化操作开始---");
        //以灰度图像的方式读取图像.
        Mat src = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
        Mat dst = new Mat();
        //对一个数组应用一个自适应阈值。
        //该函数根据公式将灰度图像转换为二进制图像：
        if (src.empty()){
          controller.addNewLog("加载图片失败！");
          return;
        }
        Imgproc.adaptiveThreshold(src, dst, 200,
            Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 7, 8);
        outputPath=PathUtil.outputTempFilePath(file,"txzjyzh");
        Imgcodecs.imwrite(outputPath,dst);
        controller.addNewLog(" 图像直接阈值化操作结束");

        controller.showOutputImg(new File(outputPath));
      }
    }.run();
  }

  public void fourierTransform(){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 傅里叶变换开始---");
        try {
          outputPath = GeometricTransformation.fourierTransform(file);
          controller.addNewLog(" 傅里叶变换结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void spin(double centerX,double centerY,double angle,double scale){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 旋转开始---");
        try {
          outputPath = GeometricTransformation.spin(file,centerX,centerY,angle,scale);
          controller.addNewLog(" 旋转结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void pan(double xoff,double yoff){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 平移开始---");
        try {
          outputPath = GeometricTransformation.pan(file,xoff,yoff);
          controller.addNewLog(" 平移结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void zoom(float width,float height){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 缩放开始---");
        try {
          outputPath = GeometricTransformation.zoom(file,width,height);
          controller.addNewLog(" 缩放结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void autoHistEqualize(){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 对比度受限直方图均衡化开始---");
        try {
          outputPath = ImageEnhancement.autoHistEqualize(file);
          controller.addNewLog(" 对比度受限直方图均衡化结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void histEqualize(){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 直方图增强开始---");
        try {
          outputPath = ImageEnhancement.histEqualize(file);
          controller.addNewLog(" 直方图增强结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void laplaceEnhance(){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 拉普拉斯算子增强开始---");
        try {
          outputPath = ImageEnhancement.laplaceEnhance(file);
          controller.addNewLog(" 拉普拉斯算子增强结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void logEnhance(){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 对数变换开始---");
        try {
          outputPath = ImageEnhancement.logEnhance(file);
          controller.addNewLog(" 对数变换结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void gammaEnhance(double γ){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 伽马变换开始---");
        try {
          outputPath = ImageEnhancement.gammaEnhance(file,γ);
          controller.addNewLog(" 伽马变换结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void laplacianSmooth(){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 拉普拉斯平滑开始---");
        try {
          outputPath = ImageEnhancement.laplacianSmooth(file);
          controller.addNewLog(" 拉普拉斯平滑结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void medianFiltering(int kernelLegth){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 均值平滑开始---");
        try {
          outputPath = ImageEnhancement.medianFiltering(file,kernelLegth);
          controller.addNewLog(" 均值平滑结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void gaussianBlur(double sigmaX){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 高斯模糊开始---");
        try {
          outputPath = ImageEnhancement.gaussianBlur(file,sigmaX);
          controller.addNewLog(" 高斯模糊结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void sobel(){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" sobel 边缘检测开始---");
        try {
          outputPath = EdgeDetection.sobel(file);
          controller.addNewLog(" sobel 边缘检测结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void canny(double threshold1,double threshold2){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" canny 边缘检测开始---");
        try {
          outputPath = EdgeDetection.canny(file,threshold1,threshold2);
          controller.addNewLog(" canny 边缘检测结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void sharping(){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 锐化开始---");
        try {
          outputPath = ImageEnhancement.sharpening(file);
          controller.addNewLog(" 锐化结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

  public void detectConers(){
    new Runnable() {
      @Override
      public void run() {
        controller.addNewLog(" 角点检测开始---");
        try {
          outputPath = EdgeDetection.detectConers(file);
          controller.addNewLog(" 角点检测结束");
          controller.showOutputImg(new File(outputPath));
        }
        catch (Exception e) {
          controller.addNewLog(e.getMessage());
        }
      }
    }.run();
  }

}
