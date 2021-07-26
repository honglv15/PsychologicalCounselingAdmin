package per.dhl.service;

import per.dhl.pojo.MenuInfo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 用户菜单服务接口
 * @author: HongLi
 * @create: 2021-07-10 10:07
 */
public interface MenuInfoService {
     /**
      * @Description: 得到菜单
      * @param:    角色Id
      * @return:  对应用户的菜单信息
      * @date: 2021/7/10 10:48
      */
    ArrayList<MenuInfo> GetMenu(Integer roleId);
}
