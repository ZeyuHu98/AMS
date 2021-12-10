package services.impl;

import services.support.JdbcServicesSupport;

public class MergeClassificatoinImpl extends JdbcServicesSupport
{
	UserActivityImpl impl = new UserActivityImpl();
	public void merge() throws Exception
	{
		String sql1 = "update algorithm set parentcid = ? where parentcid = ?";
		Object[] args1 = {getFromDto("cid1"), getFromDto("cid2")};
		executeUpdate(sql1, args1);
		
		String sql2 = "update classification set parentcid = ? where parentcid = ?";
		executeUpdate(sql2, args1);
		
		String sql3 = "delete from classification where cid = ?";
		Object[] args3 = {getFromDto("cid2")};
		executeUpdate(sql3, args3);
		
		impl.update("merge existing classifications", getFromDto("uid"));
	}
}
