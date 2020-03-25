/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.agentInfoCount;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "AgentInfo")
public class AgentInfoCountReqDto implements IReqServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = 4163869201395549293L;

    private String agentLevelName;

    private String agentLevelCode;

    private Integer atAgentLevelTypeId;

    public String getAgentLevelName() {
        return agentLevelName;
    }

    public String getAgentLevelCode() {
        return agentLevelCode;
    }

    public Integer getAtAgentLevelTypeId() {
        return atAgentLevelTypeId;
    }

    public void setAgentLevelName(String agentLevelName) {
        this.agentLevelName = agentLevelName;
    }

    public void setAgentLevelCode(String agentLevelCode) {
        this.agentLevelCode = agentLevelCode;
    }

    public void setAtAgentLevelTypeId(Integer atAgentLevelTypeId) {
        this.atAgentLevelTypeId = atAgentLevelTypeId;
    }

    @Override
    public DaoType getDaoType() {
        return DaoType.COUNT;
    }

}
