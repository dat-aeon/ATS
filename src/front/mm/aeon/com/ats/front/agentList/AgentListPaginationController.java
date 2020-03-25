/**************************************************************************
 * $Date: 2018-09-04$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.agentList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.aeon.com.ats.base.dto.agentInfoSelectList.AgentInfoSelectListReqDto;
import mm.aeon.com.ats.base.dto.agentInfoSelectList.AgentInfoSelectListResDto;
import mm.aeon.com.ats.front.common.VCSMPasswordEncoder;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentListPaginationController extends LazyDataModel<AgentListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private AgentInfoSelectListReqDto agentInfoSearchReqDto;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public AgentListPaginationController(int rowCount, AgentInfoSelectListReqDto agentInfoSearchReqDto) {
        this.rowCount = rowCount;
        this.agentInfoSearchReqDto = agentInfoSearchReqDto;
    }

    @Override
    public Object getRowKey(AgentListLineBean line) {
        return line.getAgentLevelId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<AgentListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Messaging History Search Pagination Process started.", LogLevel.INFO);
        agentInfoSearchReqDto.setLimit(pageSize);
        agentInfoSearchReqDto.setOffset(first);
        agentInfoSearchReqDto.setSortField(sortField);
        agentInfoSearchReqDto.setSortOrder(sortOrder.toString());

        List<AgentListLineBean> resultList = new ArrayList<AgentListLineBean>();
        try {
            List<AgentInfoSelectListResDto> resDtoList =
                    (List<AgentInfoSelectListResDto>) CommonUtil.getDaoServiceInvoker().execute(agentInfoSearchReqDto);

            for (AgentInfoSelectListResDto resdto : resDtoList) {
                AgentListLineBean lineBean = new AgentListLineBean();

                lineBean.setAgentLevelId(resdto.getAgentLevelId());
                lineBean.setAgentLevelName(resdto.getAgentLevelName());
                lineBean.setPassword(VCSMPasswordEncoder.base64Decode(resdto.getPassword()));
                lineBean.setAgentLevelCode(resdto.getAgentLevelCode());
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
