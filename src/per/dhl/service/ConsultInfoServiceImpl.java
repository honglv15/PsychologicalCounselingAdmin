package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.ConsultInfoMapper;
import per.dhl.util.SqlSessionUtil;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 咨询师服务实现类
 * @author: HongLi
 * @create: 2021-07-13 14:30
 */
@Component
public class ConsultInfoServiceImpl implements ConsultInfoService {
    @Override
    public Integer AddConsultInfo(Integer admId, Integer selectedVal, String school, double cost, String pbg, String brief) {
        SqlSession session = SqlSessionUtil.getSession();
        ConsultInfoMapper mapper = session.getMapper(ConsultInfoMapper.class);
        Integer AddConsultInfoCount = mapper.AddConsultInfo(admId, selectedVal, school, cost, pbg, brief);
        session.commit();
        session.close();
        return AddConsultInfoCount;
    }

    @Override
    public Integer AddConsultFiledInfo(Integer admId, String filedId) {
        SqlSession session = SqlSessionUtil.getSession();
        ConsultInfoMapper mapper = session.getMapper(ConsultInfoMapper.class);
        Integer AddConsultFiledInfoCount = mapper.AddConsultFiledInfo(admId,filedId );
        session.commit();
        session.close();
        return AddConsultFiledInfoCount;
    }
}
