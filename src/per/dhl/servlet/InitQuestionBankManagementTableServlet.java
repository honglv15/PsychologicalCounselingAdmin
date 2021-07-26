package per.dhl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import per.dhl.factory.ServiceFactory;
import per.dhl.service.QuestionBankManagementService;

import java.io.IOException;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 初始化题库管理表格
 * @author: HongLi
 * @create: 2021-07-26 10:33
 */
@WebServlet("/InitQuestionBankManagementTableServlet")
public class InitQuestionBankManagementTableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("初始化题库管理表格");
        Integer selectValue = Integer.valueOf(req.getParameter("selectValue"));
        Integer limit = Integer.valueOf(req.getParameter("limit"));
        Integer offset = Integer.valueOf(req.getParameter("offset"));
        System.out.println(selectValue + limit + offset);

        QuestionBankManagementService service = ServiceFactory.newInstance().getService(QuestionBankManagementService.class);
        service.InitQuestionBankManagementTable(selectValue,limit,offset);


    }
}
