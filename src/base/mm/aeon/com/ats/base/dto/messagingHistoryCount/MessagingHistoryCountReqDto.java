/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.dto.messagingHistoryCount;

import java.util.Date;

import mm.com.dat.presto.main.common.dao.bean.DaoType;
import mm.com.dat.presto.main.common.dao.bean.IReqServiceDto;
import mm.com.dat.presto.main.core.dao.controller.MyBatisMapper;

@MyBatisMapper(namespace = "MessagingHistory")
public class MessagingHistoryCountReqDto implements IReqServiceDto {
    /**
     * 
     */
    private static final long serialVersionUID = 4163869201395549293L;

    private String customerName;

    private String agentName;

    private Date fromDate;

    private Date toDate;

    private Integer categoryId;

    private Integer brandId;

    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    @Override
    public DaoType getDaoType() {
        return DaoType.COUNT;
    }

}
