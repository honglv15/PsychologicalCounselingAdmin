package per.dhl.service;

import per.dhl.qo.QuestionBankManagementQo;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 题库管理服务
 * @author: HongLi
 * @create: 2021-07-26 10:38
 */
public interface QuestionBankManagementService {
    ArrayList<QuestionBankManagementQo> InitQuestionBankManagementTable(Integer value, Integer limit, Integer selectValue);
}
