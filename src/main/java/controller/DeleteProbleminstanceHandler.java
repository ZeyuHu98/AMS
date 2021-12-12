package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.BenchmarkImpl;
import services.impl.DeleteImpl;
import services.impl.ProblemInstanceImpl;

import java.util.HashMap;
import java.util.Map;

public class DeleteProbleminstanceHandler implements RequestHandler<Map<String, Object>, Response>
{
	DeleteImpl dImpl = new DeleteImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		dImpl.setDto(request);
		Response response;
		try
		{
			boolean success = dImpl.deleteProblemInstance();
			
			if (success)
				response = new Response(200, "Delete problem instance success", true);
			else
				response = new Response(200, "Delete problem instance failed", false);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		
		return response;
	}
	
}