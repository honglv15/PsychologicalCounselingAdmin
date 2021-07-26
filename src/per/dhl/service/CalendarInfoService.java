package per.dhl.service;

import per.dhl.pojo.CalendarInfo;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description: 咨询师日历事件
 * @author: HongLi
 * @create: 2021-07-15 11:31
 */
public interface CalendarInfoService {
    Integer AddEvents(Integer accountID, String eventTime);
    ArrayList<CalendarInfo> initEvents();
    Integer DelEvent(Integer adminId, String eventTime);
}
