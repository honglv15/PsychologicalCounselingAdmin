package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.UserInfoMapper;
import per.dhl.pojo.UserInfo;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

@Component
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public ArrayList<UserInfo> selectUsers(String userNameSearch, Integer userIfDel, Integer lim, Integer off) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        ArrayList<UserInfo> userInfos = mapper.selectUsers(userNameSearch, userIfDel, lim, off);
        session.commit();
        session.close();
        return userInfos;
    }

    @Override
    public Integer countUsers(String userNameSearch, Integer userIfDel, Integer lim, Integer off) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        Integer count = mapper.countUsers(userNameSearch, userIfDel, lim, off);
        session.commit();
        session.close();
        return count;
    }

    @Override
    public Integer modifyUserStatus(String userAccount, Integer userStatus) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        Integer modifyCount = mapper.modifyUserStatus(userAccount, userStatus);
        session.commit();
        session.close();
        return modifyCount;
    }

    @Override
    public Integer delUser(String userAccount) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        Integer delUserCount = mapper.delUser(userAccount);
        session.commit();
        session.close();
        return delUserCount;
    }

    @Override
    public Integer resetPwd(String userAccount,String userPwd) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        Integer resetPwdCount = mapper.resetPwd(userAccount,userPwd);
        session.commit();
        session.close();
        return resetPwdCount;
    }

    @Override
    public ArrayList<UserInfo> searchUserInfo(String userNameSearch, Integer userStatus, Integer limit, Integer offset) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        ArrayList<UserInfo> userInfos = mapper.searchUserInfo(userNameSearch, userStatus, limit, offset);
        session.commit();
        session.close();
        return userInfos;
    }

    @Override
    public Integer SearchCountUsers(String userNameSearch, Integer userStatus) {
        SqlSession session = SqlSessionUtil.getSession();
        UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
        Integer count = mapper.SearchCountUsers(userNameSearch, userStatus);
        session.commit();
        session.close();
        return count;
    }
}
