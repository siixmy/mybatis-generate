package ${ftl_service_package}.impl;

import ${ftl_dao_package}.IBaseDao;
import ${ftl_dao_package}.I${className}Dao;
import ${ftl_model_package}.${className};
import ${ftl_service_package}.AbstractBaseService;
import ${ftl_service_package}.I${className}Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
* service impl
*
* @author ${ftl_author}
* @date ${ftl_now}
* @version ${ftl_version}
*/
@Service
public class ${className}Service extends AbstractBaseService<${className}> implements I${className}Service {
    @Resource
    private I${className}Dao ${className?uncap_first}Dao;

    @Override
    protected ICrmDao<${className}> getMyBatisRepository() {
        return ${className?uncap_first}Dao;
    }

<#list classFields as v>
    <#if v.isPrimaryKey=true>
        <#assign beanKeyName=v.field>
        <#assign beanKeyType=v.type>
    </#if>
</#list>

    @Override
    protected ${className} constructPkEntity(final Integer id) {
        return new ${className}(){{ set${beanKeyName?cap_first}(id);}};
    }

    @Override
    protected ${beanKeyType} getPk(${className} obj) {
        return obj.get${beanKeyName?cap_first}();
    }

}

