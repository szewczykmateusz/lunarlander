import java.util.*;
import java.util.Properties;

//CLASS reading the config file

public class Config {
   Properties configFile;
   
	public Config() {
	 configFile = new java.util.Properties();
	 try {
	 		configFile.load(this.getClass().getClassLoader().getResourceAsStream("config/config.cfg"));
	 	} catch(Exception e) {
	     	e.printStackTrace();
	 	}
	}
 
   public String getProperty(String key) {
	 String value = this.configFile.getProperty(key);
	 return value;
   }
}