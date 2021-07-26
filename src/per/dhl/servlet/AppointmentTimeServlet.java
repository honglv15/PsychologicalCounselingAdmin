package per.dhl.servlet;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.AdminInfo;
import per.dhl.service.AdminInfoService;
import per.dhl.service.CalendarInfoService;

import java.io.IOException;
import java.util.Date;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 预定时间Servlet
 * @author: HongLi
 * @create: 2021-07-15 10:57
 */
@WebServlet("/AppointmentTimeServlet")
public class AppointmentTimeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("咨询师时间预约");
        String[] orderTimeList = req.getParameterValues("orderTime[]");
        HttpSession session = req.getSession();//本次请求的session对象
        AdminInfo admin = (AdminInfo) session.getAttribute("admin");

        System.out.println(orderTimeList + " " + "用户ID" + admin.getAdminId() + orderTimeList);
        int index = 0;
        for (String orderTime : orderTimeList) {
            CalendarInfoService calendarInfoService = ServiceFactory.newInstance().getService(CalendarInfoService.class);
            Integer addEventsCount = calendarInfoService.AddEvents(admin.getAdminId(), orderTime);
            System.out.println(orderTime);
            System.out.println(addEventsCount);
            index++;
        }
        JsonDto jsonDto = new JsonDto();
        if (index > 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("预约设置成功");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("预约设置失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }

}
