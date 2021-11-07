package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import model.Classification;
import services.impl.AlgorithmImpl;
import services.impl.AlgorithmTreeImpl;
import services.impl.ClassificationImpl;
import system.tools.Tools;

public class QueryClassificationHandler implements RequestHandler<Map<String, Object>, Response>
{
	
	LambdaLogger logger; 
	AlgorithmTreeImpl impl = new AlgorithmTreeImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context)
	{
		logger = context.getLogger();
		logger.log("Load QueryClassificationHandler.");
		logger.log(request.toString());
		
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
