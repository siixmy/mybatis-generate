package ${ftl_service_package};

import ${ftl_package}.exception.${ftl_exception_class};
import com.tc.crm.utils.pagination.Page;
import com.tc.crm.utils.pagination.PageList;

import java.util.List;

/**
* 定义常用业务功能接口
*
* @author ${ftl_author}
* @date ${ftl_now}
* @version ${ftl_version}
*/
public interface IBaseService<T> {
    void create(T entity) throws ${ftl_exception_class};
    void modifyEntityById(T entity) throws ${ftl_exception_class};
    void deleteEntityById(Integer id) throws ${ftl_exception_class};
    T getEntityById(Integer id);
    int countByByCriteria(T entity);
    List<T> queryEntityList(T queryObject) throws ${ftl_exception_class};
    PageList<T> queryEntityPageList(PageAttribute pageAttr, T queryObject, Map<String, Object> otherParam);
 }
