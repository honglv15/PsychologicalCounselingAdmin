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
import per.dhl.service.OrderInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 用户诊断
 * @author: HongLi
 * @create: 2021-07-23 10:51
 */
@WebServlet("/DiagnosisUserServlet")
public class DiagnosisUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("用户诊断");
        String myAnswer = req.getParameter("myAnswer");
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        System.out.println(myAnswer + userId);

        OrderInfoService service = ServiceFactory.newInstance().getService(OrderInfoService.class);
        Integer integer = service.DiagnosisUser(myAnswer, userId);

        JsonDto jsonDto = new JsonDto();
        if (integer > 0) {
            jsonDto.setId(0);
            jsonDto.setMessage("诊断成功");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("诊断失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
