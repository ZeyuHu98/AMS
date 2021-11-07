package controller;

import java.util.Map;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.UserImpl;

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
			Map<String, String> data = impl.login();
			if (data == null)
				response = new Response(200, "Login fail. Wrong username or password.", false);
			else
				response = new Response(200, "Login succeed.", true, data);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
}
