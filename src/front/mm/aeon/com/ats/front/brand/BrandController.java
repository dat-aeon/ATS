/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.brand;

import mm.aeon.com.ats.base.dto.brandList.BrandSearchCountReqBean;
import mm.aeon.com.ats.base.dto.brandList.BrandSelectListReqDto;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.front.common.util.CommonUtil;
import mm.aeon.com.ats.log.ASSLogger;
import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.core.front.controller.IControllerAccessor;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.exception.DaoSqlException;
import mm.com.dat.presto.main.exception.PrestoDBException;
import mm.com.dat.presto.main.front.message.MessageBean;
import mm.com.dat.presto.main.front.message.MessageType;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class BrandController extends AbstractController implements IControllerAccessor<BrandFormBean, BrandFormBean> {
    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public BrandFormBean process(BrandFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        applicationLogger.log("Brand Searching Process started.", LogLevel.INFO);

        MessageBean messageBean;

        BrandSearchCountReqBean reqCountDto = new BrandSearchCountReqBean();

        BrandHeaderBean headerBean = formBean.getSearchHeaderBean();

        if (headerBean.getBrandName() != null)
            reqCountDto.setBrandName(headerBean.getBrandName().trim().toLowerCase());

        if (headerBean.getBrandCode() != null)
            reqCountDto.setBrandCode(headerBean.getBrandCode().trim().toLowerCase());

        BrandSelectListReqDto reqDto = new BrandSelectListReqDto();
        reqDto.setBrandName(reqCountDto.getBrandName());
        reqDto.setBrandCode(reqCountDto.getBrandCode());

        try {
            int totalCount = (Integer) CommonUtil.getDaoServiceInvoker().execute(reqCountDto);
            formBean.setTotalCount(totalCount);
            formBean.setBrandSelectListReqDto(reqDto);
            if (totalCount == 0)
                messageBean = new MessageBean(MessageId.MI0008);
            else
                messageBean = new MessageBean(MessageId.MI0007, String.valueOf(totalCount));
            messageBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Brand searching finished", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
