package services.impl;

import java.util.List;
import java.util.Map;
import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class AlgorithmImpl extends JdbcServicesSupport
{
	static String DOMAINNAME = "";
	
	public boolean addAlgorithm() throws Exception
	{
		boolean result = true;
		DBUtils.beginTransaction();
		try 
		{
			StringBuilder sql = new StringBuilder()
					.append("insert into algorithm(url, parentcid, name, introduction,")
					.append("content, timecplx, spacecplx) values (?,?,?,?,?,?,?)");
			Object[] args = {"", getFromDto("parentcid"), getFromDto("name"), getFromDto("introduction"),
					getFromDto("content"), getFromDto("timecplx"), getFromDto("spacecplx")
			};
			this.executeUpdate(sql.toString(), args);
		} 
		catch (Exception e) 
		{
			result = false;
			DBUtils.rollback();
			e.printStackTrace();
		}
		finally
		{
			DBUtils.commit();
			DBUtils.close();
		}
		return result;
	}
	
	public List<Map<String, String>> query() throws Exception
	{
		return queryForList("select aid, name, parentcid, url from algorithm");
	}
	
	public Map<String, String> querySingle() throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select * from algorithm where aid = ?");
		Object[] args = {getFromDto("aid")};
		return this.queryForMap(sql.toString(), args);
	}
}
