package conan.weiax.config;

public class DataSourceContextHolder {

	private static final ThreadLocal<String> local = new ThreadLocal<String>();
	
	public static ThreadLocal<String> getLocal(){
		return local;
	}
	
	public static void change2Read(){
		local.set(DataSourceType.read.getType());
	}
	public static void change2Write(){
		local.set(DataSourceType.write.getType());
	}
	
	public static String getDatabaseType(){
		return local.get();
	}
}
