package com.mordor.mordorLloguer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class OracleDataBase implements AlmacenDatosDB {

	public ArrayList<Empleado> getCustomEmpleados(String where) {

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "SELECT * FROM EMPLEADO ";

		if (where != null) {
			query += "WHERE " + where;
			System.out.println(query);
		}

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Empleado empleado;

			while (rs.next()) {

				empleado = new Empleado(rs.getInt("IDEMPLEADO"), rs.getString("DNI"), rs.getString("NOMBRE"),
						rs.getString("APELLIDOS"), rs.getString("CP"), rs.getString("EMAIL"), rs.getDate("FECHANAC"),
						rs.getString("CARGO"), rs.getString("DOMICILIO"), rs.getString("PASSWORD"));

				empleados.add(empleado);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleados;

	}

	@Override
	public ArrayList<Empleado> getEmpleados() {

		return getCustomEmpleados(null);

	}

	@Override
	public ArrayList<Empleado> getEmpleadosPorCP(String cp) {

		return getCustomEmpleados("CP='" + cp + "'");

	}

	@Override
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo) {

		return getCustomEmpleados("CARGO='" + cargo + "'");

	}

	@Override
	public Empleado getEmpleadosPorDNI(String dni) {
		
		ArrayList<Empleado> empleados = getCustomEmpleados("DNI='" + dni + "'");
		
		if(empleados.size()==0) {
			return null;
		}else {
			return empleados.get(0);
		}
			
	}

	@Override
	public boolean updateEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmpleado(String dni) {
		
		return false;
	}

	@Override
	public boolean authenticate(String dni, String password) {
		
		DataSource ds = MyDataSourceOracle.getOracleDataSource();
		boolean valido = false;
		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI =? AND PASSWORD =? ";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(query);) {

			pstmt.setString(1, dni);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			rs.next();
			if (rs.getInt(1) > 0) {
				valido = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return valido;

		
	}

}
