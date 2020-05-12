package org.csu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/12 0:23
 */
public class TimeUtil {
  static final String str = "[yyy/MM/dd HH:mm:ss]";

  public static String date2formatStr(Date date){
    SimpleDateFormat sdf = new SimpleDateFormat(str);
    return sdf.format(date);
  }


}
