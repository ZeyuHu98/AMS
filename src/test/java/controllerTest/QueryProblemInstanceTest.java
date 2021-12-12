package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.QueryProblemInstanceHandler;
import testSupport.LambdaTest;

public class QueryProblemInstanceTest extends LambdaTest
{
	@Test
	public void testQueryProblemInstance()
	{
		apiCall = "query ProblemInstance";
		handler = new QueryProblemInstanceHandler();
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
