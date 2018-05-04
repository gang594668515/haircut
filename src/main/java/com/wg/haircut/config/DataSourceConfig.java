package com.wg.haircut.config;


import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 数据源的配置:在springboot2.0版本，如果不定义SqlSessionFactory，那么将会导致错误，因为无法注入SqlSessionFactory，
 * 下面注释部分的内容非常的重要，这也是整合当中遇到的很大的坑，如果不将PageInterceptor作为插件设置到SqlSessionFactoryBean中，
 * 导致分页失效
 * Created by Gang.Wang on 2018/5/03.
 */
@Configuration
@MapperScan("com.wg.haircut.dao")
@EnableTransactionManagement
public class DataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private Environment env;

    @Autowired
    private PageInterceptor pageInterceptor;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dataSource);
        //该配置非常的重要，如果不将PageInterceptor设置到SqlSessionFactoryBean中，导致分页失效
        fb.setPlugins(new Interceptor[]{pageInterceptor});
        fb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        return fb.getObject();
    }
}
