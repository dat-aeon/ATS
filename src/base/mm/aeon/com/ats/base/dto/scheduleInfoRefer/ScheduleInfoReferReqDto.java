/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.scheduleInfoRefer;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "ScheduleInfo")
public class ScheduleInfoReferReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = -8193215351274229102L;

    private Boolean delFlag;

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.REFER;
    }

}
