/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.categoryInfoSelectForUpdate;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class CategoryInfoSelectForUpdateResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -2984883292668226332L;

    private Integer categoryId;

    private Timestamp updatedTime;

     
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}
