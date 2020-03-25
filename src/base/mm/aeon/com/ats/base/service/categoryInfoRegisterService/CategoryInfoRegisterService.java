/**************************************************************************
 * $Date : $
 * $Author : Su Su Sandi $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.base.service.categoryInfoRegisterService;

import mm.aeon.com.ats.base.dto.categoryInfoRegister.CategoryInfoRegisterReqDto;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.common.service.bean.AbstractService;
import mm.com.dat.presto.main.common.service.bean.IService;
import mm.com.dat.presto.main.common.service.bean.ServiceStatusCode;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.RecordDuplicatedException;
import mm.com.dat.presto.main.log.LogLevel;

public class CategoryInfoRegisterService extends AbstractService
        implements IService<CategoryInfoRegisterServiceReqBean, CategoryInfoRegisterServiceResBean> {

    private ASSLogger logger = new ASSLogger();

    @Override
    public CategoryInfoRegisterServiceResBean execute(CategoryInfoRegisterServiceReqBean reqBean) {

        CategoryInfoRegisterReqDto reqDto = new CategoryInfoRegisterReqDto();
        CategoryInfoRegisterServiceResBean resBean = new CategoryInfoRegisterServiceResBean();
        
        reqDto.setCreatedBy(CommonUtil.getLoginUserInfo().getUserId());
        reqDto.setCreatedTime(CommonUtil.getCurrentTimeStamp());
        reqDto.setName(reqBean.getName());
        reqDto.setDelFlag(reqBean.getDelFlag());
        
        resBean.setServiceStatus(ServiceStatusCode.OK);

        try {
            this.getDaoServiceInvoker().execute(reqDto);  
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
