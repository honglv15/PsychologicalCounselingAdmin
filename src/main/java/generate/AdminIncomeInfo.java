package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * admin_income_info
 * @author 
 */
@Data
public class AdminIncomeInfo implements Serializable {
    private Integer aincomeId;

    private Integer consultId;

    private Integer userId;

    private String aincomeContent;

    private Double aincomeMoney;

    private String aincomeTime;

    private static final long serialVersionUID = 1L;
}