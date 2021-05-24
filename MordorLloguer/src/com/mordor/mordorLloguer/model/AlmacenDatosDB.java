package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.ArrayList;

public interface AlmacenDatosDB {

	public ArrayList<Empleado> getEmpleados();
	public ArrayList<Empleado> getEmpleadosPorCP(String cp);
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo);
	public Empleado getEmpleadosPorDNI(String dni);
	boolean addEmpleado(Empleado empleado);
	boolean updateEmpleado(Empleado empleado);
	boolean deleteEmpleado(String dni);
	boolean authenticate(String dni, String password);
	public ArrayList<Cliente> getClientes();
	boolean deleteCliente(String dni);
	boolean addCliente(Cliente cliente);
	
}
