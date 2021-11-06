package controller;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.AlgorithmImpl;
import services.impl.ClassificationImpl;
import system.tools.Tools;

public class QueryClassificationHandler implements RequestHandler<Map<String, Object>, Response>
{
	
	LambdaLogger logger; 
	ClassificationImpl impl1 = new ClassificationImpl();
	AlgorithmImpl impl2 = new AlgorithmImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context)
	{
		logger = context.getLogger();
		logger.log("Load QueryClassificationHandler.");
		logger.log(request.toString());
		impl1.setDto(request);
		impl2.setDto(request);
		Response response;
		try
		{
			Map<String, Object> data = new HashMap<>();
			data.put("classificationList", impl1.query());
			data.put("algorithmList", impl2.query());
			response = new Response(200, "Query algorithm succeed.", true, data);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response =  new Response();
		}
		return response;
	}
}
