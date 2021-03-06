package com.mordor.mordorLloguer.controladores;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.mordor.mordorLloguer.config.MyConfig;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Car;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.model.Minibus;
import com.mordor.mordorLloguer.model.MyBusTableModel;
import com.mordor.mordorLloguer.model.MyCarTableModel;
import com.mordor.mordorLloguer.model.MyDataSourceOracle;
import com.mordor.mordorLloguer.model.MyTruckTableModel;
import com.mordor.mordorLloguer.model.MyVanTableModel;
import com.mordor.mordorLloguer.model.Truck;
import com.mordor.mordorLloguer.model.Van;
import com.mordor.mordorLloguer.vistas.vistaLogin;
import com.mordor.mordorLloguer.vistas.vistaPrincipal;
import com.mordor.mordorLloguer.vistas.vistaTabla;
import com.mordor.mordorLloguer.vistas.vistaPreferencias;
import com.mordor.mordorLloguer.vistas.JIFInvoice;
import com.mordor.mordorLloguer.vistas.vistaCarga;
import com.mordor.mordorLloguer.vistas.vistaTablaClientes;
import com.mordor.mordorLloguer.vistas.vistaVehiculos;

public class Controlador implements ActionListener {

	private vistaPrincipal vista;
	private vistaLogin vistaLogin;
	private vistaPreferencias vistaPreferencias;
	private vistaTabla vistaTabla;
	private vistaCarga vistaCarga;
	private vistaTablaClientes vistaTablaClientes;
	private vistaVehiculos vistaVehiculos = new vistaVehiculos();
	private JIFInvoice vistaFacturas;
	private static JDesktopPane desktopPane;
	private AlmacenDatosDB modelo;
	private SwingWorker<Boolean, Void> task;
	private SwingWorker<Void, Void> task2;
	private SwingWorker<Void, Void> task3;
	private SwingWorker<Void, Integer> taskVehiculos;
	private boolean valido;
	private static ArrayList<Car> coches;
	private static ArrayList<Van> furgonetas;
	private static ArrayList<Truck> camiones;
	private static ArrayList<Minibus> buses;
	
	public Controlador(vistaPrincipal vista, AlmacenDatosDB modelo) {

		super();
		this.vista = vista;
		this.modelo = modelo;
		this.desktopPane = vista.getDesktopPane();
		inicializar();

	}

	private void inicializar() {

		// A??adir ActionListener
		vista.getBtnlogin().addActionListener(this);
		vista.getBtnlogout().addActionListener(this);
		vista.getMntmExit().addActionListener(this);
		vista.getMntmPreferences().addActionListener(this);
		vista.getBtnTabla().addActionListener(this);
		vista.getBtnClientes().addActionListener(this);
		vista.getBtnVehiculos().addActionListener(this);
		vista.getBtnInvoice().addActionListener(this);

		// A??adir ActionCommand
		vista.getBtnlogin().setActionCommand("Abrir login");
		vista.getBtnlogout().setActionCommand("Logout");
		vista.getMntmExit().setActionCommand("Exit");
		vista.getMntmPreferences().setActionCommand("Preferences");
		vista.getBtnTabla().setActionCommand("Tabla");
		vista.getBtnClientes().setActionCommand("Tabla cliente");
		vista.getBtnVehiculos().setActionCommand("Vehiculos");
		vista.getBtnInvoice().setActionCommand("Factura");

	}

	public void go() {
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();

		if (comando.equals("Abrir login")) {
			abrirLogin();
		} else if (comando.equals("Logout")) {
			logout();
		} else if (comando.equals("Exit")) {
			vista.dispose();
		} else if (comando.equals("Login")) {
			login();
		} else if (comando.equals("Preferences")) {
			preferences();
		} else if (comando.equals("Tabla")) {
			cargarTabla();
		} else if (comando.equals("Save Preferences")) {
			savePreferences();
		} else if (comando.equals("Cancelar carga")) {
			task2.cancel(true);
		} else if (comando.equals("Tabla cliente")) {
			cargarTablaClientes();
		} else if (comando.equals("Vehiculos")) {
			cargarTablaVehiculos();
		} else if (comando.equals("Factura")) {
			try {
				vistaFacturas();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void vistaFacturas() throws ParseException {
		
		if (!isOpen(vistaFacturas)) {

			vistaFacturas = new JIFInvoice();
			ControladorFacturas controladorFacturas = new ControladorFacturas(modelo, vistaFacturas);
			addJInternalFrame(vistaFacturas);
			centrar(vistaFacturas);
		}
		
	}

	private void cargarTablaVehiculos() {
		
		if (!Controlador.isOpen(vistaCarga)) {
			vistaCarga = new vistaCarga("Loading the data from the database");
			vistaCarga.getProgressBar().setIndeterminate(false);
			Controlador.addJInternalFrame(vistaCarga);
			Controlador.centrar(vistaCarga);

			vistaCarga.getBtnCancel().addActionListener(this);
			vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");
		}
		
		taskVehiculos = new SwingWorker<Void, Integer>() {

			@Override
			protected Void doInBackground() throws Exception {

				int i = 0;
				try {
					coches = modelo.getCoches();
					publish(++i);
					furgonetas = modelo.getFurgonetas();
					publish(++i);
					camiones = modelo.getCamiones();
					publish(++i);
					buses = modelo.getMinibus();
					publish(++i);
					mostrarTablaVehiculos();
						
					
				}catch(Exception e) {
					e.printStackTrace();
				}

				return null;

			}
			
			@Override
			protected void process(List<Integer> chunk) {
				
				vistaCarga.getProgressBar().setValue(chunk.get(0)*25);
				vistaCarga.getLblNewLabel().setText("Loading the data from the database. " + chunk.get(0) + "/4");
				
			}

			@Override
			protected void done() {

				vistaCarga.dispose();

			}
		};

		taskVehiculos.execute();
		
		

	}
	
	private void mostrarTablaVehiculos() throws ParseException {
		
		
		if (!isOpen(vistaVehiculos)) {
			vistaVehiculos = new vistaVehiculos();
			ControladorVehiculos controladorVehiculos = new ControladorVehiculos(modelo, vistaVehiculos);
			MyCarTableModel carTable = new MyCarTableModel(coches);
			vistaVehiculos.getPanelCar().getTable().setModel(carTable);

			MyVanTableModel vanTable = new MyVanTableModel(furgonetas);
			vistaVehiculos.getPanelVan().getTable().setModel(vanTable);

			MyTruckTableModel truckTable = new MyTruckTableModel(camiones);
			vistaVehiculos.getPanelTruck().getTable().setModel(truckTable);

			MyBusTableModel busTable = new MyBusTableModel(buses);
			vistaVehiculos.getPanelMinibus().getTable().setModel(busTable);
			addJInternalFrame(vistaVehiculos);
			centrar(vistaVehiculos);
		}
	}

	private void cargarTablaClientes() {

		if (!isOpen(vistaCarga)) {
			if (!isOpen(vistaTablaClientes)) {
				vistaCarga = new vistaCarga("Loading the data from the database");
				addJInternalFrame(vistaCarga);
				centrar(vistaCarga);

				vistaCarga.getBtnCancel().addActionListener(this);
				vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");
			}
		}

		task3 = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {

				mostrarTablaClientes();

				return null;

			}

			@Override
			protected void done() {

				vistaCarga.dispose();

			}
		};

		task3.execute();

	}

	private void mostrarTablaClientes() {

		if (!isOpen(vistaTablaClientes)) {

			vistaTablaClientes = new vistaTablaClientes();
			ControladorClientes controladorClientes = new ControladorClientes(modelo, vistaTablaClientes);
			controladorClientes.go();
			addJInternalFrame(vistaTablaClientes);
			centrar(vistaTablaClientes);

		}

	}

	private void cargarTabla() {

		if (!isOpen(vistaCarga)) {
			if (!isOpen(vistaTabla)) {
				vistaCarga = new vistaCarga("Loading the data from the database");
				addJInternalFrame(vistaCarga);
				centrar(vistaCarga);

				vistaCarga.getBtnCancel().addActionListener(this);
				vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");
			}
		}

		task2 = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {

				mostrarTabla();

				return null;

			}

			@Override
			protected void done() {

				vistaCarga.dispose();

			}
		};

		task2.execute();

	}

	private void savePreferences() {

		MyConfig.getInstance().setProperties(vistaPreferencias.getTxtFieldDriver().getText(),
				vistaPreferencias.getTxtFieldDireccion().getText(), vistaPreferencias.getTxtFieldUsuario().getText(),
				String.valueOf(vistaPreferencias.getPassfieldcontrasenya().getPassword()));

		JOptionPane.showMessageDialog(null, "Saved Correctly", "Saved Correctly", JOptionPane.INFORMATION_MESSAGE);
		vistaPreferencias.dispose();

	}

	private void mostrarTabla() {

		if (!isOpen(vistaTabla)) {

			vistaTabla = new vistaTabla();
			ControladorTabla controlador = new ControladorTabla(modelo, vistaTabla);
			controlador.go();
			addJInternalFrame(vistaTabla);
			centrar(vistaTabla);

		}
	}

	static void addJInternalFrame(JInternalFrame jif) {
		desktopPane.add(jif);
		centrar(jif);
		jif.setVisible(true);

		try {
			jif.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	static boolean isOpen(JInternalFrame jif) {
		boolean existe = false;
		JInternalFrame[] frames = desktopPane.getAllFrames();

		for (JInternalFrame frame : frames) {
			if (frame == jif) {
				existe = true;
			}
		}

		return existe;
	}

	static void centrar(JInternalFrame jif) {
		Dimension deskSize = desktopPane.getSize();
		Dimension jifSize = jif.getSize();
		jif.setLocation((deskSize.width - jifSize.width) / 2, (deskSize.height - jifSize.height) / 2);

	}

	private void preferences() {

		if (!isOpen(vistaPreferencias)) {

			vistaPreferencias = new vistaPreferencias();
			rellenarCampos();
			addJInternalFrame(vistaPreferencias);
			centrar(vistaPreferencias);
		}

		Action action = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				savePreferences();
			}

		};

		vistaPreferencias.getTxtFieldUsuario().setAction(action);
		vistaPreferencias.getTxtFieldDireccion().setAction(action);
		vistaPreferencias.getTxtFieldDriver().setAction(action);
		vistaPreferencias.getPassfieldcontrasenya().setAction(action);

		vistaPreferencias.getBtnGuardar().addActionListener(this);
		vistaPreferencias.getBtnGuardar().setActionCommand("Save Preferences");

	}

	private void rellenarCampos() {

		vistaPreferencias.getTxtFieldDriver().setText(MyConfig.getInstance().getDriver());
		vistaPreferencias.getTxtFieldDireccion().setText(MyConfig.getInstance().getURL());
		vistaPreferencias.getTxtFieldUsuario().setText(MyConfig.getInstance().getNombre());
		vistaPreferencias.getPassfieldcontrasenya().setText(MyConfig.getInstance().getPassword());

	}

	private void logout() {

		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Warning!",
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			cambiarActivacionBotones(false);
		}

	}

	private void login() {

		vistaLogin.getProgressBar().setVisible(true);
		vistaLogin.getLblError().setText("");
		valido = false;

		task = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {

				if (modelo.authenticate(vistaLogin.getTextField().getText(),
						String.valueOf(vistaLogin.getPasswordField().getPassword()))) {
					valido = true;
				}

				return valido;
			}

			@Override
			protected void done() {

				if (valido) {
					JOptionPane.showMessageDialog(null, "Session successfully started", "Rigth login",
							JOptionPane.INFORMATION_MESSAGE);
					vistaLogin.dispose();

					Empleado e = modelo.getEmpleadosPorDNI(vistaLogin.getTextField().getText());
					vista.getLblBienvenido().setText("Welcome, ");
					vista.getLblNombre().setText(e.getNombre());
					cambiarActivacionBotones(true);
					vistaLogin.getProgressBar().setVisible(false);
				} else {
					vistaLogin.getProgressBar().setVisible(false);
					vistaLogin.getLblError().setText("Incorrect username or password");
				}

			}

		};

		task.execute();

	}

	private void cambiarActivacionBotones(boolean estado) {

		vista.getBtnlogin().setEnabled(!estado);
		vista.getBtnlogout().setEnabled(estado);
		vista.getBtnTabla().setEnabled(estado);
		vista.getBtnClientes().setEnabled(estado);
		vista.getBtnVehiculos().setEnabled(estado);
		vista.getBtnInvoice().setEnabled(estado);

		if (!estado) {
			JOptionPane.showMessageDialog(null, "Session closed successfully", "Rigth logout",
					JOptionPane.INFORMATION_MESSAGE);
			vista.getLblBienvenido().setText("");
			vista.getLblNombre().setText("");
		}

	}

	private void abrirLogin() {

		if (!isOpen(vistaLogin)) {

			vistaLogin = new vistaLogin();
			addJInternalFrame(vistaLogin);
			centrar(vistaLogin);

			Action action = new AbstractAction() {

				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					login();
				}

			};

			vistaLogin.getTextField().setAction(action);
			vistaLogin.getPasswordField().setAction(action);

			vistaLogin.getBtnLogin().addActionListener(this);
			vistaLogin.getBtnLogin().setActionCommand("Login");
		}

	}

	public static ArrayList<Car> getCoches() {
		return coches;
	}

	public static ArrayList<Van> getFurgonetas() {
		return furgonetas;
	}

	public static ArrayList<Truck> getCamiones() {
		return camiones;
	}

	public static ArrayList<Minibus> getBuses() {
		return buses;
	}
	
	

}
