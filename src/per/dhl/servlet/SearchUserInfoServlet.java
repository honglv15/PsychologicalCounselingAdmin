package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.UserInfo;
import per.dhl.service.UserInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 搜索
 * @author: HongLi
 * @create: 2021-07-12 15:02
 */
@WebServlet("/SearchUserInfoServlet")
public class SearchUserInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userNameSearch = req.getParameter("userNameSearch");
        Integer userStatus = Integer.valueOf(req.getParameter("userStatus"));
        Integer limit = Integer.valueOf(req.getParameter("limit"));
        Integer offset = Integer.valueOf(req.getParameter("offset"));

        UserInfoService userInfoService = ServiceFactory.newInstance().getService(UserInfoService.class);
        ArrayList<UserInfo> userList = userInfoService.searchUserInfo(userNameSearch, userStatus, limit, offset);
        Integer userCount = userInfoService.SearchCountUsers(userNameSearch, userStatus);

        System.out.println("表格" + userList);

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
