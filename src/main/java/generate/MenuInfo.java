package generate;

import java.io.Serializable;
import lombok.Data;

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

    private static final long serialVersionUID = 1L;
}