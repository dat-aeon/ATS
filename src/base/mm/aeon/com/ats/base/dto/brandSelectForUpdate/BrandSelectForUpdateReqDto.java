/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.brandSelectForUpdate;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "BrandInfo")
public class BrandSelectForUpdateReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = 4817428713690805284L;
    private String brandCode;

    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.SELECT_FOR_UPDATE;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

}
