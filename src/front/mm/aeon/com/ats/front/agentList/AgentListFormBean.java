/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentList;

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

import mm.aeon.com.ats.base.dto.agentInfoSelectList.AgentInfoSelectListReqDto;
import mm.aeon.com.ats.front.agentManagement.AgentManagementHeaderBean;
import mm.aeon.com.ats.front.common.constants.LinkTarget;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;

@Name("agentListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgentListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private AgentListHeaderBean searchHeaderBean;

    private List<AgentListLineBean> lineBeans;

    private LazyDataModel<AgentListLineBean> lazyModel;

    private AgentListLineBean lineBean;

    private ArrayList<SelectItem> agentSelectItemList;

    private Integer totalCount;

    private AgentInfoSelectListReqDto agentSearchReqDto;

    @In(required = false, value = "agentUpdateParam")
    @Out(required = false, value = "agentUpdateParam")
    private AgentManagementHeaderBean updateParam;

    @In(required = false, value = "agentRegisterParam")
    @Out(required = false, value = "agentRegisterParam")
    private AgentManagementHeaderBean registerParam;

    private boolean init = true;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private int pageFirst;

    private ArrayList<SelectItem> atAgentLevelTypeSelectItemList;

    @Begin(nested = true)
    @Action
    public void init() {
        this.getMessageContainer().clearAllMessages(true);
        searchHeaderBean = new AgentListHeaderBean();
        this.doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        this.doReload = new Boolean(false);
        this.updateParam = null;
        this.lazyModel = null;

        if (!this.getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new AgentListPaginationController(totalCount, agentSearchReqDto);

        }

        return LinkTarget.OK;
    }

    public String prepareUpdate(AgentListLineBean lineBean) {

        updateParam = new AgentManagementHeaderBean();
        updateParam.setAgentLevelId(lineBean.getAgentLevelId());
        updateParam.setAgentLevelName(lineBean.getAgentLevelName());
        updateParam.setAtAgentLevelTypeId(lineBean.getAtAgentLevelTypeId());
        updateParam.setPassword(lineBean.getPassword());
        updateParam.setAgentLevelCode(lineBean.getAgentLevelCode());
        updateParam.setUpdatedTime(lineBean.getUpdatedTime());
        updateParam.setAtAgentLevelTypeSelectItemList(atAgentLevelTypeSelectItemList);
        return LinkTarget.UPDATE_INIT;
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

    public String getAtAgentLevelTypeValue(Integer i) {
        if (i != null) {
            for (SelectItem selectItem : atAgentLevelTypeSelectItemList) {
                if (i.equals(selectItem.getValue())) {
                    return selectItem.getLabel();
                }
            }
        }
        return "";
    }

    public void reset() {
        this.searchHeaderBean = new AgentListHeaderBean();
    }

    public String prepareRegister() {
        this.updateParam = null;
        registerParam = new AgentManagementHeaderBean();
        registerParam.setAtAgentLevelTypeSelectItemList(atAgentLevelTypeSelectItemList);
        return LinkTarget.REGISTER;
    }

    public AgentManagementHeaderBean getUpdateParam() {
        return updateParam;
    }

    public void setUpdateParam(AgentManagementHeaderBean updateParam) {
        this.updateParam = updateParam;
    }

    public AgentListHeaderBean getSearchHeaderBean() {
        return searchHeaderBean;
    }

    public void setSearchHeaderBean(AgentListHeaderBean searchHeaderBean) {
        this.searchHeaderBean = searchHeaderBean;
    }

    public List<AgentListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<AgentListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public LazyDataModel<AgentListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AgentListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public AgentListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(AgentListLineBean lineBean) {
        this.lineBean = lineBean;
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

    public ArrayList<SelectItem> getAgentSelectItemList() {
        return agentSelectItemList;
    }

    public void setAgentSelectItemList(ArrayList<SelectItem> agentSelectItemList) {
        this.agentSelectItemList = agentSelectItemList;
    }

    public ArrayList<SelectItem> getAtAgentLevelTypeSelectItemList() {
        return atAgentLevelTypeSelectItemList;
    }

    public void setAtAgentLevelTypeSelectItemList(ArrayList<SelectItem> atAgentLevelTypeSelectItemList) {
        this.atAgentLevelTypeSelectItemList = atAgentLevelTypeSelectItemList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public AgentInfoSelectListReqDto getAgentSearchReqDto() {
        return agentSearchReqDto;
    }

    public void setAgentSearchReqDto(AgentInfoSelectListReqDto agentSearchReqDto) {
        this.agentSearchReqDto = agentSearchReqDto;
    }
}
