
package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * 工厂模式接口
 * 一共12个方法:
 * 5个获取实例方法
 * 5个判断方法
 * 1个获取类型方法
 * 1个获取别名方法
 */
public interface BeanFactory {

	/**
	 * 使用BeanFactory的getBean时,如果该Bean实例是一个工厂类(FactoryBean类型),则调用该对象的getObject方法
	 * 返回这个工厂实例生产的产品,如果调用getBean时加上&则返回该工厂实例本身
	 */
	String FACTORY_BEAN_PREFIX = "&";

	/**
	 * 根据name获取Bean实例
	 */
	Object getBean(String name) throws BeansException;

	/**
	 * 根据name和类型获取Bean实例
	 */
	<T> T getBean(String name, @Nullable Class<T> requiredType) throws BeansException;

	/**
	 * 根据name找到相应的Bean,并调用有参构造函数进行初始化
	 */
	Object getBean(String name, Object... args) throws BeansException;

	/**
	 * 根据类型获取Bean实例
	 */
	<T> T getBean(Class<T> requiredType) throws BeansException;

	/**
	 * 根据类型找到相应的Bean,并调用有参构造函数进行初始化
	 */
	<T> T getBean(Class<T> requiredType, Object... args) throws BeansException;

	/**
	 * 根据name判断容器中是否存在该Bean
	 */
	boolean containsBean(String name);

	/**
	 * 根据name判断该Bean是否是单例
	 */
	boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

	/**
	 * 根据name判断该Bean是否是原型
	 */
	boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

	/**
	 * 根据name和type对容器中的Bean进行比对
	 */
	boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException;

	/**
	 * 根据name和type对容器中的Bean进行比对
	 */
	boolean isTypeMatch(String name, @Nullable Class<?> typeToMatch) throws NoSuchBeanDefinitionException;

	/**
	 * 根据name获取Bean的类型
	 */
	@Nullable
	Class<?> getType(String name) throws NoSuchBeanDefinitionException;

	/**
	 * 根据name获取Bean的别名
	 */
	String[] getAliases(String name);

}
