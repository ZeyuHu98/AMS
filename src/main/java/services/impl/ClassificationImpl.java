package services.impl;

import java.util.List;
import java.util.Map;

import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class ClassificationImpl extends JdbcServicesSupport
{
	public List<Map<String, String>> query() throws Exception
	{
		return queryForList("select * from classification");
	}
	
	public boolean addClassification() throws Exception
	{
		boolean res = true;
		int parentcid = Integer.parseInt((String)this.getFromDto("parentcid"));
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
