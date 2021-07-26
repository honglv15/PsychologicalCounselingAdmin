package generate;

import generate.AdminIncomeInfo;

public interface AdminIncomeInfoDao {
    int deleteByPrimaryKey(Integer aincomeId);

    int insert(AdminIncomeInfo record);

    int insertSelective(AdminIncomeInfo record);

    AdminIncomeInfo selectByPrimaryKey(Integer aincomeId);

    int updateByPrimaryKeySelective(AdminIncomeInfo record);

    int updateByPrimaryKey(AdminIncomeInfo record);
}