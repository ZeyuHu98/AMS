package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.*;
import testSupport.LambdaTest;

public class LoginTest extends LambdaTest
{
	@Test
	public void testLogin()
	{
		apiCall = "login";
		handler = new LoginHandler();
		Map<String, Object> requestSuccess = new HashMap<>();
		requestSuccess.put("uname", "wangzx");
		requestSuccess.put("password", "123456");
		Map<String, Object> requestFail = new HashMap<>();
		requestFail.put("uname", "wangzx");
		requestFail.put("password", "123457");
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
