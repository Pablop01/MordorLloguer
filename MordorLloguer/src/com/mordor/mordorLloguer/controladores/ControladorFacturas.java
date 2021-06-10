package com.mordor.mordorLloguer.controladores;

import com.mordor.mordorLloguer.vistas.JIFInvoice;
import com.mordor.mordorLloguer.vistas.vistaCarga;
import com.mordor.mordorLloguer.vistas.vistaNewInvoce;
import com.mordor.mordorLloguer.vistas.vistaRegistration;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.mordor.mordorLloguer.model.*;

public class ControladorFacturas implements ActionListener {

	private AlmacenDatosDB modelo;
	private JIFInvoice vista;
	private vistaCarga vistaCarga;
	private vistaNewInvoce vistaNewInvoce;
	private vistaRegistration vistaRegistration;
	private ArrayList<Vehicle> vehiculos;
	private ArrayList<Car> coches;
	private ArrayList<Minibus> buses;
	private ArrayList<Van> furgonetas;
	private ArrayList<Truck> camiones;
	private ArrayList<Factura> facturas;
	private ArrayList<Alquiler> alquileres;
	private ArrayList<Alquiler> alquileresMostrar;
	private ArrayList<Cliente> clientes;
	private SwingWorker<Void, Integer> taskFacturas;
	private SwingWorker<Boolean, Void> task;
	private Double precioBase;
	private Double iva = 1.21;
	private int contador;

	public ControladorFacturas(AlmacenDatosDB modelo, JIFInvoice vista) throws ParseException {
		super();
		this.modelo = modelo;
		this.vista = vista;

		if (!Controlador.isOpen(vistaCarga)) {
			vistaCarga = new vistaCarga("Loading the data from the database");
			vistaCarga.getProgressBar().setIndeterminate(false);
			Controlador.addJInternalFrame(vistaCarga);
			Controlador.centrar(vistaCarga);

			vistaCarga.getBtnCancel().addActionListener(this);
			vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");
		}

		taskFacturas = new SwingWorker<Void, Integer>() {

			@Override
			protected Void doInBackground() throws Exception {

				int i = 0;
				try {
					coches = modelo.getCoches();
					publish(++i);
					buses = modelo.getMinibus();
					publish(++i);
					furgonetas = modelo.getFurgonetas();
					publish(++i);
					camiones = modelo.getCamiones();
					publish(++i);
					facturas = modelo.getFacturas();
					publish(++i);
					alquileres = modelo.getAlquileres();
					publish(++i);
					clientes = modelo.getClientes();
					publish(++i);
					vehiculos = new ArrayList<Vehicle>();
					vehiculos.addAll(coches);
					vehiculos.addAll(camiones);
					vehiculos.addAll(buses);
					vehiculos.addAll(furgonetas);
					contador = 0;
					inicializar();

				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;

			}

			@Override
			protected void process(List<Integer> chunk) {

				vistaCarga.getProgressBar().setValue(chunk.get(0) * 14);
				vistaCarga.getLblNewLabel().setText("Loading the data from the database. " + chunk.get(0) + "/7");

			}

			@Override
			protected void done() {

				vistaCarga.dispose();

			}
		};

		taskFacturas.execute();

	}

	private void inicializar() {

		vista.getBtnCerrar().addActionListener(this);
		vista.getBtnNextInvoice().addActionListener(this);
		vista.getBtnPreviousInvoice().addActionListener(this);
		vista.getBtnNewInvoce().addActionListener(this);
		vista.getBtnRemoveInvoice().addActionListener(this);
		vista.getBtnCheck().addActionListener(this);
		vista.getBtnAddDetail().addActionListener(this);
		vista.getBtnRemoveDetail().addActionListener(this);

		vista.getBtnCerrar().setActionCommand("cerrar vista");
		vista.getBtnNextInvoice().setActionCommand("next");
		vista.getBtnPreviousInvoice().setActionCommand("previous");
		vista.getBtnNewInvoce().setActionCommand("newinvoce");
		vista.getBtnRemoveInvoice().setActionCommand("removeinvoice");
		vista.getBtnCheck().setActionCommand("check");
		vista.getBtnAddDetail().setActionCommand("addDetail");
		vista.getBtnRemoveDetail().setActionCommand("removeDetail");

		rellenarCampos(contador);

	}

	private void rellenarCampos(int contador) {

		precioBase = 0.0;

		if (contador == 0) {
			vista.getBtnNextInvoice().setEnabled(true);
			vista.getBtnPreviousInvoice().setEnabled(false);
		} else if (contador == facturas.size() - 1) {
			vista.getBtnPreviousInvoice().setEnabled(true);
			vista.getBtnNextInvoice().setEnabled(false);
		} else {
			vista.getBtnPreviousInvoice().setEnabled(true);
			vista.getBtnNextInvoice().setEnabled(true);
		}

		boolean encontrado = false;
		Factura f = facturas.get(contador);
		int id = f.getClienteId();
		int idFactura = f.getIdFactura();
		int i = 0;
		Cliente c = null;
		alquileresMostrar = new ArrayList<Alquiler>();
		while (!encontrado) {
			if (clientes.get(i).getId() == id) {
				c = clientes.get(i);
				encontrado = true;
			}
			i++;
		}

		for (Alquiler a : alquileres) {
			if (a.getIdFactura() == idFactura) {
				this.alquileresMostrar.add(a);
				precioBase += a.getPrecio();
			}
		}

		if (encontrado) {
			DecimalFormat formato1 = new DecimalFormat("#.00");
			vista.getTxtFieldNombre().setText(c.getNombre());
			vista.getTxtFieldApellidos().setText(c.getApellidos());
			vista.getTxtFieldDNI().setText(c.getDNI());
			vista.getTxtFieldNumeroFactura().setText(String.valueOf(f.getIdFactura()));
			vista.getWebDateFieldFechaFactura().setDate(f.getFehcaFac());
			vista.getTxtFieldSuma().setText(String.valueOf(formato1.format(precioBase)));
			vista.getTxtFieldTotal().setText(String.valueOf(formato1.format(precioBase * iva)));
			vista.getTxtFieldImpuestos().setText(String.valueOf(formato1.format((precioBase * iva) - precioBase)));

			MyInvoceTableModel tabla = new MyInvoceTableModel(alquileresMostrar, vehiculos);

			vista.getTableDetalles().setModel(tabla);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();

		if (comando.equals("cerrar vista")) {
			cerrarVista();
		} else if (comando.equals("next")) {
			contador++;
			rellenarCampos(contador);
		} else if (comando.equals("previous")) {
			contador--;
			rellenarCampos(contador);
		} else if (comando.equals("newinvoce")) {
			vistaNewInvoce();
		} else if (comando.equals("addnew")) {
			try {
				newInvoce();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (comando.equals("removeinvoice")) {
			try {
				removeInvoice();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (comando.equals("check")) {
			facturaCobrada();
		} else if (comando.equals("addDetail")) {
			vistaAddDetail();
		} else if (comando.equals("newDetail")) {
			try {
				addDetail();
			} catch (HeadlessException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (comando.equals("removeDetail")) {
			removeDetail();
		}

	}

	private void removeDetail() {
		
		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Warning!",
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			
			if (vista.getTableDetalles().getSelectedRow() == -1) {

				JOptionPane.showMessageDialog(null, "You must select at least one row", "ERROR", JOptionPane.ERROR_MESSAGE);

			} else {

				if (!Controlador.isOpen(vistaCarga)) {

					vistaCarga = new vistaCarga("Removing from the database");
					Controlador.addJInternalFrame(vistaCarga);
					Controlador.centrar(vistaCarga);

					vistaCarga.getBtnCancel().addActionListener(this);
					vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");

				}

				task = new SwingWorker<Boolean, Void>() {

					ArrayList<Alquiler> alquileres;

					@Override
					protected Boolean doInBackground() throws Exception {

						int[] rows = new int[vista.getTableDetalles().getRowCount()];
						rows = vista.getTableDetalles().getSelectedRows();

						alquileres = ((MyTableModel) vista.getTableDetalles().getModel()).get(rows);

						for (Alquiler a : alquileres)
							modelo.deleteDetail(a);

						return true;

					}

					@Override
					protected void done() {

						for (Alquiler a : alquileres)
							((MyTableModel) vista.getTableDetalles().getModel()).delete(a);
						vistaCarga.dispose();
					}
				};

				task.execute();

			}
			
		}
		
		
	}

	private void vistaAddDetail() {

		if (!Controlador.isOpen(vistaRegistration)) {

			vistaRegistration = new vistaRegistration();
			Controlador.addJInternalFrame(vistaRegistration);
			Controlador.centrar(vistaRegistration);

			vistaRegistration.getBtnConfirm().addActionListener(this);
			vistaRegistration.getBtnConfirm().setActionCommand("newDetail");

		}

	}

	private void addDetail() throws HeadlessException, SQLException, ParseException {

	
		boolean mEncontrada = false;
		Cliente c;
		String dni = "";

		if (vistaRegistration.getTxtFieldFInicio().getDate() == null || vistaRegistration.getTxtFieldMatricula().getText().equals("") || 
				vistaRegistration.getTxtFieldFFin().getDate() == null) {

			JOptionPane.showMessageDialog(null, "You must fill in all the fields ", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {


			Optional<Vehicle> ma = vehiculos.stream().filter(v -> v.getMatricula().toLowerCase()
					.equals(vistaRegistration.getTxtFieldMatricula().getText().toLowerCase())).findFirst();

			if (ma.isPresent()) {
				mEncontrada = true;
			}


			if (!mEncontrada) {
				JOptionPane.showMessageDialog(null, "There is no vehicle with that registration", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (mEncontrada) {

				String matricula;
				Date fechaInicio;
				Date fechaFin;

				matricula = vistaRegistration.getTxtFieldMatricula().getText();
				fechaInicio = new java.sql.Date(vistaRegistration.getTxtFieldFInicio().getDate().getTime());
				fechaFin = new java.sql.Date(vistaRegistration.getTxtFieldFFin().getDate().getTime());
				Factura f = facturas.get(contador);
				Alquiler a = new Alquiler(null, f.getIdFactura(), matricula, fechaInicio, fechaFin, 0.0);

				if (modelo.addAlquiler(a, vista.getTxtFieldDNI().getText().toUpperCase())) {
					facturas = modelo.getFacturas();
					alquileres = modelo.getAlquileres();
					JOptionPane.showMessageDialog(null, "Detail created successfully", "Successfully",
							JOptionPane.INFORMATION_MESSAGE);
					contador = 0;
					rellenarCampos(contador);
				}

			}

		}

	}

	private void facturaCobrada() {

		Factura f = facturas.get(contador);
		try {
			if (modelo.facturaCobrada(f.getIdFactura())) {
				JOptionPane.showMessageDialog(null, "Check successfully", "Successfully",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void removeInvoice() throws ParseException {

		Factura f = facturas.get(contador);
		if (modelo.deleteFactura(f)) {
			facturas = modelo.getFacturas();
			alquileres = modelo.getAlquileres();
			JOptionPane.showMessageDialog(null, "Invoice deleted successfully", "Successfully",
					JOptionPane.INFORMATION_MESSAGE);
			if (contador == 0) {
				rellenarCampos(++contador);
			} else {
				rellenarCampos(--contador);
			}
		}

	}

	private void newInvoce() throws SQLException, ParseException {

		boolean idEncontrado = false;
		boolean mEncontrada = false;
		Cliente c;
		String dni = "";

		if (vistaNewInvoce.getTxtFieldFInicio().getDate() == null || vistaNewInvoce.getTxtFieldFFin().getDate() == null
				|| vistaNewInvoce.getTxtFieldIdCliente().getText().equals("")
				|| vistaNewInvoce.getTxtFieldMatricula().getText().equals("")) {

			JOptionPane.showMessageDialog(null, "You must fill in all the fields ", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {

			Optional<Cliente> cl = clientes.stream().filter(c1 -> c1.getDNI().toLowerCase()
					.equals(vistaNewInvoce.getTxtFieldIdCliente().getText().toLowerCase())).findFirst();

			if (cl.isPresent()) {
				idEncontrado = true;
				dni = cl.get().getDNI();
			}

			Optional<Vehicle> ma = vehiculos.stream().filter(v -> v.getMatricula().toLowerCase()
					.equals(vistaNewInvoce.getTxtFieldMatricula().getText().toLowerCase())).findFirst();

			if (ma.isPresent()) {
				mEncontrada = true;
			}

			if (!idEncontrado) {
				JOptionPane.showMessageDialog(null, "There is no client with that ID", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (!mEncontrada) {
				JOptionPane.showMessageDialog(null, "There is no vehicle with that registration", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (idEncontrado && mEncontrada) {

				String matricula;
				Date fechaInicio;
				Date fechaFin;

				matricula = vistaNewInvoce.getTxtFieldMatricula().getText();
				fechaInicio = new java.sql.Date(vistaNewInvoce.getTxtFieldFInicio().getDate().getTime());
				fechaFin = new java.sql.Date(vistaNewInvoce.getTxtFieldFFin().getDate().getTime());

				Alquiler a = new Alquiler(null, null, matricula, fechaInicio, fechaFin, 0.0);

				if (modelo.addAlquiler(a, dni.toUpperCase())) {
					facturas = modelo.getFacturas();
					alquileres = modelo.getAlquileres();
					JOptionPane.showMessageDialog(null, "Invoice created successfully", "Successfully",
							JOptionPane.INFORMATION_MESSAGE);
					contador = 0;
					rellenarCampos(contador);
				}

			}

		}

	}

	private void vistaNewInvoce() {

		if (!Controlador.isOpen(vistaNewInvoce)) {

			vistaNewInvoce = new vistaNewInvoce();
			Controlador.addJInternalFrame(vistaNewInvoce);
			Controlador.centrar(vistaNewInvoce);

			vistaNewInvoce.getBtnAdd().addActionListener(this);
			vistaNewInvoce.getBtnAdd().setActionCommand("addnew");

		}

	}

	private void cerrarVista() {

		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Warning!",
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			vista.dispose();
		}

	}

}
