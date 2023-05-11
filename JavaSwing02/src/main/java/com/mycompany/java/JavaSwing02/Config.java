package com.mycompany.java.JavaSwing02;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {
    public static Map<String,String> getDbConfig(){
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            Map<String, String> props = new HashMap<>();
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            props.put("DBPASSWORD",prop.getProperty("DB.PASSWORD"));
            props.put("DBUSERNAME",prop.getProperty("DB.USERNAME"));
            props.put("DBURL",prop.getProperty("DB.URL"));
            return props;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
