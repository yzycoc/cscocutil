package com.yzycoc.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.yzycoc.custom.TimeUtiles;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * @program: cscocutil
 * @description: MasterDataSourceConfig
 * @author: yzy
 * @create: 2020-08-04 22:31
 * @Version 1.0
 **/

@Configuration
@MapperScan(basePackages = "com.yzycoc.cocutil.SQLMy",sqlSessionFactoryRef = "mySqlSessionFactory")
public class MasterMyDataSourceConfig {
    @Primary
    @Bean(name = "myDataSource")
    @ConfigurationProperties("spring.datasource.my")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("myDataSource") DataSource dataSource) throws Exception {
        System.out.println("链接“自己的”中心数据库！"+ TimeUtiles.getStringDate());
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }
}
