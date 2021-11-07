package services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Classification;
import services.support.JdbcServicesSupport;

public class AlgorithmTreeImpl extends JdbcServicesSupport
{
	
	public List<Classification> queryTree() throws Exception
	{
		Map<Integer, Classification> cidToClf = new HashMap<>();
		
		List<Map<String, String>> classificationList =  queryForList("select * from classification");

		for (Map<String, String> map : classificationList)
			cidToClf.put(Integer.parseInt(map.get("cid")), new Classification(map));

		System.out.println(cidToClf);
		
		for (Classification c : cidToClf.values())
			if (c.parentcid != 0)
				cidToClf.get(c.parentcid).subClassifications.add(c);

		
		List<Map<String, String>> algorithmList =  queryForList("select * from algorithm");
		
		for (Map<String, String> map : algorithmList) 
			cidToClf.get(Integer.parseInt(map.get("parentcid"))).subAlgorithms.add(map);
		
		// result includes classifications with depth 0
		List<Classification> result = new ArrayList<>();
		for (Classification c : cidToClf.values())
			if (c.depth == 0)
				result.add(c);
		return result;
	}
	

}
