package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.ImplementationImpl;

import java.util.HashMap;
import java.util.Map;

public class AddImplementationHandler implements RequestHandler<Map<String, Object>, Response>
{
	ImplementationImpl impl = new ImplementationImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{

		impl.setDto(request);
		Response response;
		try
		{
			boolean success = impl.addImplementation();
			if (success)
				response = new Response(200, "Add implementation succeed.", true);
			else
				response = new Response(200, "Add implementation failed.", false);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		
		return response;
	}
	
}
