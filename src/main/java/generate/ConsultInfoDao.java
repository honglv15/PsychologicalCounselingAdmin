package generate;

import generate.ConsultInfo;

public interface ConsultInfoDao {
    int deleteByPrimaryKey(Integer consultId);

    int insert(ConsultInfo record);

    int insertSelective(ConsultInfo record);

    ConsultInfo selectByPrimaryKey(Integer consultId);

    int updateByPrimaryKeySelective(ConsultInfo record);

    int updateByPrimaryKey(ConsultInfo record);
}