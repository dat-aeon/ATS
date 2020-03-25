/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.operatorList;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

public class OperatorListLineBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1734777366621170975L;

    private Integer userId;

    private String userName;

    private String userLoginId;
    
    private Timestamp updatedTime;

    private Timestamp createdTime;

    private String createdBy;

    private String updatedBy;

    private ArrayList<SelectItem> operatorRoleSelectItemList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


    public ArrayList<SelectItem> getOperatorRoleSelectItemList() {
        return operatorRoleSelectItemList;
    }

    public void setOperatorRoleSelectItemList(ArrayList<SelectItem> operatorRoleSelectItemList) {
        this.operatorRoleSelectItemList = operatorRoleSelectItemList;
    }

}
