package per.dhl.service;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 心理咨询师表
 * @author: HongLi
 * @create: 2021-07-13 14:28
 */
public interface ConsultInfoService {
    Integer AddConsultInfo(Integer admId, Integer selectedVal, String school, double cost, String pbg, String brief);

    Integer AddConsultFiledInfo(Integer admId, String filedId);
}
