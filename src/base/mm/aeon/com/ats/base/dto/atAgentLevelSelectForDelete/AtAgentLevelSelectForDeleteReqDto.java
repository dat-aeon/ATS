/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.atAgentLevelSelectForDelete;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "ProductTypeInfo")
public class AtAgentLevelSelectForDeleteReqDto implements IReqServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = 5853821004589839154L;
    private Integer agentLevelTypeId;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.SELECT_FOR_UPDATE;

    }

    public Integer getAgentLevelTypeId() {
        return agentLevelTypeId;
    }

    public void setAgentLevelTypeId(Integer agentLevelTypeId) {
        this.agentLevelTypeId = agentLevelTypeId;
    }
}
