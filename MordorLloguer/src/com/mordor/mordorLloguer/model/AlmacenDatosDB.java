package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
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
	public ArrayList<Car> getCoches() throws ParseException;
	public ArrayList<Truck> getCamiones() throws ParseException;
	public ArrayList<Van> getFurgonetas() throws ParseException;
	public ArrayList<Minibus> getMinibus() throws ParseException;
	boolean deleteCar(Car car);
	boolean deleteVan(Van van);
	boolean deleteTruck(Truck truck);
	boolean deleteBus(Minibus bus);
	boolean addCar(Car car) throws SQLException;
	boolean addVan(Van van) throws SQLException;
	boolean addTruck(Truck truck) throws SQLException;
	boolean addBus(Minibus bus) throws SQLException;
	boolean editCar(Car car);
	boolean editVan(Van van);
	boolean editTruck(Truck truck);
	boolean editBus(Minibus bus);
	public String obtenerModelo(String matricula);
	public ArrayList<Factura> getFacturas() throws ParseException;
	public ArrayList<Alquiler> getAlquileres();
	boolean addAlquiler(Alquiler a, String dni) throws SQLException;
	boolean deleteFactura(Factura f);
	boolean deleteAlquiler(int idFactura);
	boolean facturaCobrada(int idFactura);
	boolean deleteDetail(Alquiler a);
}
