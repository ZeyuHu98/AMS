package services.impl;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.kinesisfirehose.model.ExtendedS3DestinationConfiguration;

import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class BenchmarkImpl extends JdbcServicesSupport
{
	public List<Map<String, String>> query() throws Exception
	{
		Object[] args = {getFromDto("iid"), getFromDto("pid")};
		return queryForList("select * from implementation "
				+ "where iid = ? and pid = ?", args);
	}
	
	
	
}
