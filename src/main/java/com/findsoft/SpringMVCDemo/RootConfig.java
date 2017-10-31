package com.findsoft.SpringMVCDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
// �����࣬������Ҫxml�ļ�
@ComponentScan
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class RootConfig {

    // ���ݿ��������
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/babysong?useUnicode=true&amp;characterEncoding=utf-8";
    private final String USER_NAME = "root";
    private final String USER_PWD = "851024";

    // druid���ݿ����ӳ��������
    private final int MAX_ACTIVE = 20;
    private final int INITIAL_SIZE = 3;
    private final int MIN_IDLE = 3;
    private final long MAX_WAITTING = 60000;// ���ȴ�ʱ�� ��λ����
    private final boolean PS_CACHE_FLAG = true;
    private final int PS_CACHE_SIZE = 20;

    @Bean(name = "dataSource")
    public DruidDataSource getDataConnectPool() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(USER_PWD);

        dataSource.setMaxActive(MAX_ACTIVE);
        dataSource.setInitialSize(INITIAL_SIZE);
        dataSource.setMinIdle(MIN_IDLE);
        dataSource.setMaxWait(MAX_WAITTING);

        dataSource.setPoolPreparedStatements(PS_CACHE_FLAG);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(PS_CACHE_SIZE);
        return dataSource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbctemplate() {
        return new JdbcTemplate(getDataConnectPool(), true);

    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getTransactionManager() {
        return new DataSourceTransactionManager(getDataConnectPool());
    }
}
