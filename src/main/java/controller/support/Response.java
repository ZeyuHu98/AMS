package controller.support;

public class Response 
{
	public int code;
	public String msg;
	public boolean success;
	public Object data;
	
	public Response()
	{
		this.code = 400;
		this.msg = "Exception encoutered.";
		this.success = false;
	}
	
	public Response(int code, String msg, boolean success)
	{
		this.code = code;
		this.msg = msg;
		this.success = success;
	}
	
	public Response(int code, String msg, boolean success, Object data)
	{
		this.code = code;
		this.msg = msg;
		this.success = success;
		this.data = data;
	}
}
