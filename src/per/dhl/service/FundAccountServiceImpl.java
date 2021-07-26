package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.FundAccountMapper;
import per.dhl.qo.FundAccountQo;
import per.dhl.qo.UserIncomeInfoQo;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 资金账户实现类
 * @author: HongLi
 * @create: 2021-07-14 09:05
 */
@Component
public class FundAccountServiceImpl implements FundAccountService {

    @Override
    public ArrayList<FundAccountQo> GetFundAccount(Integer lim, Integer off) {
        SqlSession session = SqlSessionUtil.getSession();
        FundAccountMapper mapper = session.getMapper(FundAccountMapper.class);
        ArrayList<FundAccountQo> userIncomeInfoQos = mapper.GetFundAccount(lim, off);
        session.commit();
        session.close();
        return userIncomeInfoQos;
    }

    @Override
    public Integer CountFundAccount() {
        SqlSession session = SqlSessionUtil.getSession();
        FundAccountMapper mapper = session.getMapper(FundAccountMapper.class);
        Integer userIncomeInfosCount = mapper.CountFundAccount();
        session.commit();
        session.close();
        return userIncomeInfosCount;
    }

    @Override
    public double getConsultMoney(Integer adminId) {
        SqlSession session = SqlSessionUtil.getSession();
        FundAccountMapper mapper = session.getMapper(FundAccountMapper.class);
        double consultMoney = mapper.getConsultMoney(adminId);
        session.commit();
        session.close();
        return consultMoney;
    }
}
