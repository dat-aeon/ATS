/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2019 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.productTypeList;

import mm.com.dat.presto.main.common.dao.bean.IResServiceDto;

public class ProductTypeSelectListResDto implements IResServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7318982398749871155L;

    private Integer productTypeId;
    private String productName;
    private Boolean delFlag;

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

}
