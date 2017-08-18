package conan.weiax.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@AutoConfigureAfter({MybatisConfig.class})
@EnableTransactionManagement
public class TransactionManagerConfig {
	
	@Bean
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSourceProxy") DataSourceProxy ds){
		return new DataSourceTransactionManager(ds);

	}

}
