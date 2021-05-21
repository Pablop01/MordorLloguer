package com.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ControladorClientes implements ActionListener, DocumentListener {

	private AlmacenDatosDB modelo;
	private vistaTablaClientes vista;
	private vistaCarga vistaCarga;
	private vistaAddCliente vistaAddCliente;
	private ArrayList<Cliente> clientes;
	private SwingWorker<Boolean, Void> task2;
	private SwingWorker<Boolean, Void> task3;
	private SwingWorker<Void, Void> task;
	private DefaultComboBoxModel dcbCarnets;
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

		vista.getBtnAdd().setActionCommand("Add");
		vista.getBtnClose().setActionCommand("Close");
		vista.getBtnDelete().setActionCommand("Delete");
		vista.getMntmAddRow().setActionCommand("Add");
		vista.getMntmDeleteRow().setActionCommand("Delete");

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
			addCustomer();
		}

	}

	private void addCustomer() {
		
		if (!Controlador.isOpen(vistaAddCliente)) {

			vistaAddCliente = new vistaAddCliente();
			Controlador.addJInternalFrame(vistaAddCliente);
			Controlador.centrar(vistaAddCliente);

		}
		
	}

	private void deleteCustomer() {

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

}
