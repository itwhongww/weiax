package conan.weiax.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({ DataSourceConfig.class })
@MapperScan(basePackages = {"conan.weiax.*.dao"})
public class MybatisConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(
			@Qualifier("dataSourceProxy") DataSourceProxy ds) throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);
		return fb.getObject();
	}

	@Bean
	public DataSourceProxy dataSourceProxy(
			@Qualifier("writeDataSource") DataSource wds,
			@Qualifier("readDataSource") DataSource rds) {

		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put(DataSourceType.write.getType(), wds);
		targetDataSources.put(DataSourceType.read.getType(), rds);
		
		DataSourceProxy proxy = new DataSourceProxy();
		proxy.setDefaultTargetDataSource(wds);
		proxy.setTargetDataSources(targetDataSources);
		return proxy;
	}
}
