package controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import services.impl.AlgorithmImpl;
import services.impl.ProblemInstanceImpl;
import system.tools.Tools;

public class QueryAlgorithmHandler implements RequestHandler<Map<String, Object>, Map<String, Object>> 
{
	LambdaLogger logger;
	AlgorithmImpl impl1 = new AlgorithmImpl();
	ProblemInstanceImpl impl2 = new ProblemInstanceImpl();

	
	@Override
	public Map<String, Object> handleRequest(Map<String, Object> request, Context context) 
	{
		logger = context.getLogger();
		logger.log("Load QueryAlgorithmHandler.");
		logger.log(request.toString());
		
		String code;
		boolean success;
		String msg;
		Map<String, Object> data = new HashMap<>();
		
		try
		{
			impl1.setDto(request);
			impl2.setDto(request);

			data.put("algorithmMap", impl1.querySingle());
			data.put("problemInstanceList", impl2.query());
			
			code = "200";
			success = true;
			msg = "Query algorithm succeed.";
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			code = "400";
			success = false;
			msg = "Exception encountered.";
		}
		Map<String, Object> response = new HashMap<>();
		Tools.setResponse(response, code, data, msg, success);
		return response;
	}
}
