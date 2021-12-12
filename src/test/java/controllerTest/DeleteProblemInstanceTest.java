package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.DeleteProbleminstanceHandler;
import testSupport.LambdaTest;

public class DeleteProblemInstanceTest extends LambdaTest 
{
	@Test
	public void testProblemInstance()
	{
		apiCall = "delete problemInstance";
		handler = new DeleteProbleminstanceHandler();
		Map<String, Object> request = new HashMap<>();
		request.put("uid", 1);
		request.put("pid", 3);
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
