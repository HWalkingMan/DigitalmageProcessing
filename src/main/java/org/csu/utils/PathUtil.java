package org.csu.utils;

import java.io.File;
import java.util.Date;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/12 0:29
 */
public class PathUtil {
  static final String outputPath="src/main/resources/outputImg/";
  static final String outputTempPath="src/main/resources/temp/";

  public static String outputTempFilePath(File file){
    return outputTempPath+file.getName();
  }

  public static String outputTempFilePath(File file,String type){

    String[] split = file.getName().split("[.]");
    return outputTempPath+split[0]+"-"+type+"."+split[1];
  }

  public static String outputFilePath(File file){
    String[] split = file.getName().split("[.]");
    return outputPath+split[0]+"-"+TimeUtil.date2DformatStr(new Date())+"."+split[1];
  }

}
