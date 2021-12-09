package services.impl;

import java.util.Map;

import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class DeleteImpl extends JdbcServicesSupport 
{
	UserActivityImpl impl = new UserActivityImpl();
	public boolean deleteAlgorithm() throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("delete from algorithm where aid = ");
		Object[] args = {getFromDto("aid")};
		this.batchUpdate(sql.toString(), args);
		
		return true;
		
	}
	
	
	public boolean deleteImplementation() throws Exception
	{
		boolean res = true;
		DBUtils.beginTransaction();
		try 
		{
			StringBuilder sql1 = new StringBuilder()
					.append("delete from implementation where iid = ?");
			Object[] args1 = {getFromDto("iid")};
			this.executeUpdate(sql1.toString(), args1);
			
			
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
			DBUtils.close();
		}
		if (res)
			res = deleteBenchmarkByImplementation();
		if (res)
		{
			impl.update("delete implementation", (String)getFromDto("uid"), (String)getFromDto("iid"));
		}
		return res;
	}
	
	
	
	
	public boolean deleteProblemInstance() throws Exception
	{
		boolean res = true;
		DBUtils.beginTransaction();
		try 
		{
			
			StringBuilder sql1 = new StringBuilder()
					.append("delete from probleminstance where pid = ?");
			Object[] args1 = {getFromDto("pid")};
			this.executeUpdate(sql1.toString(), args1);
					
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
			DBUtils.close();
		}
		if (res)
			res = deleteBenchmarkByProblemInstance();
		if (res)
		{
			impl.update("delete probleminstance", (String)getFromDto("uid"), (String)getFromDto("pid"));
		}
		return res;
	}
	
	
	public boolean deleteBenchmarkByImplementation() throws Exception
	{
		boolean res = true;
		DBUtils.beginTransaction();
		try 
		{
			
			StringBuilder sql1 = new StringBuilder()
					.append("delete from benchmark where iid = ?");
			Object[] args1 = {getFromDto("iid")};
			this.executeUpdate(sql1.toString(), args1);			
			
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
	
	public boolean deleteBenchmarkByImplementation(int iid) throws Exception
	{
		boolean res = true;
		DBUtils.beginTransaction();
		try 
		{
			
			StringBuilder sql1 = new StringBuilder()
					.append("delete from benchmark where iid = ?");
			Object[] args1 = {iid};
			this.executeUpdate(sql1.toString(), args1);			
			
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
	
	
	
	
	public boolean deleteBenchmarkByProblemInstance() throws Exception
	{
		boolean res = true;
		DBUtils.beginTransaction();
		try 
		{
			
			StringBuilder sql1 = new StringBuilder()
					.append("delete from benchmark where pid = ?");
			Object[] args1 = {getFromDto("pid")};
			this.executeUpdate(sql1.toString(), args1);			
			
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
