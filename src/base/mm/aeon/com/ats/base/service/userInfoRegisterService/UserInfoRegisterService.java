/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.userInfoRegisterService;

import mm.aeon.com.ats.base.dto.passwordInfoRegister.PasswordInfoRegisterReqDto;
import mm.aeon.com.ats.base.dto.userInfoRegister.UserInfoRegisterReqDto;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class UserInfoRegisterService extends AbstractService
        implements IService<UserInfoRegisterServiceReqBean, UserInfoRegisterServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public UserInfoRegisterServiceResBean execute(UserInfoRegisterServiceReqBean reqBean) {

        UserInfoRegisterReqDto reqDto = new UserInfoRegisterReqDto();
        PasswordInfoRegisterReqDto pwReqDto = null;

        UserInfoRegisterServiceResBean resBean = new UserInfoRegisterServiceResBean();

        reqDto.setCreatedBy(CommonUtil.getLoginUserInfo().getUserId());
        reqDto.setCreatedTime(CommonUtil.getCurrentTimeStamp());
        reqDto.setLoginId(reqBean.getLoginId());
        reqDto.setName(reqBean.getName());
        reqDto.setDelFlag(reqBean.getDelFlag());
        reqDto.setUserTypeId(reqBean.getUserTypeId());

        try {
            this.getDaoServiceInvoker().execute(reqDto);
            if (reqDto.getUserId() != null) {
                pwReqDto = new PasswordInfoRegisterReqDto();

                pwReqDto.setUserId(reqDto.getUserId());
                pwReqDto.setCreatedBy(CommonUtil.getLoginUserInfo().getUserId());
                pwReqDto.setUserTypeId(reqBean.getUserTypeId());
                pwReqDto.setPassword(reqBean.getPassword());
                pwReqDto.setDelFlag(0);
                pwReqDto.setCreatedTime(CommonUtil.getCurrentTimeStamp());

                this.getDaoServiceInvoker().execute(pwReqDto);
                resBean.setServiceStatus(ServiceStatusCode.OK);

            }
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
