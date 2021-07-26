package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.service.AdminInfoService;

import java.io.IOException;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 删除后台用户
 * @author: HongLi
 * @create: 2021-07-12 21:20
 */
@WebServlet("/delAdmServelet")
public class DelAdmServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String AdmAccount = req.getParameter("AdmAccount");

        AdminInfoService adminInfoService = ServiceFactory.newInstance().getService(AdminInfoService.class);
        Integer delAdmCount = adminInfoService.delAdmStatus(AdmAccount);
        System.out.println("删除后台用户"+AdmAccount+"修改"+delAdmCount);

        JsonDto jsonDto = new JsonDto();
        if (delAdmCount > 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("删除失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
