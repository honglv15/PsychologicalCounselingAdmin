package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * order_info
 * @author 
 */
@Data
public class OrderInfo implements Serializable {
    private Integer orderId;

    private String orderTime;

    private String orderSuctime;

    private Integer consultId;

    private Integer userId;

    private Integer fieldId;

    private Integer stateId;

    private String orderProblem;

    private String orderReply;

    private String replyTime;

    private String commentContent;

    private String commentTime;

    private static final long serialVersionUID = 1L;
}