package controller;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.AlgorithmImpl;
import services.impl.ImplementationImpl;
import services.impl.ProblemInstanceImpl;

public class QueryAlgorithmHandler implements RequestHandler<Map<String, Object>, Response> 
{
	LambdaLogger logger;
	AlgorithmImpl impl1 = new AlgorithmImpl();
	ProblemInstanceImpl impl2 = new ProblemInstanceImpl();
	ImplementationImpl impl3 = new ImplementationImpl();
	
//	@Override
//	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) 
//	{
//		Map<String, Object> request = new HashMap<>();
//		request.putAll(input.getQueryStringParameters());
//		
//		logger = context.getLogger();
//		logger.log("Load QueryAlgorithmHandler.");
//		logger.log(request.toString());
//		
//		impl1.setDto(request);
//		impl2.setDto(request);
//		impl3.setDto(request);
//		
//		StringBuilder sBuilder = new StringBuilder();
//		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
//
//		try
//		{	
//			Map<String, Object> data = new HashMap<>();
//			data.put("algorithmMap", impl1.querySingle());
//			data.put("problemInstanceList", impl2.query());
//			data.put("implementationList", impl3.query());
//			
//			response.setBody(new Gson().toJson(data));
//			response.setStatusCode(200);
//		}
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//			response.setBody("fail");
//			response.setStatusCode(400);
//		}
//		return response;
//	}

	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		logger = context.getLogger();
		logger.log("Load QueryAlgorithmHandler.");
		logger.log(request.toString());
		
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
