/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.categoryInfoRegisterService;

import java.sql.Timestamp;

import mm.com.dat.presto.main.common.service.bean.AbstractServiceReqBean;

public class CategoryInfoRegisterServiceReqBean extends AbstractServiceReqBean {

    /**
     * 
     */
    private static final long serialVersionUID = -1865860167533025178L;

    private Integer categoryId;

    private Boolean delFlag;

    private String name;

    private String createdBy;

    private Timestamp createdTime;

    
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


    public String getCreatedBy() {
        return createdBy;
    }


    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public Timestamp getCreatedTime() {
        return createdTime;
    }


    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }


    @Override
    public String getServiceId() {
        return "CATEGORYINFOSI";
    }
}
