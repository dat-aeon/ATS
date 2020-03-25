/**************************************************************************
 * $Date: 2019-02-12$
 * $Author: Aung Ko lin$
 * $Rev:  $
 * 2019 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.messagingHistory;

import java.io.Serializable;

public class AgentCallHistoryListLineBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1373078522115084916L;

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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

    public String getAgentLevelName() {
        return agentLevelName;
    }

    public void setAgentLevelName(String agentLevelName) {
        this.agentLevelName = agentLevelName;
    }
}
