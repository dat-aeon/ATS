/**************************************************************************
 * $Date: 2018-09-04$
 * $Author: Arjun $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/
package mm.aeon.com.ats.front.categoryList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import mm.com.dat.presto.main.log.ApplicationLogger;
import mm.com.dat.presto.main.log.LogLevel;

public class CategoryListPaginationController extends LazyDataModel<CategoryListLineBean> {

    /**
     * 
     */
    private static final long serialVersionUID = -8785598350999473739L;

    private int rowCount;

    private List<CategoryListLineBean> allDataList;

    private ApplicationLogger applicationLogger = new ApplicationLogger();

    public CategoryListPaginationController(int rowCount, List<CategoryListLineBean> allDataList) {
        this.rowCount = rowCount;
        this.allDataList = allDataList;
    }

    @Override
    public Object getRowKey(CategoryListLineBean line) {
        return line.getName();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public List<CategoryListLineBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        applicationLogger.log("Category Search Pagination Process started.", LogLevel.INFO);

        List<CategoryListLineBean> resultList = new ArrayList<CategoryListLineBean>();

        int endIndex = first + pageSize;

        if (endIndex > allDataList.size()) {
            endIndex = allDataList.size();
        }

        if (sortField != null) {
            Collections.sort(allDataList, new CategoryListLazySorter(sortField, sortOrder));
        }

        resultList = allDataList.subList(first, endIndex);

        applicationLogger.log("Category Search Pagination Process finished.", LogLevel.INFO);
        return resultList;
    }

}
