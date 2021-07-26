package per.dhl.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import per.dhl.mapper.CalendarInfoMapper;
import per.dhl.pojo.CalendarInfo;
import per.dhl.util.SqlSessionUtil;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 咨询师日历事件实现类
 * @author: HongLi
 * @create: 2021-07-15 11:35
 */
@Component
public class CalendarInfoServiceImpl implements CalendarInfoService {
    @Override
    public Integer AddEvents(Integer accountID, String EventTime) {
        SqlSession session = SqlSessionUtil.getSession();
        CalendarInfoMapper mapper = session.getMapper(CalendarInfoMapper.class);
        Integer consultId = mapper.getConsultId(accountID);
        System.out.println("咨询师Id"+consultId);

        ArrayList<CalendarInfo> IfEvent = mapper.checkIfEvent(consultId, EventTime);
        System.out.println("是否有该时间" + IfEvent);
        if (IfEvent.size() == 0) {
            Integer addEventsCount = mapper.AddEvents(consultId, EventTime);
            session.commit();
            session.close();
            return addEventsCount;
        } else {
            session.close();
            return 0;
        }
    }

    @Override
    public ArrayList<CalendarInfo> initEvents() {
        SqlSession session = SqlSessionUtil.getSession();
        CalendarInfoMapper mapper = session.getMapper(CalendarInfoMapper.class);
        ArrayList<CalendarInfo> calendarInfos = mapper.initEvents();
        session.commit();
        session.close();
        return calendarInfos;
    }

    @Override
    public Integer DelEvent(Integer adminId, String eventTime) {
        SqlSession session = SqlSessionUtil.getSession();
        CalendarInfoMapper mapper = session.getMapper(CalendarInfoMapper.class);
        Integer consultId = mapper.getConsultId(adminId);
        System.out.println("咨询师Id"+consultId);
        Integer calendarInfosCount = mapper.DelEvents(consultId, eventTime);
        session.commit();
        session.close();
        return calendarInfosCount;
    }
}
