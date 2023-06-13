package util;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
public class Config {

	    public static Map<String,String> getConfig(){
	        try (InputStream input = Config.class.getResourceAsStream("/config.properties")) {
	            Map<String, String> props = new HashMap<>();
	            Properties prop = new Properties();

	            // load a properties file
	            prop.load(input);
	            // get the property value and print it out
	            props.put("DBPASSWORD",prop.getProperty("DBPASSWORD"));
	            props.put("DBUSERNAME",prop.getProperty("DBUSERNAME"));
	            props.put("DBURL",prop.getProperty("DBURL"));
	            props.put("UPLOADS",prop.getProperty("UPLOADS"));
	            
	            return props;

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return null;
	    }

}
