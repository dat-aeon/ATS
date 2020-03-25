/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.categoryManagement;

import java.io.Serializable;
import java.sql.Timestamp;

public class CategoryManagementHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4998619738834744521L;


    private Integer categoryId;

    private String name;

    private Timestamp updatedTime;
    
    private String createdBy;
    
    private Timestamp createdTime;
    
    private String updatedBy;
    
    private Boolean delFlag;

    private boolean isUpdate;
    
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public CategoryManagementHeaderBean copyCategoryManagementHeaderBean(
            CategoryManagementHeaderBean categoryManagementHeaderBean) {

        this.categoryId = categoryManagementHeaderBean.getCategoryId();
        this.name = categoryManagementHeaderBean.getName();
        this.createdBy = categoryManagementHeaderBean.getCreatedBy();
        this.createdTime = categoryManagementHeaderBean.getCreatedTime();
        this.updatedTime = categoryManagementHeaderBean.getUpdatedTime();
        this.updatedBy=categoryManagementHeaderBean.getUpdatedBy();
        this.delFlag=categoryManagementHeaderBean.getDelFlag();

        return this;
    }

}
