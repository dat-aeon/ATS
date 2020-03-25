/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.brandUpdateService;

import mm.aeon.com.ats.base.common.constants.ASSServiceStatusCommon;
import mm.aeon.com.ats.base.dto.brandSelectForUpdate.BrandSelectForUpdateReqDto;
import mm.aeon.com.ats.base.dto.brandSelectForUpdate.BrandSelectForUpdateResDto;
import mm.aeon.com.ats.base.dto.brandUpdate.BrandUpdateReqDto;
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

public class BrandUpdateService extends AbstractService
        implements IService<BrandUpdateServiceReqBean, BrandUpdateServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public BrandUpdateServiceResBean execute(BrandUpdateServiceReqBean reqBean) {
        BrandUpdateServiceResBean resBean = new BrandUpdateServiceResBean();

        BrandSelectForUpdateReqDto selectForUpdateReqDto = new BrandSelectForUpdateReqDto();
        selectForUpdateReqDto.setBrandCode(reqBean.getBrandCode());

        try {
            BrandSelectForUpdateResDto selectForUpdateResDto =
                    (BrandSelectForUpdateResDto) this.getDaoServiceInvoker().execute(selectForUpdateReqDto);

            if (selectForUpdateResDto == null) {
                resBean.setServiceStatus(ServiceStatusCode.RECORD_NOT_FOUND_ERROR);
            } else if (null != selectForUpdateResDto.getUpdatedTime()
                    && !selectForUpdateResDto.getUpdatedTime().equals(reqBean.getUpdateTime())) {
                resBean.setServiceStatus(ASSServiceStatusCommon.RECORD_ALREADY_UPDATE);
            } else {
                BrandUpdateReqDto updateReqDto = new BrandUpdateReqDto();

                updateReqDto.setBrandCode(reqBean.getBrandCode());
                updateReqDto.setBrandName(reqBean.getBrandName());
                updateReqDto.setUpdatedBy(CommonUtil.getLoginUserInfo().getUserId());
                updateReqDto.setUpdatedTime(CommonUtil.getCurrentTimeStamp());
                updateReqDto.setDelFlag(reqBean.getDelFlag());

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
