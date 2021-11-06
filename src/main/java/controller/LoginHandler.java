package controller;

import java.util.HashMap;
import java.util.Map;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.support.Response;
import services.impl.UserImpl;
import system.tools.Tools;

public class LoginHandler implements RequestHandler<Map<String, Object>, Response>
{
	LambdaLogger logger;
	UserImpl impl = new UserImpl();

	@Override
	public Response handleRequest(Map<String, Object> request, Context context)
	{
		logger = context.getLogger();
		logger.log("Load LoginHandler");
		logger.log(request.toString());
			
		impl.setDto(request);
		Response response;
		
		try
		{
			Map<String, String> map = impl.login();
			if (map == null)
				response = new Response(304, "Login fail. Wrong username or password.", false);
			else
				response = new Response(304, "Login fail. Wrong username or password.", false, map);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
}
