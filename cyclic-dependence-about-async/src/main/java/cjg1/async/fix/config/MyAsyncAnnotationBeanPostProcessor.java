package cjg1.async.fix.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: chenjingang@guazi.com  2020-06-30 20:59
 * <p>
 * 解决spring使用async的循环依赖
 */
public class MyAsyncAnnotationBeanPostProcessor extends AsyncAnnotationBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {


    private final Set<Object> earlyAsyncProxyReferences = Collections.newSetFromMap(new ConcurrentHashMap<>(16));

    /**
     * 提前暴露，利用earlyAsyncProxyReferences来将代理提前
     *
     * 放入下面的单例工厂（其中一级缓存）
     * @see DefaultSingletonBeanRegistry#addSingletonFactory(java.lang.String, org.springframework.beans.factory.ObjectFactory)
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        Object cacheKey = getCacheKey(bean.getClass(), beanName);
        this.earlyAsyncProxyReferences.add(cacheKey);
        logger.warn("提前代理前async:" + bean);
        Object o = super.postProcessAfterInitialization(bean, beanName);
        logger.warn("提前代理后async:" + o);

        return o;
    }

    /**
     * 如果在getEarlyBeanReference（出现循环依赖的时候才会调用getEarlyBeanReference）代理过了，那么直接返回
     * @param bean
     * @param beanName
     * @return
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Object cacheKey = getCacheKey(bean.getClass(), beanName);
        if (!this.earlyAsyncProxyReferences.contains(cacheKey)) {
            return super.postProcessAfterInitialization(bean, beanName);
        }
        logger.error("已经提前代理async:" + bean);
        return bean;
    }

    protected Object getCacheKey(Class<?> beanClass, @Nullable String beanName) {
        if (StringUtils.hasLength(beanName)) {
            return (FactoryBean.class.isAssignableFrom(beanClass) ?
                    BeanFactory.FACTORY_BEAN_PREFIX + beanName : beanName);
        } else {
            return beanClass;
        }
    }
}
