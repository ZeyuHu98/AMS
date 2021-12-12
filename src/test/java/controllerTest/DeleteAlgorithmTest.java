package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.DeleteAlgorithmHandler;
import testSupport.LambdaTest;

public class DeleteAlgorithmTest extends LambdaTest 
{
	@Test
	public void testQueryAlgorithm()
	{
		apiCall = "delete algorithm";
		handler = new DeleteAlgorithmHandler();
		Map<String, Object> request = new HashMap<>();
		request.put("uid", 1);
		request.put("aid", 1);
		try
		{
			testSucceed(request);
			testFail(new HashMap<String, Object>());
		}
		catch (AssertionError e) 
		{
			Assert.fail(e.getMessage());
		}
	}
}
