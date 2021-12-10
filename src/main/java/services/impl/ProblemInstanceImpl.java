package services.impl;

import java.util.List;
import java.util.Map;

import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class ProblemInstanceImpl extends JdbcServicesSupport 
{
	UserActivityImpl impl = new UserActivityImpl();
	public List<Map<String, String>> query() throws Exception
	{
		StringBuilder sql =  new StringBuilder()
				.append("select * from probleminstance where aid = ?");
		Object[] args = {getFromDto("aid")};
		return queryForList(sql.toString(), args);
	}
	
	
	public boolean addProblemInstance() throws Exception
	{
		boolean res = true;
		DBUtils.beginTransaction();
		try 
		{
			StringBuilder sql = new StringBuilder()
					.append("insert into probleminstance (aid, instance, type) values (?,?,?);");
			Object[] args = {getFromDto("aid"), getFromDto("instance"), getFromDto("type")};
			this.executeUpdate(sql.toString(), args);
			impl.update("add problemInstance", getFromDto("uid"));
		}
		catch (Exception e)
		{
			res = false;
			DBUtils.rollback();
			e.printStackTrace();
		}
		finally 
		{
			DBUtils.commit();
			DBUtils.close();// TODO: handle finally clause
		}
		return res;
	}
	
	
	
	
}
