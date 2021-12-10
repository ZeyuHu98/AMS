package controller;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.DeleteImpl;

public class DeleteClassificationHandler implements RequestHandler<Map<String, Object>, Response>
{
	DeleteImpl impl = new DeleteImpl();
	@Override
	public Response handleRequest(Map<String, Object> request, Context context)
	{
		Response response;
		
		try 
		{
			boolean success = impl.deleteClassification();
			if (success)
				response = new Response(200, "Delete classification succeed.", success);
			else
				response = new Response(200, "Delete classification fail.", success);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
}
