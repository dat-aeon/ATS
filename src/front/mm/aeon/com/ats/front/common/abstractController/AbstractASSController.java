/**************************************************************************
 * $Date: 2018-06-20$
 * $Author: Khin Yadanar Thein $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.common.abstractController;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import mm.aeon.com.ats.front.common.constants.VCSCommon;
 import mm.com.dat.presto.main.core.front.controller.AbstractController;
import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

/**
 * AbstractProjectController Class.
 * <p>
 * 
 * <pre>
 * 
 * </pre>
 * 
 * </p>
 */
public abstract class AbstractASSController extends AbstractController {

  
    private ApplicationLogger applicationLogger = new ApplicationLogger();

    protected ArrayList<SelectItem> getEmptyList() {

        applicationLogger.log("User Search Process started.", LogLevel.INFO);

        ArrayList<SelectItem> emptyList = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem();
        item.setLabel(VCSCommon.SPACE);
        item.setValue(null);
        emptyList.add(item);

        return emptyList;
    }

}
