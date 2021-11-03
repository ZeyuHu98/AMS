package services.impl;

import services.support.JdbcServicesSupport;

public class UserImpl extends JdbcServicesSupport
{
	boolean login()
	{
		StringBuilder sql = new StringBuilder("select * from user WHERE uname = ? and password = \"111111\"");
		return false;
	}

	boolean regiser()
	{
		return false;
	}
}
