package org.csu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/15 0:03
 */
public class FileUtil {
  public static boolean copyFile(File srcFile,String targetPath) throws IOException {
    File targetFile=new File(targetPath);
    if (targetFile.exists())
      return false;
    InputStream input = null;
    OutputStream output = null;

    try {
      input = new FileInputStream(srcFile);
      output = new FileOutputStream(targetFile);
      byte[] buf = new byte[1024];
      int bytesRead;
      while ((bytesRead = input.read(buf)) > 0) {
        output.write(buf, 0, bytesRead);
      }
      input.close();
      output.close();
      return true;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
  }

}
