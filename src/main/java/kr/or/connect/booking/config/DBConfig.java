package kr.or.connect.booking.config;

import javax.sql.DataSource;

//import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
//@Configuration
//@EnableTransactionManagement // 트랜잭션과 관련된 설정을 자동으로해주는 어노테이션, 디비에 쓰이므로 꼭 해주자!
//@PropertySource("classpath:application.properties")
//public class DBConfig implements TransactionManagementConfigurer {
	/*
	private String driverClassName  = "com.mysql.jdbc.Driver";

	private String url = "jdbc:mysql://localhost:3307/connectdb?useUnicode=true&characterEncoding=utf8";

	private String username = "connectuser";

	private String password = "1234";
	*/
//	@Value("${spring.datasource.driver-class-name}")
//	private String driverClassName;
//	@Value("${spring.datasource.url}")
//	private String url;
//	@Value("${spring.datasource.username}")
//	private String username;
//	@Value("${spring.datasource.password}")
//	private String password;
//
//
//
//	@Bean
//	public DataSource dataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(driverClassName);
//		dataSource.setUrl(url);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//		return dataSource;
//	}
//
//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return transactionManger();
//	}
//
//
//	@Bean
//	public PlatformTransactionManager transactionManger() {
//		return new DataSourceTransactionManager(dataSource());
//	}


//	@Bean
//	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
//		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
//		databasePopulator.addScript(new ClassPathResource("dbScript/ddl.sql"));
//		databasePopulator.addScript(new ClassPathResource("dbScript/dml.sql"));
//		databasePopulator.setIgnoreFailedDrops(true);
//
//		DataSourceInitializer initializer = new DataSourceInitializer();
//		initializer.setDataSource(dataSource);
//		initializer.setDatabasePopulator(databasePopulator);
//
//		return initializer;
//	}
	
	
	
	
	
	
	
	
	
//}
