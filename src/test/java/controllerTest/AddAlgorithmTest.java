package controllerTest;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.AddAlgorithmHandler;
import testSupport.LambdaTest;

public class AddAlgorithmTest extends LambdaTest
{
	@Test
	public void testAddAlgorithm() throws Exception
	{
		apiCall = "add algorithm";
		handler = new AddAlgorithmHandler();
		Map<String, Object> request = new HashMap<>();
		request.put("uid", 1);
		request.put("parentcid", 1);
		request.put("name", "testAlgo");
		request.put("introduction", "testIntroduction");
		request.put("content", "testContent");
		request.put("timecplx", "testTimecplx");
		request.put("spacecplx", "testSpacecplx");
		try
		{
			this.testSucceed(request);
			this.testFail(new HashMap<String, Object>());
		}
		catch (AssertionError e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
