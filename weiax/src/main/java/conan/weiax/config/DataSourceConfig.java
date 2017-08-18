package conan.weiax.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
@Configuration
public class DataSourceConfig {
	
	@Value("${datasource.type}")
	private Class<? extends DataSource> dataSourceType;
	
	@Bean(name = "writeDataSource", destroyMethod="close")
	@Primary
	@ConfigurationProperties(prefix = "datasource.write")
	public DataSource writeDataSource(){
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
	
	@Bean(name = "readDataSource", destroyMethod = "close")
	@ConfigurationProperties(prefix = "datasource.read")
	public DataSource readDataSourceOne(){
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

}
