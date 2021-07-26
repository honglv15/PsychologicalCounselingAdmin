package per.dhl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * consult_info
 * @author 
 */
@Data
public class ConsultInfo implements Serializable {
    private Integer consultId;

    private Integer adminId;

    private String consultName;

    private String consultImg;

    private Integer titleId;

    private String consultSchool;

    private Integer fieldId;

    private String consultBackground;

    private String consultIntro;

    private Double consultFee;

    private Double consultMoney;

    private String remark;

}