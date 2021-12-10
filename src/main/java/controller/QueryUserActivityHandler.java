package controller;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.UserActivityImpl;

public class QueryUserActivityHandler implements RequestHandler<Map<String, Object>, Response>
{
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		UserActivityImpl impl = new UserActivityImpl();
		Response response;
		try
		{
			response = new Response(200, "Query user activity succeed.", true, impl.query());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			response = new Response();
		}
		// TODO Auto-generated method stub
		return response;
	}
}
