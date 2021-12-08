package services.impl;

import java.util.Map;

import services.support.JdbcServicesSupport;

public class UserImpl extends JdbcServicesSupport
{
	UserActivityImpl impl = new UserActivityImpl();
	public Map<String, String> login() throws Exception
	{
		StringBuilder sql = new StringBuilder("select uid, uname, type from user WHERE uname = ? and password = ?");
		Object[] args = {this.getFromDto("uname"), this.getFromDto("password")};
		Map<String, String> dataMap = queryForMap(sql.toString(), args);
		
		impl.update("user login", dataMap.get("uid"));
		
		return dataMap;
	}

	public boolean register() throws Exception
	{

		
		// check whether the uname already exists
		StringBuilder sql1 = new StringBuilder("select * from user where uname = ?");
		Object[] args1 = {this.getFromDto("uname")};
		Map<String, String> dataMap = queryForMap(sql1.toString(), args1);
		
		// add if uname has not been used
		if (dataMap == null)
		{
			StringBuilder sql2 = new StringBuilder("insert into user(uname, password, type) values (?, ?, ?)");
			Object[] args2 = {this.getFromDto("uname"), this.getFromDto("password"), 1};
			boolean res = this.executeUpdate(sql2.toString(), args2) == 1;
			
			String sql = "select uid from user where uname = ?";
			impl.update("user register", queryForMap(sql, getFromDto("uname")).get("uid"));
			
			return res;
		}
		else
			return false;
	}
}
