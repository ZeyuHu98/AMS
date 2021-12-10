package services.impl;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.kinesisfirehose.model.ExtendedS3DestinationConfiguration;

import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class BenchmarkImpl extends JdbcServicesSupport
{
	UserActivityImpl impl = new UserActivityImpl();
	public List<Map<String, String>> query() throws Exception
	{
		Object[] args = {getFromDto("iid"), getFromDto("pid")};
		return queryForList("select * from benchmark "
				+ "where iid = ? and pid = ?", args);
	}
	
	public boolean addBenchmark() throws Exception
	{
		boolean res = true;
		DBUtils.beginTransaction();
		try 
		{
			StringBuilder sql = new StringBuilder()
					.append("insert into benchmark(iid, pid, time, name, l1cache, l2cache, chip, core, thread) values (?,?,?,?,?,?,?,?,?);");
			Object[] args = {getFromDto("iid"), getFromDto("pid"), getFromDto("time"), getFromDto("name"), getFromDto("l1cache"),
					getFromDto("l2cache"), getFromDto("chip"), getFromDto("core"), getFromDto("thread")};
			this.executeUpdate(sql.toString(), args);

			impl.update("add benchmark", getFromDto("uid"));
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
	
	
	public boolean deleteBenchmark() throws Exception
	{
		boolean res = true;
		DBUtils.beginTransaction();
		try 
		{
			StringBuilder sql1 = new StringBuilder()
					.append("delete from benchmark where bid = ?");
			Object[] args1 = {getFromDto("bid")};
			this.executeUpdate(sql1.toString(), args1);
			impl.update("delete benchmark", getFromDto("uid"), getFromDto("bid"));
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
