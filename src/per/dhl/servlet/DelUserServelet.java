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
 * @description: 删除用户
 * @author: HongLi
 * @create: 2021-07-12 10:27
 */
@WebServlet("/delUserServelet")
public class DelUserServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("delUserServelet");
        String userAccount = req.getParameter("userAccount");
        UserInfoService userInfoService = ServiceFactory.newInstance().getService(UserInfoService.class);
        Integer delUserCount = userInfoService.delUser(userAccount);
        System.out.println("删除行数"+delUserCount);
        JsonDto jsonDto = new JsonDto();
        if (delUserCount > 0) {
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
