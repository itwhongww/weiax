package conan.weiax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    public static void main(String[] args){
    	List<String> list = new ArrayList<String>();
    	list.add("1");
    	list.add("123");
    	list.add("12");
    	list.add("2315");
    	int maxCount = 0;
    	Iterator<String> iterator = list.iterator();
    	while(iterator.hasNext()){
    		String tmp = iterator.next();
    		if(tmp.length()>maxCount){
    			maxCount = tmp.length();
    		}
    	}
    	
    	for(int i = 0 ; i < maxCount ;i ++){
    		List<String> tmpList = new ArrayList<String>();
    		for(String tmp : list){
    			if(tmp.length() >= i+1){
    				String tr = tmp.substring(i, i+1);
    				tmpList.add(tr);
    			}
    		}
    		compare(tmpList);
    	}
    }
    public static void compare(List<String> list){
    	Map<String,Integer> map = new HashMap<String,Integer>();
    	for(String tmp:list){
    		if(map.containsKey(tmp)){
    			map.put(tmp,map.get(tmp)+1);
    		}else{
    			map.put(tmp,1);
    		}
    	}
    	Set<String> set = map.keySet();
    	String maxKey = null;
    	int maxValue = 0;
    	for(String key:set){
    		int value = map.get(key);
    		if(value > maxValue){
    			maxKey = key;
    			maxValue = value;
    		}else if(value == maxValue){
    			maxKey = maxKey+","+key;
    		}
    	}
    	System.out.println("=========maxKey:"+maxKey+"---maxValue:"+maxValue);
    }
}
