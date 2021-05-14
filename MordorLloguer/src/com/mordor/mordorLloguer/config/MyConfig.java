package com.mordor.mordorLloguer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class MyConfig {

	private static MyConfig instancia = new MyConfig();
	private String defaultFile = "db.properties";
	private String appFile = "mydb.properties";
	private Properties properties;

	private String key = "ieslavereda.es";
	private Map<String, String> propiedadesSeguras;

	private MyConfig() {

		Properties defaultProperties = new Properties();

		try (FileInputStream fis = new FileInputStream(new File(defaultFile))) {

			defaultProperties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}

		properties = new Properties(defaultProperties);

		try (FileInputStream fis = new FileInputStream(new File(appFile))) {

			properties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			checkEncriptedProperties();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		

	}

	public void setProperties(String driver, String URL, String name, String pass) {

		properties.setProperty("ORACLE_DB_DRIVER_CLASS", driver);
		properties.setProperty("ORACLE_DB_URL", URL);
		properties.setProperty("ORACLE_DB_USERNAME", encrypt(name));
		properties.setProperty("IS_ORACLE_DB_USERNAME_ENCRYPTED", "true");
		properties.setProperty("ORACLE_DB_PASSWORD", encrypt(pass));
		properties.setProperty("IS_ORACLE_DB_PASSWORD_ENCRYPTED", "true");
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
		return decryptPropertyValue("ORACLE_DB_USERNAME");
	}

	public String getPassword() {
		return decryptPropertyValue("ORACLE_DB_PASSWORD");
	}

	public void guardar() {

		try (FileOutputStream fos = new FileOutputStream(new File(appFile))) {

			properties.store(fos, "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void checkEncriptedProperties() throws Exception {

		// Creamos un map con todas las propiedades que deben ser encriptadas,

		// junto con las propiedades que nos indican el estado de estas.

		propiedadesSeguras = new HashMap<String, String>();

		propiedadesSeguras.put("ORACLE_DB_USERNAME", "IS_ORACLE_DB_USERNAME_ENCRYPTED");

		propiedadesSeguras.put("ORACLE_DB_PASSWORD", "IS_ORACLE_DB_PASSWORD_ENCRYPTED");

		// Si no existe la propiedad que indica si esta encriptado una key,

		// la creamos y la ponemos a false

		for (String isEncripted : propiedadesSeguras.values()) {

			if (!properties.containsKey(isEncripted))

				properties.put(isEncripted, "false");

		}

		// Encriptamos las claves si fuera necesario antes de leer las propiedades

		for (String property : propiedadesSeguras.keySet())

			encryptPropertyValue(property, propiedadesSeguras.get(property));

		// Guardamos las propiedades.

		// De esta forma las propiedades que no estaban encriptadas,

		// pasaran a disco encriptadas

		guardar();

	}

	private void encryptPropertyValue(String propertyKey, String isPropertyKeyEncrypted) {

		// Retrieve boolean properties value to see if password is already

		// encrypted or not

		String isEncrypted = properties.getProperty(isPropertyKeyEncrypted);

		// Check if password is encrypted?

		if (isEncrypted.equals("false")) {

			String tmpPwd = properties.getProperty(propertyKey);

			String encryptedPassword = encrypt(tmpPwd);

			// Overwrite password with encrypted password in the properties file

			// using Apache Commons Cinfiguration library

			properties.setProperty(propertyKey, encryptedPassword);

			// Set the boolean flag to true to indicate future encryption

			// operation that password is already encrypted

			properties.setProperty(isPropertyKeyEncrypted, "true");

			// Save the properties file

			guardar();

		}

	}

	private String encrypt(String tmpPwd) {

		// Encrypt

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		encryptor.setPassword(key);

		String encryptedPassword = encryptor.encrypt(tmpPwd);

		return encryptedPassword;

	}

	private String decryptPropertyValue(String propertyKey) {

		String encryptedPropertyValue = properties.getProperty(propertyKey);

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		encryptor.setPassword(key);

		String decryptedPropertyValue = encryptor.decrypt(encryptedPropertyValue);

		return decryptedPropertyValue;

	}

}
