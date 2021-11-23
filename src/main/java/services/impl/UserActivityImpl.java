package services.impl;

import services.support.JdbcServicesSupport;

import java.sql.Timestamp;
import java.util.Date;

public class UserActivityImpl extends JdbcServicesSupport 
{
	public void update(String activity, int oid) throws Exception
	{
		String sql = "insert into useractivity(uid, activity, oid, time) values (?,?,?,?);";
		Object[] args = {this.getFromDto("uid"), activity, oid, new Timestamp(new Date().getTime())};
		this.executeUpdate(sql, args);
	}
}
