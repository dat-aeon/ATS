/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.categoryInfoRefer;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "CategoryInfo")
public class CategoryInfoReferReqDto implements IReqDto {

    /**
     * 
     */
    private static final long serialVersionUID = -902879166749490589L;

    private Integer categoryId;
 
    
    public Integer getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    public DaoType getDaoType() {
        // TODO Auto-generated method stub
        return DaoType.REFER;
    }

}
