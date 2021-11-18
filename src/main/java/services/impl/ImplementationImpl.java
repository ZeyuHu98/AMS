package services.impl;

import java.util.List;
import java.util.Map;

import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class ImplementationImpl extends JdbcServicesSupport
{
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
			StringBuilder sql = new StringBuilder()
					.append("insert into implementation(aid, language, code) values (?,?,?);");
			Object[] args = {getFromDto("aid"), getFromDto("language"), getFromDto("code")};
			this.executeUpdate(sql.toString(), args);
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
	
	public boolean deleteImplementation() throws Exception
	{
		boolean res = true;
		DBUtils.beginTransaction();
		try 
		{
			//TODO currently only has 1 as type, should changed to 2.
			StringBuilder sql = new StringBuilder("select type from user WHERE uid = ?");
			Object[] args = {this.getFromDto("uid")};
			Map<String, String> dataMap = queryForMap(sql.toString(), args);
			if (dataMap.get("type").equals("1"))
				
			{
				StringBuilder sql1 = new StringBuilder()
						.append("delete from implementation where iid = ?");
				Object[] args1 = {getFromDto("iid")};
				this.executeUpdate(sql1.toString(), args1);
			}
			else
			{
				res = false;
			}
			
			
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
