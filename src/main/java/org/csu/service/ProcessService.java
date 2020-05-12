package org.csu.service;
import java.io.File;
import org.csu.controller.MainController;
import org.csu.utils.PathUtil;
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
        outputPath=PathUtil.outputFilePath(file,"txzjyzh");
        Imgcodecs.imwrite(outputPath,dst);
        controller.addNewLog(" 图像直接阈值化操作结束");

        controller.showOutputImg(new File(outputPath));
      }
    }.run();
  }
}
