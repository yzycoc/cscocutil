package com.yzycoc.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

import javax.sql.DataSource;

/**
 * @program: cscocutil
 * @description: MasterDataSourceConfig
 * @author: yzy
 * @create: 2020-08-04 22:31
 * @Version 1.0
 **/

@Configuration
@MapperScan(basePackages = "com.yzycoc.cocutil.SQLAll",sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterAllDataSourceConfig {
    @Primary
    @Bean(name = "masterDataSource")
    @ConfigurationProperties("spring.datasource.all")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        System.out.println("链接“ALL”中心数据库！");
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }
}
