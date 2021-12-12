package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.QueryAlgorithmHandler;
import testSupport.LambdaTest;

public class QueryAlgorithmTest extends LambdaTest
{
	@Test
	public void testQueryAlgorithm()
	{
		apiCall = "query algorithm";
		handler = new QueryAlgorithmHandler();
		Map<String, Object> requestSuccess = new HashMap<>();
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
