package com.findsoft.SpringMVCDemo;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 此类代替web.xml,配置DispatchServlet
 * 
 * @author Administrator
 *
 */
public class APPInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class,RedisConfig.class };// 返回数组，也就是rootconfig可以多个，通常是数据库配置以及普通bean配置
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
