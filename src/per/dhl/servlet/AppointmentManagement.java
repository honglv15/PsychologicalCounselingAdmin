package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.qo.AppointmentManagementQo;
import per.dhl.service.OrderInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 预约管理
 * @author: HongLi
 * @create: 2021-07-25 09:26
 */
@WebServlet("/AppointmentManagement")
public class AppointmentManagement extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String consult = req.getParameter("consult");
        String userAccount = req.getParameter("userAccount");
        String Stime = req.getParameter("Stime");
        String Etime = req.getParameter("Etime");
        Integer limit = Integer.valueOf(req.getParameter("limit"));
        Integer offset = Integer.valueOf(req.getParameter("offset"));
        System.out.println(consult + userAccount + Stime + Etime + limit + offset);

        OrderInfoService service = ServiceFactory.newInstance().getService(OrderInfoService.class);
        ArrayList<AppointmentManagementQo> appointmentManagementQos = service.AppointmentManagement(consult, userAccount, Stime, Etime, limit, offset);

        System.out.println(appointmentManagementQos);

        JsonDto jsonDto = new JsonDto();
        if (appointmentManagementQos.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("appointmentManagementQos", appointmentManagementQos);
            jsonDto.setMessage("初始化成功");
        } else {
            jsonDto.setId(1);
            jsonDto.getDatas().put("appointmentManagementQos", appointmentManagementQos);
            jsonDto.setMessage("初始化失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}

