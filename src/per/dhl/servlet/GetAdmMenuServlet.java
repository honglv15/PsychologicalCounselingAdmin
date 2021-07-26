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
import per.dhl.pojo.MenuInfo;
import per.dhl.service.AdminInfoService;
import per.dhl.service.MenuInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 得到管理员菜单
 * @author: HongLi
 * @create: 2021-07-10 10:06
 */
@WebServlet("/GetAdmMenuServlet")
public class GetAdmMenuServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("adm菜单");
        HttpSession session = req.getSession();//本次请求的session对象
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("admin");
        System.out.println("通过session拿到的值" + adminInfo);
        Integer roleId = adminInfo.getRoleId();
        MenuInfoService menuInfoService = ServiceFactory.newInstance().getService(MenuInfoService.class);
        ArrayList<MenuInfo> menuInfos = menuInfoService.GetMenu(roleId);
        System.out.println("菜单信息:"+menuInfos);
        JsonDto jsonDto = new JsonDto();
        if (menuInfos.size()>0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("menuInfos",menuInfos);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("菜单加载错误");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
