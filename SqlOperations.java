import java.io.*;
import java.sql.*;
import java.util.Scanner; 
public class SqlOperations
{
String sqlPath,username,pwd;
SqlOperations(String sqlPath, String username, String pwd)
{
this.sqlPath=sqlPath;
this.username=username;
this.pwd=pwd;
}
public boolean takeBackup(String dbName, String backupPath,String backupFileName)
{ 
	boolean status= false;
            try
            {
                FileWriter fos=new FileWriter(backupPath+"\\mycmd.bat");
                String cmd="mysqldump -u "+username+" -p "+dbName+" > "+backupPath+"\\"+backupFileName;
                fos.write(cmd);
                fos.close();
                Runtime rt=Runtime.getRuntime();
                rt.exec("cmd.exe /c start " +backupPath+"\\mycmd.bat");
		status= true;
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
		e.printStackTrace();
            }
		return status;

}
public boolean restoreBackup(String dbName, String backupPath,String backupFileName)
{ 
	boolean status= false;
            try
            {
                FileWriter fos=new FileWriter(backupPath+"\\mycmd1.bat");
                String cmd="mysql -u "+username+" -p "+dbName+" < "+backupPath+"\\"+backupFileName;
                fos.write(cmd);
                fos.close();
                Runtime rt=Runtime.getRuntime();
                rt.exec("cmd.exe /c start " +backupPath+"\\mycmd1.bat");
                System.out.println("Backup restored successfully!");
		status= true;
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
		e.printStackTrace();
            }
		return status;

}
public static void main(String args[])
{
	Scanner scan= new Scanner(System.in);
	System.out.println("Enter SQL bin path");
	String sqlPath=scan.nextLine();
	System.out.println("Enter SQL username");
	String username=scan.nextLine();
	System.out.println("Enter SQL password");
	String pwd=scan.nextLine();

	SqlOperations sqlObj = new SqlOperations(sqlPath,username,pwd);

	System.out.println("SqlOperations class created");

	System.out.println("Enter Database name");
	String dbName=scan.nextLine();
	System.out.println("Enter Backup Path");
	String backupPath=scan.nextLine();
	System.out.println("Enter Backup File Name");
	String backupFileName=scan.nextLine();
	boolean backup_status=sqlObj.takeBackup(dbName,backupPath,backupFileName);

	if(backup_status) { System.out.println("Successfull backup");}
	else { System.out.println("Please try again");}
	boolean restore_status=sqlObj.restoreBackup(dbName,backupPath,backupFileName);

	if(restore_status) { System.out.println("Successfull restoring of database");}
	else { System.out.println("Please try again");}
}
}