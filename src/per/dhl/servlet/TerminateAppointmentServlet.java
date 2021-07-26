package per.dhl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.factory.ServiceFactory;
import per.dhl.service.OrderInfoService;

import java.io.IOException;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 终止预约
 * @author: HongLi
 * @create: 2021-07-25 12:30
 */

@WebServlet("/TerminateAppointmentServlet")
public class TerminateAppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("终止预约");
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        String orderTime = req.getParameter("orderTime");
        System.out.println(userId+" "+orderTime);

        OrderInfoService service = ServiceFactory.newInstance().getService(OrderInfoService.class);
        Integer integer = service.TerminateAppointment(userId, orderTime);


    }
}
