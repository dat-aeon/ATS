/**************************************************************************
 * $Date: 2018-06-20$
 * $Author: Swe Hsu Mon $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.agentInfoSelectList;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "AgentInfo")
public class AgentInfoSelectListReqDto implements IReqServiceDto {

    private static final long serialVersionUID = 2672207836115099916L;

    private String agentLevelName;

    private String agentLevelCode;

    private Integer atAgentLevelTypeId;

    private Integer limit;

    private Integer offset;

    private String sortField;

    private String sortOrder;

    @Override
    public DaoType getDaoType() {
        return DaoType.SELECT_LIST;
    }

    public Integer getAtAgentLevelTypeId() {
        return atAgentLevelTypeId;
    }

    public void setAtAgentLevelTypeId(Integer atAgentLevelTypeId) {
        this.atAgentLevelTypeId = atAgentLevelTypeId;
    }

    public String getAgentLevelName() {
        return agentLevelName;
    }

    public void setAgentLevelName(String agentLevelName) {
        this.agentLevelName = agentLevelName;
    }

    public String getAgentLevelCode() {
        return agentLevelCode;
    }

    public void setAgentLevelCode(String agentLevelCode) {
        this.agentLevelCode = agentLevelCode;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public String getSortField() {
        return sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
