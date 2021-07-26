package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.CalendarInfo;
import per.dhl.service.CalendarInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 日历事件初始化
 * @author: HongLi
 * @create: 2021-07-16 12:43
 */
@WebServlet("/AppointmentTimeInitEvents")
public class AppointmentTimeInitEventsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("初始化事件");
        CalendarInfoService calendarInfoService = ServiceFactory.newInstance().getService(CalendarInfoService.class);
        ArrayList<CalendarInfo> calendarInfos = calendarInfoService.initEvents();
        System.out.println(calendarInfos);


        JsonDto jsonDto = new JsonDto();
        if (calendarInfos.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("calendarInfos", calendarInfos);
            jsonDto.setMessage("初始化成功");
        } else {
            jsonDto.setId(1);
            jsonDto.getDatas().put("calendarInfos", calendarInfos);
            jsonDto.setMessage("初始化失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);


    }
}
