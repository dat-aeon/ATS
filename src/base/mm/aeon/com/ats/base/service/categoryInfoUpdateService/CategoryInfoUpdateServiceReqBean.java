/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.categoryInfoUpdateService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class CategoryInfoUpdateServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -3883054262804630617L;

    private Integer categoryId;

    private Boolean delFlag;

    private String name;

    private String updatedBy;

    private Timestamp updatedTime;

    
    public Integer getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public Boolean getDelFlag() {
        return delFlag;
    }


    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getUpdatedBy() {
        return updatedBy;
    }


    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


    public Timestamp getUpdatedTime() {
        return updatedTime;
    }


    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }


    @Override
    public String getServiceId() {
        return "CATEGORYINFOSU";
    }
}
