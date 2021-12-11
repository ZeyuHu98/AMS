package controller;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.BenchmarkImpl;

public class QueryBenchmarkHandler implements RequestHandler<Map<String, Object>, Response> 
{
	BenchmarkImpl impl = new BenchmarkImpl();

	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		impl.setDto(request);
		Response response;
		try
		{	
			response = new Response(200, "Query implementation succeed.", true, impl.query());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
}
