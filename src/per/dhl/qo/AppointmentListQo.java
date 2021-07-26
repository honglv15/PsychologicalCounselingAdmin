package per.dhl.qo;

import lombok.Data;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 预约列表查询类
 * @author: HongLi
 * @create: 2021-07-21 10:01
 */
@Data
public class AppointmentListQo {
    private String orderTime;

    private String userAccount;

    private String userId;

    private String fieldName;

    private String stateName;

    private String orderProblem;
}
