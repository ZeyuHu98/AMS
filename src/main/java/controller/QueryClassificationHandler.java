package controller;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import model.Classification;
import services.impl.AlgorithmTreeImpl;

public class QueryClassificationHandler implements RequestHandler<Map<String, Object>, Response>
{
	
	AlgorithmTreeImpl impl = new AlgorithmTreeImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context)
	{
		impl.setDto(request);

		Response response;
		try
		{
			List<Classification> data = impl.queryTree();
			response = new Response(200, "Query classification tree succeed.", true, data);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response =  new Response();
		}
		return response;
	}
}
