package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.pojo.ConsultInfo;
import per.dhl.qo.AppointmentListQo;
import per.dhl.qo.AppointmentManagementQo;
import per.dhl.qo.OrderTableQo;

import java.util.ArrayList;

public interface OrderInfoMapper {
    ArrayList<AppointmentListQo> getOrderTable(@Param("stime") String stime, @Param("etime") String etime, @Param("state") Integer state, @Param("limit") Integer limit, @Param("offset") Integer offset);

    Integer countOrderTable(@Param("stime") String stime, @Param("etime") String etime, @Param("state") Integer state, @Param("limit") Integer limit, @Param("offset") Integer offset);

    Integer confirmAppointment(@Param("orderTime") String orderTime, @Param("userId") Integer userId);

    double queryUserIfMoney(@Param("userId") Integer userId);

    ConsultInfo queryConsultMoney(@Param("adminId") Integer adminId);

    Integer updateUserMoney(@Param("balance") double balance, @Param("userId") Integer userId);

    Integer updateConsultMoney(@Param("nowConsultMoney") double nowConsultMoney, @Param("adminId") Integer adminId);

    Integer addConsultAccountTable(@Param("userId") Integer userId, @Param("consultId") Integer consultId, @Param("consultFee") Double consultFee, @Param("order") String order);

    Integer addUserAccountTable(@Param("userId") Integer userId, @Param("consultId") Integer consultId, @Param("consultFee") Double consultFee, @Param("order") String order, @Param("pay") String pay);

    Integer DiagnosisUser(@Param("myAnswer") String myAnswer, @Param("userId") Integer userId);

    Integer updateState(@Param("userId") Integer userId);

    ArrayList<OrderTableQo> GetDetailOrderInfo(@Param("orderTime") String orderTime, @Param("userId") Integer userId);

    ArrayList<AppointmentManagementQo> AppointmentManagement(@Param("consult") String consult, @Param("userAccount") String userAccount, @Param("stime") String stime, @Param("etime") String etime, @Param("limit") Integer limit, @Param("offset") Integer offset);

    Integer TerminateAppointment(@Param("userId") Integer userId, @Param("orderTime") String orderTime);
}
