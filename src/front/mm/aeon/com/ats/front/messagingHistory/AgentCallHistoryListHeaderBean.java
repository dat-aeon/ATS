/**************************************************************************
 * $Date: 2019-01-28$
 * $Author: Aung Ko lin$
 * $Rev:  $
 * 2019 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.messagingHistory;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class AgentCallHistoryListHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1373078522115084916L;
    
    private String agentName;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    
}
