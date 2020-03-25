/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.agentLevelInfoUpdateService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class AgentLevelInfoUpdateServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -3883054262804630617L;

    private Integer agentLevelTypeId;

    private String name;

    private Integer timeMinuteInterval;

    private Timestamp updatedTime;

    private String createdBy;

    private Timestamp createdTime;

    private String updatedBy;

    private Boolean delFlag;

    @Override
    public String getServiceId() {
        return "AGENTLEVELINFOSU";
    }

    public Integer getAgentLevelTypeId() {
        return agentLevelTypeId;
    }

    public String getName() {
        return name;
    }

    public Integer getTimeMinuteInterval() {
        return timeMinuteInterval;
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

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setAgentLevelTypeId(Integer agentLevelTypeId) {
        this.agentLevelTypeId = agentLevelTypeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeMinuteInterval(Integer timeMinuteInterval) {
        this.timeMinuteInterval = timeMinuteInterval;
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

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

}
