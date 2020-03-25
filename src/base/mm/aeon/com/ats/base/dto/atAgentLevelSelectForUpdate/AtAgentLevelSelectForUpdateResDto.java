/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.atAgentLevelSelectForUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class AtAgentLevelSelectForUpdateResDto implements IResServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = 922720431242824989L;
    private Integer agentLevelTypeId;
    private Timestamp updatedTime;

    public Integer getAgentLevelTypeId() {
        return agentLevelTypeId;
    }

    public void setAgentLevelTypeId(Integer agentLevelTypeId) {
        this.agentLevelTypeId = agentLevelTypeId;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
