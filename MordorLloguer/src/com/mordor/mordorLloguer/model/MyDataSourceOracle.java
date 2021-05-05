package com.mordor.mordorLloguer.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.datasource.impl.OracleDataSource;

public class MyDataSourceOracle {

	private static String defaultProperties = "db.properties";
	public static DataSource getOracleDataSource() {
		
		// Propiedades donde tenemos los datos de acceso a la BD

		Properties props = new Properties();

		// Objeto DataSource que devolveremos

		OracleDataSource oracleDS = null;

		try(FileInputStream fis = new FileInputStream("db.properties");) {

		// Cargamos las propiedades

		props.load(fis);

		// Generamos el DataSource con los datos URL, user y passwd necesarios

		oracleDS = new OracleDataSource();

		oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));

		oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));

		oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));

		} catch (IOException e) {

		e.printStackTrace();

		} catch (SQLException e) {

		e.printStackTrace();

		}

		return oracleDS; 
	}	
	
}
