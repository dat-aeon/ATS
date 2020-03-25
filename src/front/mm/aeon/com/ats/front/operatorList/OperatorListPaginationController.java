/**************************************************************************
 * $Date: 2018-09-05$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.operatorList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class OperatorListPaginationController extends LazyDataModel<OperatorListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private List<OperatorListLineBean> allDataList;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public OperatorListPaginationController(int rowCount, List<OperatorListLineBean> allDataList) {
        this.rowCount = rowCount;
        this.allDataList = allDataList;
    }

    @Override
    public Object getRowKey(OperatorListLineBean line) {
        return line.getUserLoginId();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<OperatorListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Operator Search Pagination Process started.", LogLevel.INFO);

        List<OperatorListLineBean> resultList = null;

        int endIndex = first + pageSize;

        if (endIndex > allDataList.size()) {
            endIndex = allDataList.size();
        }

        if (sortField != null) {
            Collections.sort(allDataList, new OperatorListLazySorter(sortField, sortOrder));
        }

        resultList = allDataList.subList(first, endIndex);

        applicationLogger.log("Operator Search Pagination Process finished.", LogLevel.INFO);
        return resultList;
    }

}
