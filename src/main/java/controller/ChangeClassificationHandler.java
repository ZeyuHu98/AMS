package controller;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.cfg.ContextAttributes.Impl;

import controller.support.Response;
import model.Classification;
import services.impl.AlgorithmImpl;
import services.impl.ClassificationImpl;
import services.impl.ImplementationImpl;

public class ChangeClassificationHandler implements RequestHandler<Map<String, Object>, Response> 
{
	AlgorithmImpl impl = new AlgorithmImpl();
	@Override
	public Response handleRequest(Map<String, Object> request, Context context) 
	{
		Response response;
		try
		{
			impl.setDto(request);
			impl.changeClassification();
			response = new Response(200, "Change classification of an algorithm.", true);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new Response();
		}
		return response;
	}
}
