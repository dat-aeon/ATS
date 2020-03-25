/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.scheduleInfoSearch;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "ScheduleInfo")
public class ScheduleSearchReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 622234158884618063L;

    private Integer scheduleTimeId;

    private Integer durationHour;

    public Integer getScheduleTimeId() {
        return scheduleTimeId;
    }

    public void setScheduleTimeId(Integer scheduleTimeId) {
        this.scheduleTimeId = scheduleTimeId;
    }

    public Integer getDurationHour() {
        return durationHour;
    }

    public void setDurationHour(Integer durationHour) {
        this.durationHour = durationHour;
    }

    @Override
    public DaoType getDaoType() {
        return DaoType.SELECT_LIST;
    }
}
