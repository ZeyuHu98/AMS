package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.DeleteImplementationHandler;
import testSupport.LambdaTest;

public class DeleteImplementationTest extends LambdaTest 
{
	@Test
	public void testDeleteImplementation()
	{
		apiCall = "delete implementation";
		handler = new DeleteImplementationHandler();
		Map<String, Object> request = new HashMap<>();
		request.put("uid", 1);
		request.put("iid", 3);
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
