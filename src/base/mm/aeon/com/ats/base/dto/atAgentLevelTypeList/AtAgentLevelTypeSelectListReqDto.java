/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.atAgentLevelTypeList;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "AtAgentLevelType")
public class AtAgentLevelTypeSelectListReqDto implements IReqServiceDto {

    /**
     * 
     */
    private static final long serialVersionUID = -5275541907255408469L;

    private Boolean delFlag;

    private Integer limit;

    private Integer offset;

    private String sortField;

    private String sortOrder;

    @Override
    public DaoType getDaoType() {
        return DaoType.SELECT_LIST;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
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
