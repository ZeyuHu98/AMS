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
		Map<String, Object> successRequest = new HashMap<>();
		successRequest.put("aid", 1);
		try
		{
			this.testSucceed(successRequest);
		}
		catch (AssertionError e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
