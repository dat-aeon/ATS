/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.scheduleUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "ScheduleInfo")
public class ScheduleUpdateReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = -748406496640180341L;

    private Integer scheduleTimeId;

    private Integer durationHour;

    private Boolean delFlag;

    private String updatedBy;

    private Timestamp updatedTime;

    public Integer getScheduleTimeId() {
        return scheduleTimeId;
    }

    public void setScheduleTimeId(Integer scheduleTimeId) {
        this.scheduleTimeId = scheduleTimeId;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getDurationHour() {
        return durationHour;
    }

    public void setDurationHour(Integer durationHour) {
        this.durationHour = durationHour;
    }

    @Override
    public DaoType getDaoType() {
        return DaoType.UPDATE;
    }

}
