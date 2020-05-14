package org.csu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/12 0:23
 */
public class TimeUtil {
  static final String dateAndTimeStr = "[yyyy/MM/dd HH:mm:ss]";
  static final String dateStr="yyyyMMddHHmmss";

  public static String date2DTformatStr(Date date){
    SimpleDateFormat sdf = new SimpleDateFormat(dateAndTimeStr);
    return sdf.format(date);
  }

  public static String date2DformatStr(Date date){
    SimpleDateFormat sdf = new SimpleDateFormat(dateStr);
    return sdf.format(date);
  }


}
