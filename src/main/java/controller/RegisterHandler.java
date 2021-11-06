package controller;

import java.util.Map;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.UserImpl;

public class RegisterHandler implements RequestHandler<Map<String, Object>, Response>
{
	LambdaLogger logger;
	UserImpl impl = new UserImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context)
	{
		logger = context.getLogger();
		logger.log("Load RegisterHandler");
		logger.log(request.toString());
		
		impl.setDto(request);
		
		Response response;
		try
		{
			boolean success = impl.register();
			if (success)
				response = new Response(200, "Register succeed.", true);
			else
				response = new Response(304, "Register fail. Username already existed.", false);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
}
