package com.simba.framework.util.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.simba.framework.distributed.ClusterExecute;
import com.simba.framework.distributed.DistributedData;
import com.simba.framework.session.page.PageParameter;
import com.simba.framework.session.page.PageParameterUtil;

/**
 * 所有的bean初始化时执行的操作
 * 
 * @author caozj
 *
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

	private static final Log logger = LogFactory.getLog(CustomBeanPostProcessor.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof PageParameter) {
			PageParameterUtil.registerPageParameter((PageParameter) bean);
			logger.info("注入页面扩展参数:" + ((PageParameter) bean).getKey());
		} else if (bean instanceof ClusterExecute) {
			String classFullPath = bean.getClass().getCanonicalName();
			DistributedData.getInstance().add(classFullPath, (ClusterExecute) bean);
			logger.info("注入集群方法:" + classFullPath);
		}
		return bean;
	}

}
