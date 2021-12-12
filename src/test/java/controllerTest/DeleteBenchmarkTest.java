package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.DeleteBenchmark;
import testSupport.LambdaTest;

public class DeleteBenchmarkTest extends LambdaTest 
{
	@Test
	public void testDeleteBenchmark()
	{
		apiCall = "delete benchmark";
		handler = new DeleteBenchmark();
		Map<String, Object> request = new HashMap<>();
		request.put("uid", 1);
		request.put("bid", 3);
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
