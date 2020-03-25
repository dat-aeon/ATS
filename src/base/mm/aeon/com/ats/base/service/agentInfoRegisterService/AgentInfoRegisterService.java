/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.agentInfoRegisterService;

import mm.aeon.com.ats.base.dto.agentInfoRegister.AgentInfoRegisterReqDto;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentInfoRegisterService extends AbstractService
        implements IService<AgentInfoRegisterServiceReqBean, AgentInfoRegisterServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public AgentInfoRegisterServiceResBean execute(AgentInfoRegisterServiceReqBean reqBean) {

        AgentInfoRegisterReqDto reqDto = new AgentInfoRegisterReqDto();
        AgentInfoRegisterServiceResBean resBean = new AgentInfoRegisterServiceResBean();

        try {
            reqDto.setAgentName(reqBean.getAgentName());
            reqDto.setPassword(reqBean.getPassword());
            reqDto.setAgentLevelCode(reqBean.getAgentLevelCode());
            reqDto.setCreatedBy(CommonUtil.getLoginUserInfo().getUserId());
            reqDto.setCreatedTime(CommonUtil.getCurrentTimeStamp());
            reqDto.setAtAgentLevelTypeId(reqBean.getAtAgentLevelTypeId());
            this.getDaoServiceInvoker().execute(reqDto);
            resBean.setServiceStatus(ServiceStatusCode.OK);

        } catch (Exception e) {
            if (e instanceof RecordDuplicatedException) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_DUPLICATED_ERROR);

            } else if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                resBean.setServiceStatus(ServiceStatusCode.SQL_ERROR);

            } else {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return resBean;
    }

}
