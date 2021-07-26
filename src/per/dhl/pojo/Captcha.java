package per.dhl.pojo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Description: 验证码
 * @date: 2021/7/1 13:17
 */
public class Captcha {

   private int width;
   private int height;
   private int codeNum;
   private String codeSet = "2345678abcdefhijkmnpqrstuvwxyzABCDEFGHJKLMNPQRTUVWXY";

   private String code;
   private BufferedImage  image;
   private Graphics2D drawer;


   public Captcha(int width , int height , int codeNum) {
       this.width = width;
       this.height = height;
       this.codeNum = codeNum;
       this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
       this.drawer = this.image.createGraphics();
       init();
   }

   public Captcha(int width , int height) {
       this(width,height,4);
   }
   public Captcha(int codeNum) {
       this(200,40,codeNum);
   }

   public Captcha() {
       this(200,40,4);
   }

   public BufferedImage getImage() {

       return this.image;
   }


   private void init() {
       drawer.setColor(Color.WHITE);
       drawer.fillRect(0, 0, width, height);
       drawRandomText();
   }


   private void drawRandomText() {
       String code = "";
       int len = codeSet.length();
       Font f = new Font("微软雅黑" , Font.BOLD , 28);
       drawer.setFont(f);
       int perWidth = this.width / this.codeNum;
       for(int i = 0 ; i < codeNum ; i++) {
           int x = perWidth * i + new Random().nextInt(5) + 10;
           int n = new Random().nextInt(len);
           char c = codeSet.charAt(n);
           drawer.setPaint(new Color(new Random().nextInt(220), new Random().nextInt(220), new Random().nextInt(220)));
           drawer.drawString(String.valueOf(c), x, this.height/2 + new Random().nextInt(5)+5);
           code += c;
       }
       this.code = code;
   }

   public String getCode() {
       return this.code;
   }

}
