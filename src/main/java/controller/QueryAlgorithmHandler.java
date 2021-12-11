package controller;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.AlgorithmImpl;
import services.impl.ImplementationImpl;
import services.impl.ProblemInstanceImpl;

public class QueryAlgorithmHandler implements RequestHandler<Map<String, Object>, Response> 
{
	AlgorithmImpl impl1 = new AlgorithmImpl();
	ProblemInstanceImpl impl2 = new ProblemInstanceImpl();
	ImplementationImpl impl3 = new ImplementationImpl();

	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		impl1.setDto(request);
		impl2.setDto(request);
		impl3.setDto(request);
		
		Response response;
		try
		{	
			Map<String, Object> data = new HashMap<>();
			data.put("algorithmMap", impl1.querySingle());
			data.put("problemInstanceList", impl2.query());
			data.put("implementationList", impl3.query());
			
			response = new Response(200, "Query algorithm succeed.", true, data);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
}
