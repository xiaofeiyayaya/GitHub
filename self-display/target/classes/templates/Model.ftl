package ${package_name}.domain.${package_name_suffix}.${table_name};
import com.cn.jxf.domain;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;

/**
* 描述：${table_annotation}模型
* @author ${author}
* @date ${date}
*/
@Entity
@Table(name="${table_name_small}")
@Where(clause = "status > '0'")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ${table_name} extends BaseEntity {

    <#if model_column?exists>
    <#list model_column as model>
    /**
    *${model.columnComment!}
    */
    <#if (model.columnType = 'INT')>
    @Column(name = "${model.columnName}",columnDefinition = "VARCHAR")
    private String ${model.changeColumnName?uncap_first};
    </#if>
    
    <#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
    @Column(name = "${model.columnName}",columnDefinition = "VARCHAR")
    private String ${model.changeColumnName?uncap_first};
    </#if>
    
    <#if model.columnType = 'DATETIME' >
    @Column(name = "${model.columnName}",columnDefinition = "TIMESTAMP")
    private Date ${model.changeColumnName?uncap_first};
    </#if>
    </#list>
    </#if>

	<#if model_column?exists>
	<#list model_column as model>
	<#if (model.columnType = 'INT')>
    public String get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
	</#if>

	<#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
    public String get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
	</#if>
	
	<#if model.columnType = 'DATETIME' >
    public Date get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
	</#if>
	</#list>
	</#if>

}
