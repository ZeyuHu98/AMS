package testSupport;

import java.util.Map;
import controller.support.*;
import org.junit.Assert;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class LambdaTest 
{
	
	protected RequestHandler<Map<String, Object>, Response> handler;
	protected String apiCall;
	
	/**
	 * Helper method that creates a context that supports logging so you can test lambda functions
	 * in JUnit without worrying about the logger anymore.
	 * 
	 * @param apiCall      An arbitrary string to identify which API is being called.
	 * @return
	 */
	protected Context createContext(String apiCall)
	{
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }
	
	protected void testSucceed(Map<String, Object> request)
	{
		Response response = handler.handleRequest(request, createContext(apiCall));
		Assert.assertEquals(200, response.code);
		Assert.assertEquals(true, response.success);
	}

	protected void testFail(Map<String, Object> request)
	{
		Response response = handler.handleRequest(request, createContext(apiCall));
		Assert.assertEquals(200, response.code);
		Assert.assertEquals(false, response.success);
	}
	
	protected void testException(Map<String, Object> request)
	{
		Response response = handler.handleRequest(request, createContext(apiCall));
		Assert.assertEquals(400, response.code);
		Assert.assertEquals(false, response.success);
	}
}
