package com.mordor.mordorLloguer.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class MyConfig {

	private static MyConfig instancia = new MyConfig();
	private String defaultFile = "db.properties";
	private Properties properties;
	
	private MyConfig() {

		Properties defaultProperties = new Properties();

		try (FileInputStream fis = new FileInputStream(new File(defaultFile))) {

			defaultProperties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}

		properties = new Properties(defaultProperties);
		
	}
	
	public static MyConfig getInstance() {
		return instancia;
	}
	
	public String getDriver() {
		return properties.getProperty("ORACLE_DB_DRIVER_CLASS");
	}
	
	public String getURL() {
		return properties.getProperty("ORACLE_DB_URL");
	}
	
	public String getNombre() {
		return properties.getProperty("ORACLE_DB_USERNAME");
	}
	
	public String getPassword() {
		return properties.getProperty("ORACLE_DB_PASSWORD");
	}
	
	
}
