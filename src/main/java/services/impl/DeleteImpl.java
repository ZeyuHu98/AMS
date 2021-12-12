package services.impl;

import java.util.*;

import services.support.JdbcServicesSupport;
import system.db.DBUtils;

public class DeleteImpl extends JdbcServicesSupport 
{
	UserActivityImpl impl = new UserActivityImpl();
	
	public boolean deleteClassification() throws Exception
	{
		String sql;
		List<Map<String, String>> mapList;
		// BFS get all classification id that need to be deleted
		Queue<Object> queue = new LinkedList<>();
		queue.add(getFromDto("cid"));
		List<Object> cids = new ArrayList<>();
		cids.add(getFromDto("cid"));
		while (!queue.isEmpty())
		{
			int size = queue.size();
			while (size-- > 0)
			{
				sql = "select cid from classification where parentcid = " + queue.poll();
				mapList = queryForList(sql);
				for (Map<String, String> map: mapList)
				{
					queue.add(map.get("cid"));
					cids.add(map.get("cid"));
				}
			}
		}
		
		// get all algorithm id
		List<Object> aids = new ArrayList<>();
		StringBuilder sb = new StringBuilder("select aid from algorithm where parentcid = ?");
		for (int i = 0; i < cids.size()-1; i++)
			sb.append(" or parentcid = ?");
		mapList = queryForList(sb.toString(), cids.toArray());
		for (Map<String, String> map: mapList)
			aids.add(map.get("aid"));
		
		boolean res = true;
		try
		{
			// delete classifications
			String sql1 = "delete from classification where cid = ?";
			this.batchUpdate(sql1, cids.toArray());
			// delete algorithm
			String sql2 = "delete from algorithm where aid = ?";
			this.batchUpdate(sql2, aids.toArray());
			// delete benchmark
			String sql3 = 
					"delete b from benchmark b, implementation i where i.iid = b.iid and i.aid = ?";
			this.batchUpdate(sql3, aids.toArray());
			// delete implementation
			String sql4 = "delete from implementation where aid = ?";
			this.batchUpdate(sql4, aids.toArray());
			// delete problemInstance
			String sql5 = "delete from probleminstance where aid = ?";
			this.batchUpdate(sql5, aids.toArray());
			
			impl.update("delete classification", getFromDto("uid"), getFromDto("cid"));
		}
		catch (Exception e) 
		{
			res = false;
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean deleteAlgorithm() throws Exception
	{
		boolean res = true;
		
		try 
		{
			Object[] args= {this.getFromDto("aid")};
		
			// delete algorithm
			String sql1 = "delete from algorithm where aid = ?";
			executeUpdate(sql1, args);
			// delete benchmark
			String sql2 = 
					"delete b from benchmark b, implementation i where i.iid = b.iid and i.aid = ?";
			executeUpdate(sql2, args);
			// delete implementation
			String sql3 = "delete from implementation where aid = ?";
			executeUpdate(sql3, args);
			// delete problem instance
			String sql4 = "delete from implementation where aid = ?";
			executeUpdate(sql4, args);
			
			impl.update("delete algorithm", getFromDto("uid"), getFromDto("aid"));
		} 
		catch (Exception e) 
		{
			res = false;
			e.printStackTrace();
		}
		
		return res;
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
			StringBuilder sql2 = new StringBuilder()
					.append("delete from benchmark where iid = ?");
			Object[] args2 = {getFromDto("iid")};
			this.executeUpdate(sql2.toString(), args2);	
			impl.update("delete implementation", getFromDto("uid"), getFromDto("iid"));
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
			StringBuilder sql2 = new StringBuilder()
					.append("delete from benchmark where pid = ?");
			Object[] args2 = {getFromDto("pid")};
			this.executeUpdate(sql2.toString(), args2);	
			impl.update("delete probleminstance", getFromDto("uid"), getFromDto("pid"));

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
		return res;
	}
	
	

}
