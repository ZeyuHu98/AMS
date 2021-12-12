package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.AddClassificationHandler;
import testSupport.LambdaTest;

public class AddClassificationTest extends LambdaTest 
{
	@Test
	public void testAddClassification()
	{
		apiCall = "add classification";
		handler = new AddClassificationHandler();
		Map<String, Object> requestSuccess = new HashMap<>();
		requestSuccess.put("uid", 7);
		requestSuccess.put("parentcid", 1);
		requestSuccess.put("url", "");
		requestSuccess.put("name", "testClassification");
		try
		{
			this.testSucceed(requestSuccess);
			this.testException(new HashMap<>());
		}
		catch (AssertionError e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
