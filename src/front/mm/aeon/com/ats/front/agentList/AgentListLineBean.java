/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentList;

import java.io.Serializable;
import java.sql.Timestamp;

public class AgentListLineBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1373078522115084916L;

    private Integer agentLevelId;

    private String agentLevelName;

    private String password;

    private String agentLevelCode;
    private Timestamp updatedTime;

    private Integer atAgentLevelTypeId;

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

    public String getAgentLevelName() {
        return agentLevelName;
    }

    public void setAgentLevelName(String agentLevelName) {
        this.agentLevelName = agentLevelName;
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

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
