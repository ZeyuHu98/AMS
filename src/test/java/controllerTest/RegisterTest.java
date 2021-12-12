package controllerTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import controller.*;
import testSupport.LambdaTest;

public class RegisterTest extends LambdaTest
{
	@Test
	public void testRegister() throws Exception
	{
		apiCall = "register";
		handler = new RegisterHandler();
		Map<String, Object> requestSuccess = new HashMap<>();
		requestSuccess.put("uname", "zeyuhu");
		requestSuccess.put("password", "123456");
		Map<String, Object> requestFail = new HashMap<>();
		requestFail.put("uname", "wangzx");
		requestFail.put("password", "123456");
		try
		{
			this.testSucceed(requestSuccess);
			this.testFail(requestFail);
			this.testException(new HashMap<>());
		}
		catch (AssertionError e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
