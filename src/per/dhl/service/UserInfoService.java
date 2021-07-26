package per.dhl.service;

import per.dhl.pojo.UserInfo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 用户信息服务类
 * @author: HongLi
 * @create: 2021-07-10 14:28
 */
public interface UserInfoService {
    ArrayList<UserInfo> selectUsers(String userNameSearch, Integer userIfDel, Integer lim, Integer off);
    Integer  countUsers(String userNameSearch, Integer userIfDel, Integer lim, Integer off);
    Integer modifyUserStatus(String userAccount, Integer userStatus);
    Integer delUser(String userAccount);
    Integer resetPwd(String userAccount,String userPwd);
    ArrayList<UserInfo> searchUserInfo(String userNameSearch, Integer userStatus, Integer limit, Integer offset);
    Integer SearchCountUsers(String userNameSearch, Integer userStatus);
}
