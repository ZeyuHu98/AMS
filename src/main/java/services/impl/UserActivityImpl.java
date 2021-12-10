package services.impl;

import services.support.JdbcServicesSupport;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserActivityImpl extends JdbcServicesSupport 
{	
	public void update(String activity, Object uid) throws Exception
	{
		String sql = "insert into useractivity(uid, activity, time) values (?,?,?);";
		Object[] args = {uid, activity, new Timestamp(new Date().getTime())};
		executeUpdate(sql, args);
	}
	
	public void update(String activity, Object uid, Object oid) throws Exception
	{
		String sql = "insert into useractivity(uid, activity, oid, time) values (?,?,?,?);";
		Object[] args = {uid, activity, oid, new Timestamp(new Date().getTime())};
		executeUpdate(sql, args);
	}
	
	public void deleteActivity() throws Exception
	{
		String sql = "delete from useractivity where uid = ?";
		Object[] args = {this.getFromDto("duid")};
		executeUpdate(sql, args);
	}
	
	public List<Map<String, String>> query() throws Exception
	{
		String sql = "select * from useractivity";
		return queryForList(sql);
	}
}
