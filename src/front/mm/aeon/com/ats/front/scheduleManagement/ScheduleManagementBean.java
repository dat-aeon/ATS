/**************************************************************************
 * $Date : $
 * $Author :Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.scheduleManagement;

import java.io.Serializable;
import java.sql.Timestamp;

public class ScheduleManagementBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4998619738834744521L;

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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public ScheduleManagementBean copyScheduleManagementBean(ScheduleManagementBean scheduleManagementBean) {
        this.scheduleTimeId = scheduleManagementBean.getScheduleTimeId();
        this.durationHour = scheduleManagementBean.getDurationHour();
        this.delFlag = scheduleManagementBean.getDelFlag();
        this.updatedBy = scheduleManagementBean.getUpdatedBy();
        this.updatedTime = scheduleManagementBean.getUpdatedTime();

        return this;
    }
}
