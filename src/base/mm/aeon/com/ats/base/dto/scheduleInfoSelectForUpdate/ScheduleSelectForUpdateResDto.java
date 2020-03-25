/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.scheduleInfoSelectForUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class ScheduleSelectForUpdateResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -2984883292668226332L;

    private Integer scheduleTimeId;

    private Timestamp updatedTime;

    public Integer getScheduleTimeId() {
        return scheduleTimeId;
    }

    public void setScheduleTimeId(Integer scheduleTimeId) {
        this.scheduleTimeId = scheduleTimeId;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
