package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user_income_info
 * @author 
 */
@Data
public class UserIncomeInfo implements Serializable {
    private Integer incomeId;

    private Integer userId;

    private Integer consultId;

    private String incomeContent;

    private String incomeOperation;

    private Double incomeMoney;

    private Date incomeTime;

    private static final long serialVersionUID = 1L;
}