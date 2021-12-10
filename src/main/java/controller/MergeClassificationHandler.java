package controller;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import controller.support.Response;
import services.impl.MergeClassificatoinImpl;

public class MergeClassificationHandler implements RequestHandler<Map<String, Object>, Response> 
{
	MergeClassificatoinImpl impl = new MergeClassificatoinImpl();
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		Response response;
		try
		{
			impl.setDto(request);
			impl.merge();
			response = new Response(200, "Merge classifications succeed.", true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}

}
