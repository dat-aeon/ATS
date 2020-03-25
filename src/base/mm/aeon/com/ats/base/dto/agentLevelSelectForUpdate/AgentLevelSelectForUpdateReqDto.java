/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.agentLevelSelectForUpdate;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "AgentInfo")
public class AgentLevelSelectForUpdateReqDto implements IReqServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = 5853821004589839154L;
    private Integer agentLevelId;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.SELECT_FOR_UPDATE;

    }

    public Integer getAgentLevelId() {
        return agentLevelId;
    }

    public void setAgentLevelId(Integer agentLevelId) {
        this.agentLevelId = agentLevelId;
    }
}
