package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.ChangeClassificationHandler;
import testSupport.LambdaTest;

public class ChangeClassificationTest extends LambdaTest
{
	@Test
	public void testChangeclassification()
	{
		apiCall = "change classification";
		handler = new ChangeClassificationHandler();
		Map<String, Object> requestSuccess = new HashMap<>();
		requestSuccess.put("uid", "7");
		requestSuccess.put("aid", 4);
		requestSuccess.put("parentcid", 3);
		try
		{
			this.testSucceed(requestSuccess);
		}
		catch (AssertionError e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
