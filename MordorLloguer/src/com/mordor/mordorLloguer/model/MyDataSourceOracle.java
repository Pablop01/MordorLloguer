package com.mordor.mordorLloguer.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mordor.mordorLloguer.config.MyConfig;

import oracle.jdbc.datasource.impl.OracleDataSource;

public class MyDataSourceOracle {

	private static String defaultProperties = "db.properties";
	public static DataSource getOracleDataSource() {

		// Objeto DataSource que devolveremos

		OracleDataSource oracleDS = null;

		try{

		// Generamos el DataSource con los datos URL, user y passwd necesarios

		oracleDS = new OracleDataSource();

		oracleDS.setURL(MyConfig.getInstance().getURL());

		oracleDS.setUser(MyConfig.getInstance().getNombre());

		oracleDS.setPassword(MyConfig.getInstance().getPassword());

		} catch (SQLException e) {

		e.printStackTrace();

		}

		return oracleDS; 
	}	
	
}
