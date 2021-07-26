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
import per.dhl.pojo.UserIncomeInfo;
import per.dhl.qo.FundAccountQo;
import per.dhl.qo.UserIncomeInfoQo;
import per.dhl.service.FundAccountService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 资金账户servlet
 * @author: HongLi
 * @create: 2021-07-14 09:03
 */
@WebServlet("/AdmFundAccountGetTableServlet")
public class AdmFundAccountGetTableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer lim = Integer.valueOf(req.getParameter("limit"));
        Integer off = Integer.valueOf(req.getParameter("offset"));
        System.out.println("FundAccountGetTableServlet" + "get" + lim + " " + off);
        FundAccountService fundAccountService = ServiceFactory.newInstance().getService(FundAccountService.class);
        ArrayList<FundAccountQo> GetFundAccountInfo = fundAccountService.GetFundAccount(lim, off);
        Integer GetFundAccountInfoCount = fundAccountService.CountFundAccount();
        //得到余额

        HttpSession session = req.getSession();
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("admin");
        Integer adminId = adminInfo.getAdminId();
        double consultMoney = fundAccountService.getConsultMoney(adminId);

        System.out.println("得到咨询师的余额:" + consultMoney);
        System.out.println("得到的资金表格:" + GetFundAccountInfo + GetFundAccountInfoCount);
        JsonDto jsonDto = new JsonDto();
        if (GetFundAccountInfo.size() > 0) {
            jsonDto.setId(0);
            jsonDto.getDatas().put("GetFundAccountInfo", GetFundAccountInfo);
            jsonDto.getDatas().put("FundAccountCount", GetFundAccountInfoCount);
            jsonDto.getDatas().put("consultMoney", consultMoney);
            jsonDto.setMessage("资金用户加载成功");
        } else {
            jsonDto.setId(1);
            jsonDto.setMessage("资金用户加载失败");
        }
        String jsonStr = JSONObject.toJSONString(jsonDto);
        resp.getWriter().println(jsonStr);


    }
}
