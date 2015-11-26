package com.t2t.top.base.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;

/******************************************************************************
 * 
 * 
 * 支持自定义加载映射文件的版本
 * 
 * 	<bean id="mapper" class="org.dozer.DozerBeanMapper">
		<property name="mappingFiles">
			<list>
				<value>dozer-config/pojocopy.xml</value>
			</list>
		</property>
	</bean>

	<bean id="beanMapper" class="cn.com.gome.hotel.utils.BeanMapper">
		<property name="mapper" ref="mapper"></property>
	</bean>
 * @author chenxushao@hotmail.com
 * @date 2015年4月24日 下午2:48:44
 * 
 *****************************************************************************/
public class CustomBeanMapper {

	private DozerBeanMapper mapper;

	/**
	 * 基于Dozer转换对象的类型.
	 */
	public <T> T map(Object source, Class<T> destinationClass) {
		return mapper.map(source, destinationClass);
	}

	/**
	 * 基于Dozer转换Collection中对象的类型.
	 */
	public <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List<T> destinationList = new ArrayList<T>();
		for (Object sourceObject : sourceList) {
			T destinationObject = mapper.map(sourceObject, destinationClass);
			if (destinationObject != null) {
				destinationList.add(destinationObject);
			}
		}
		return destinationList;
	}

	public <T> Set<T> mapSet(Collection sourceList, Class<T> destinationClass) {
		Set<T> destinationSet = new HashSet<T>();
		for (Object sourceObject : sourceList) {
			T destinationObject = mapper.map(sourceObject, destinationClass);
			if (destinationObject != null) {
				destinationSet.add(destinationObject);
			}
		}
		return destinationSet;
	}

	/**
	 * 基于Dozer将对象A的值拷贝到对象B中.
	 */
	public void copy(Object source, Object destinationObject) {
		mapper.map(source, destinationObject);
	}

	/**
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}
}
