package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.QueryImplementationHandler;
import testSupport.LambdaTest;

public class QueryImplementationTest extends LambdaTest
{
	@Test
	public void testQueryImplementation()
	{
		apiCall = "query implementation";
		handler = new QueryImplementationHandler();
		Map<String, Object> requestSuccess = new HashMap<>();
		requestSuccess.put("uid", "7");
		requestSuccess.put("aid", "1");
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
