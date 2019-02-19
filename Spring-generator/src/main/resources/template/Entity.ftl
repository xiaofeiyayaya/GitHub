package ${content.entity.classPackage};

/**
 * ${content.entity.tableName}实体类
 * 
 * @author 
 *
 */
public class ${content.entity.className} extends BaseEntity{
	<#list content.entity.attrs as item> 
	//${item.remarks!}
	private ${item.javaType} ${item.field}; 
	</#list>
	
	<#list content.entity.attrs as item> 
	
	public ${item.javaType} ${item.fget}() {
		return ${item.field};
	}

	public void ${item.fset}(${item.javaType} ${item.field}) {
		this.${item.field} = ${item.field};
	}
	</#list>
}
