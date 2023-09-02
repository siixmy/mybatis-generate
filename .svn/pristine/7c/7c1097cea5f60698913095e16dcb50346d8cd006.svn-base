package ${ftl_model_package};

import java.io.Serializable;
import java.util.Date;

/**
* model ${className}
*
* @author ${ftl_author}
* @date ${ftl_now}
* @version ${ftl_version}
*/
public class ${className} extends AbsPojoSet<${className}> implements Serializable {
<#list classFields as v>
    private ${v.type} ${v.field};
</#list>

<#list classFields as v>
    public ${className} set${v.field?cap_first}(${v.type} ${v.field}){
        this.${v.field} = ${v.field};
        return this;
    }
    public ${v.type} get${v.field?cap_first}(){
        return this.${v.field};
    }

</#list>

<#list classFields as v>
    <#if v.isPrimaryKey=true>
        <#assign beanKeyName=v.field>
    </#if>
</#list>

    @Override
    public void setPk(Long pk){
        this.${beanKeyName} = pk;
    }

    @Override
    public Long getPk() {
        return ${beanKeyName};
    }
}