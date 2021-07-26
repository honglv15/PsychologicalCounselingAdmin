package generate;

import generate.MenuInfo;

public interface MenuInfoDao {
    int deleteByPrimaryKey(Integer menuId);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);
}