package org.csu.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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

  private File file;

  private ProcessService service;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    service=new ProcessService(this);
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
    chooser.setInitialDirectory(new File("I:\\")); // 设置文件对话框的初始目录

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
  }

  private void usableBtn(){
    btn_output.setDisable(false);
    btn_pan.setDisable(false);
    btn_spin.setDisable(false);
    btn_zoom.setDisable(false);
    btn_fourierTransform.setDisable(false);
    btn_txzjyzh.setDisable(false);
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
    else if (type.isEmpty()){
      this.addNewLog("无可用操作");
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
}
