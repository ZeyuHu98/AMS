package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import services.impl.ImplementationImpl;
import system.tools.Tools;

public class QueryImplementationHandler implements RequestHandler<Map<String, Object>, Map<String, Object>>
{
	
	LambdaLogger logger; 
	ImplementationImpl impl = new ImplementationImpl();
	
	@Override
	public Map<String, Object> handleRequest(Map<String, Object> request, Context context)
	{
		logger = context.getLogger();
		logger.log("Load QueryImplementation");
		logger.log(request.toString());
		List<Map<String, String>> dataList = new ArrayList<>();
		int statusCode = 200;
		try
		{
			dataList = impl.query();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			statusCode = 400;
		}

		Map<String, Object> map = new HashMap<>();
		map.put("statusCode", statusCode);
		map.put("dataList", Tools.toJson(dataList));
		
		return map;
	}
}

