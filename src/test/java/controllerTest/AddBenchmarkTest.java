package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.AddBenchmarkHandler;
import testSupport.LambdaTest;

public class AddBenchmarkTest extends LambdaTest
{
	@Test
	public void testAddBenchmark()
	{
		apiCall = "query algorithm";
		handler = new AddBenchmarkHandler();
		Map<String, Object> successRequest = new HashMap<>();
		successRequest.put("uid", 7);
		successRequest.put("iid", 1);
		successRequest.put("pid", 1);
		successRequest.put("time", 34);
		successRequest.put("name", "123");
		successRequest.put("l1cache", "4mb");
		successRequest.put("l2cache", "8mb");
		successRequest.put("chip", "m1");
		successRequest.put("core", "20");
		successRequest.put("thread", "20");
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
