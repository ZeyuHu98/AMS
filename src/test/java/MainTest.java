import system.db.DBUtils;

public class MainTest 
{
	public static void main(String[] args) throws Exception
	{
		DBUtils.prepareStatement("select * from classification;");
		System.out.println("success!");
	}
}
