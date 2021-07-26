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
import per.dhl.service.UserInfoService;

import java.io.IOException;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 修改后台用户状态
 * @author: HongLi
 * @create: 2021-07-12 20:56
 */
@WebServlet("/ModifyAdmStatusServelet")
public class ModifyAdmStatusServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String AdmAccount = req.getParameter("AdmAccount");
        Integer AdmStatus = Integer.valueOf(req.getParameter("AdmStatus"));
        System.out.println("修改后台用户信息"+AdmAccount+" "+AdmStatus);

        AdminInfoService adminInfoService = ServiceFactory.newInstance().getService(AdminInfoService.class);
        Integer modifyCount = adminInfoService.modifyAdmStatus(AdmAccount, AdmStatus);
        System.out.println("修改后台用户行数"+modifyCount);

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
