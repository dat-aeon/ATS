/**************************************************************************
 * $Date : $
 * $Author : $
 * $Rev : $
 * Copyright (c) 2014 DIR-ACE Technology Ltd. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.operatorList;

import java.util.ArrayList;
import java.util.List;

import mm.aeon.com.ats.base.dto.userInfoSelectList.UserInfoSelectListReqDto;
import mm.aeon.com.ats.base.dto.userInfoSelectList.UserInfoSelectListResDto;
import mm.aeon.com.ats.front.common.constants.MessageId;
import mm.aeon.com.ats.front.common.constants.VCSCommon;
import mm.aeon.com.ats.front.common.exception.InvalidScreenTransitionException;
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
import mm.com.dat.presto.utils.common.InputChecker;

public class OperatorListController extends AbstractController
        implements IControllerAccessor<OperatorListFormBean, OperatorListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public OperatorListFormBean process(OperatorListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        if (!VCSCommon.USER_TYPE_ADMIN.equals(CommonUtil.getLoginUserInfo().getUserTypeName())) {
            logger.log("Invalid URL Access.[Operator List]", new InvalidScreenTransitionException(), LogLevel.ERROR);
            throw new InvalidScreenTransitionException();
        }

        applicationLogger.log("Operator searching process stared.", LogLevel.INFO);
        MessageBean messageBean;

        UserInfoSelectListReqDto reqDto = new UserInfoSelectListReqDto();
        reqDto.setUserTypeId(VCSCommon.USER_TYPE_ATS_OPERATOR_ID);

        if (!InputChecker.isBlankOrNull(formBean.getSearchHeaderBean().getUserLoginId())) {
            reqDto.setLoginId(formBean.getSearchHeaderBean().getUserLoginId().toLowerCase());
        }
        if (!InputChecker.isBlankOrNull(formBean.getSearchHeaderBean().getUserName())) {
            reqDto.setName(formBean.getSearchHeaderBean().getUserName().toLowerCase());
        }

        try {
            List<UserInfoSelectListResDto> resDtoList =
                    (List<UserInfoSelectListResDto>) this.getDaoServiceInvoker().execute(reqDto);
            List<OperatorListLineBean> lineBeanList = new ArrayList<OperatorListLineBean>();

            for (UserInfoSelectListResDto resDto : resDtoList) {
                OperatorListLineBean lineBean = new OperatorListLineBean();

                lineBean.setUpdatedTime(resDto.getUpdatedTime());
                lineBean.setUserId(resDto.getUserId());
                lineBean.setUserLoginId(resDto.getLoginId());
                lineBean.setUserName(resDto.getName());
                lineBean.setCreatedTime(resDto.getCreatedTime());
                lineBean.setCreatedBy(resDto.getCreatedBy());
                lineBean.setUpdatedBy(resDto.getUpdatedBy());


                lineBeanList.add(lineBean);
            }

            formBean.setLineBeanList(lineBeanList);

            if (lineBeanList.size() == 0) {
                messageBean = new MessageBean(MessageId.MI0008);
            } else {
                messageBean = new MessageBean(MessageId.MI0007, String.valueOf(lineBeanList.size()));
            }
            messageBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(messageBean);
            applicationLogger.log(messageBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Operator searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
