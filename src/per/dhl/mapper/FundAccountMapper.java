package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.pojo.UserIncomeInfo;
import per.dhl.qo.FundAccountQo;
import per.dhl.qo.UserIncomeInfoQo;

import java.util.ArrayList;

public interface FundAccountMapper {
    ArrayList<FundAccountQo> GetFundAccount(@Param("lim") Integer lim, @Param("off") Integer off);

    Integer CountFundAccount();

    double getConsultMoney(@Param("adminId") Integer adminId);
}
