import java.util.*;
import java.util.Properties;

//CLASS reading the config file

public class Config {
   Properties configFile;
   
	public Config(int levelNumber) {
	 configFile = new java.util.Properties();
	 try {
	 	StringBuilder builder = new StringBuilder();
	 	builder.append("config/level");
	 	builder.append(levelNumber);
	 	builder.append(".cfg");
//	 		configFile.load(this.getClass().getClassLoader().getResourceAsStream("config/config.cfg"));
		 configFile.load(this.getClass().getClassLoader().getResourceAsStream(builder.toString()));
	 	} catch(Exception e) {
	     	e.printStackTrace();
	 	}
	}
 
   public String getProperty(String key) {
	 String value = this.configFile.getProperty(key);
	 return value;
   }
}