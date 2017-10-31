package com.findsoft.SpringMVCDemo;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * �������web.xml,����DispatchServlet
 * 
 * @author Administrator
 *
 */
public class APPInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class,RedisConfig.class };// �������飬Ҳ����rootconfig���Զ����ͨ�������ݿ������Լ���ͨbean����
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
