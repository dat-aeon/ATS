/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.scheduleInfoRefer;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class ScheduleInfoReferResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 4511546202956199291L;

    private Integer scheduleTimeId;

    private Double durationHour;

    public Integer getScheduleTimeId() {
        return scheduleTimeId;
    }

    public void setScheduleTimeId(Integer scheduleTimeId) {
        this.scheduleTimeId = scheduleTimeId;
    }

    public Double getDurationHour() {
        return durationHour;
    }

    public void setDurationHour(Double durationHour) {
        this.durationHour = durationHour;
    }
    

    
}
