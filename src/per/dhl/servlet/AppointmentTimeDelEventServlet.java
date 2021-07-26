package per.dhl.servlet;

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
import per.dhl.service.CalendarInfoService;

import java.io.IOException;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 删除日历的事件
 * @author: HongLi
 * @create: 2021-07-16 23:28
 */
@WebServlet("/AppointmentTimeDelEventServlet")
public class AppointmentTimeDelEventServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("删除事件");

        String eventTime = req.getParameter("EventTime");
        HttpSession session = req.getSession();//本次请求的session对象
        AdminInfo admin = (AdminInfo) session.getAttribute("admin");
        CalendarInfoService calendarInfoService = ServiceFactory.newInstance().getService(CalendarInfoService.class);
        Integer DelEventsCount = calendarInfoService.DelEvent(Integer.valueOf(admin.getAdminId()), eventTime);
        System.out.println("删除事件数:" + DelEventsCount);
        System.out.println("删除事件" + eventTime + " " + admin.getAdminId() + " " + DelEventsCount);
        JsonDto jsonDto = new JsonDto();
        if (DelEventsCount > 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("删除成功");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("删除失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
