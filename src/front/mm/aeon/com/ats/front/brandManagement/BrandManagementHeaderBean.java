/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.brandManagement;

import java.io.Serializable;
import java.sql.Timestamp;

public class BrandManagementHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2616584191432100620L;
    private String brandCode;
    private String brandName;
    private Timestamp updatedTime;
    private boolean isUpdate;

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public BrandManagementHeaderBean copyBrandManagementHeaderBean(
            BrandManagementHeaderBean brandManagementHeaderBean) {

        this.brandCode = brandManagementHeaderBean.getBrandCode();
        this.brandName = brandManagementHeaderBean.getBrandName();
        this.updatedTime = brandManagementHeaderBean.getUpdatedTime();
        return this;
    }
}
