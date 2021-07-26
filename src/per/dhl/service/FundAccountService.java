package per.dhl.service;

import per.dhl.qo.FundAccountQo;
import per.dhl.qo.UserIncomeInfoQo;

import java.util.ArrayList;

public interface FundAccountService {
   ArrayList<FundAccountQo> GetFundAccount(Integer lim, Integer off);

    Integer CountFundAccount();

    double getConsultMoney(Integer adminId);
}
