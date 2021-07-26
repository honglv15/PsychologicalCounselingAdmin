package generate;

import java.io.Serializable;
import lombok.Data;

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

    private static final long serialVersionUID = 1L;
}