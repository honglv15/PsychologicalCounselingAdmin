package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.AdminInfo;
import per.dhl.service.AdminInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 管理员信息Servlet
 * @author: HongLi
 * @create: 2021-07-09 22:40
 */
@WebServlet("/AdminInfoServlet")
public class AdminInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String admname = req.getParameter("admname");
        String admpwd = req.getParameter("admpwd");
        String captchaCode = req.getParameter("captchaCode");

        System.out.println(admname + " " + admpwd + " " + captchaCode);
        System.out.println("doPost");

        HttpSession session = req.getSession();//本次请求的session对象
        String codeValue = (String) session.getAttribute("code");//得到存放在session中的验证码
        AdminInfoService adminInfoService = ServiceFactory.newInstance().getService(AdminInfoService.class);
        AdminInfo adminInfo = adminInfoService.AdminLogin(admname, admpwd);

        System.out.println("查询出的用户:" + adminInfo);

        session.setAttribute("admin", adminInfo);

        JsonDto jsonDto = new JsonDto();
        if (adminInfo != null) {
            jsonDto.setId(0);
            jsonDto.setMessage("success");
            jsonDto.setLocation("AdmMain.html");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("账号密码错误！");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);

    }
}
