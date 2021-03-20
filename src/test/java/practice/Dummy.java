package practice;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

public class Dummy {

	public static void main(String[] args) throws Exception
	{
		//1.Get OS Properties(System properties)info
		System.out.println(System.getProperty("user.dir"));//Working directory
		System.out.println(System.getProperty("os.name"));//Opertaing System name
		//2.Get Environment Variables info at System or User
		System.out.println(System.getenv("JAVA_HOME"));//get value of environment variable
		ProcessBuilder pb=new ProcessBuilder();
		Map<String,String> envMap=pb.environment(); //get values of all environment variable
		for(Map.Entry<String,String> e:envMap.entrySet())
		{
			System.out.println(e.getKey()+"<=>"+e.getValue());
		}
		//3.Get properties info in our properties file
		File f=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		FileInputStream fi=new FileInputStream(f); //get Read permission
		Properties p=new Properties();
		p.load(fi);
		System.out.println(p.getProperty("url"));
		System.out.println(p.getProperty("maxwait"));
		System.out.println(p.getProperty("uri"));

	}

}
