/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentList;

import java.io.Serializable;

public class AgentListHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 42293481942974157L;

    private String agentLevelName;

    private String agentLevelCode;

    private Integer atAgentLevelTypeId;

    public Integer getAtAgentLevelTypeId() {
        return atAgentLevelTypeId;
    }

    public void setAtAgentLevelTypeId(Integer atAgentLevelTypeId) {
        this.atAgentLevelTypeId = atAgentLevelTypeId;
    }

    public String getAgentLevelName() {
        return agentLevelName;
    }

    public void setAgentLevelName(String agentLevelName) {
        this.agentLevelName = agentLevelName;
    }

    public String getAgentLevelCode() {
        return agentLevelCode;
    }

    public void setAgentLevelCode(String agentLevelCode) {
        this.agentLevelCode = agentLevelCode;
    }

}
