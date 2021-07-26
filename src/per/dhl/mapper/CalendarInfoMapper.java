package per.dhl.mapper;

import org.apache.ibatis.annotations.Param;
import per.dhl.pojo.CalendarInfo;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @program: PsychologicalCounselingAdmin
 * @description:
 * @author: HongLi
 * @create: 2021-07-16 11:40
 */
public interface CalendarInfoMapper {
    Integer AddEvents(@Param("consultId") Integer accountID, @Param("calendarTime") String eventTime);

    ArrayList<CalendarInfo> initEvents();

    Integer DelEvents(@Param("adminId") Integer adminId, @Param("eventTime") String eventTime);

    ArrayList<CalendarInfo> checkIfEvent(@Param("consultId") Integer accountID, @Param("calendarTime") String eventTime);

    Integer getConsultId(@Param("accountID") Integer accountID);
}
