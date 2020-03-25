/**************************************************************************
 * $Date: 2018-08-02$
 * $Author: Arjun$
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.categoryList;

import java.util.ArrayList;
import java.util.List;

import mm.aeon.com.ats.base.dto.categoryInfoSelectList.CategoryInfoSelectListReqDto;
import mm.aeon.com.ats.base.dto.categoryInfoSelectList.CategoryInfoSelectListResDto;
import mm.aeon.com.ats.front.common.constants.MessageId;
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

public class CategoryListSearchController extends AbstractController
        implements IControllerAccessor<CategoryListFormBean, CategoryListFormBean> {

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    private ASSLogger logger = new ASSLogger();

    @Override
    public CategoryListFormBean process(CategoryListFormBean formBean) {

        formBean.getMessageContainer().clearAllMessages(!formBean.getDoReload());

        applicationLogger.log("Category Searching Process started.", LogLevel.INFO);

        MessageBean msgBean;
        CategoryInfoSelectListReqDto reqDto = new CategoryInfoSelectListReqDto();

        if (!InputChecker.isBlankOrNull(formBean.getSearchHeaderBean().getName())) {
            reqDto.setName(formBean.getSearchHeaderBean().getName().toLowerCase());
        }

        reqDto.setCategoryId(formBean.getSearchHeaderBean().getCategoryId());

        try {
            List<CategoryInfoSelectListResDto> resDtoList =
                    (List<CategoryInfoSelectListResDto>) this.getDaoServiceInvoker().execute(reqDto);

            List<CategoryListLineBean> lineBeanList = new ArrayList<CategoryListLineBean>();

            for (CategoryInfoSelectListResDto resdto : resDtoList) {
                CategoryListLineBean lineBean = new CategoryListLineBean();

                lineBean.setCategoryId(resdto.getCategoryId());
                lineBean.setName(resdto.getName());
                lineBean.setUpdatedTime(resdto.getUpdatedTime());
                lineBean.setUpdatedBy(resdto.getUpdatedBy());
                lineBean.setCreatedBy(resdto.getCreatedBy());
                lineBean.setCreatedTime(resdto.getCreatedTime());
                lineBean.setDelFlag(resdto.getDelFlag());
                lineBeanList.add(lineBean);
            }

            formBean.setLineBeans(lineBeanList);

            if (lineBeanList.size() == 0) {
                msgBean = new MessageBean(MessageId.MI0008);
            } else {
                msgBean = new MessageBean(MessageId.MI0007, String.valueOf(lineBeanList.size()));
            }
            msgBean.setMessageType(MessageType.INFO);
            formBean.getMessageContainer().addMessage(msgBean);
            applicationLogger.log(msgBean.getMessage(), LogLevel.INFO);
            applicationLogger.log("Category searching finished.", LogLevel.INFO);

        } catch (PrestoDBException e) {
            if (e instanceof DaoSqlException) {
                logger.log(e.getCause().getMessage(), e.getCause(), LogLevel.ERROR);
                throw new BaseException(e.getCause());
            }
        }

        return formBean;
    }

}
