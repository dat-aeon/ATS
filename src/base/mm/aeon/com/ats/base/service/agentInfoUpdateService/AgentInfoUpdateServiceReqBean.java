/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.agentInfoUpdateService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class AgentInfoUpdateServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -3883054262804630617L;

    private Integer agentLevelId;

    private String agentName;

    private String password;

    private String agentLevelCode;

    private Integer delFlag;
    private Timestamp updatedTime;

    private Integer atAgentLevelTypeId;

    @Override
    public String getServiceId() {
        return "AGENTINFOSU";
    }

    public Integer getAtAgentLevelTypeId() {
        return atAgentLevelTypeId;
    }

    public void setAtAgentLevelTypeId(Integer atAgentLevelTypeId) {
        this.atAgentLevelTypeId = atAgentLevelTypeId;
    }

    public Integer getAgentLevelId() {
        return agentLevelId;
    }

    public void setAgentLevelId(Integer agentLevelId) {
        this.agentLevelId = agentLevelId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgentLevelCode() {
        return agentLevelCode;
    }

    public void setAgentLevelCode(String agentLevelCode) {
        this.agentLevelCode = agentLevelCode;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
