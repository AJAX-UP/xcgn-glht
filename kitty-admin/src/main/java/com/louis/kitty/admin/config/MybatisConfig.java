package com.louis.kitty.admin.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Mybatis配置
 * @author Louis
 * @date Oct 29, 2018
 */
@Configuration
@MapperScan("com.louis.kitty.*.dao")	// 扫描DAO
public class MybatisConfig {
  @Autowired
  private DataSource dataSource;

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setTypeAliasesPackage("com.louis.kitty.*.model");	// 扫描Model

	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));	// 扫描映射文件

    return sessionFactory.getObject();
  }
}
