package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.ProblemInstanceImpl;

import java.util.HashMap;
import java.util.Map;

public class AddProblemInstanceHandler implements RequestHandler<Map<String, Object>, Response>
{

	LambdaLogger logger; 
	ProblemInstanceImpl impl = new ProblemInstanceImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		logger = context.getLogger();
		logger.log("Load AddProblemInstanceHandler");
		logger.log(request.toString());
		
		impl.setDto(request);
		Response response;
		try
		{
			boolean success = impl.addProblemInstance();
			if (success)
				response = new Response(200, "Add problem instance succeed.", true);
			else
				response = new Response(304, "Add problem instance failed.", false);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		
		return response;
	}
	
}
