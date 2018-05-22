package com.example.demo;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class DemoApplicationTests {

  @Test
  public void contextLoads() throws IOException {

    File file = new File("G:\\imgs\\dog.jpg");
    Thumbnails.of(file).width(100).height(579).keepAspectRatio(false).toFile(new File("G:\\imgs\\dog152.jpg"));
  }

}
