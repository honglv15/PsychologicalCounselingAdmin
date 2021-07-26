package per.dhl.service;

import per.dhl.qo.AppointmentListQo;
import per.dhl.qo.AppointmentManagementQo;
import per.dhl.qo.OrderTableQo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 预约表格的服务
 * @author: HongLi
 * @create: 2021-07-21 09:55
 */
public interface OrderInfoService {
    ArrayList<AppointmentListQo> getOrderTable(String stime, String etime, Integer state, Integer limit, Integer offset);

    Integer confirmAppointment(String orderTime, Integer id, Integer userId);

    Integer countOrderTable(String stime, String etime, Integer state, Integer limit, Integer offset);

    Integer DiagnosisUser(String myAnswer, Integer userId);

    ArrayList<OrderTableQo> GetDetailOrderInfo(String orderTime, Integer userId);

    ArrayList<AppointmentManagementQo> AppointmentManagement(String consult, String userAccount, String stime, String etime, Integer limit, Integer offset);

    Integer TerminateAppointment(Integer userId, String orderTime);
}
