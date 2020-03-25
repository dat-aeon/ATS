/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.agentCallHistorySearch;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AgentCallHistorySearchResDto implements IResServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = -6382621224350722250L;

    private Integer teleMarketingAgentId;

    private String agentName;

    private Integer callCount;

    private Integer agentLevelId;

    private String agentLevelName;

    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getTeleMarketingAgentId() {
        return teleMarketingAgentId;
    }

    public void setTeleMarketingAgentId(Integer teleMarketingAgentId) {
        this.teleMarketingAgentId = teleMarketingAgentId;
    }

    public Integer getCallCount() {
        return callCount;
    }

    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
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

    public String getAgentLevelName() {
        return agentLevelName;
    }

    public void setAgentLevelName(String agentLevelName) {
        this.agentLevelName = agentLevelName;
    }

}
