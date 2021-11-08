package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Classification 
{
	public int cid;
	public String url;
	public String name;
	public int parentcid;
	public int depth;
	
	public List<Object> sub;
	
	public Classification(Map<String, String> classificationMap)
	{
		sub = new ArrayList<>();
		try 
		{
			cid = Integer.parseInt(classificationMap.get("cid"));
			url = classificationMap.get("url");
			name = classificationMap.get("name");
			parentcid = Integer.parseInt(classificationMap.get("parentcid"));
			depth = Integer.parseInt(classificationMap.get("depth"));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
}
