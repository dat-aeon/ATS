/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentLevelList;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.model.LazyDataModel;

import mm.aeon.com.ats.base.dto.atAgentLevelTypeList.AtAgentLevelTypeSelectListReqDto;
import mm.aeon.com.ats.front.agentLevelManagement.AgentLevelManagementHeaderBean;
import mm.aeon.com.ats.front.common.constants.LinkTarget;
import mm.aeon.com.ats.front.common.constants.VCSCommon;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;

@Name("agentLevelListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgentLevelListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private AgentLevelListHeaderBean searchHeaderBean;

    private List<AgentLevelListLineBean> lineBeans;

    private LazyDataModel<AgentLevelListLineBean> lazyModel;

    private AgentLevelListLineBean lineBean;

    private ArrayList<SelectItem> statusList;

    private AtAgentLevelTypeSelectListReqDto agentLevelReqDto;

    private Integer totalCount;

    @Out(required = false, value = "agentLevelUpdateParam")
    private AgentLevelManagementHeaderBean updateParam;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    @Begin(nested = true)
    public void init() {
        this.getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new AgentLevelListHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new AgentLevelListPaginationController(totalCount, agentLevelReqDto);
        }

        return LinkTarget.OK;
    }

    public String prepareUpdate(AgentLevelListLineBean lineBean) {

        this.updateParam = new AgentLevelManagementHeaderBean();
        this.updateParam.setAgentLevelTypeId(lineBean.getAtAgentLevelTypeId());
        this.updateParam.setName(lineBean.getName());
        this.updateParam.setTimeMinuteInterval(lineBean.getTimeMinuteInterval());
        this.updateParam.setCreatedBy(lineBean.getCreatedBy());
        this.updateParam.setCreatedTime(lineBean.getCreatedTime());
        this.updateParam.setUpdatedBy(lineBean.getUpdatedBy());
        this.updateParam.setUpdatedTime(lineBean.getUpdatedTime());
        this.updateParam.setDelFlag(lineBean.getDelFlag());

        return LinkTarget.UPDATE_INIT;
    }

    public String back() {
        this.getMessageContainer().clearAllMessages(true);
        this.init = true;
        this.updateParam = null;
        return LinkTarget.SEARCH;
    }

    public String detail(AgentLevelListLineBean lineBean) {
        this.lineBean = lineBean;
        return LinkTarget.DETAIL;
    }

    @Action
    public String delete() {
        this.doReload = false;
        this.lineBean = null;
        if (!getMessageContainer().checkMessage(MessageType.ERROR)) {
            this.doReload = true;
        }

        return LinkTarget.OK;
    }

    public void reset() {
        this.searchHeaderBean = new AgentLevelListHeaderBean();
    }

    public String prepareRegister() {
        this.updateParam = null;
        return LinkTarget.REGISTER;
    }

    public AgentLevelManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(AgentLevelManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public AgentLevelListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(AgentLevelListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public List<AgentLevelListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<AgentLevelListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<AgentLevelListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AgentLevelListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public AgentLevelListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(AgentLevelListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public ArrayList<SelectItem> getStatusList() {

        statusList = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem();
        item.setLabel(VCSCommon.SPACE);
        item.setValue(VCSCommon.TWO);
        statusList.add(item);

        item = new SelectItem();
        item.setLabel(VCSCommon.DISABLED);
        item.setValue(VCSCommon.ZERO);
        statusList.add(item);

        item = new SelectItem();
        item.setLabel(VCSCommon.ENABLED);
        item.setValue(VCSCommon.ONE);
        statusList.add(item);

        return statusList;
    }

    public void setStatusList(ArrayList<SelectItem> statusList) {
        this.statusList = statusList;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public int getPageFirst() {
        return pageFirst;
    }

    public void setPageFirst(int pageFirst) {
        this.pageFirst = pageFirst;
    }

    public AtAgentLevelTypeSelectListReqDto getAgentLevelReqDto() {
        return agentLevelReqDto;
    }

    public void setAgentLevelReqDto(AtAgentLevelTypeSelectListReqDto agentLevelReqDto) {
        this.agentLevelReqDto = agentLevelReqDto;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
