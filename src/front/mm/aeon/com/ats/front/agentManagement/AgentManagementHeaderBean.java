/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentManagement;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

public class AgentManagementHeaderBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4998619738834744521L;

    private Integer agentLevelId;

    private String agentLevelName;

    private String password;

    private String agentLevelCode;

    private boolean isUpdate;

    private Timestamp updatedTime;

    private Integer atAgentLevelTypeId;

    private ArrayList<SelectItem> atAgentLevelTypeSelectItemList;

    public Integer getAtAgentLevelTypeId() {
        return atAgentLevelTypeId;
    }

    public void setAtAgentLevelTypeId(Integer atAgentLevelTypeId) {
        this.atAgentLevelTypeId = atAgentLevelTypeId;
    }

    public ArrayList<SelectItem> getAtAgentLevelTypeSelectItemList() {
        return atAgentLevelTypeSelectItemList;
    }

    public void setAtAgentLevelTypeSelectItemList(ArrayList<SelectItem> atAgentLevelTypeSelectItemList) {
        this.atAgentLevelTypeSelectItemList = atAgentLevelTypeSelectItemList;
    }

    public Integer getAgentLevelId() {
        return agentLevelId;
    }

    public void setAgentLevelId(Integer agentLevelId) {
        this.agentLevelId = agentLevelId;
    }

    public String getAgentLevelName() {
        return agentLevelName;
    }

    public void setAgentLevelName(String agentLevelName) {
        this.agentLevelName = agentLevelName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgentLevelCode() {
        return agentLevelCode;
    }

    public void setAgentLevelCode(String agentLevelCode) {
        this.agentLevelCode = agentLevelCode;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public AgentManagementHeaderBean copyAgentManagementHeaderBean(
            AgentManagementHeaderBean agentManagementHeaderBean) {

        this.agentLevelId = agentManagementHeaderBean.getAgentLevelId();
        this.agentLevelName = agentManagementHeaderBean.getAgentLevelName();
        this.password = agentManagementHeaderBean.getPassword();
        this.atAgentLevelTypeId = agentManagementHeaderBean.getAtAgentLevelTypeId();
        this.agentLevelCode = agentManagementHeaderBean.getAgentLevelCode();
        this.updatedTime = agentManagementHeaderBean.getUpdatedTime();

        return this;
    }

}
