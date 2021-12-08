package services.impl;

import services.support.JdbcServicesSupport;

public class DeleteImpl extends JdbcServicesSupport 
{
	public boolean deleteAlgorithm() throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("delete from algorithm where aid = ");
		Object[] args = {getFromDto("aid")};
		this.batchUpdate(sql.toString(), args);
		
		return true;
		
	}
}
