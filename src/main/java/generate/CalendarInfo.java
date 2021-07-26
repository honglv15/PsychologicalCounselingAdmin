package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * calendar_info
 * @author 
 */
@Data
public class CalendarInfo implements Serializable {
    private Integer calendarId;

    private Integer consultId;

    private Integer calendarState;

    private Date calendarTime;

    private static final long serialVersionUID = 1L;
}