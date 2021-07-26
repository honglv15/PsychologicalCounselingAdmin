package per.dhl.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.dto.JsonDto;
import per.dhl.factory.ServiceFactory;
import per.dhl.pojo.UserInfo;
import per.dhl.qo.AdmTableQo;
import per.dhl.service.AdminInfoService;
import per.dhl.service.UserInfoService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 后台用户搜索
 * @author: HongLi
 * @create: 2021-07-12 21:59
 */
@WebServlet("/SearchAdmInfoServlet")
public class SearchAdmInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("后台用户搜索");
        String AdmNameSearch = req.getParameter("AdmNameSearch");
        String AdmStatus = req.getParameter("AdmStatus");
        String titleSelect = req.getParameter("titleSelect");
        Integer limit = Integer.valueOf(req.getParameter("limit"));
        Integer offset = Integer.valueOf(req.getParameter("offset"));

        AdminInfoService adminInfoService = ServiceFactory.newInstance().getService(AdminInfoService.class);
        ArrayList<AdmTableQo> AdmList = adminInfoService.searchAdmInfo(AdmNameSearch, AdmStatus,titleSelect, limit, offset);
        Integer AdmCount = adminInfoService.searchCountAdm(AdmNameSearch, AdmStatus,titleSelect);

        System.out.println("后台用户搜索 "+AdmNameSearch+" "+AdmStatus+" "+titleSelect+" "+limit+" "+offset);
        System.out.println("后台用户结果"+AdmList+" "+AdmCount);

        JsonDto jsonDto = new JsonDto();
        if (AdmList.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("userList", AdmList);
            jsonDto.getDatas().put("userCount", AdmCount);
            jsonDto.setMessage("success");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("修改失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);
    }
}
