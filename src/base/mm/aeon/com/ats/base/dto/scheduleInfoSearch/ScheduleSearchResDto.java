/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.scheduleInfoSearch;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class ScheduleSearchResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7591551575539498312L;

    private Integer scheduleTimeId;

    private Integer durationHour;

    private Boolean delFlag;

    private Timestamp updatedTime;

    private Timestamp createdTime;

    private String createdBy;

    private String updatedBy;

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

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
