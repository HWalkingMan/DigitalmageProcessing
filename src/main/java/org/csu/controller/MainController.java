package org.csu.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.csu.service.ProcessService;
import org.csu.utils.FileUtil;
import org.csu.utils.PathUtil;
import org.csu.utils.TimeUtil;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/11 23:28
 */
public class MainController implements Initializable {

  public AnchorPane rootLayout;
  public ImageView img_input;
  public ImageView img_output;
  public TextArea txa_output;
  public Button btn_open;
  public Button btn_output;
  public Button btn_txzjyzh;
  public Button btn_pan;
  public Button btn_spin;
  public Button btn_zoom;
  public Button btn_fourierTransform;
  public Label la_para1;
  public TextField ed_para1;
  public TextField ed_para2;
  public Label la_para2;
  public TextField ed_para3;
  public Label la_para3;
  public TextField ed_para4;
  public Label la_para4;
  public Button btn_go;
  public Label la_inputSize;
  public Label la_outputSize;
  public Button btn_clear;
  public Slider sl_para5;
  public Slider sl_para6;
  public Label la_para5;
  public Label la_para6;
  public Button btn_autoHistEqualize;
  public Button btn_histEqualize;
  public Button btn_laplaceEnhance;
  public Button btn_logEnhance;
  public Button btn_gammaEnhance;
  public Button btn_laplacianSmooth;
  public Button btn_medianFiltering;
  public Button btn_gaussianBlur;
  public Button btn_canny;
  public Button btn_sobel;
  public Label la_para5v;
  public Label la_para6v;
  public Button btn_sharping;
  public Button btn_detectConers;

  private File file;

  private ProcessService service;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    service=new ProcessService(this);
    sl_para5.setMajorTickUnit(127);
    sl_para5.setMinorTickCount(8);
    sl_para5.setShowTickLabels(true);
    sl_para5.setShowTickMarks(true);
    sl_para5.setBlockIncrement(16);
    sl_para5.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        la_para5v.setText(String.format("%.1f", newValue));
      }
    });
    sl_para6.setMajorTickUnit(127);
    sl_para6.setMinorTickCount(8);
    sl_para6.setShowTickLabels(true);
    sl_para6.setShowTickMarks(true);
    sl_para6.setBlockIncrement(16);
    sl_para6.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        la_para6v.setText(String.format("%.1f", newValue));
      }
    });

  }

  public void btn_openClick(ActionEvent actionEvent) {
    if(file!=null) {
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("提示");
      alert.setHeaderText("你已经打开了一张图片，是否要覆盖当前图片");

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.CANCEL) {
        return;
      }
    }

    FileChooser chooser = new FileChooser(); // 创建一个文件对话框
    chooser.setTitle("打开文件"); // 设置文件对话框的标题
    chooser.setInitialDirectory(new File("G:\\")); // 设置文件对话框的初始目录

    // 给文件对话框添加多个文件类型的过滤器
    chooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("所有图像", "*.jpg", "*.png", "*.bmp"),
        new FileChooser.ExtensionFilter("所有文件", "*.*"));
    // 显示文件打开对话框，且该对话框支持同时选择多个文件
    Window stage = rootLayout.getScene().getWindow();
    file = chooser.showOpenDialog(stage); // 显示文件打开对话框

    if (file == null) {
      this.addNewLog("取消打开文件");
    }
    else {
      this.addNewLog("成功打开文件 [ "+file.getAbsolutePath()+" ]");
      Image inputImage = new Image(file.toURI().toString());
      img_input.setImage(inputImage);
      la_inputSize.setText(inputImage.getWidth() +"×"+ inputImage.getHeight());
      service.setFile(file);
      usableBtn();
    }
  }

  public void btn_outputClick(ActionEvent actionEvent) {
    String outputPath = service.getOutputPath();
    if (outputPath == null || outputPath.isEmpty()) {
      this.addNewLog("无可输出内容");
    }
    else {
      File file = new File(outputPath);
      String realPath= PathUtil.outputFilePath(file);
      try {
        FileUtil.copyFile(file,realPath);
        this.addNewLog("文件输出至 [ " + realPath + " ]");
      }
      catch (IOException e) {
        this.addNewLog(e.getMessage());
      }
    }
  }

  public void addNewLog(String msg){
    txa_output.appendText(TimeUtil.date2DTformatStr(new Date())+" "+msg+"\n");
  }

  public void showOutputImg(File file){
    Image outputImage = new Image(file.toURI().toString());
    img_output.setImage(outputImage);
    la_outputSize.setText(outputImage.getWidth() +"×"+ outputImage.getHeight());
  }

  private void resetParas(){
    btn_go.setVisible(false);
    la_para1.setVisible(false);
    ed_para1.setVisible(false);
    ed_para2.setVisible(false);
    la_para2.setVisible(false);
    ed_para3.setVisible(false);
    la_para3.setVisible(false);
    ed_para4.setVisible(false);
    la_para4.setVisible(false);
    sl_para5.setVisible(false);
    la_para5.setVisible(false);
    sl_para6.setVisible(false);
    la_para6.setVisible(false);
    la_para5v.setVisible(false);
    la_para6v.setVisible(false);
  }

  private void usableBtn(){
    btn_output.setDisable(false);
    btn_pan.setDisable(false);
    btn_spin.setDisable(false);
    btn_zoom.setDisable(false);
    btn_fourierTransform.setDisable(false);
    btn_txzjyzh.setDisable(false);
    btn_autoHistEqualize.setDisable(false);
    btn_histEqualize.setDisable(false);
    btn_laplaceEnhance.setDisable(false);
    btn_logEnhance.setDisable(false);
    btn_gammaEnhance.setDisable(false);
    btn_laplacianSmooth.setDisable(false);
    btn_medianFiltering.setDisable(false);
    btn_gaussianBlur.setDisable(false);
    btn_canny.setDisable(false);
    btn_sobel.setDisable(false);
    btn_sharping.setDisable(false);
    btn_detectConers.setDisable(false);
  }

  private String type="";

  public void btn_goClick(ActionEvent actionEvent) {
    if (type.equals("spin")){
      try {
        double centerX= Double.parseDouble(ed_para1.getText());
        double centerY= Double.parseDouble(ed_para2.getText());
        double angle= Double.parseDouble(ed_para3.getText());
        double scale= Double.parseDouble(ed_para4.getText());
        service.spin(centerX,centerY,angle,scale);
      }
      catch (Exception e){
        e.printStackTrace();
        this.addNewLog(e.getMessage());
        this.addNewLog("输入格式错误,请重新输入");
      }
      resetParas();
    }
    else if (type.equals("pan")){
      try {
        double xoff= Double.parseDouble(ed_para1.getText());
        double yoff= Double.parseDouble(ed_para2.getText());
        service.pan(xoff,yoff);
      }
      catch (Exception e){
        e.printStackTrace();
        this.addNewLog(e.getMessage());
        this.addNewLog("输入格式错误,请重新输入");
      }
      resetParas();
    }
    else if (type.equals("zoom")){
      try {
        float width= Float.parseFloat(ed_para1.getText());
        float height= Float.parseFloat(ed_para2.getText());
        service.zoom(width,height);
      }
      catch (Exception e){
        e.printStackTrace();
        this.addNewLog(e.getMessage());
        this.addNewLog("输入格式错误,请重新输入");
      }
      resetParas();
    }
    else if (type.equals("gammaEnhance")){
      try {
        double r= Double.parseDouble(ed_para1.getText());
        service.gammaEnhance(r);
      }
      catch (Exception e){
        e.printStackTrace();
        this.addNewLog(e.getMessage());
        this.addNewLog("输入格式错误,请重新输入");
      }
      resetParas();
    }
    else if (type.equals("medianFiltering")){
      try {
        int kernelLegth= Integer.parseInt(ed_para1.getText());
        service.medianFiltering(kernelLegth);
      }
      catch (Exception e){
        e.printStackTrace();
        this.addNewLog(e.getMessage());
        this.addNewLog("输入格式错误,请重新输入");
      }
      resetParas();
    }
    else if (type.equals("gaussianBlur")){
      try {
        double r= Double.parseDouble(ed_para1.getText());
        service.gaussianBlur(r);
      }
      catch (Exception e){
        e.printStackTrace();
        this.addNewLog(e.getMessage());
        this.addNewLog("输入格式错误,请重新输入");
      }
      resetParas();
    }
    else if (type.equals("canny")){
      try {
        double t1= sl_para5.getValue();
        double t2=sl_para6.getValue();
        service.canny(t1,t2);
      }
      catch (Exception e){
        e.printStackTrace();
        this.addNewLog(e.getMessage());
        this.addNewLog("输入格式错误,请重新输入");
      }
      resetParas();
    }
    else if (type.isEmpty()){
      this.addNewLog("无可用操作");
    }
    else {
      this.addNewLog("操作异常");
    }
  }

  public void btn_panClick(ActionEvent actionEvent) {
    resetParas();
    type="pan";
    la_para1.setText("X offset");
    la_para2.setText("Y offset");
    la_para1.setVisible(true);
    la_para2.setVisible(true);
    ed_para1.setVisible(true);
    ed_para2.setVisible(true);
    btn_go.setVisible(true);
  }

  public void btn_spinClick(ActionEvent actionEvent) {
    resetParas();
    type="spin";
    la_para1.setText("center X");
    la_para2.setText("center Y");
    la_para3.setText("angle");
    la_para4.setText("scale");
    la_para1.setVisible(true);
    la_para2.setVisible(true);
    la_para3.setVisible(true);
    la_para4.setVisible(true);
    ed_para1.setVisible(true);
    ed_para2.setVisible(true);
    ed_para3.setVisible(true);
    ed_para4.setVisible(true);
    btn_go.setVisible(true);
  }

  public void btn_zoomClick(ActionEvent actionEvent) {
    resetParas();
    type="zoom";
    la_para1.setText("width");
    la_para2.setText("height");
    la_para1.setVisible(true);
    la_para2.setVisible(true);
    ed_para1.setVisible(true);
    ed_para2.setVisible(true);
    btn_go.setVisible(true);
  }

  public void btn_fourierTransformClick(ActionEvent actionEvent) {
    resetParas();
    service.fourierTransform();
  }

  public void btn_txzjyzhClick(ActionEvent actionEvent) {
    resetParas();
    service.txzjyzh();
  }



  public void btn_autoHistEqualizeClick(ActionEvent actionEvent) {
    resetParas();
    service.autoHistEqualize();
  }

  public void btn_histEqualizeClick(ActionEvent actionEvent) {
    resetParas();
    service.histEqualize();
  }

  public void btn_laplaceEnhanceClick(ActionEvent actionEvent) {
    resetParas();
    service.laplaceEnhance();
  }

  public void btn_logEnhanceClick(ActionEvent actionEvent) {
    resetParas();
    service.logEnhance();
  }

  public void btn_gammaEnhanceClick(ActionEvent actionEvent) {
    resetParas();
    type="gammaEnhance";
    la_para1.setText("γ");
    la_para1.setVisible(true);
    ed_para1.setVisible(true);
    btn_go.setVisible(true);
  }

  public void btn_laplacianSmoothClick(ActionEvent actionEvent) {
    resetParas();
    service.laplacianSmooth();
  }

  public void btn_medianFilteringClick(ActionEvent actionEvent) {
    resetParas();
    type="medianFiltering";
    la_para1.setText("kernelLegth");
    la_para1.setVisible(true);
    ed_para1.setVisible(true);
    btn_go.setVisible(true);
  }

  public void btn_gaussianBlurClick(ActionEvent actionEvent) {
    resetParas();
    type="gaussianBlur";
    la_para1.setText("sigmaX");
    la_para1.setVisible(true);
    ed_para1.setVisible(true);
    btn_go.setVisible(true);
  }

  public void btn_cannyClick(ActionEvent actionEvent) {
    resetParas();
    type="canny";
    la_para5.setText("阈值1 范围（0-255）");
    sl_para5.setMin(0);
    sl_para5.setMax(255);
    sl_para6.setMin(0);
    sl_para6.setMax(255);
    sl_para5.setValue(40);
    la_para5.setVisible(true);
    sl_para5.setVisible(true);
    la_para6.setText("阈值2 范围（0-255）");
    sl_para6.setValue(200);
    la_para6.setVisible(true);
    sl_para6.setVisible(true);
    btn_go.setVisible(true);
    la_para5v.setVisible(true);
    la_para6v.setVisible(true);
  }

  public void btn_sobelClick(ActionEvent actionEvent) {
    resetParas();
    service.sobel();
  }

  public void btn_sharpingClick(ActionEvent actionEvent) {
    resetParas();
    service.sharping();
  }

  public void btn_detectConersClick(ActionEvent actionEvent) {
    resetParas();
    service.detectConers();
  }

  public void btn_clearClick(ActionEvent actionEvent) {
    txa_output.clear();
  }
}
