package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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

    private static final long serialVersionUID = 1L;
}