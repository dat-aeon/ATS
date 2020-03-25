/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentLevelManagement;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import mm.aeon.com.ats.front.common.constants.LinkTarget;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;

@Name("agentLevelManagementFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgentLevelManagementFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 2561404663068363440L;

    @In(required = false, value = "agentLevelUpdateParam")
    @Out(required = false, value = "agentLevelUpdateParam")
    private AgentLevelManagementHeaderBean updateParam;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private ArrayList<SelectItem> agentSelectItemList;

    private boolean init = true;

    @In(required = false, value = "agentRegisterParam")
    @Out(required = false, value = "agentRegisterParam")
    private AgentLevelManagementHeaderBean registerParam;

    private AgentLevelManagementHeaderBean registerHeaderBean;

    private AgentLevelManagementHeaderBean backUpHeaderBean;

    private boolean isUpdate;

    private boolean isValidStatusUpdate;

    private boolean isError = false;

    private boolean cancel = false;

    private String generatedPassword = "";

    @Begin(join = true)
    public void init() {
        init = false;
        isUpdate = false;
        cancel = true;
        this.getMessageContainer().clearAllMessages(true);
        registerHeaderBean = new AgentLevelManagementHeaderBean();
        backUpHeaderBean = new AgentLevelManagementHeaderBean();
    }

    @Begin(join = true)
    public void updateInit() {
        this.getMessageContainer().clearAllMessages(true);

        init = false;
        isUpdate = true;
        cancel = false;
        registerHeaderBean = new AgentLevelManagementHeaderBean();
        registerHeaderBean.setAgentLevelTypeId(updateParam.getAgentLevelTypeId());
        registerHeaderBean.setName(updateParam.getName());
        registerHeaderBean.setTimeMinuteInterval(updateParam.getTimeMinuteInterval());
        registerHeaderBean.setUpdatedTime(updateParam.getUpdatedTime());

        this.backUpHeaderBean =
                new AgentLevelManagementHeaderBean().copyAgentLevelManagementHeaderBean(this.registerHeaderBean);
    }

    @Action
    public String register() {
        if (getMessageContainer().checkMessage(MessageType.ERROR)) {
            return LinkTarget.ERROR;
        } else if (updateParam != null) {
            doReload = new Boolean(true);
            registerHeaderBean = new AgentLevelManagementHeaderBean();
            init = true;
            cancel = true;
            return LinkTarget.SEARCH;
        }
        doReload = new Boolean(true);
        registerHeaderBean = new AgentLevelManagementHeaderBean();
        return LinkTarget.OK;
    }

    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.updateParam = null;
        this.registerHeaderBean = null;

        return LinkTarget.SEARCH;
    }

    public void clear() {
        this.registerHeaderBean = new AgentLevelManagementHeaderBean();
    }

    public void reset() {
        this.registerHeaderBean = registerHeaderBean.copyAgentLevelManagementHeaderBean(backUpHeaderBean);
    }

    public ArrayList<SelectItem> getAgentSelectItemList() {
        return agentSelectItemList;
    }

    public void setAgentSelectItemList(ArrayList<SelectItem> agentSelectItemList) {
        this.agentSelectItemList = agentSelectItemList;
    }

    public AgentLevelManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(AgentLevelManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public AgentLevelManagementHeaderBean getRegisterHeaderBean() {
        return registerHeaderBean;
    }

    public void setRegisterHeaderBean(AgentLevelManagementHeaderBean registerHeaderBean) {
        this.registerHeaderBean = registerHeaderBean;
    }

    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean isValidStatusUpdate() {
        return isValidStatusUpdate;
    }

    public void setValidStatusUpdate(boolean isValidStatusUpdate) {
        this.isValidStatusUpdate = isValidStatusUpdate;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public AgentLevelManagementHeaderBean getBackUpHeaderBean() {
        return backUpHeaderBean;
    }

    public String getGeneratedPassword() {
        return generatedPassword;
    }

    public void setGeneratedPassword(String generatedPassword) {
        this.generatedPassword = generatedPassword;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBackUpHeaderBean(AgentLevelManagementHeaderBean backUpHeaderBean) {
        this.backUpHeaderBean = backUpHeaderBean;
    }

}
