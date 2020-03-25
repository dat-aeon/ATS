/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.atAgentLevelTypeList;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AtAgentLevelTypeSelectListResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7398632890706637216L;

    private Integer atAgentLevelTypeId;

    private String name;

    private Integer timeMinuteInterval;

    private Timestamp updatedTime;

    private String createdBy;

    private Timestamp createdTime;

    private String updatedBy;

    private Boolean delFlag;

    public Integer getAtAgentLevelTypeId() {
        return atAgentLevelTypeId;
    }

    public void setAtAgentLevelTypeId(Integer atAgentLevelTypeId) {
        this.atAgentLevelTypeId = atAgentLevelTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeMinuteInterval() {
        return timeMinuteInterval;
    }

    public void setTimeMinuteInterval(Integer timeMinuteInterval) {
        this.timeMinuteInterval = timeMinuteInterval;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
