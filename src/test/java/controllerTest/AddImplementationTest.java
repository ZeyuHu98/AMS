package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.AddImplementationHandler;
import testSupport.LambdaTest;

public class AddImplementationTest extends LambdaTest
{
	@Test
	public void testAddImplementation()
	{
		apiCall = "add ImplementationTest";
		handler = new AddImplementationHandler();
		Map<String, Object> successRequest = new HashMap<>();
		successRequest.put("uid", 7);
		successRequest.put("aid", 1);
		successRequest.put("language", "java");
		successRequest.put("code", "this is for test");
		try
		{
			this.testSucceed(successRequest);
			this.testFail(new HashMap<String, Object>());
		}
		catch (AssertionError e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
