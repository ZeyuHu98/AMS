/**
 * ����ÿ��SQL����ִ�з�ʽ
 */
package services.support;

import java.sql.*;

import system.db.DBUtils;

class PstmMetaData 
{
    private PreparedStatement pstm=null;
    private boolean           isBatch=false;
	public PstmMetaData(final PreparedStatement pstm,final boolean isBatch) 
	{
		this.pstm=pstm;
		this.isBatch=isBatch;
	}
	
	public void executePreparedStatement()throws Exception
	{
		if(this.isBatch)
		{
			this.pstm.executeBatch();
		}
		else
		{
			this.pstm.executeUpdate();
		}	
	}
	
	public void close()
	{
		DBUtils.close(this.pstm);
	}
}
