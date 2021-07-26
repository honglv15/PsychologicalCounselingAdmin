package per.dhl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * admin_info
 * @author 
 */
@Data
public class AdminInfo implements Serializable {
    private Integer adminId;

    private String adminAccount;

    private String adminPassword;

    private Integer roleId;

    private Integer adminStatue;

    private Integer adminIfDel;

}