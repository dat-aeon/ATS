/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.agentLevelSelectForUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AgentLevelSelectForUpdateResDto implements IResServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = 922720431242824989L;
    private Integer agentLevelId;
    private Timestamp updatedTime;

    public Integer getAgentLevelId() {
        return agentLevelId;
    }

    public void setAgentLevelId(Integer agentLevelId) {
        this.agentLevelId = agentLevelId;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
