package services.impl;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.kinesisfirehose.model.ExtendedS3DestinationConfiguration;

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
					.append("insert into implementation(aid, url, language, code) values (?,?,?,?);");
			Object[] args = {getFromDto("aid"), getFromDto("url"), getFromDto("language"), getFromDto("code")};
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
