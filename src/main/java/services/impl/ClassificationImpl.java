package services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.kinesisfirehose.model.ExtendedS3DestinationConfiguration;

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
		DBUtils.beginTransaction();
		try 
		{
			StringBuilder sql = new StringBuilder()
					.append("insert into classification(url, parentcid, name, depth) values (?,?,?,?);");
			Object[] args = {getFromDto("url"), getFromDto("parentcid"), getFromDto("name"), getFromDto("depth")};
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
