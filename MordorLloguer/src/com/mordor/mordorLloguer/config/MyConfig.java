package com.mordor.mordorLloguer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
	
	public void setProperties(String driver, String URL, String name, String pass) {
		
		properties.setProperty("ORACLE_DB_DRIVER_CLASS", driver);
		properties.setProperty("ORACLE_DB_URL", URL);
		properties.setProperty("ORACLE_DB_USERNAME", name);
		properties.setProperty("ORACLE_DB_PASSWORD", pass);
		guardar();
		
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
	
	public void guardar() {
		
		try(FileOutputStream fos = new FileOutputStream(new File(defaultFile))){
			
			properties.store(fos, "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
