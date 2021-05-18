package com.mordor.mordorLloguer.controladores;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.alee.laf.table.WebTable;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.vistas.vistaCarga;
import com.mordor.mordorLloguer.vistas.vistaTabla;
import com.mordor.mordorLloguer.vistas.vistaAdd;

public class ControladorTabla implements ActionListener {

	private AlmacenDatosDB modelo;
	private vistaTabla vista;
	private vistaCarga vistaCarga;
	private vistaAdd vistaAdd;
	private static JDesktopPane desktopPane;
	private ArrayList<Empleado> empleados;
	private DefaultComboBoxModel dcbCargos;
	private DefaultComboBoxModel dcbCargosAdd;
	private DefaultComboBoxModel dcbDireccion;
	private SwingWorker<Boolean, Void> task2;
	private SwingWorker<Void, Void> task;
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
		vista.getMntmAddRow().addActionListener(this);
		vista.getMntmDeleteRow().addActionListener(this);

		vista.getBtnAdd().setActionCommand("Add");
		vista.getBtnClose().setActionCommand("Close");
		vista.getBtnDelete().setActionCommand("Delete");
		vista.getComboBoxSort().setActionCommand("Cambio");
		vista.getComboBoxDirection().setActionCommand("Cambio direccion");
		vista.getMntmAddRow().setActionCommand("Add");
		vista.getMntmDeleteRow().setActionCommand("Delete");

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
		} else if (comando.equals("Close")) {
			closeTable();
		} else if (comando.equals("Delete")) {
			deleteEmployee();
		} else if (comando.equals("Cancelar carga")) {
			task2.cancel(true);
		} else if (comando.equals("Add")) {
			vistaAddEmployee();
		} else if (comando.equals("Add Employee")) {
			addEmployee();
		}

	}

	private void addEmployee() {

		String DNI = vistaAdd.getTxtFieldDNI().getText();
		String nombre = vistaAdd.getTxtFieldName().getText();
		String apellidos = vistaAdd.getTxtFieldSurname().getText();
		String CP = vistaAdd.getTxtFieldCP().getText();
		String email = vistaAdd.getTxtFieldEmail().getText();
		java.sql.Date fechaNac = new java.sql.Date(vistaAdd.getTxtFieldBirthday().getDate().getTime());
		String cargo = String.valueOf(vistaAdd.getComboBox().getSelectedItem());
		String domicilio = vistaAdd.getTxtFieldAdress().getText();
		String password = String.valueOf(vistaAdd.getPasswordField().getPassword());

		Empleado e = new Empleado(DNI, nombre, apellidos, CP, email, fechaNac, cargo, domicilio, password);
		if (modelo.addEmpleado(e)) {
			JOptionPane.showMessageDialog(null, "Employee created successfully", "Correct procedure",
					JOptionPane.INFORMATION_MESSAGE);
			vistaAdd.dispose();
			recargarTabla();
		}
	}

	private void recargarTabla() {

		if (!Controlador.isOpen(vistaCarga)) {
			vistaCarga = new vistaCarga("Updating the data from the database");
			Controlador.addJInternalFrame(vistaCarga);
			Controlador.centrar(vistaCarga);

			vistaCarga.getBtnCancel().addActionListener(this);
			vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");
		}

		task = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {

				empleados = modelo.getEmpleados();
				ordenarPorDNI();
				
				return null;

			}

			@Override
			protected void done() {

				vistaCarga.dispose();

			}
		};

		task.execute();

	}

	private void vistaAddEmployee() {

		if (!Controlador.isOpen(vistaAdd)) {

			vistaAdd = new vistaAdd();
			Controlador.addJInternalFrame(vistaAdd);
			Controlador.centrar(vistaAdd);

		}

		String[] cargoss = new String[] { "Administrativo", "Comercial", "Gerente", "Mecanico" };

		Vector<String> cargos = new Vector<String>();
		for (String c : cargoss) {
			cargos.add(c);
		}

		dcbCargosAdd = new DefaultComboBoxModel();
		dcbCargosAdd.addAll(cargos);
		dcbCargosAdd.setSelectedItem("Administrativo");
		vistaAdd.getComboBox().setModel(dcbCargosAdd);

		vistaAdd.getBtnAdd().addActionListener(this);
		vistaAdd.getBtnAdd().setActionCommand("Add Employee");

	}

	private void deleteEmployee() {

		if (!Controlador.isOpen(vistaCarga)) {

			vistaCarga = new vistaCarga("Removing employees from the database");
			Controlador.addJInternalFrame(vistaCarga);
			Controlador.centrar(vistaCarga);

			vistaCarga.getBtnCancel().addActionListener(this);
			vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");

		}

		task2 = new SwingWorker<Boolean, Void>() {

			ArrayList<Empleado> empleados;

			@Override
			protected Boolean doInBackground() throws Exception {

				int[] rows = new int[vista.getTable().getRowCount()];
				rows = vista.getTable().getSelectedRows();

				empleados = ((MyTableModel) vista.getTable().getModel()).get(rows);

				for (Empleado empleado : empleados)
					modelo.deleteEmpleado(empleado.getDNI());

				return true;

			}

			@Override
			protected void done() {

				for (Empleado empleado : empleados)
					((MyTableModel) vista.getTable().getModel()).delete(empleado);
				vistaCarga.dispose();
			}
		};

		task2.execute();

	}

	private void closeTable() {

		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the employee table?",
				"Warning!", JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			vista.dispose();
		}

	}

	private void cambiarDireccion() {

		String orden = String.valueOf(vista.getComboBoxDirection().getSelectedItem());

		if (orden.equals("DESCENDENTE")) {

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
