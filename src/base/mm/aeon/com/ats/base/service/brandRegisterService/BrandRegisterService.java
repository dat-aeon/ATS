/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.brandRegisterService;

import mm.aeon.com.ats.base.dto.brandRegister.BrandRegisterReqDto;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class BrandRegisterService extends AbstractService
        implements IService<BrandRegisterServiceReqBean, BrandRegisterServiceResBean> {
    private ASSLogger logger = new ASSLogger();

    @Override
    public BrandRegisterServiceResBean execute(BrandRegisterServiceReqBean reqBean) {
        // TODO Auto-generated method stub
        BrandRegisterReqDto reqDto = new BrandRegisterReqDto();

        BrandRegisterServiceResBean resBean = new BrandRegisterServiceResBean();

        reqDto.setCreatedBy(CommonUtil.getLoginUserInfo().getUserId());
        reqDto.setCreatedTime(CommonUtil.getCurrentTimeStamp());
        reqDto.setBrandCode(reqBean.getBrandCode());
        reqDto.setBrandName(reqBean.getBrandName());

        try {
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
