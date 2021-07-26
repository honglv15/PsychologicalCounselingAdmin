package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.pojo.UserInfo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description:
 * @author: HongLi
 * @create: 2021-07-10 14:30
 */
public interface UserInfoMapper {
    ArrayList<UserInfo> selectUsers(@Param("userNameSearch") String userNameSearch, @Param("userIfDel") Integer userIfDel,@Param("lim") Integer lim,@Param("off") Integer off);
    Integer countUsers(@Param("userNameSearch") String userNameSearch, @Param("userIfDel") Integer userIfDel,@Param("lim") Integer lim,@Param("off") Integer off);

    Integer modifyUserStatus(@Param("userAccount")String userAccount, @Param("userStatus")Integer userStatus);

    Integer delUser(@Param("userAccount")String userAccount);

    Integer resetPwd(@Param("userAccount")String userAccount,@Param("userPwd")String userPwd);

    ArrayList<UserInfo> searchUserInfo(@Param("userNameSearch")String userNameSearch, @Param("userStatus")Integer userStatus, @Param("lim")Integer limit, @Param("off")Integer offset);

    Integer SearchCountUsers(@Param("userNameSearch")String userNameSearch, @Param("userStatus")Integer userStatus);
}
