package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.qo.AppointmentListQo;
import per.dhl.service.CalendarInfoService;
import per.dhl.service.OrderInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 得到预约列表
 * @author: HongLi
 * @create: 2021-07-21 09:43
 */
@WebServlet("/GetOrderTableServlet")
public class GetOrderTableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer limit = Integer.valueOf(req.getParameter("limit"));
        Integer offset = Integer.valueOf(req.getParameter("offset"));

        System.out.println("翻页" + limit + offset);
        String Stime = req.getParameter("Stime");
        String Etime = req.getParameter("Etime");
        Integer State = Integer.valueOf(req.getParameter("State"));
        System.out.println("得到预约列表参数" + "#" + Stime + "#" + Etime + "#" + State);
        OrderInfoService service = ServiceFactory.newInstance().getService(OrderInfoService.class);
        ArrayList<AppointmentListQo> orderTable = service.getOrderTable(Stime, Etime, State, limit, offset);
        Integer countOrderTableCount = service.countOrderTable(Stime, Etime, State, limit, offset);
        System.out.println("得到预约列表" + orderTable);
        JsonDto jsonDto = new JsonDto();
        if (orderTable.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("orderTable", orderTable);
            jsonDto.getDatas().put("countOrderTableCount", countOrderTableCount);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("表格加载错误");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
