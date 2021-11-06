package services.impl;

import java.util.List;
import java.util.Map;

import services.support.JdbcServicesSupport;

public class ProblemInstanceImpl extends JdbcServicesSupport 
{
	public List<Map<String, String>> query() throws Exception
	{
		StringBuilder sql =  new StringBuilder()
				.append("select pid, type, instance, bucketurl from probleminstance")
				.append("where aid = ?");
		Object[] args = {getFromDto("aid")};
		return queryForList(sql.toString(), args);
	}
	
	
}
