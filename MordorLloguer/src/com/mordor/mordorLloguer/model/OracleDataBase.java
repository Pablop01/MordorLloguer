package com.mordor.mordorLloguer.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import com.mordor.mordorLloguer.config.MyConfig;

import oracle.jdbc.OracleTypes;

public class OracleDataBase implements AlmacenDatosDB {

	public ArrayList<Empleado> getCustomEmpleados(String where) {

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "SELECT * FROM EMPLEADO ";

		if (where != null) {
			query += "WHERE " + where;
		}

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Empleado empleado;

			while (rs.next()) {

				empleado = new Empleado(rs.getString("DNI"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"),
						rs.getString("CP"), rs.getString("EMAIL"), rs.getDate("FECHANAC"), rs.getString("CARGO"),
						rs.getString("DOMICILIO"), rs.getString("PASSWORD"));

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

		if (empleados.size() == 0) {
			return null;
		} else {
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

		boolean eliminado = false;

		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "DELETE FROM EMPLEADO WHERE DNI = ?";

		try (Connection con = ds.getConnection()) {

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, dni);

			if (pstmt.executeUpdate() == 1) {
				eliminado = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eliminado;
	}

	@Override
	public boolean authenticate(String dni, String password) {

		DataSource ds = MyDataSourceOracle.getOracleDataSource();
		boolean valido = false;
		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI=? AND PASSWORD=ENCRYPT_PASWD.encrypt_val(?) ";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(query);) {

			pstmt.setString(1, dni);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			rs.next();
			if (rs.getInt(1) > 0) {
				valido = true;
			}

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null,
					"The database configuration is incorrect, You can change it in preferences", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		}

		return valido;

	}

	@Override
	public boolean addEmpleado(Empleado e) {

		boolean insertado = false;

		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password)"
				+ " VALUES (?,?,?,?,?,?,?,?,encrypt_paswd.encrypt_val(?))";

		try (Connection con = ds.getConnection()) {

			PreparedStatement pstmt = con.prepareStatement(query);

			int pos = 0;
			pstmt.setString(++pos, e.getDNI());
			pstmt.setString(++pos, e.getNombre());
			pstmt.setString(++pos, e.getApellidos());
			pstmt.setString(++pos, e.getDomicilio());
			pstmt.setString(++pos, e.getCP());
			pstmt.setString(++pos, e.getEmail());
			pstmt.setDate(++pos, e.getFechaNac());
			pstmt.setString(++pos, e.getCargo());
			pstmt.setString(++pos, e.getPassword());

			if (pstmt.executeUpdate() == 1) {
				insertado = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getErrorCode());
			if (ex.getErrorCode() == 00001) {
				JOptionPane.showMessageDialog(null, "There is already an employee with this DNI", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		return insertado;

	}

	@Override
	public ArrayList<Cliente> getClientes() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "SELECT * FROM CLIENTE ";

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Cliente cliente;

			while (rs.next()) {

				cliente = new Cliente(rs.getString("DNI"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"),
						rs.getString("DOMICILIO"), rs.getString("CP"), rs.getString("EMAIL"), rs.getDate("FECHANAC"),
						rs.getString("CARNET").charAt(0), rs.getBytes("FOTO"));

				clientes.add(cliente);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientes;

	}
	

	@Override
	public boolean deleteCliente(String dni) {

		boolean eliminado = false;

		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "{call GESTIONALQUILER.bajaCliente(?)}";

		try (Connection con = ds.getConnection()) {

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, dni);

			if (pstmt.executeUpdate() == 1) {
				eliminado = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eliminado;

	}

	@Override
	public boolean addCliente(Cliente cliente) {
		boolean eliminado = false;

		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "{call GESTIONALQUILER.grabarCliente(?,?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection()) {

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, cliente.getDNI());
			pstmt.setString(2, cliente.getNombre());
			pstmt.setString(3, cliente.getApellidos());
			pstmt.setString(4, cliente.getDomicilio());
			pstmt.setString(5, cliente.getCP());
			pstmt.setString(6, cliente.getEmail());
			pstmt.setDate(7, cliente.getFechaNac());
			pstmt.setString(8, String.valueOf(cliente.getCarnet()));
			pstmt.setBytes(9, cliente.getFoto());

			if (pstmt.executeUpdate() == 1) {
				eliminado = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eliminado;

	}

	@Override
	public ArrayList<Car> getCoches() {
		
		ArrayList<Car> coches = new ArrayList<Car>();
		
		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "{call ?:=GESTIONVEHICULOS.listarVehiculos('COCHE')}";
		
		try (Connection con = ds.getConnection()) {

			CallableStatement cstmt = con.prepareCall(query);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			cstmt.execute();
			
			ResultSet rs = (ResultSet) cstmt.getObject(1);
			
			Car car;
			
			while (rs.next()) {

				car = new Car(rs.getString("MATRICULA"), rs.getDouble("PRECIODIA"), rs.getString("MARCA"), rs.getString("DESCRIPCION"),
						rs.getString("COLOR"), rs.getString("MOTOR"), rs.getDouble("CILINDRADA"), rs.getDate("FECHAADQ"), rs.getString("ESTADO")
						, rs.getString("CARNET").charAt(0), rs.getInt("NUMPLAZAS"), rs.getInt("NUMPUERTAS"));
				coches.add(car);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return coches;
	}

	@Override
	public ArrayList<Truck> getCamiones() {
		
		ArrayList<Truck> camiones = new ArrayList<Truck>();
		
		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "{call ?:=GESTIONVEHICULOS.listarVehiculos('CAMION')}";
		
		try (Connection con = ds.getConnection()) {

			CallableStatement cstmt = con.prepareCall(query);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			cstmt.execute();
			
			ResultSet rs = (ResultSet) cstmt.getObject(1);
			
			Truck truck;
			
			while (rs.next()) {

				truck = new Truck(rs.getString("MATRICULA"), rs.getDouble("PRECIODIA"), rs.getString("MARCA"), rs.getString("DESCRIPCION"),
						rs.getString("COLOR"), rs.getString("MOTOR"), rs.getDouble("CILINDRADA"), rs.getDate("FECHAADQ"), rs.getString("ESTADO")
						, rs.getString("CARNET").charAt(0), rs.getInt("NUMRUEDAS"), rs.getInt("MMA"));
				camiones.add(truck);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return camiones;
		
	}

	@Override
	public ArrayList<Van> getFurgonetas() {
		
		ArrayList<Van> furgonetas = new ArrayList<Van>();
		
		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "{call ?:=GESTIONVEHICULOS.listarVehiculos('FURGONETA')}";
		
		try (Connection con = ds.getConnection()) {

			CallableStatement cstmt = con.prepareCall(query);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			cstmt.execute();
			
			ResultSet rs = (ResultSet) cstmt.getObject(1);
			
			Van van;
			
			while (rs.next()) {

				van = new Van(rs.getString("MATRICULA"), rs.getDouble("PRECIODIA"), rs.getString("MARCA"), rs.getString("DESCRIPCION"),
						rs.getString("COLOR"), rs.getString("MOTOR"), rs.getDouble("CILINDRADA"), rs.getDate("FECHAADQ"), rs.getString("ESTADO")
						, rs.getString("CARNET").charAt(0), rs.getDouble("MMA"));
				furgonetas.add(van);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return furgonetas;
		
	}

	@Override
	public ArrayList<Minibus> getMinibus() {
		
		ArrayList<Minibus> buses = new ArrayList<Minibus>();
		
		DataSource ds = MyDataSourceOracle.getOracleDataSource();

		String query = "{call ?:=GESTIONVEHICULOS.listarVehiculos('MICROBUS')}";
		
		try (Connection con = ds.getConnection()) {

			CallableStatement cstmt = con.prepareCall(query);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			cstmt.execute();
			
			ResultSet rs = (ResultSet) cstmt.getObject(1);
			
			Minibus minibus;
			
			while (rs.next()) {

				minibus = new Minibus(rs.getString("MATRICULA"), rs.getDouble("PRECIODIA"), rs.getString("MARCA"), rs.getString("DESCRIPCION"),
						rs.getString("COLOR"), rs.getString("MOTOR"), rs.getDouble("CILINDRADA"), rs.getDate("FECHAADQ"), rs.getString("ESTADO")
						, rs.getString("CARNET").charAt(0), rs.getInt("NUMPLAZAS"), rs.getDouble("MEDIDA"));
				buses.add(minibus);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return buses;
		
	}

}
