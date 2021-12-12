package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.MergeClassificationHandler;
import testSupport.LambdaTest;

public class MergeClassificationTest extends LambdaTest 
{
	@Test
	public void testMergeClassification()
	{
		apiCall = "merge classification";
		handler = new MergeClassificationHandler();
		Map<String, Object> requestSuccess = new HashMap<>();
		requestSuccess.put("uid", "7");
		requestSuccess.put("cid1", 5);
		requestSuccess.put("cid2", 6);
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
