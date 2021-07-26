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
import per.dhl.service.ConsultInfoService;

import java.io.IOException;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 添加用户信息
 * @author: HongLi
 * @create: 2021-07-13 09:58
 */
@WebServlet("/AddAdmInfoServlet")
public class AddAdmInfoServlet extends HttpServlet {
    private Integer ConsultFiledInfoCount;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("添加后台用户");
        String admAccount = req.getParameter("$admAccount");
        String admPwd = req.getParameter("$admPwd");
        Integer admRoleVal = Integer.valueOf(req.getParameter("$admRoleVal"));

        System.out.println("角色"+admRoleVal);

        JsonDto jsonDto = new JsonDto();
//        添加后台管理员用户表
        if (admRoleVal == 1) {
            AdminInfoService adminInfoService = ServiceFactory.newInstance().getService(AdminInfoService.class);
            Integer count = adminInfoService.AddAdmInfo(admAccount, admPwd, 1);
            System.out.println("管理员添加" + count);
            Integer admId = adminInfoService.getAdmID(admAccount);
            System.out.println("用户ID" + admId);
            ConsultInfoService consultInfoService = ServiceFactory.newInstance().getService(ConsultInfoService.class);
            Integer ConsultInfoCount = consultInfoService.AddConsultInfo(admId, 5, null, 0, null, null);

            if (ConsultInfoCount > 0) {
                jsonDto.setId(0);
                jsonDto.setMessage("管理员添加成功");
            } else {
                jsonDto.setId(1);
                jsonDto.setMessage("失败");
            }
            String jsonStr = JSONObject.toJSONString(jsonDto);
            resp.getWriter().println(jsonStr);
        } else if (admRoleVal == 2) {
            Integer selectedVal = Integer.valueOf(req.getParameter("$selectedVal"));
            String[] AddFiledList = req.getParameterValues("AddFiledList[]");
            String School = req.getParameter("$School");
            double cost = Double.parseDouble(req.getParameter("$cost"));
            String pbg = req.getParameter("$pbg");
            String brief = req.getParameter("$brief");
            System.out.println("添加用户:" + admAccount + " " + admPwd + " " + admRoleVal + " " + selectedVal + " 用户领域表:" + AddFiledList + " " + School + " 费用" + cost + " " + pbg + " " + brief);

            AdminInfoService adminInfoService = ServiceFactory.newInstance().getService(AdminInfoService.class);
            Integer count = adminInfoService.AddAdmInfo(admAccount, admPwd, 2);
            if (count > 0) {
                Integer admId = adminInfoService.getAdmID(admAccount);
                System.out.println("用户ID" + admId);
                ConsultInfoService consultInfoService = ServiceFactory.newInstance().getService(ConsultInfoService.class);
                Integer ConsultInfoCount = consultInfoService.AddConsultInfo(admId, selectedVal, School, cost, pbg, brief);
                System.out.println("咨询表添加数" + ConsultInfoCount);
                for (String FiledId : AddFiledList) {
                    ConsultFiledInfoCount = consultInfoService.AddConsultFiledInfo(admId, FiledId);
                    System.out.println("添加的领域" + ConsultFiledInfoCount);
                }
                if (ConsultFiledInfoCount > 0) {
                    jsonDto.setId(0);
                    jsonDto.setMessage("咨询师添加成功");
                } else {
                    jsonDto.setId(1);
                    jsonDto.setMessage("咨询师添加失败");
                }
            }
            String jsonStr = JSONObject.toJSONString(jsonDto);
            resp.getWriter().println(jsonStr);
        }
    }


}
