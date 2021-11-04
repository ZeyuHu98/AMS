package controller;

import java.util.HashMap;
import java.util.Map;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import services.impl.UserImpl;
import system.tools.Tools;

public class LoginHandler implements RequestHandler<Map<String, Object>, Map<String, Object>>
{
	LambdaLogger logger;
	UserImpl impl = new UserImpl();
	
	@Override
	public Map<String, Object> handleRequest(Map<String, Object> request, Context context)
	{
		logger = context.getLogger();
		impl = new UserImpl();
		logger.log("Load LoginHandler");
		Map<String, Object> response = new HashMap<>();
		String statusCode = "200";
		try
		{
			impl.setDto(request);
			response.putAll(impl.login());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			statusCode = "400";
		}
		response.put("statusCode", statusCode);
		return response;
	}
}
