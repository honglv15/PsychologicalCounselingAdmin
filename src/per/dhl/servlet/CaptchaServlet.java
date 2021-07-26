package per.dhl.servlet;

import per.dhl.pojo.Captcha;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @program: JF21030701
 * @description:
 * @author: HongLi
 * @create: 2021-07-01 13:53
 */

@WebServlet("/CaptchaServlet")
public class CaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("doget");
        Captcha captcha = new Captcha(350,35);
        BufferedImage image = captcha.getImage(); //获取图片
        String code = captcha.getCode();  //获取验证码
        req.getSession().setAttribute("code",code);  //把验证码存入session
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        ImageIO.write(image,"jpeg",servletOutputStream); //把图片通过ervletOutput流输出
    }
}
