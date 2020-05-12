package org.csu.controller;

import java.io.File;
import java.io.FileOutputStream;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.csu.service.ProcessService;
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
      img_input.setImage(new Image(file.toURI().toString()));
      service.setFile(file);
    }
  }

  public void btn_outputClick(ActionEvent actionEvent) {
    String outputPath = service.getOutputPath();
    if (outputPath == null || outputPath.isEmpty()) {
      this.addNewLog("无可输出内容");
    }
    else {
      this.addNewLog("文件输出至 [ " + outputPath + " ]");
    }
  }

  public void btn_txzjyzhClick(ActionEvent actionEvent) {
    service.txzjyzh();
  }

  public void addNewLog(String msg){
    txa_output.appendText(TimeUtil.date2formatStr(new Date())+" "+msg+"\n");
  }

  public void showOutputImg(File file){
    img_output.setImage(new Image(file.toURI().toString()));
  }

}
