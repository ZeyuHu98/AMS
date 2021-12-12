package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.QueryBenchmarkHandler;
import testSupport.LambdaTest;

public class QueryBenchmarkTest extends LambdaTest
{
	@Test
	public void testQueryBenchmark()
	{
		apiCall = "query benchmark";
		handler = new QueryBenchmarkHandler();
		Map<String, Object> requestSuccess = new HashMap<>();
		requestSuccess.put("iid", "1");
		requestSuccess.put("pid", "1");
		try
		{
			testSucceed(requestSuccess);
			
		}
		catch (AssertionError e) 
		{
			Assert.fail(e.getMessage());
		}
	}
}
