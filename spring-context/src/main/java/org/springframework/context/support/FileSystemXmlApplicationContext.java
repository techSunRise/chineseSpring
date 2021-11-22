
package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;

/**
 * ApplicationContext接口的具体实现类
 */
public class FileSystemXmlApplicationContext extends AbstractXmlApplicationContext {

	/**
	 * 无参构造方法
	 */
	public FileSystemXmlApplicationContext() {
	}

	/**
	 * 设置父容器
	 * 1、调用AbstractApplicationContext的无参构造方法
	 * 2、调用AbstractApplicationContext的setParent方法
	 */
	public FileSystemXmlApplicationContext(ApplicationContext parent) {
		super(parent);
	}

	/**
	 * 设置容器单个配置文件,并刷新容器
	 * 定义配置文件路径三种方式:
	 * 1、不加盘符的路径默认为项目的根路径(例如:写"test.xml"默认在chineseSpring路径下查找)
	 * 2、加盘符的路径(例如:"C:\\test\\test.xml",使用C盘下test文件夹中的test.xml作为配置文件)
	 * 3、要使用类路径,必须加前缀classpath:(例如:"classpath:test.xml",使用类路径下的test.xml作为配置文件,类路径为项目的资源路径)
	 */
	public FileSystemXmlApplicationContext(String configLocation) throws BeansException {
		this(new String[] {configLocation}, true, null);
	}

	/**
	 * 设置容器多个配置文件,并刷新容器
	 */
	public FileSystemXmlApplicationContext(String... configLocations) throws BeansException {
		this(configLocations, true, null);
	}

	/**
	 * 设置父容器和多个配置文件，并刷新容器
	 */
	public FileSystemXmlApplicationContext(String[] configLocations, ApplicationContext parent) throws BeansException {
		this(configLocations, true, parent);
	}

	/**
	 * 设置容器多个配置文件,通过refresh来控制是否刷新容器
	 */
	public FileSystemXmlApplicationContext(String[] configLocations, boolean refresh) throws BeansException {
		this(configLocations, refresh, null);
	}

	/**
	 * 设置容器的配置文件路径和父容器,并通过refresh来控制容器是否刷新
	 * refresh()方法是刷新容器的入口方法
	 */
	public FileSystemXmlApplicationContext(
			String[] configLocations, boolean refresh, @Nullable ApplicationContext parent)
			throws BeansException {

		super(parent);
		setConfigLocations(configLocations);
		if (refresh) {
			refresh();
		}
	}

	/**
	 * 模板方法,重写DefaultResourceLoader中的方法
	 * 将传入的路径字符串转换成Resource(资源类)
	 */
	@Override
	protected Resource getResourceByPath(String path) {
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		return new FileSystemResource(path);
	}

}
