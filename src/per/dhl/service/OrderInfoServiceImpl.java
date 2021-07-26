package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.OrderInfoMapper;
import per.dhl.pojo.ConsultInfo;
import per.dhl.qo.AppointmentListQo;
import per.dhl.qo.AppointmentManagementQo;
import per.dhl.qo.OrderTableQo;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 预约表格服务实现类
 * @author: HongLi
 * @create: 2021-07-21 09:56
 */
@Component
public class OrderInfoServiceImpl implements OrderInfoService {
    Integer ifUpdate;

    @Override
    public ArrayList<AppointmentListQo> getOrderTable(String stime, String etime, Integer state, Integer limit, Integer offset) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderInfoMapper mapper = session.getMapper(OrderInfoMapper.class);
        ArrayList<AppointmentListQo> orderTable = mapper.getOrderTable(stime, etime, state, limit, offset);
        session.commit();
        session.close();
        return orderTable;
    }


    @Override
    public Integer confirmAppointment(String orderTime, Integer adminId, Integer userId) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderInfoMapper mapper = session.getMapper(OrderInfoMapper.class);
        double userMoney = mapper.queryUserIfMoney(userId);//查询用户是否有钱
        ConsultInfo consultInfos = mapper.queryConsultMoney(adminId);//查询咨询师看诊的费用
        Double consultFee = consultInfos.getConsultFee();//得到咨询师看诊的费用
        if (userMoney - consultFee <= 0) {
            session.close();
            return 0;
        } else {
            //确认预约
            Integer integer = mapper.confirmAppointment(orderTime, userId);
            double balance = userMoney - consultFee;
            //更新用户的余额
            Integer moneyIfUpdate = mapper.updateUserMoney(balance, userId);
            System.out.println("用户余额是否更新成功:" + moneyIfUpdate);
            //更新咨询师资金
            Double consultMoney = consultInfos.getConsultMoney();//咨询师的口袋的钱
            double nowConsultMoney = consultMoney + consultFee; //咨询师现在的钱
            Integer updateConsultMoney = mapper.updateConsultMoney(nowConsultMoney, adminId);
            System.out.println("咨询师余额是否更新成功:" + updateConsultMoney);
            //添加咨询表格
            Integer consultId = consultInfos.getConsultId();//咨询师ID
            String order = "预约";
            Integer ifAddConsultAccountTable = mapper.addConsultAccountTable(userId, consultId, consultFee, order);
            System.out.println("插入咨询师账户业务表格:" + ifAddConsultAccountTable);
            //用户表格更新
            String pay = "支出";
            Integer ifAddUserAccountTable = mapper.addUserAccountTable(userId, consultId, consultFee, order, pay);
            System.out.println("插入用户账户表格:" + ifAddUserAccountTable);
            session.commit();
            session.close();
            return integer;
        }
    }

    @Override
    public Integer countOrderTable(String stime, String etime, Integer state, Integer limit, Integer offset) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderInfoMapper mapper = session.getMapper(OrderInfoMapper.class);
        Integer integer = mapper.countOrderTable(stime, etime, state, limit, offset);
        session.commit();
        session.close();
        return integer;
    }

    @Override
    public Integer DiagnosisUser(String myAnswer, Integer userId) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderInfoMapper mapper = session.getMapper(OrderInfoMapper.class);
        Integer integer = mapper.DiagnosisUser(myAnswer, userId);
        if (integer > 0) {
            ifUpdate = mapper.updateState(userId);
        }
        if (ifUpdate >= 0) {
            session.commit();
            session.close();
            return integer;
        } else {
            return 0;
        }

    }

    @Override
    public ArrayList<OrderTableQo> GetDetailOrderInfo(String orderTime, Integer userId) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderInfoMapper mapper = session.getMapper(OrderInfoMapper.class);
        ArrayList<OrderTableQo> orderInfos = mapper.GetDetailOrderInfo(orderTime, userId);
        session.commit();
        session.close();
        return orderInfos;
    }

    @Override
    public ArrayList<AppointmentManagementQo> AppointmentManagement(String consult, String userAccount, String stime, String etime, Integer limit, Integer offset) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderInfoMapper mapper = session.getMapper(OrderInfoMapper.class);
        ArrayList<AppointmentManagementQo> appointmentListQos = mapper.AppointmentManagement(consult, userAccount, stime, etime, limit, offset);
        session.commit();
        session.close();
        return appointmentListQos;
    }

    @Override
    public Integer TerminateAppointment(Integer userId, String orderTime) {
        SqlSession session = SqlSessionUtil.getSession();
        OrderInfoMapper mapper = session.getMapper(OrderInfoMapper.class);
        Integer integer = mapper.TerminateAppointment(userId, orderTime);
        session.commit();
        session.close();
        return integer;
    }
}
