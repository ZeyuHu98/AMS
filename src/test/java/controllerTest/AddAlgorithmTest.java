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
		Map<String, Object> requestSuccess = new HashMap<>();
		requestSuccess.put("uid", 1);
		requestSuccess.put("parentcid", 1);
		requestSuccess.put("name", "testAlgo");
		requestSuccess.put("introduction", "testIntroduction");
		requestSuccess.put("content", "testContent");
		requestSuccess.put("timecplx", "testTimecplx");
		requestSuccess.put("spacecplx", "testSpacecplx");
		Map<String, Object> requestFail = new HashMap<>();
		try
		{
			this.testSucceed(requestSuccess);
			this.testFail(requestFail);
		}
		catch (AssertionError e)
		{
			Assert.fail(e.getMessage());
		}
		
	}
}
