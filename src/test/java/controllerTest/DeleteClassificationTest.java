package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.DeleteClassificationHandler;
import testSupport.LambdaTest;

public class DeleteClassificationTest extends LambdaTest 
{
	@Test
	public void testDeleteClassification()
	{
		apiCall = "delete classification";
		handler = new DeleteClassificationHandler();
		Map<String, Object> requestSuccess = new HashMap<>();
		requestSuccess.put("uid", 7);
		requestSuccess.put("cid", 4);
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
