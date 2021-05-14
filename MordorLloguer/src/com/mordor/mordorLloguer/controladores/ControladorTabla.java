package com.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.vistas.vistaTabla;

public class ControladorTabla implements ActionListener {

	private AlmacenDatosDB modelo;
	private vistaTabla vista;
	private ArrayList<Empleado> empleados;
	private DefaultComboBoxModel dcbCargos;
	private DefaultComboBoxModel dcbDireccion;
	String[] header = { "DNI", "Nombre", "Apellidos", "Domicilio", "CP", "Email", "Nacimiento", "Cargo" };

	public ControladorTabla(AlmacenDatosDB modelo, vistaTabla vista) {

		this.modelo = modelo;
		this.vista = vista;
		this.empleados = modelo.getEmpleados();
		inicializar();

	}

	private void inicializar() {

		vista.getBtnAdd().addActionListener(this);
		vista.getBtnClose().addActionListener(this);
		vista.getBtnDelete().addActionListener(this);
		vista.getComboBoxSort().addActionListener(this);
		vista.getComboBoxDirection().addActionListener(this);

		vista.getBtnAdd().setActionCommand("Add");
		vista.getBtnClose().setActionCommand("Close");
		vista.getBtnDelete().setActionCommand("Delete");
		vista.getComboBoxSort().setActionCommand("Cambio");
		vista.getComboBoxDirection().setActionCommand("Cambio direccion");

		ordenarPorDNI();
		rellenarComboBox();

	}

	private void rellenarTabla() {

		MyEmployeeTableModel tabla = new MyEmployeeTableModel(empleados, header);

		vista.getTable().setModel(tabla);

	}

	private void ordenarPorDNI() {

		Collections.sort(empleados, (e1, e2) -> e1.getDNI().compareTo(e2.getDNI()));

		MyEmployeeTableModel tabla = new MyEmployeeTableModel(empleados, header);

		vista.getTable().setModel(tabla);
	}

	private void ordenarPorNombre() {

		Collections.sort(empleados, (e1, e2) -> e1.getNombre().compareTo(e2.getNombre()));

		MyEmployeeTableModel tabla = new MyEmployeeTableModel(empleados, header);

		vista.getTable().setModel(tabla);

	}

	private void ordenarPorApellidos() {

		Collections.sort(empleados, (e1, e2) -> e1.getApellidos().compareTo(e2.getApellidos()));

		MyEmployeeTableModel tabla = new MyEmployeeTableModel(empleados, header);

		vista.getTable().setModel(tabla);
	}

	private void ordenarPorDomicilio() {

		Collections.sort(empleados, (e1, e2) -> e1.getDomicilio().compareTo(e2.getDomicilio()));

		MyEmployeeTableModel tabla = new MyEmployeeTableModel(empleados, header);

		vista.getTable().setModel(tabla);
	}

	private void ordenarPorCP() {

		Collections.sort(empleados, (e1, e2) -> e1.getCP().compareTo(e2.getCP()));

		MyEmployeeTableModel tabla = new MyEmployeeTableModel(empleados, header);

		vista.getTable().setModel(tabla);

	}

	private void ordenarPorEmail() {

		Collections.sort(empleados, (e1, e2) -> e1.getEmail().compareTo(e2.getEmail()));

		MyEmployeeTableModel tabla = new MyEmployeeTableModel(empleados, header);

		vista.getTable().setModel(tabla);

	}

	private void ordenarPorNacimiento() {

		Collections.sort(empleados, (e1, e2) -> e1.getFechaNac().compareTo(e2.getFechaNac()));

		MyEmployeeTableModel tabla = new MyEmployeeTableModel(empleados, header);

		vista.getTable().setModel(tabla);

	}

	private void ordenarPorCargo() {

		Collections.sort(empleados, (e1, e2) -> e1.getCargo().compareTo(e2.getCargo()));

		MyEmployeeTableModel tabla = new MyEmployeeTableModel(empleados, header);

		vista.getTable().setModel(tabla);

	}

	private void rellenarComboBox() {

		String[] cargoss = new String[] { "DNI", "NOMBRE", "APELLIDOS", "DOMICILIO", "CP", "EMAIL", "NACIMIENTO",
				"CARGO" };

		Vector<String> cargos = new Vector<String>();
		for (String c : cargoss) {
			cargos.add(c);
		}

		String[] direccionn = new String[] { "ASCENDENTE", "DESCENDENTE" };

		Vector<String> direccion = new Vector<String>();
		for (String c : direccionn) {
			direccion.add(c);
		}

		dcbCargos = new DefaultComboBoxModel();
		dcbDireccion = new DefaultComboBoxModel();

		dcbCargos.addAll(cargos);
		dcbCargos.setSelectedItem("DNI");

		dcbDireccion.addAll(direccion);
		dcbDireccion.setSelectedItem("ASCENDENTE");

		vista.getComboBoxSort().setModel(dcbCargos);
		vista.getComboBoxDirection().setModel(dcbDireccion);

	}

	public void go() {

		vista.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();

		if (comando.equals("Cambio")) {
			cambiarOrdenTabla();
			dcbDireccion.setSelectedItem("ASCENDENTE");
			vista.getComboBoxDirection().setModel(dcbDireccion);
		} else if (comando.equals("Cambio direccion")) {
			cambiarDireccion();
		}

	}

	private void cambiarDireccion() {

		String orden = String.valueOf(vista.getComboBoxDirection().getSelectedItem());
		
		if(orden.equals("DESCENDENTE")) {
		
			Collections.reverse(empleados);

			MyEmployeeTableModel tabla = new MyEmployeeTableModel(empleados, header);

			vista.getTable().setModel(tabla);
		}
	}

	private void cambiarOrdenTabla() {

		String orden = String.valueOf(vista.getComboBoxSort().getSelectedItem());

		if (orden.equals("DNI")) {
			ordenarPorDNI();
		} else if (orden.equals("NOMBRE")) {
			ordenarPorNombre();
		} else if (orden.equals("APELLIDOS")) {
			ordenarPorApellidos();
		} else if (orden.equals("DOMICILIO")) {
			ordenarPorDomicilio();
		} else if (orden.equals("CARGO")) {
			ordenarPorCargo();
		} else if (orden.equals("CP")) {
			ordenarPorCP();
		} else if (orden.equals("EMAIL")) {
			ordenarPorEmail();
		} else if (orden.equals("NACIMIENTO")) {
			ordenarPorNacimiento();
		}

	}

	public class MyEmployeeTableModel extends MyTableModel<Empleado> {

		private List<Empleado> data;

		public MyEmployeeTableModel(List<Empleado> data, String[] header) {
			super(data, header);
			this.data = data;
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {

			case 0:
				return data.get(row).getDNI();
			case 1:
				return data.get(row).getNombre();
			case 2:
				return data.get(row).getApellidos();
			case 3:
				return data.get(row).getDomicilio();
			case 4:
				return data.get(row).getCP();
			case 5:
				return data.get(row).getEmail();
			case 6:
				return data.get(row).getFechaNac();
			case 7:
				return data.get(row).getCargo();

			}
			return null;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {

			switch (columnIndex) {
			case 6:
				return Date.class;
			default:
				return String.class;
			}

		}

	}

}
