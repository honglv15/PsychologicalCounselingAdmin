package generate;

import generate.UserIncomeInfo;

public interface UserIncomeInfoDao {
    int deleteByPrimaryKey(Integer incomeId);

    int insert(UserIncomeInfo record);

    int insertSelective(UserIncomeInfo record);

    UserIncomeInfo selectByPrimaryKey(Integer incomeId);

    int updateByPrimaryKeySelective(UserIncomeInfo record);

    int updateByPrimaryKey(UserIncomeInfo record);
}