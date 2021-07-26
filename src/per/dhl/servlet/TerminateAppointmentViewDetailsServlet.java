package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.qo.OrderTableQo;
import per.dhl.service.OrderInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 预约管理查看详情
 * @author: HongLi
 * @create: 2021-07-25 21:23
 */
@WebServlet("/TerminateAppointmentViewDetailsServlet")
public class TerminateAppointmentViewDetailsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("预约管理查看详情");

        System.out.println("预定列表详细信息");
//        HttpSession session = req.getSession();//本次请求的session对象
//        AdminInfo adminInfo = (AdminInfo) session.getAttribute("admin");
//        Integer adminId = adminInfo.getAdminId();
        String orderTime = req.getParameter("orderTime");
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        OrderInfoService service = ServiceFactory.newInstance().getService(OrderInfoService.class);
        ArrayList<OrderTableQo> orderInfos = service.GetDetailOrderInfo(orderTime, userId);
        System.out.println(orderInfos);
        System.out.println(userId + " " + orderTime + " ");
        JsonDto jsonDto = new JsonDto();
        if (orderInfos.size() > 0) {
            jsonDto.getDatas().put("orderInfos", orderInfos);
            jsonDto.setMessage("查看成功");
        } else {
            jsonDto.setMessage("查看成功");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);

    }
}
