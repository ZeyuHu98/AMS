package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.BenchmarkImpl;

import java.util.HashMap;
import java.util.Map;

public class AddBenchmarkHandler implements RequestHandler<Map<String, Object>, Response>
{

	LambdaLogger logger; 
	BenchmarkImpl impl = new BenchmarkImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		logger = context.getLogger();
		logger.log("Load AddImplementationHandler");
		logger.log(request.toString());
		
		impl.setDto(request);
		Response response;
		try
		{
			boolean success = impl.addBenchmark();
			if (success)
				response = new Response(200, "Add benchmark succeed.", true);
			else
				response = new Response(304, "Add benchmark failed.", false);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		
		return response;
	}
	
}
