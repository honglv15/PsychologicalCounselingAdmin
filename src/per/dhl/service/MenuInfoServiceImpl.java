package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.MenuInfoMapper;
import per.dhl.pojo.MenuInfo;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 管理员菜单实现类
 * @author: HongLi
 * @create: 2021-07-10 10:08
 */
@Component
public class MenuInfoServiceImpl implements MenuInfoService {

    @Override
    public ArrayList<MenuInfo> GetMenu(Integer roleId) {
        SqlSession session = SqlSessionUtil.getSession();
        MenuInfoMapper mapper = session.getMapper(MenuInfoMapper.class);
        ArrayList<MenuInfo> menuInfo = mapper.getMenuById(roleId);
        session.commit();
        session.close();
        return menuInfo;
    }
}
