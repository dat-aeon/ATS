/**************************************************************************
 * $Date: 2018-09-04$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentLevelList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ats.base.dto.atAgentLevelTypeList.AtAgentLevelTypeSelectListReqDto;
import mm.aeon.com.ats.base.dto.atAgentLevelTypeList.AtAgentLevelTypeSelectListResDto;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentLevelListPaginationController extends LazyDataModel<AgentLevelListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private AtAgentLevelTypeSelectListReqDto agentLevelInfoSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public AgentLevelListPaginationController(int rowCount,
            AtAgentLevelTypeSelectListReqDto agentLevelInfoSearchReqDto) {
        this.rowCount = rowCount;
        this.agentLevelInfoSearchReqDto = agentLevelInfoSearchReqDto;
    }

    @Override
    public Object getRowKey(AgentLevelListLineBean line) {
        return line.getAtAgentLevelTypeId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<AgentLevelListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Messaging History Search Pagination Process started.", LogLevel.INFO);
        agentLevelInfoSearchReqDto.setLimit(pageSize);
        agentLevelInfoSearchReqDto.setOffset(first);
        agentLevelInfoSearchReqDto.setSortField(sortField);
        agentLevelInfoSearchReqDto.setSortOrder(sortOrder.toString());

        List<AgentLevelListLineBean> resultList = new ArrayList<AgentLevelListLineBean>();
        try {
            List<AtAgentLevelTypeSelectListResDto> resDtoList = (List<AtAgentLevelTypeSelectListResDto>) CommonUtil
                    .getDaoServiceInvoker().execute(agentLevelInfoSearchReqDto);

            for (AtAgentLevelTypeSelectListResDto resdto : resDtoList) {
                AgentLevelListLineBean lineBean = new AgentLevelListLineBean();

                lineBean.setAtAgentLevelTypeId(resdto.getAtAgentLevelTypeId());
                lineBean.setName(resdto.getName());
                lineBean.setTimeMinuteInterval(resdto.getTimeMinuteInterval());
                lineBean.setUpdatedTime(resdto.getUpdatedTime());
                lineBean.setAtAgentLevelTypeId(resdto.getAtAgentLevelTypeId());
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
