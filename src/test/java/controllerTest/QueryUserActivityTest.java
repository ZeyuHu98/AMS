package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.QueryUserActivityHandler;
import testSupport.LambdaTest;

public class QueryUserActivityTest extends LambdaTest 
{
	@Test
	public void testQueryUserActivity() throws Exception
	{ 
		apiCall = "query user activity";
		handler = new QueryUserActivityHandler();
		Map<String, Object> request = new HashMap<>();
		request.put("quid", 7);
		try
		{
			this.testSucceed(request);
		}
		catch (AssertionError e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
