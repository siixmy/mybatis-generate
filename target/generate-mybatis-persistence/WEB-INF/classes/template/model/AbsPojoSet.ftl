package ${ftl_model_package};

/**
* pojo基础信息装填
*
* @author ${ftl_author}
* @date ${ftl_now}
* @version ${ftl_version}
*/
public abstract class AbsPojoSet<T> {

    //排序字段
    private String orderBy;

    public abstract void setPk(Long pk) ;

    public abstract Long getPk();

    public String getOrderBy() {
    return orderBy;
    }

    public T setOrderBy(String orderBy) {
    this.orderBy = orderBy;
    return (T)this;
    }
    }
