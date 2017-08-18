package conan.weiax.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceProxy extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return DataSourceContextHolder.getDatabaseType();
	}

}
