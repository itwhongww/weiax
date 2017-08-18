package conan.weiax.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class DataSourceAop {
	private static Logger log = LoggerFactory.getLogger(DataSourceAop.class);

	@Before("@annotation(conan.weiax.config.annotation.Read)")
	public void setReadDataSourceType(){
		log.info("dataSource切换到：Read");
		DataSourceContextHolder.change2Read();
	}
	@Before("@annotation(conan.weiax.config.annotation.Write)")
	public void setWriteDataSourceType(){
		log.info("dataSource切换到：Write");
		DataSourceContextHolder.change2Write();
	}
}
