package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.AdminInfo;
import per.dhl.qo.AppointmentListQo;
import per.dhl.service.OrderInfoService;
import per.dhl.util.SqlSessionUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 确认预约servlet
 * @author: HongLi
 * @create: 2021-07-21 14:16
 */
@WebServlet("/ConfirmAppointmentServlet")
public class ConfirmAppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("确认预约");
        HttpSession session = req.getSession();//本次请求的session对象
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("admin");
        Integer adminId = adminInfo.getAdminId();

        String orderTime = req.getParameter("orderTime");
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        OrderInfoService service = ServiceFactory.newInstance().getService(OrderInfoService.class);
        Integer integer = service.confirmAppointment(orderTime, adminId, userId);

        System.out.println(userId + " " + orderTime + " " + adminId);
        JsonDto jsonDto = new JsonDto();
        if (integer > 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("确认预约成功");
        } else if (integer == 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("用户账户余额不足");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("确认预约失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
