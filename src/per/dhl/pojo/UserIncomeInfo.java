package per.dhl.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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

    private String incomeTime;

}