/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.agentLevelInfoRegisterService;

import mm.aeon.com.ats.base.dto.agentLevelInfoRegister.AgentLevelInfoRegisterReqDto;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentLevelInfoRegisterService extends AbstractService
        implements IService<AgentLevelInfoRegisterServiceReqBean, AgentLevelInfoRegisterServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public AgentLevelInfoRegisterServiceResBean execute(AgentLevelInfoRegisterServiceReqBean reqBean) {

        AgentLevelInfoRegisterReqDto reqDto = new AgentLevelInfoRegisterReqDto();
        AgentLevelInfoRegisterServiceResBean resBean = new AgentLevelInfoRegisterServiceResBean();

        try {
            reqDto.setName(reqBean.getName());
            reqDto.setTimeMinuteInterval(reqBean.getTimeMinuteInterval());
            reqDto.setCreatedBy(CommonUtil.getLoginUserInfo().getUserId());
            reqDto.setCreatedTime(CommonUtil.getCurrentTimeStamp());
            reqDto.setDelFlag(false);
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
