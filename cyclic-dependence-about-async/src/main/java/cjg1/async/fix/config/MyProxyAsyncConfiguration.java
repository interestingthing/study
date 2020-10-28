///*
// * Copyright 2002-2015 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package cjg.async.fix.config;
//
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Role;
//import org.springframework.core.annotation.AnnotationAttributes;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.core.type.AnnotationMetadata;
//import org.springframework.scheduling.annotation.AbstractAsyncConfiguration;
//import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.config.TaskManagementConfigUtils;
//import org.springframework.util.Assert;
//
//import java.lang.annotation.Annotation;
//
///**
// * 此处继承是为了使用注解 cjg.async.MyEnableAsync 从而加载自己优化之后的配置类
// */
//@Configuration
//@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//public class MyProxyAsyncConfiguration extends AbstractAsyncConfiguration {
//
//    @Override
//    public void setImportMetadata(AnnotationMetadata importMetadata) {
//        this.enableAsync = AnnotationAttributes.fromMap(
//                importMetadata.getAnnotationAttributes(MyEnableAsync.class.getName(), false));
//        if (this.enableAsync == null) {
//            throw new IllegalArgumentException(
//                    "@EnableAsync is not present on importing class " + importMetadata.getClassName());
//        }
//    }
//
//    @Bean(name = TaskManagementConfigUtils.ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME)
//    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//    @Primary
//    public AsyncAnnotationBeanPostProcessor asyncAdvisor() {
//        Assert.notNull(this.enableAsync, "@EnableAsync annotation metadata was not injected");
//        AsyncAnnotationBeanPostProcessor bpp = new MyAsyncAnnotationBeanPostProcessor();
//        Class<? extends Annotation> customAsyncAnnotation = this.enableAsync.getClass("annotation");
//        if (customAsyncAnnotation != AnnotationUtils.getDefaultValue(EnableAsync.class, "annotation")) {
//            bpp.setAsyncAnnotationType(customAsyncAnnotation);
//        }
//        if (this.executor != null) {
//            bpp.setExecutor(this.executor);
//        }
//        if (this.exceptionHandler != null) {
//            bpp.setExceptionHandler(this.exceptionHandler);
//        }
//        bpp.setProxyTargetClass(this.enableAsync.getBoolean("proxyTargetClass"));
//        bpp.setOrder(this.enableAsync.<Integer>getNumber("order"));
//        return bpp;
//    }
//
//}
