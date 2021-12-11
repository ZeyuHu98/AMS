package controller;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.ProblemInstanceImpl;

public class QueryProblemInstanceHandler implements RequestHandler<Map<String, Object>, Response> 
{
	ProblemInstanceImpl impl = new ProblemInstanceImpl();

	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		impl.setDto(request);
		Response response;
		try
		{	
			response = new Response(200, "Query problem instance succeed.", true, impl.query());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
}
