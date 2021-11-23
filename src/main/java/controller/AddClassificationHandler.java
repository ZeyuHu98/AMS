package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.ClassificationImpl;
import system.tools.Tools;

import java.util.HashMap;
import java.util.Map;

public class AddClassificationHandler implements RequestHandler<Map<String, Object>, Response>
{
	ClassificationImpl impl = new ClassificationImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		Response response;

		try
		{
			impl.setDto(request);
			boolean success = impl.addClassification();
			if (success)
				response = new Response(200, "Add classification succeed.", success);
			else
				response = new Response(200, "Add calssification fail.", success);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
	
}
