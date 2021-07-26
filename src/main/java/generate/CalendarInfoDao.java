package generate;

import generate.CalendarInfo;

public interface CalendarInfoDao {
    int deleteByPrimaryKey(Integer calendarId);

    int insert(CalendarInfo record);

    int insertSelective(CalendarInfo record);

    CalendarInfo selectByPrimaryKey(Integer calendarId);

    int updateByPrimaryKeySelective(CalendarInfo record);

    int updateByPrimaryKey(CalendarInfo record);
}