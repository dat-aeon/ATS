/**************************************************************************
 * $Date: 2019-02-12$
 * $Author: Aung Ko lin$
 * $Rev:  $
 * 2019 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.messagingHistory;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;

import mm.aeon.com.ats.base.dto.agentCallHistorySearch.AgentCallHistorySearchReqDto;
import mm.aeon.com.ats.front.common.constants.LinkTarget;
import mm.com.dat.presto.main.core.front.controller.AbstractFormBean;
import mm.com.dat.presto.main.core.front.controller.Action;
import mm.com.dat.presto.main.core.front.controller.FormBean;
import mm.com.dat.presto.main.core.front.controller.IRequest;
import mm.com.dat.presto.main.core.front.controller.IResponse;
import mm.com.dat.presto.main.front.message.MessageType;

@Name("agentCallHistoryListFormBean")
@Scope(ScopeType.CONVERSATION)
@FormBean
public class AgentCallHistoryListFormBean extends AbstractFormBean implements IRequest, IResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -7135450431138864114L;

    private List<MessagingHistoryListLineBean> lineBeans;

    private LazyDataModel<AgentCallHistoryListLineBean> lazyModel;

    private MessagingHistoryListLineBean lineBean;

    private MessagingHistoryListHeaderBean agentCallHeaderBean;

    @In(required = false, value = "doReload")
    @Out(required = false, value = "doReload")
    private Boolean doReload;

    private boolean init = true;

    private int pageFirst;

    private int activeIndex;

    private int totalCount;

    private AgentCallHistorySearchReqDto agentCallHistorySearchReqDto;

    @Begin(nested = true)
    public void init() {
        getMessageContainer().clearAllMessages(true);
        agentCallHeaderBean = new MessagingHistoryListHeaderBean();
        doReload = new Boolean(true);
        init = false;
    }

    @Action
    public String search() {
        lazyModel = null;
        doReload = new Boolean(false);
        if (!getMessageContainer().checkMessage(MessageType.ERROR) && totalCount != 0) {
            lazyModel = new AgentCallHistoryListPaginationController(totalCount, agentCallHistorySearchReqDto);
        }

        return LinkTarget.OK;
    }

    public void onTabChange(TabChangeEvent event) {
        activeIndex = ((TabView) event.getSource()).getIndex();
    }

    public void reset() {
        this.agentCallHeaderBean = new MessagingHistoryListHeaderBean();
    }

    public List<MessagingHistoryListLineBean> getLineBeans() {
        return lineBeans;
    }

    public void setLineBeans(List<MessagingHistoryListLineBean> lineBeans) {
        this.lineBeans = lineBeans;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public MessagingHistoryListLineBean getLineBean() {
        return lineBean;
    }

    public void setLineBean(MessagingHistoryListLineBean lineBean) {
        this.lineBean = lineBean;
    }

    public int getPageFirst() {
        return pageFirst;
    }

    public void setPageFirst(int pageFirst) {
        this.pageFirst = pageFirst;
    }

    public MessagingHistoryListHeaderBean getAgentCallHeaderBean() {
        return agentCallHeaderBean;
    }

    public void setAgentCallHeaderBean(MessagingHistoryListHeaderBean agentCallHeaderBean) {
        this.agentCallHeaderBean = agentCallHeaderBean;
    }

    public Boolean getDoReload() {
        return doReload;
    }

    public void setDoReload(Boolean doReload) {
        this.doReload = doReload;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public LazyDataModel<AgentCallHistoryListLineBean> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AgentCallHistoryListLineBean> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public AgentCallHistorySearchReqDto getAgentCallHistorySearchReqDto() {
        return agentCallHistorySearchReqDto;
    }

    public void setAgentCallHistorySearchReqDto(AgentCallHistorySearchReqDto agentCallHistorySearchReqDto) {
        this.agentCallHistorySearchReqDto = agentCallHistorySearchReqDto;
    }

}
