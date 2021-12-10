package system.db;

import java.sql.*;
//��Դ�ļ�������
import java.util.ResourceBundle;
//�̸߳���--����Ϊ��ǰ�̰߳󶨶���
import java.lang.ThreadLocal;

public class DBUtils
{
	//1.����������--��������jar��,�����������·��
	private static String driver=null;
	//2.�������Ӵ�--�������ݿ�������ַ�����ݿ�����
	private static String url=null;
	private static String userName=null;
	private static String password=null;
	
	
	//
	private static final ThreadLocal<java.sql.Connection> threadLocal=new ThreadLocal<>();

	/**
	 * ��̬��
	 * <
	 *   ��̬��Ĵ���,���౻��һ�μ������ڴ�ʱ��,ִ��һ��,�Ժ���ִ��
	 *   ��̬�鴴���Ķ���,��פ�ڴ�,ֱ������ִ�н���
	 * >
	 */
	static
	{
		try 
		{
			//��ȡ��Դ�ļ�����������
			ResourceBundle bundle=ResourceBundle.getBundle("DBOptions");
			//ͨ����������,��ȡ����
			driver=bundle.getString("DRIVER");
			url=bundle.getString("URL");
			userName=bundle.getString("USERNAME");
			password=bundle.getString("PASSWORD");
			//3.��������
//			Class.forName(driver);
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
    
	private DBUtils() 
	{
	}
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	private static Connection getConnection()throws Exception
	{
		//��ȡ��ǰ�̰߳����Ӷ���
		Connection conn=threadLocal.get();
		//�жϵ�ǰ�߳��Ƿ�������Ӷ���
		if(conn==null || conn.isClosed())
		{
			//�������Ӷ���
			conn=DriverManager.getConnection(url,userName, password);
			//�����Ӷ����뵱ǰ�̰߳�
			threadLocal.set(conn);
		}
		return conn;
	}
	
	/**
	 * SQL�����뷽��
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public static PreparedStatement prepareStatement(final String sql)throws Exception
	{
		return DBUtils.getConnection().prepareStatement(sql);
	}
	

	//=======================����Ϊ������ط���================================
	/**
	 * ��������
	 * @throws Exception
	 */
	public static void beginTransaction()throws Exception
	{
		DBUtils.getConnection().setAutoCommit(false);
	}
	
	/**
	 * �ύ����
	 * @throws Exception
	 */
	public static void commit()throws Exception
	{
		DBUtils.getConnection().commit();
	}
	
	/**
	 * �ع�����
	 */
	public static void rollback()
	{
		try 
		{
			DBUtils.getConnection().rollback();
		}
		catch (Exception e) 
		{
			try 
			{
				DBUtils.getConnection().rollback();
			}
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * ��������
	 */
	public static void endTransaction()
	{
		try 
		{
			DBUtils.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	//=======================����Ϊ��Դ���ٷ���================================
	
	
	public static void close(ResultSet rs)
	{
		try
		{
			if(rs!=null)   //���NPE
			{
				rs.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstm)
	{
		try
		{
			if(pstm!=null)
			{
				pstm.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void close()
	{
		try
		{
			 //1.��ȡ��ǰ�̰߳󶨵����Ӷ���
			Connection conn=threadLocal.get();
			//2.�ж������Ƿ���Ҫ����
			if(conn!=null && !conn.isClosed())
			{
				//3.��������
				conn.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
