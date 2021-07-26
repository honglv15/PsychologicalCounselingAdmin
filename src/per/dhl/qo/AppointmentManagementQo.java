package per.dhl.qo;

import jakarta.servlet.http.HttpServlet;
import lombok.Data;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 预约管理Qo
 * @author: HongLi
 * @create: 2021-07-25 09:56
 */
@Data
public class AppointmentManagementQo {
    private String orderTime;
    private String userId;
    private String userAccount;
    private String adminAccount;
    private String fieldName;
    private String orderSuctime;
    private String stateName;
    private String CommentTime;
}
