package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.BenchmarkImpl;
import services.impl.ProblemInstanceImpl;

import java.util.HashMap;
import java.util.Map;

public class DeleteProbleminstanceHandler implements RequestHandler<Map<String, Object>, Response>
{
	ProblemInstanceImpl pImpl = new ProblemInstanceImpl();
	BenchmarkImpl bImpl = new BenchmarkImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		pImpl.setDto(request);
		bImpl.setDto(request);
		Response response;
		try
		{
			boolean success = pImpl.deleteProblemInstance();
			// TODO discuss whether it is necessary to combine these two transaction into one transaction, 
			// since if the benchmark fail, we have to roll back the previous transaction.
			if (success)
				success &= bImpl.deleteBenchmarkByProblemInstance();
			
			if (success)
				response = new Response(200, "Delete problem instance success", true);
			else
				response = new Response(304, "Delete problem instance failed", false);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		
		return response;
	}
	
}