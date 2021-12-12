package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.*;
import testSupport.LambdaTest;

public class DeleteUserTest extends LambdaTest
{
	@Test
	public void testDeleteUser()
	{
		apiCall = "delete user and activity";
		handler = new DeleteUserHandler();
		Map<String, Object> requestSuccess = new HashMap<>();
		requestSuccess.put("duid", 11);
		requestSuccess.put("uid", 1);
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
