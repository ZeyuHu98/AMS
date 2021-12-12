package controllerTest;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import controller.*;
import testSupport.LambdaTest;

public class QueryUserTest extends LambdaTest 
{
	@Test
	public void testQueryUserActivity() throws Exception
	{
		apiCall = "query user";
		handler = new QueryUserHandler();
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
