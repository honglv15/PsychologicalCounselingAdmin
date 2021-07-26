package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.service.UserInfoService;

import java.io.IOException;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 重置密码
 * @author: HongLi
 * @create: 2021-07-12 11:21
 */
@WebServlet("/ResetUserPwdServelet")
public class ResetUserPwdServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ResetUserPwdServelet");
        String userAccount = req.getParameter("userAccount");
        String userPwd = "123";
        UserInfoService userInfoService = ServiceFactory.newInstance().getService(UserInfoService.class);
        Integer resetPwdCount = userInfoService.resetPwd(userAccount, userPwd);
        JsonDto jsonDto = new JsonDto();
        if (resetPwdCount > 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("重置密码成功");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("修改失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
