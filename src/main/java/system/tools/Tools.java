package system.tools;

import java.util.Map.Entry;

import services.impl.ClassificationImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tools
{
	public static void main(String[] args) throws Exception
	{
		ClassificationImpl impl = new ClassificationImpl();
		List<Map<String, String>> dataList = impl.query();
		System.out.println(listToJson(dataList));
	}
	
	public static String mapToJson(Map<String, String> dataMap)
	{
		StringBuilder result = new StringBuilder("[");
		for (Entry<String, String> entry : dataMap.entrySet())
			result.append(entry.getKey()).append(" : ").append(entry.getValue()).append(",");
		result.deleteCharAt(result.length()-1);
		result.append("]");
		return result.toString();
	}
	
	public static String listToJson(List<Map<String, String>> dataList)
	{
		StringBuilder result = new StringBuilder("[");
		for (Map<String, String> dataMap : dataList)
			result.append(mapToJson(dataMap)).append("\r\n");
		result.append("]");
		return result.toString();
	}
}
