package per.dhl.qo;

import lombok.Data;

import java.io.Serializable;

/**
 * user_income_info
 *
 * @author
 */
@Data
public class UserIncomeInfoQo implements Serializable {
    private String userName;

    private Integer incomeId;

    private Integer userId;

    private Integer consultId;

    private String incomeContent;

    private String incomeOperation;

    private Double incomeMoney;

    private String incomeTime;

}