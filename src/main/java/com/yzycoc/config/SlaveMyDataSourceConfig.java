package com.yzycoc.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

/**
 * @program: cscocutil
 * @description: SlaveDataSourceConfig
 * @author: yzy
 * @create: 2020-08-04 22:32
 * @Version 1.0
 **/
@Configuration
@MapperScan(basePackages = "com.yzycoc.cocutil.SQLmy",sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveMyDataSourceConfig {
    @Bean(name = "slaveDataSource")
    @ConfigurationProperties("spring.datasource.my")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        System.out.println("链接“My”个人配置数据库！");
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }
}

