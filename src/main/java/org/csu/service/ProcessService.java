package org.csu.service;
import java.io.File;
import org.csu.controller.MainController;
import org.csu.utils.PathUtil;
import org.csu.variety.GeometricTransformation;
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
}
