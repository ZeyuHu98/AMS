package system.tools;

import java.util.Map;

import com.google.gson.Gson;

import services.impl.ClassificationImpl;
import services.impl.UserImpl;

public class Tools
{
	public static void main(String[] args) throws Exception 
	{
		UserImpl impl = new UserImpl();
		System.out.println(gson.toJson(impl.loginTest()).replaceAll("\\", ""));
	}
	
	private static Gson gson = new Gson();
	
	public static String toJson(Object o)
	{
		return gson.toJson(o);
	}
	
	public static void setResponse(Map<String, Object> response, String code, Object data, String msg, boolean success)
	{
		response.put("code", code);
		response.put("data", data);
		response.put("msg", msg);
		response.put("success", success);
	}
	
	public static void setResponse(Map<String, Object> response, int code, Object data, String msg, boolean success)
	{
		response.put("code", code);
		response.put("data", data);
		response.put("msg", msg);
		response.put("success", success);
	}
}
