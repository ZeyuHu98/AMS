package services.impl;

import java.util.Map;

import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class ClassificationImpl extends JdbcServicesSupport
{
	UserActivityImpl impl = new UserActivityImpl();
	
	public boolean addClassification() throws Exception
	{
		boolean res = true;
		int parentcid = Integer.parseInt(this.getFromDto("parentcid").toString());
		int depth;
		if (parentcid != 0)
		{
			Map<String, String> map = queryForMap("select depth from classification where cid = ?", parentcid);
			depth = Integer.parseInt(map.get("depth")) + 1;
		}
		else
			depth = 0;

		DBUtils.beginTransaction();
		try 
		{
			StringBuilder sql = new StringBuilder()
					.append("insert into classification(url, parentcid, name, depth) values (?,?,?,?);");
			Object[] args = {getFromDto("url"), getFromDto("parentcid"), getFromDto("name"), depth};
			this.executeUpdate(sql.toString(), args);
			
			String sql2 = "select cid from classification where name = ? and parentcid = ?";
			Object[] args2 = {getFromDto("name"), getFromDto("parentcid")};
			
			impl.update("add classification", getFromDto("uid"), queryForMap(sql2, args2).get("cid"));
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
	
	public boolean delete()
	{
		return false;
	}
}
