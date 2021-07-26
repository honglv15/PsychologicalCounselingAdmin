package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.pojo.MenuInfo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description:
 * @author: HongLi
 * @create: 2021-07-10 10:50
 */
public interface MenuInfoMapper {
    ArrayList<MenuInfo> getMenuById(@Param("roleId") Integer roleId);
}
