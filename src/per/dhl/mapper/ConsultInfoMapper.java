package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;

public interface ConsultInfoMapper {
    Integer AddConsultInfo(@Param("admId") Integer admId, @Param("selectedVal") Integer selectedVal, @Param("school") String school, @Param("cost") double cost, @Param("pbg") String pbg, @Param("brief") String brief);

    Integer AddConsultFiledInfo(@Param("admId") Integer admId, @Param("filedId") String filedId);
}
