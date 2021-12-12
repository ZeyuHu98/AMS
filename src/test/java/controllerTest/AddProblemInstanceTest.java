package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.AddProblemInstanceHandler;
import testSupport.LambdaTest;

public class AddProblemInstanceTest extends LambdaTest
{
	@Test
	public void testAddProblemInstance()
	{
		apiCall = "add ProblemInstance";
		handler = new AddProblemInstanceHandler();
		Map<String, Object> successRequest = new HashMap<>();
		successRequest.put("uid", 7);
		successRequest.put("aid", 1);
		successRequest.put("input", "1233");
		successRequest.put("output", "this is for test");
		successRequest.put("timeComplexityType", "0");
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
