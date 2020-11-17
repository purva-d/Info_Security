import java.io.FileWriter;
public class LogFile{
public static void main(String args[]){
try{
	String cmd="WMIC /OUTPUT:C:\\LIST.HTML PROCESS get name,processid,creationdate /format:hform";
	FileWriter fos = new FileWriter("C:\\mycmd.bat");
	fos.write(cmd);
	fos.close();
	Runtime rt = Runtime.getRuntime();
	rt.exec("cmd.exe /c start C:\\mycmd.bat");

}
catch(Exception e)
	{System.out.println("Error "+e.getMessage());}
}
}