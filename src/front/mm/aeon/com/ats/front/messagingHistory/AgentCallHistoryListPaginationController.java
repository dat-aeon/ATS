/**************************************************************************
 * $Date: 2018-09-04$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.messagingHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ats.base.dto.agentCallHistorySearch.AgentCallHistorySearchReqDto;
import mm.aeon.com.ats.base.dto.agentCallHistorySearch.AgentCallHistorySearchResDto;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentCallHistoryListPaginationController extends LazyDataModel<AgentCallHistoryListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private AgentCallHistorySearchReqDto agentCallHistorySearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public AgentCallHistoryListPaginationController(int rowCount,
            AgentCallHistorySearchReqDto agentCallHistorySearchReqDto) {
        this.rowCount = rowCount;
        this.agentCallHistorySearchReqDto = agentCallHistorySearchReqDto;
    }

    @Override
    public Object getRowKey(AgentCallHistoryListLineBean line) {
        return line.getTeleMarketingAgentId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<AgentCallHistoryListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Messaging History Search Pagination Process started.", LogLevel.INFO);
        agentCallHistorySearchReqDto.setLimit(pageSize);
        agentCallHistorySearchReqDto.setOffset(first);
        agentCallHistorySearchReqDto.setSortField(sortField);
        agentCallHistorySearchReqDto.setSortOrder(sortOrder.toString());

        List<AgentCallHistoryListLineBean> resultList = new ArrayList<AgentCallHistoryListLineBean>();
        try {
            List<AgentCallHistorySearchResDto> resDtoList = (List<AgentCallHistorySearchResDto>) CommonUtil
                    .getDaoServiceInvoker().execute(agentCallHistorySearchReqDto);

            for (AgentCallHistorySearchResDto agentCallHistorySearchResDto : resDtoList) {
                AgentCallHistoryListLineBean lineBean = new AgentCallHistoryListLineBean();

                lineBean.setTeleMarketingAgentId(agentCallHistorySearchResDto.getTeleMarketingAgentId());
                lineBean.setAgentName(agentCallHistorySearchResDto.getAgentName());
                lineBean.setCallCount(agentCallHistorySearchResDto.getCallCount());
                lineBean.setAgentLevelId(agentCallHistorySearchResDto.getAgentLevelId());
                lineBean.setAgentLevelName(agentCallHistorySearchResDto.getAgentLevelName());
                lineBean.setLoginName(agentCallHistorySearchResDto.getLoginName());

                resultList.add(lineBean);
            }

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                applicationLogger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        applicationLogger.log("App Search Pagination Process finished.", LogLevel.INFO);
        return resultList;
    }

}
