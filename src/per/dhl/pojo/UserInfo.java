package per.dhl.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user_info
 * @author 
 */
@Data
public class UserInfo implements Serializable {
    private Integer userId;

    private String userAccount;

    private String userPwd;

    private String userName;

    private Integer userAge;

    private Integer userSex;

    private Integer userPhone;

    private String userAddress;

    private Integer userStatus;

    private Double userMoney;

    private Integer userIfDel;

    private Date userCtime;

    private Date userMtime;

}