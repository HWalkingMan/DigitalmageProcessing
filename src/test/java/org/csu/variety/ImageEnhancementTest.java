package org.csu.variety;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.Test;
import org.opencv.core.Core;

/**
 * @Description
 * @Author WM
 * @Date 2020/5/22 8:13
 */
public class ImageEnhancementTest {
  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  @Test
  public void contrastEnhancement() {
  }

  @Test
  public void histEqualize() throws Exception {
    ImageEnhancement.histEqualize(new File("src/main/resources/inputImg/duck.jpg"));
  }

  @Test
  public void laplaceEnhance() throws Exception {
    ImageEnhancement.laplaceEnhance(new File("src/main/resources/inputImg/duck.jpg"));
  }

  @Test
  public void logEnhance() throws Exception {
    ImageEnhancement.logEnhance(new File("src/main/resources/inputImg/duck.jpg"));
  }

  @Test
  public void gammaEnhance() throws Exception {
    ImageEnhancement.gammaEnhance(new File("src/main/resources/inputImg/duck.jpg"));
    ImageEnhancement.gammaEnhance(new File("src/main/resources/inputImg/duck.jpg"),1.6);
  }

  @Test
  public void autoHistEqualize() throws Exception {
    ImageEnhancement.autoHistEqualize(new File("src/main/resources/inputImg/duck.jpg"));
  }


  @Test
  public void laplacianSmooth() throws Exception {
    ImageEnhancement.laplacianSmooth(new File("src/main/resources/inputImg/duck.jpg"));
  }

  @Test
  public void sharpening() throws Exception {
    ImageEnhancement.sharpening(new File("src/main/resources/inputImg/lena.jpg"));
  }

  @Test
  public void gaussianBlur() throws Exception {
    ImageEnhancement.gaussianBlur(new File("src/main/resources/inputImg/duck.jpg"));
  }
  
  @Test
  public void medianFiltering() throws Exception {
    ImageEnhancement.medianFiltering(new File("src/main/resources/inputImg/duck.jpg"));
  }

  @Test
  public void blur() throws Exception {
    ImageEnhancement.blur(new File("src/main/resources/inputImg/duck.jpg"),15);
  }

  @Test
  public void saltNoise() throws Exception {
    ImageEnhancement.saltNoise(new File("src/main/resources/inputImg/duck.jpg"));
  }
}