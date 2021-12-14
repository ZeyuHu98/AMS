package controller;

import java.util.Map;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.UserImpl;

public class QueryUserHandler implements RequestHandler<Map<String, Object>, Response> 
{
	UserImpl impl = new UserImpl();
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		Response response;
		try 
		{
			impl.setDto(request);
			response = new Response(200, "Query registered user succeed.", true, impl.getUser());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
}
