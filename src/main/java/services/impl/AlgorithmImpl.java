package services.impl;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.Select;

import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class AlgorithmImpl extends JdbcServicesSupport
{
	UserActivityImpl impl = new UserActivityImpl();
	
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
				
			//update activity
			String sql2 = "select aid from algorithm where name = ? and parentcid = ?";
			Object[] args2 = {getFromDto("name"), getFromDto("parentcid")};
			String oid = queryForMap(sql2, args2).get("aid");
			impl.update("add algorithm", getFromDto("uid"), oid);
			
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
	
	public void changeClassification() throws Exception
	{
		String sql = "update algorithm set parentcid = ? where aid = ?";
		Object[] args = {getFromDto("parentcid"), getFromDto("aid")};
		executeUpdate(sql, args);
		impl.update("Change parent classification", getFromDto("uid"), getFromDto("aid"));
	}

}
