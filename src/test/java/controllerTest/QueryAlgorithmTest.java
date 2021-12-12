package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.QueryAlgorithmHandler;
import testSupport.LambdaTest;

public class QueryAlgorithmTest extends LambdaTest
{
	@Test
	public void testQueryAlgorithm()
	{
		apiCall = "query algorithm";
		handler = new QueryAlgorithmHandler();
		Map<String, Object> successRequest = new HashMap<>();
		successRequest.put("uid", 1);
		successRequest.put("parentcid", 1);
		successRequest.put("name", "testAlgo");
		successRequest.put("introduction", "testIntroduction");
		successRequest.put("content", "testContent");
		successRequest.put("timecplx", "testTimecplx");
		successRequest.put("spacecplx", "testSpacecplx");
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
