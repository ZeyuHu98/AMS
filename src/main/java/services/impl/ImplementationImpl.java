package services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class ImplementationImpl extends JdbcServicesSupport
{
	UserActivityImpl impl = new UserActivityImpl();
	public List<Map<String, String>> query() throws Exception
	{
		Object[] args = {getFromDto("aid")};
		return queryForList("select * from implementation "
				+ "where aid = ?", args);
	}
	
	public boolean addImplementation() throws Exception
	{
		boolean res = true;
		DBUtils.beginTransaction();
		try 
		{
			StringBuilder sql1 = new StringBuilder()
					.append("select uname from user where uid = ?");
			Object[] args1 = {getFromDto("uid")};
			Map<String, String> unameRes = queryForMap(sql1.toString(), args1);
			String uName = unameRes.get("uname");
			StringBuilder sql = new StringBuilder()
					.append("insert into implementation(aid, language, code, author, time) values (?,?,?,?,?);");
			Object[] args = {getFromDto("aid"), getFromDto("language"), getFromDto("code"), uName, new Timestamp(new Date().getTime())};
			this.executeUpdate(sql.toString(), args);
			impl.update("add implementation", getFromDto("uid"));

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
