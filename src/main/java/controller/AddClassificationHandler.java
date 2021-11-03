package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import services.impl.ClassificationImpl;

import java.util.HashMap;
import java.util.Map;

public class AddClassificationHandler implements RequestHandler<Map<String, Object>, Map<String, Object>>
{

	LambdaLogger logger; 
	ClassificationImpl impl = new ClassificationImpl();
	
	@Override
	public Map<String, Object> handleRequest(Map<String, Object> request, Context context) 
	{
		logger = context.getLogger();
		logger.log("Load AddClassificationHandler");
		logger.log(request.toString());
		
		int statusCode = 201;
		try
		{
			impl.setDto(request);
			boolean b = impl.addClassification();
			if (!b)
				statusCode = 400;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<>();
		map.put("statusCode", statusCode);
		
		return map;
	}
	
}
