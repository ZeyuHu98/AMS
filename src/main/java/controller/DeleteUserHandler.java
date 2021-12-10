package controller;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import controller.support.Response;
import services.impl.UserImpl;

public class DeleteUserHandler implements RequestHandler<Map<String, Object>, Response> 
{
	UserImpl impl = new UserImpl();
	
	@Override
	public Response handleRequest(Map<String, Object> request, Context context)
	{
		Response response;
		try
		{
			impl.setDto(request);
			impl.deleteUser();
			response = new Response(200, "Delete user succeed.", true);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
}
