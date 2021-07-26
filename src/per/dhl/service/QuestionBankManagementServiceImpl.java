package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.QuestionBankManagementMapper;
import per.dhl.qo.QuestionBankManagementQo;
import per.dhl.util.SqlSessionUtil;

import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 题库管理服务实现类
 * @author: HongLi
 * @create: 2021-07-26 10:38
 */
@Component
public class QuestionBankManagementServiceImpl implements QuestionBankManagementService {

    @Override
    public ArrayList<QuestionBankManagementQo> InitQuestionBankManagementTable(Integer offset, Integer limit, Integer selectValue) {
        SqlSession session = SqlSessionUtil.getSession();
        QuestionBankManagementMapper mapper = session.getMapper(QuestionBankManagementMapper.class);
        ArrayList<QuestionBankManagementQo> questionBankManagementQos = mapper.InitQuestionBankManagementTable(selectValue,limit,offset);


        return null;
    }
}
