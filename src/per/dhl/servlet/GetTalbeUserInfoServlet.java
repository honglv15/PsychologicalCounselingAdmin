package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.AdminInfo;
import per.dhl.pojo.UserInfo;
import per.dhl.service.AdminInfoService;
import per.dhl.service.UserInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 得到用户表格
 * @author: HongLi
 * @create: 2021-07-10 14:24
 */
@WebServlet("/GetTalbeUserInfoServlet")
public class GetTalbeUserInfoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userNameSearch = req.getParameter("userNameSearch");
        Integer userIfDel = Integer.valueOf(req.getParameter("userIfDel"));
        Integer limit = Integer.valueOf(req.getParameter("limit"));
        Integer offset = Integer.valueOf(req.getParameter("offset"));


        System.out.println("搜索字段"+userNameSearch+" "+userIfDel);
        UserInfoService userInfoService = ServiceFactory.newInstance().getService(UserInfoService.class);
        ArrayList<UserInfo> userList = userInfoService.selectUsers(userNameSearch, userIfDel, limit, offset);
        Integer userCount = userInfoService.countUsers(userNameSearch, userIfDel, limit, offset);

        System.out.println("表格"+userList);

        JsonDto jsonDto = new JsonDto();
        if (userList.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("userList", userList);
            jsonDto.getDatas().put("userCount", userCount);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("修改失败");
        }

        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
