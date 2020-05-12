package org.csu.utils;

import java.io.File;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/12 0:29
 */
public class PathUtil {
  static final String outputPath="src/main/resources/outputImg/";

  public static String outputFilePath(File file){
    return outputPath+file.getName();
  }

  public static String outputFilePath(File file,String type){

    String[] split = file.getName().split("[.]");
    return outputPath+split[0]+"-"+type+"."+split[1];
  }

}
