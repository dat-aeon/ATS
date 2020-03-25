/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.agentInfoUpdateService;

import mm.aeon.com.ats.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ats.base.dto.agentInfoUpdate.AgentInfoUpdateReqDto;
import mm.aeon.com.ats.base.dto.agentLevelSelectForUpdate.AgentLevelSelectForUpdateReqDto;
import mm.aeon.com.ats.base.dto.agentLevelSelectForUpdate.AgentLevelSelectForUpdateResDto;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PhysicalRecordLockedException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class AgentInfoUpdateService extends AbstractService
        implements IService<AgentInfoUpdateServiceReqBean, AgentInfoUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public AgentInfoUpdateServiceResBean execute(AgentInfoUpdateServiceReqBean reqBean) {

        AgentInfoUpdateServiceResBean resBean = new AgentInfoUpdateServiceResBean();
        AgentLevelSelectForUpdateReqDto selectForUpdateReqDto = new AgentLevelSelectForUpdateReqDto();
        selectForUpdateReqDto.setAgentLevelId(reqBean.getAgentLevelId());

        try {
            AgentLevelSelectForUpdateResDto selectForUpdateResDto =
                    (AgentLevelSelectForUpdateResDto) this.getDaoServiceInvoker().execute(selectForUpdateReqDto);

            if (selectForUpdateResDto == null) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_NOT_FOUND_ERROR);
            } else if (null != selectForUpdateResDto.getUpdatedTime()
                    && !selectForUpdateResDto.getUpdatedTime().equals(reqBean.getUpdatedTime())) {
                resBean.setServiceStatus(ASSServiceStatusCommon.RECORD_ALREADY_UPDATE);
            } else {
                AgentInfoUpdateReqDto updateReqDto = new AgentInfoUpdateReqDto();

                updateReqDto.setAgentLevelId(reqBean.getAgentLevelId());
                updateReqDto.setAgentName(reqBean.getAgentName());
                updateReqDto.setPassword(reqBean.getPassword());
                updateReqDto.setAgentLevelCode(reqBean.getAgentLevelCode());
                updateReqDto.setDelFlag(reqBean.getDelFlag());
                updateReqDto.setUpdatedBy(CommonUtil.getLoginUserInfo().getUserId());
                updateReqDto.setUpdatedTime(CommonUtil.getCurrentTimeStamp());
                updateReqDto.setAtAgentLevelTypeId(reqBean.getAtAgentLevelTypeId());
                this.getDaoServiceInvoker().execute(updateReqDto);
                resBean.setServiceStatus(ServiceStatusCode.OK);

            }
        } catch (PrestoDBException e) {
            if (e instanceof RecordDuplicatedException) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_DUPLICATED_ERROR);
            } else if (e instanceof PhysicalRecordLockedException) {
                resBean.setServiceStatus(ServiceStatusCode.PHYSICAL_RECORD_LOCKED_ERROR);
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
