package com.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.mordor.mordorLloguer.controladores.ControladorTabla.MyEmployeeTableModel;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Cliente;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.vistas.vistaAdd;
import com.mordor.mordorLloguer.vistas.vistaCarga;
import com.mordor.mordorLloguer.vistas.vistaTabla;
import com.mordor.mordorLloguer.vistas.vistaTablaClientes;
import com.mordor.mordorLloguer.vistas.vistaAddCliente;
import com.mordor.mordorLloguer.vistas.vistaEdit;

public class ControladorClientes implements ActionListener, DocumentListener, MouseListener {

	private AlmacenDatosDB modelo;
	private vistaTablaClientes vista;
	private vistaCarga vistaCarga;
	private vistaAddCliente vistaAddCliente;
	private vistaEdit vistaEdit;
	private ArrayList<Cliente> clientes;
	private SwingWorker<Boolean, Void> task2;
	private SwingWorker<Boolean, Void> task3;
	private SwingWorker<Void, Void> task;
	private DefaultComboBoxModel dcbCarnets;
	private DefaultComboBoxModel dcbCarnetsAdd;
	String[] header = { "DNI", "Nombre", "Apellidos", "Domicilio", "CP", "Email", "Nacimiento", "Carnet" };

	public ControladorClientes(AlmacenDatosDB modelo, vistaTablaClientes vista) {

		this.modelo = modelo;
		this.vista = vista;
		this.clientes = modelo.getClientes();

		inicializar();

	}

	private void inicializar() {

		vista.getBtnAdd().addActionListener(this);
		vista.getBtnClose().addActionListener(this);
		vista.getBtnDelete().addActionListener(this);
		vista.getMntmAddRow().addActionListener(this);
		vista.getMntmDeleteRow().addActionListener(this);
		vista.getBtnEdit().addActionListener(this);

		vista.getBtnAdd().setActionCommand("Add");
		vista.getBtnClose().setActionCommand("Close");
		vista.getBtnDelete().setActionCommand("Delete");
		vista.getMntmAddRow().setActionCommand("Add");
		vista.getMntmDeleteRow().setActionCommand("Delete");
		vista.getBtnEdit().setActionCommand("Edit");

		rellenarComboBox();
		rellenarTabla();
	}

	private void rellenarComboBox() {

		String[] carnetss = new String[] { "ALL", "A", "B", "C", "D", "E", "Z" };

		Vector<String> carnets = new Vector<String>();
		for (String c : carnetss) {
			carnets.add(c);
		}

		dcbCarnets = new DefaultComboBoxModel();
		dcbCarnets.addAll(carnets);
		dcbCarnets.setSelectedItem("ALL");
		vista.getComboBox().setModel(dcbCarnets);

		vista.getTxtFieldDni().getDocument().addDocumentListener(this);
		vista.getTxtFieldName().getDocument().addDocumentListener(this);
		vista.getTxtFieldSurname().getDocument().addDocumentListener(this);
		vista.getComboBox().addActionListener(this);
		vista.getComboBox().setActionCommand("cambio");

	}

	private void rellenarTabla() {

		MyCustomerTableModel tabla = new MyCustomerTableModel(clientes, header);

		vista.getTable().setModel(tabla);

	}

	public void go() {

		vista.setVisible(true);

	}

	private void closeTable() {

		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the customers table?",
				"Warning!", JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			vista.dispose();
		}

	}

	public class MyCustomerTableModel extends MyTableModel<Cliente> {

		private List<Cliente> data;

		public MyCustomerTableModel(List<Cliente> data, String[] header) {
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
				return data.get(row).getCarnet();

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

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();

		if (comando.equals("Close")) {
			closeTable();
		} else if (comando.equals("cambio")) {
			cambiarOrden();
		} else if (comando.equals("Delete")) {
			deleteCustomer();
		} else if (comando.equals("Add")) {
			vistaAdd();
		} else if (comando.equals("Cancel add")) {
			closeAdd();
		} else if (comando.equals("Add customer")) {
			addCustomer();
		} else if (comando.equals("Edit")) {
			vistaEdit();
		} else if (comando.equals("Cancel edit")) {
			closeEdit();
		} else if (comando.equals("Edit customer")) {
			editCustomer();
		}

	}

	private void editCustomer() {
		
		String DNI = vistaEdit.getTxtFieldDNI().getText();
		String nombre = vistaEdit.getTxtFieldName().getText();
		String apellidos = vistaEdit.getTxtFieldSurname().getText();
		String CP = vistaEdit.getTxtFieldCP().getText();
		String email = vistaEdit.getTxtFieldEmail().getText();
		String domicilio = vistaEdit.getTxtFieldAdress().getText();
		String license = String.valueOf(vistaEdit.getComboBox().getSelectedItem());

		if (vistaEdit.getTxtFieldBirthday().getDate() == null | DNI.equals("") | nombre.equals("")
				| apellidos.equals("") | email.equals("") | domicilio.equals("") | CP.equals("")) {

			JOptionPane.showMessageDialog(null, "You must fill in all the fields", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		} else {

			java.sql.Date fechaNac = new java.sql.Date(vistaEdit.getTxtFieldBirthday().getDate().getTime());

			Cliente c = new Cliente(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, license.charAt(0));
			if (modelo.addCliente(c)) {
				JOptionPane.showMessageDialog(null, "Client successfully updated", "Correct procedure",
						JOptionPane.INFORMATION_MESSAGE);
				vistaEdit.dispose();
			}

			recargarTabla();

		}
		
	}

	private void closeEdit() {
		
		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the window?", "Warning!",
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			vistaEdit.dispose();
		}
		
	}

	private void vistaEdit() {

		if (vista.getTable().getSelectedRow() == -1) {

			JOptionPane.showMessageDialog(null, "You must select one row", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {

			if (!Controlador.isOpen(vistaEdit)) {
				vistaEdit = new vistaEdit();
				Controlador.addJInternalFrame(vistaEdit);
				Controlador.centrar(vistaEdit);
			}
			
			vistaEdit.getBtnAdd().addActionListener(this);
			vistaEdit.getBtnCancel().addActionListener(this);
			
			vistaEdit.getBtnAdd().setActionCommand("Edit customer");
			vistaEdit.getBtnCancel().setActionCommand("Cancel edit");
			
			String[] carnetss = new String[] { "ALL", "A", "B", "C", "D", "E", "Z" };

			Vector<String> carnets = new Vector<String>();
			for (String c : carnetss) {
				carnets.add(c);
			}

			dcbCarnets = new DefaultComboBoxModel();
			dcbCarnets.addAll(carnets);
			dcbCarnets.setSelectedItem("ALL");
			vistaEdit.getComboBox().setModel(dcbCarnets);
			
			int[] rows = new int[vista.getTable().getRowCount()];
			rows = vista.getTable().getSelectedRows();
			ArrayList<Cliente> clienteEdit = new ArrayList<Cliente>();
			
			clienteEdit = ((MyTableModel) vista.getTable().getModel()).get(rows);
			
			Cliente c = clienteEdit.get(0);
			
			vistaEdit.getTxtFieldDNI().setText(c.getDNI());
			vistaEdit.getTxtFieldName().setText(c.getNombre());
			vistaEdit.getTxtFieldSurname().setText(c.getApellidos());
			vistaEdit.getTxtFieldEmail().setText(c.getEmail());
			vistaEdit.getTxtFieldAdress().setText(c.getDomicilio());
			vistaEdit.getComboBox().setSelectedItem(String.valueOf(c.getCarnet()));
			vistaEdit.getTxtFieldBirthday().setDate(c.getFechaNac());
			vistaEdit.getTxtFieldCP().setText(c.getCP());
			
		}
	}

	private void addCustomer() {

		String DNI = vistaAddCliente.getTxtFieldDNI().getText();
		String nombre = vistaAddCliente.getTxtFieldName().getText();
		String apellidos = vistaAddCliente.getTxtFieldSurname().getText();
		String CP = vistaAddCliente.getTxtFieldCP().getText();
		String email = vistaAddCliente.getTxtFieldEmail().getText();
		String domicilio = vistaAddCliente.getTxtFieldAdress().getText();
		String license = String.valueOf(vistaAddCliente.getComboBox().getSelectedItem());

		if (vistaAddCliente.getTxtFieldBirthday().getDate() == null | DNI.equals("") | nombre.equals("")
				| apellidos.equals("") | email.equals("") | domicilio.equals("") | CP.equals("")) {

			JOptionPane.showMessageDialog(null, "You must fill in all the required fields (*)", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		} else {

			java.sql.Date fechaNac = new java.sql.Date(vistaAddCliente.getTxtFieldBirthday().getDate().getTime());

			Cliente c = new Cliente(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, license.charAt(0));
			if (modelo.addCliente(c)) {
				JOptionPane.showMessageDialog(null, "Customer created successfully", "Correct procedure",
						JOptionPane.INFORMATION_MESSAGE);
				vistaAddCliente.dispose();
			}

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

				clientes = modelo.getClientes();
				rellenarTabla();

				return null;

			}

			@Override
			protected void done() {

				vistaCarga.dispose();

			}
		};

		task.execute();

	}

	private void closeAdd() {

		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the window?", "Warning!",
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			vistaAddCliente.dispose();
		}

	}

	private void vistaAdd() {

		if (!Controlador.isOpen(vistaAddCliente)) {

			vistaAddCliente = new vistaAddCliente();
			Controlador.addJInternalFrame(vistaAddCliente);
			Controlador.centrar(vistaAddCliente);

		}

		String[] carnetss = new String[] { "A", "B", "C", "D", "E", "Z" };

		Vector<String> carnets = new Vector<String>();
		for (String c : carnetss) {
			carnets.add(c);
		}

		dcbCarnetsAdd = new DefaultComboBoxModel();
		dcbCarnetsAdd.addAll(carnets);
		dcbCarnetsAdd.setSelectedItem("A");
		vistaAddCliente.getComboBox().setModel(dcbCarnetsAdd);

		vistaAddCliente.getLblFoto().addMouseListener(this);
		vistaAddCliente.getBtnAdd().addActionListener(this);
		vistaAddCliente.getBtnCancel().addActionListener(this);
		vistaAddCliente.getBtnAdd().setActionCommand("Add customer");
		vistaAddCliente.getBtnCancel().setActionCommand("Cancel add");

	}

	private void deleteCustomer() {

		if (vista.getTable().getSelectedRow() == -1) {

			JOptionPane.showMessageDialog(null, "You must select at least one row", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {

			if (!Controlador.isOpen(vistaCarga)) {

				vistaCarga = new vistaCarga("Removing employees from the database");
				Controlador.addJInternalFrame(vistaCarga);
				Controlador.centrar(vistaCarga);

				vistaCarga.getBtnCancel().addActionListener(this);
				vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");

			}

			task3 = new SwingWorker<Boolean, Void>() {

				ArrayList<Cliente> clientes;

				@Override
				protected Boolean doInBackground() throws Exception {

					int[] rows = new int[vista.getTable().getRowCount()];
					rows = vista.getTable().getSelectedRows();

					clientes = ((MyTableModel) vista.getTable().getModel()).get(rows);

					for (Cliente c : clientes)
						modelo.deleteCliente(c.getDNI());

					return true;

				}

				@Override
				protected void done() {

					for (Cliente c : clientes)
						((MyTableModel) vista.getTable().getModel()).delete(c);
					vistaCarga.dispose();
				}
			};

			task3.execute();

		}

	}

	private void cambiarOrden() {

		List<Cliente> temp = clientes.stream()
				.filter((c) -> c.getDNI().toUpperCase().contains(vista.getTxtFieldDni().getText().toUpperCase()))
				.filter((c) -> c.getNombre().toUpperCase().contains(vista.getTxtFieldName().getText().toUpperCase()))
				.filter((c) -> c.getApellidos().toUpperCase()
						.contains(vista.getTxtFieldSurname().getText().toUpperCase()))
				.filter((c) -> String.valueOf(c.getCarnet()).toUpperCase()
						.contains(vista.getComboBox().getSelectedItem().toString())
						|| vista.getComboBox().getSelectedItem().toString().equals("ALL"))
				.collect(Collectors.toList());

		MyCustomerTableModel tabla = new MyCustomerTableModel(temp, header);
		vista.getTable().setModel(tabla);

	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {

		cambiarOrden();

	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {

		cambiarOrden();

	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {

		cambiarOrden();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("e");

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
