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
 * @description: 修改用户状态
 * @author: HongLi
 * @create: 2021-07-11 22:42
 */
@WebServlet("/ModifyUserStatusServelet")
public class ModifyUserStatusServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userAccount = req.getParameter("userAccount");
        Integer userStatus = Integer.valueOf(req.getParameter("userStatus"));

        UserInfoService userInfoService = ServiceFactory.newInstance().getService(UserInfoService.class);
        Integer modifyCount = userInfoService.modifyUserStatus(userAccount, userStatus);
        System.out.println(userAccount + " " + userStatus+" 修改后"+modifyCount);
        System.out.println("修改后+"+ modifyCount);
        JsonDto jsonDto = new JsonDto();
        if (modifyCount != 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("修改失败");
        }

        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
