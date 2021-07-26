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
import per.dhl.qo.AdmTableQo;
import per.dhl.service.AdminInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 后台用户Servlet
 * @author: HongLi
 * @create: 2021-07-12 13:20
 */
@WebServlet("/BgUserInfoGetTableServlet")
public class BgUserInfoGetTableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer limit = Integer.valueOf(req.getParameter("limit"));
        Integer offset = Integer.valueOf(req.getParameter("offset"));

        AdminInfoService adminInfoService = ServiceFactory.newInstance().getService(AdminInfoService.class);
        ArrayList<AdmTableQo> userList = adminInfoService.selectAdms(limit, offset);
        Integer userCount = adminInfoService.countSelectAdms();

        System.out.println( userList+"管理员用户"+userCount);

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
