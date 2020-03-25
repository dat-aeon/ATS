/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.agentLevelInfoCount;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "AtAgentLevelType")
public class AgentLevelInfoCountReqDto implements IReqServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = 4163869201395549293L;

    @Override
    public DaoType getDaoType() {
        return DaoType.COUNT;
    }

}
