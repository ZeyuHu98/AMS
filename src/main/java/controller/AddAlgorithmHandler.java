package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.AlgorithmImpl;
import services.impl.ClassificationImpl;
import services.impl.UserActivityImpl;
import system.tools.Tools;

import java.util.HashMap;
import java.util.Map;

public class AddAlgorithmHandler implements RequestHandler<Map<String, Object>, Response>
{

	AlgorithmImpl impl = new AlgorithmImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		Response response;
		
		try
		{
			impl.setDto(request);
			boolean success = impl.addAlgorithm();
			if (success)
				response = new Response(200, "Add algorithm succeed.", success);
			else
				response = new Response(200, "Add algorithm fail.", success);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
	
}
