package per.dhl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * menu_info
 * @author 
 */
@Data
public class MenuInfo implements Serializable {
    private Integer menuId;

    private String menuName;

    private String menuUrl;

    private Integer menuPid;

}