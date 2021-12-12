package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.QueryClassificationHandler;
import testSupport.LambdaTest;

public class QueryClassificationTest extends LambdaTest
{
	@Test
	public void testQueryClassification()
	{
		apiCall = "query classification";
		handler = new QueryClassificationHandler();
		try
		{
			this.testSucceed(new HashMap<>());
		}
		catch (AssertionError e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
