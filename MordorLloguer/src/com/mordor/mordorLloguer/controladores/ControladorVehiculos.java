package com.mordor.mordorLloguer.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mordor.mordorLloguer.controladores.ControladorClientes.MyCustomerTableModel;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Car;
import com.mordor.mordorLloguer.model.Cliente;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.model.Minibus;
import com.mordor.mordorLloguer.model.MyBusTableModel;
import com.mordor.mordorLloguer.model.MyCarTableModel;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.model.MyTruckTableModel;
import com.mordor.mordorLloguer.model.MyVanTableModel;
import com.mordor.mordorLloguer.model.Truck;
import com.mordor.mordorLloguer.model.Van;
import com.mordor.mordorLloguer.model.Vehicle;
import com.mordor.mordorLloguer.vistas.vistaAdd;
import com.mordor.mordorLloguer.vistas.vistaCarga;
import com.mordor.mordorLloguer.vistas.vistaEdit;
import com.mordor.mordorLloguer.vistas.vistaTabla;
import com.mordor.mordorLloguer.vistas.vistaVehiculos;
import com.mordor.mordorLloguer.vistas.vistaAddCar;
import com.mordor.mordorLloguer.vistas.vistaEditCar;
import com.mordor.mordorLloguer.vistas.vistaAddVan;
import com.mordor.mordorLloguer.vistas.vistaEditVan;

public class ControladorVehiculos implements ActionListener, DocumentListener {

	private AlmacenDatosDB modelo;
	private vistaVehiculos vista;
	private vistaCarga vistaCarga;
	private ArrayList<Car> coches;
	private ArrayList<Minibus> buses;
	private ArrayList<Van> furgonetas;
	private ArrayList<Truck> camiones;
	private ArrayList<String> carnetCar;
	private ArrayList<String> engineCar;
	private ArrayList<String> carnetVan;
	private ArrayList<String> engineVan;
	private ArrayList<String> carnetTruck;
	private ArrayList<String> engineTruck;
	private ArrayList<String> carnetBus;
	private ArrayList<String> engineBus;
	private DefaultComboBoxModel dcmec;
	private DefaultComboBoxModel dcmcc;
	private DefaultComboBoxModel dcmev;
	private DefaultComboBoxModel dcmcv;
	private DefaultComboBoxModel dcmet;
	private DefaultComboBoxModel dcmct;
	private DefaultComboBoxModel dcmeb;
	private DefaultComboBoxModel dcmcb;
	private SwingWorker<Boolean, Void> task;
	private SwingWorker<Boolean, Void> task2;
	private SwingWorker<Boolean, Void> task3;
	private SwingWorker<Boolean, Void> task4;
	private SwingWorker<Void, Void> task5;
	private SwingWorker<Void, Void> task6;
	private vistaAddCar vistaAddCar;
	private vistaEditCar vistaEditCar;
	private vistaAddVan vistaAddVan;
	private vistaEditVan vistaEditVan;
	private DefaultComboBoxModel dcbCarnetsAdd;
	private DefaultComboBoxModel dcbEstadoAdd;
	private DefaultComboBoxModel dcbMotorAdd;
	String[] header = { "DNI", "Nombre", "Apellidos", "Domicilio", "CP", "Email", "Nacimiento", "Cargo" };

	public ControladorVehiculos(AlmacenDatosDB modelo, vistaVehiculos vista) {

		this.modelo = modelo;
		this.vista = vista;
		this.coches = Controlador.getCoches();
		this.buses = Controlador.getBuses();
		this.furgonetas = Controlador.getFurgonetas();
		this.camiones = Controlador.getCamiones();
		inicializar();

	}

	private void inicializar() {

		vista.getPanelCar().getBtnAdd().addActionListener(this);
		vista.getPanelVan().getBtnAdd().addActionListener(this);
		vista.getPanelTruck().getBtnAdd().addActionListener(this);
		vista.getPanelMinibus().getBtnAdd().addActionListener(this);
		vista.getPanelCar().getBtnCancel().addActionListener(this);
		vista.getPanelVan().getBtnCancel().addActionListener(this);
		vista.getPanelTruck().getBtnCancel().addActionListener(this);
		vista.getPanelMinibus().getBtnCancel().addActionListener(this);
		vista.getPanelCar().getBtnDelete().addActionListener(this);
		vista.getPanelVan().getBtnDelete().addActionListener(this);
		vista.getPanelTruck().getBtnDelete().addActionListener(this);
		vista.getPanelMinibus().getBtnDelete().addActionListener(this);
		vista.getPanelCar().getBtnEdit().addActionListener(this);
		vista.getPanelVan().getBtnEdit().addActionListener(this);
		vista.getPanelTruck().getBtnEdit().addActionListener(this);
		vista.getPanelMinibus().getBtnEdit().addActionListener(this);

		vista.getPanelCar().getBtnAdd().setActionCommand("Add car");
		vista.getPanelVan().getBtnAdd().setActionCommand("Add van");
		vista.getPanelTruck().getBtnAdd().setActionCommand("Add truck");
		vista.getPanelMinibus().getBtnAdd().setActionCommand("Add bus");
		vista.getPanelCar().getBtnCancel().setActionCommand("Cancel");
		vista.getPanelVan().getBtnCancel().setActionCommand("Cancel");
		vista.getPanelTruck().getBtnCancel().setActionCommand("Cancel");
		vista.getPanelMinibus().getBtnCancel().setActionCommand("Cancel");
		vista.getPanelCar().getBtnDelete().setActionCommand("Delete car");
		vista.getPanelVan().getBtnDelete().setActionCommand("Delete van");
		vista.getPanelTruck().getBtnDelete().setActionCommand("Delete truck");
		vista.getPanelMinibus().getBtnDelete().setActionCommand("Delete bus");
		vista.getPanelCar().getBtnEdit().setActionCommand("Edit car");
		vista.getPanelVan().getBtnEdit().setActionCommand("Edit van");
		vista.getPanelTruck().getBtnEdit().setActionCommand("Edit truck");
		vista.getPanelMinibus().getBtnEdit().setActionCommand("Edit bus");

		// Panel Coche
		vista.getPanelCar().getTxtFieldModel().getDocument().addDocumentListener(this);
		vista.getPanelCar().getTxtFieldModel().getDocument().putProperty("owner",
				vista.getPanelCar().getTxtFieldModel());
		vista.getPanelCar().getTxtFieldRegistration().getDocument().addDocumentListener(this);
		vista.getPanelCar().getTxtFieldRegistration().getDocument().putProperty("owner",
				vista.getPanelCar().getTxtFieldRegistration());
		vista.getPanelCar().getComboBoxEngine().addActionListener(this);
		vista.getPanelCar().getComboBoxLicense().addActionListener(this);
		vista.getPanelCar().getComboBoxEngine().setActionCommand("cambio car");
		vista.getPanelCar().getComboBoxLicense().setActionCommand("cambio car");

		// Panel Bus
		vista.getPanelMinibus().getTxtFieldModel().getDocument().addDocumentListener(this);
		vista.getPanelMinibus().getTxtFieldModel().getDocument().putProperty("owner",
				vista.getPanelMinibus().getTxtFieldModel());
		vista.getPanelMinibus().getTxtFieldRegistration().getDocument().addDocumentListener(this);
		vista.getPanelMinibus().getTxtFieldRegistration().getDocument().putProperty("owner",
				vista.getPanelMinibus().getTxtFieldRegistration());
		vista.getPanelMinibus().getComboBoxEngine().addActionListener(this);
		vista.getPanelMinibus().getComboBoxLicense().addActionListener(this);
		vista.getPanelMinibus().getComboBoxEngine().setActionCommand("cambio bus");
		vista.getPanelMinibus().getComboBoxLicense().setActionCommand("cambio bus");

		// Panel Furgoneta
		vista.getPanelVan().getTxtFieldModel().getDocument().addDocumentListener(this);
		vista.getPanelVan().getTxtFieldModel().getDocument().putProperty("owner",
				vista.getPanelVan().getTxtFieldModel());
		vista.getPanelVan().getTxtFieldRegistration().getDocument().addDocumentListener(this);
		vista.getPanelVan().getTxtFieldRegistration().getDocument().putProperty("owner",
				vista.getPanelVan().getTxtFieldRegistration());
		vista.getPanelVan().getComboBoxEngine().addActionListener(this);
		vista.getPanelVan().getComboBoxLicense().addActionListener(this);
		vista.getPanelVan().getComboBoxEngine().setActionCommand("cambio van");
		vista.getPanelVan().getComboBoxLicense().setActionCommand("cambio van");

		// Panel camion
		vista.getPanelTruck().getTxtFieldModel().getDocument().addDocumentListener(this);
		vista.getPanelTruck().getTxtFieldModel().getDocument().putProperty("owner",
				vista.getPanelTruck().getTxtFieldModel());
		vista.getPanelTruck().getTxtFieldRegistration().getDocument().addDocumentListener(this);
		vista.getPanelTruck().getTxtFieldRegistration().getDocument().putProperty("owner",
				vista.getPanelTruck().getTxtFieldRegistration());
		vista.getPanelTruck().getComboBoxEngine().addActionListener(this);
		vista.getPanelTruck().getComboBoxLicense().addActionListener(this);
		vista.getPanelTruck().getComboBoxEngine().setActionCommand("cambio truck");
		vista.getPanelTruck().getComboBoxLicense().setActionCommand("cambio truck");

		rellenarComboEngineCar();
		rellenarComboCarnetCar();
		rellenarComboEngineVan();
		rellenarComboCarnetVan();
		rellenarComboEngineTruck();
		rellenarComboCarnetTruck();
		rellenarComboEngineBus();
		rellenarComboCarnetBus();

	}

	private void rellenarTablaCar() {

		MyCarTableModel tabla = new MyCarTableModel(coches);

		vista.getPanelCar().getTable().setModel(tabla);

	}

	private void rellenarTablaVan() {

		MyVanTableModel tabla = new MyVanTableModel(furgonetas);

		vista.getPanelVan().getTable().setModel(tabla);

	}

	private void rellenarTablaTruck() {

		MyTruckTableModel tabla = new MyTruckTableModel(camiones);

		vista.getPanelTruck().getTable().setModel(tabla);

	}

	private void rellenarTablaBus() {

		MyBusTableModel tabla = new MyBusTableModel(buses);

		vista.getPanelMinibus().getTable().setModel(tabla);

	}

	private void rellenarComboEngineCar() {

		String engine;

		engineCar = new ArrayList<String>();

		for (Car c : coches) {
			engine = c.getMotor();
			if (!engineCar.contains(engine)) {
				engineCar.add(engine);
			}
		}

		dcmec = new DefaultComboBoxModel();
		dcmec.addElement("ALL");
		dcmec.addAll(engineCar);
		dcmec.setSelectedItem("ALL");
		vista.getPanelCar().getComboBoxEngine().setModel(dcmec);

	}

	private void rellenarComboCarnetCar() {

		String carnet;

		carnetCar = new ArrayList<String>();

		for (Car c : coches) {
			carnet = String.valueOf(c.getCarnet());

			if (!carnetCar.contains(carnet)) {
				carnetCar.add(carnet);
			}
		}

		dcmcc = new DefaultComboBoxModel();
		dcmcc.addElement("ALL");
		dcmcc.addAll(carnetCar);
		dcmcc.setSelectedItem("ALL");
		vista.getPanelCar().getComboBoxLicense().setModel(dcmcc);

	}

	private void cambiarOrdenCar() {

		List<Car> temp = coches.stream()
				.filter((c) -> c.getMatricula().toUpperCase()
						.contains(vista.getPanelCar().getTxtFieldRegistration().getText().toUpperCase()))
				.filter((c) -> c.getMarca().toUpperCase()
						.contains(vista.getPanelCar().getTxtFieldModel().getText().toUpperCase()))
				.filter((c) -> c.getMotor().toUpperCase().contains(
						String.valueOf(vista.getPanelCar().getComboBoxEngine().getSelectedItem()).toUpperCase())
						|| vista.getPanelCar().getComboBoxEngine().getSelectedItem().toString().equals("ALL"))
				.filter((c) -> String.valueOf(c.getCarnet()).toUpperCase().contains(
						String.valueOf(vista.getPanelCar().getComboBoxLicense().getSelectedItem()).toUpperCase())
						|| vista.getPanelCar().getComboBoxLicense().getSelectedItem().toString().equals("ALL"))
				.collect(Collectors.toList());

		MyCarTableModel tabla = new MyCarTableModel(temp);
		vista.getPanelCar().getTable().setModel(tabla);

		if (vista.getPanelCar().getComboBoxEngine().getSelectedItem().toString().equals("ALL")) {
			rellenarComboEngineCar();
		} else {
			cambiarComboBoxEngineCar(temp);
		}

		if (vista.getPanelCar().getComboBoxLicense().getSelectedItem().toString().equals("ALL")) {
			rellenarComboCarnetCar();
		} else {
			cambiarComBoxCarnetCar(temp);
		}

	}

	private void cambiarComBoxCarnetCar(List<Car> temp) {

		Set<String> tmp;
		ArrayList<String> newComboCarnet = new ArrayList<String>();

		tmp = temp.stream().map((c) -> String.valueOf(c.getCarnet())).collect(Collectors.toSet());

		String carnet = "";

		for (String s : tmp) {
			if (!newComboCarnet.contains(s)) {
				newComboCarnet.add(s);
				carnet = s;
			}
		}

		DefaultComboBoxModel ndcmcc = new DefaultComboBoxModel();
		ndcmcc.addElement("ALL");
		ndcmcc.addAll(newComboCarnet);
		ndcmcc.setSelectedItem(carnet);
		vista.getPanelCar().getComboBoxLicense().setModel(ndcmcc);

	}

	private void cambiarComboBoxEngineCar(List<Car> temp) {

		Set<String> tmp;
		ArrayList<String> newComboEngine = new ArrayList<String>();

		tmp = temp.stream().map((c) -> c.getMotor()).collect(Collectors.toSet());

		String engine = "";

		for (String s : tmp) {
			if (!newComboEngine.contains(s)) {
				newComboEngine.add(s);
				engine = s;
			}
		}

		DefaultComboBoxModel ndcmcc = new DefaultComboBoxModel();
		ndcmcc.addElement("ALL");
		ndcmcc.addAll(newComboEngine);
		ndcmcc.setSelectedItem(engine);
		vista.getPanelCar().getComboBoxEngine().setModel(ndcmcc);

	}

	private void rellenarComboEngineVan() {

		String engine;

		engineVan = new ArrayList<String>();

		for (Van v : furgonetas) {
			engine = v.getMotor();
			if (!engineVan.contains(engine)) {
				engineVan.add(engine);
			}
		}

		dcmev = new DefaultComboBoxModel();
		dcmev.addElement("ALL");
		dcmev.addAll(engineVan);
		dcmev.setSelectedItem("ALL");
		vista.getPanelVan().getComboBoxEngine().setModel(dcmev);

	}

	private void rellenarComboCarnetVan() {

		String carnet;

		carnetVan = new ArrayList<String>();

		for (Van v : furgonetas) {
			carnet = String.valueOf(v.getCarnet());

			if (!carnetVan.contains(carnet)) {
				carnetVan.add(carnet);

			}
		}

		dcmcv = new DefaultComboBoxModel();
		dcmcv.addElement("ALL");
		dcmcv.addAll(carnetVan);
		dcmcv.setSelectedItem("ALL");
		vista.getPanelVan().getComboBoxLicense().setModel(dcmcv);

	}

	private void cambiarOrdenVan() {

		List<Van> temp = furgonetas.stream()
				.filter((c) -> c.getMatricula().toUpperCase()
						.contains(vista.getPanelVan().getTxtFieldRegistration().getText().toUpperCase()))
				.filter((c) -> c.getMarca().toUpperCase()
						.contains(vista.getPanelVan().getTxtFieldModel().getText().toUpperCase()))
				.filter((c) -> c.getMotor().toUpperCase().contains(
						String.valueOf(vista.getPanelVan().getComboBoxEngine().getSelectedItem()).toUpperCase())
						|| vista.getPanelVan().getComboBoxEngine().getSelectedItem().toString().equals("ALL"))
				.filter((c) -> String.valueOf(c.getCarnet()).contains(
						String.valueOf(vista.getPanelVan().getComboBoxLicense().getSelectedItem()).toUpperCase())
						|| vista.getPanelVan().getComboBoxLicense().getSelectedItem().toString().equals("ALL"))
				.collect(Collectors.toList());

		MyVanTableModel tabla = new MyVanTableModel(temp);
		vista.getPanelVan().getTable().setModel(tabla);

		if (vista.getPanelVan().getComboBoxEngine().getSelectedItem().toString().equals("ALL")) {
			rellenarComboEngineVan();
		} else {
			cambiarComboBoxEngineVan(temp);
		}

		if (vista.getPanelVan().getComboBoxLicense().getSelectedItem().toString().equals("ALL")) {
			rellenarComboCarnetVan();
		} else {
			cambiarComBoxCarnetVan(temp);
		}

	}

	private void cambiarComBoxCarnetVan(List<Van> temp) {

		Set<String> tmp;
		ArrayList<String> newComboCarnet = new ArrayList<String>();

		tmp = temp.stream().map((c) -> String.valueOf(c.getCarnet())).collect(Collectors.toSet());

		String carnet = "";

		for (String s : tmp) {
			if (!newComboCarnet.contains(s)) {
				newComboCarnet.add(s);
				carnet = s;
			}
		}

		DefaultComboBoxModel ndcmcv = new DefaultComboBoxModel();
		ndcmcv.addElement("ALL");
		ndcmcv.addAll(newComboCarnet);
		ndcmcv.setSelectedItem(carnet);
		vista.getPanelVan().getComboBoxLicense().setModel(ndcmcv);

	}

	private void cambiarComboBoxEngineVan(List<Van> temp) {

		Set<String> tmp;
		ArrayList<String> newComboEngine = new ArrayList<String>();

		tmp = temp.stream().map((c) -> c.getMotor()).collect(Collectors.toSet());

		String engine = "";

		for (String s : tmp) {
			if (!newComboEngine.contains(s)) {
				newComboEngine.add(s);
				engine = s;
			}
		}

		DefaultComboBoxModel ndcmcc = new DefaultComboBoxModel();
		ndcmcc.addElement("ALL");
		ndcmcc.addAll(newComboEngine);
		ndcmcc.setSelectedItem(engine);
		vista.getPanelVan().getComboBoxEngine().setModel(ndcmcc);

	}

	private void rellenarComboEngineTruck() {

		String engine;

		engineTruck = new ArrayList<String>();

		for (Truck t : camiones) {
			engine = t.getMotor();
			if (!engineTruck.contains(engine)) {
				engineTruck.add(engine);
			}
		}

		dcmet = new DefaultComboBoxModel();
		dcmet.addElement("ALL");
		dcmet.addAll(engineTruck);
		dcmet.setSelectedItem("ALL");
		vista.getPanelTruck().getComboBoxEngine().setModel(dcmet);

	}

	private void rellenarComboCarnetTruck() {

		String carnet;

		carnetTruck = new ArrayList<String>();

		for (Truck t : camiones) {
			carnet = String.valueOf(t.getCarnet());

			if (!carnetTruck.contains(carnet)) {
				carnetTruck.add(carnet);

			}
		}

		dcmct = new DefaultComboBoxModel();
		dcmct.addElement("ALL");
		dcmct.addAll(carnetTruck);
		dcmct.setSelectedItem("ALL");
		vista.getPanelTruck().getComboBoxLicense().setModel(dcmct);

	}

	private void cambiarOrdenTruck() {

		List<Truck> temp = camiones.stream()
				.filter((c) -> c.getMatricula().toUpperCase()
						.contains(vista.getPanelTruck().getTxtFieldRegistration().getText().toUpperCase()))
				.filter((c) -> c.getMarca().toUpperCase()
						.contains(vista.getPanelTruck().getTxtFieldModel().getText().toUpperCase()))
				.filter((c) -> c.getMotor().toUpperCase().contains(
						String.valueOf(vista.getPanelTruck().getComboBoxEngine().getSelectedItem()).toUpperCase())
						|| vista.getPanelTruck().getComboBoxEngine().getSelectedItem().toString().equals("ALL"))
				.filter((c) -> String.valueOf(c.getCarnet()).contains(
						String.valueOf(vista.getPanelTruck().getComboBoxLicense().getSelectedItem()).toUpperCase())
						|| vista.getPanelTruck().getComboBoxLicense().getSelectedItem().toString().equals("ALL"))
				.collect(Collectors.toList());

		MyTruckTableModel tabla = new MyTruckTableModel(temp);
		vista.getPanelTruck().getTable().setModel(tabla);

		if (vista.getPanelTruck().getComboBoxEngine().getSelectedItem().toString().equals("ALL")) {
			rellenarComboEngineTruck();
		} else {
			cambiarComboBoxEngineTruck(temp);
		}

		if (vista.getPanelTruck().getComboBoxLicense().getSelectedItem().toString().equals("ALL")) {
			rellenarComboCarnetTruck();
		} else {
			cambiarComBoxCarnetTruck(temp);
		}

	}

	private void cambiarComBoxCarnetTruck(List<Truck> temp) {

		Set<String> tmp;
		ArrayList<String> newComboCarnet = new ArrayList<String>();

		tmp = temp.stream().map((c) -> String.valueOf(c.getCarnet())).collect(Collectors.toSet());

		String carnet = "";

		for (String s : tmp) {
			if (!newComboCarnet.contains(s)) {
				newComboCarnet.add(s);
				carnet = s;
			}
		}

		DefaultComboBoxModel ndcmct = new DefaultComboBoxModel();
		ndcmct.addElement("ALL");
		ndcmct.addAll(newComboCarnet);
		ndcmct.setSelectedItem(carnet);
		vista.getPanelTruck().getComboBoxLicense().setModel(ndcmct);

	}

	private void cambiarComboBoxEngineTruck(List<Truck> temp) {

		Set<String> tmp;
		ArrayList<String> newComboEngine = new ArrayList<String>();

		tmp = temp.stream().map((c) -> c.getMotor()).collect(Collectors.toSet());

		String engine = "";

		for (String s : tmp) {
			if (!newComboEngine.contains(s)) {
				newComboEngine.add(s);
				engine = s;
			}
		}

		DefaultComboBoxModel ndcmct = new DefaultComboBoxModel();
		ndcmct.addElement("ALL");
		ndcmct.addAll(newComboEngine);
		ndcmct.setSelectedItem(engine);
		vista.getPanelTruck().getComboBoxEngine().setModel(ndcmct);

	}

	private void rellenarComboEngineBus() {

		String engine;

		engineBus = new ArrayList<String>();

		for (Minibus b : buses) {
			engine = b.getMotor();
			if (!engineBus.contains(engine)) {
				engineBus.add(engine);
			}
		}

		dcmeb = new DefaultComboBoxModel();
		dcmeb.addElement("ALL");
		dcmeb.addAll(engineBus);
		dcmeb.setSelectedItem("ALL");
		vista.getPanelMinibus().getComboBoxEngine().setModel(dcmeb);

	}

	private void rellenarComboCarnetBus() {

		String carnet;

		carnetBus = new ArrayList<String>();

		for (Minibus b : buses) {
			carnet = String.valueOf(b.getCarnet());

			if (!carnetBus.contains(carnet)) {
				carnetBus.add(carnet);

			}
		}

		dcmcb = new DefaultComboBoxModel();
		dcmcb.addElement("ALL");
		dcmcb.addAll(carnetBus);
		dcmcb.setSelectedItem("ALL");
		vista.getPanelMinibus().getComboBoxLicense().setModel(dcmcb);

	}

	private void cambiarOrdenBus() {

		List<Minibus> temp = buses.stream()
				.filter((c) -> c.getMatricula().toUpperCase()
						.contains(vista.getPanelMinibus().getTxtFieldRegistration().getText().toUpperCase()))
				.filter((c) -> c.getMarca().toUpperCase()
						.contains(vista.getPanelMinibus().getTxtFieldModel().getText().toUpperCase()))
				.filter((c) -> c.getMotor().toUpperCase().contains(
						String.valueOf(vista.getPanelMinibus().getComboBoxEngine().getSelectedItem()).toUpperCase())
						|| vista.getPanelMinibus().getComboBoxEngine().getSelectedItem().toString().equals("ALL"))
				.filter((c) -> String.valueOf(c.getCarnet()).contains(
						String.valueOf(vista.getPanelMinibus().getComboBoxLicense().getSelectedItem()).toUpperCase())
						|| vista.getPanelMinibus().getComboBoxLicense().getSelectedItem().toString().equals("ALL"))
				.collect(Collectors.toList());

		MyBusTableModel tabla = new MyBusTableModel(temp);
		vista.getPanelMinibus().getTable().setModel(tabla);

		if (vista.getPanelMinibus().getComboBoxEngine().getSelectedItem().toString().equals("ALL")) {
			rellenarComboEngineBus();
		} else {
			cambiarComboBoxEngineBus(temp);
		}

		if (vista.getPanelMinibus().getComboBoxLicense().getSelectedItem().toString().equals("ALL")) {
			rellenarComboCarnetBus();
		} else {
			cambiarComBoxCarnetBus(temp);
		}

	}

	private void cambiarComBoxCarnetBus(List<Minibus> temp) {

		Set<String> tmp;
		ArrayList<String> newComboCarnet = new ArrayList<String>();

		tmp = temp.stream().map((c) -> String.valueOf(c.getCarnet())).collect(Collectors.toSet());

		String carnet = "";

		for (String s : tmp) {
			if (!newComboCarnet.contains(s)) {
				newComboCarnet.add(s);
				carnet = s;
			}
		}

		DefaultComboBoxModel ndcmcb = new DefaultComboBoxModel();
		ndcmcb.addElement("ALL");
		ndcmcb.addAll(newComboCarnet);
		ndcmcb.setSelectedItem(carnet);
		vista.getPanelMinibus().getComboBoxLicense().setModel(ndcmcb);

	}

	private void cambiarComboBoxEngineBus(List<Minibus> temp) {

		Set<String> tmp;
		ArrayList<String> newComboEngine = new ArrayList<String>();

		tmp = temp.stream().map((c) -> c.getMotor()).collect(Collectors.toSet());

		String engine = "";

		for (String s : tmp) {
			if (!newComboEngine.contains(s)) {
				newComboEngine.add(s);
				engine = s;
			}
		}

		DefaultComboBoxModel ndcmcb = new DefaultComboBoxModel();
		ndcmcb.addElement("ALL");
		ndcmcb.addAll(newComboEngine);
		ndcmcb.setSelectedItem(engine);
		vista.getPanelMinibus().getComboBoxEngine().setModel(ndcmcb);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();

		if (comando.equals("cambio car")) {
			cambiarOrdenCar();
		} else if (comando.equals("cambio van")) {
			cambiarOrdenVan();
		} else if (comando.equals("cambio truck")) {
			cambiarOrdenTruck();
		} else if (comando.equals("cambio bus")) {
			cambiarOrdenBus();
		} else if (comando.equals("Delete car")) {
			deleteCar();
		} else if (comando.equals("Delete van")) {
			deleteVan();
		} else if (comando.equals("Delete truck")) {
			deleteTruck();
		} else if (comando.equals("Delete bus")) {
			deleteBus();
		} else if (comando.equals("Add car")) {
			vistaAddCar();
		} else if (comando.equals("Add new car")) {
			addCar();
		} else if (comando.equals("Cancel")) {
			closeTable();
		} else if (comando.equals("Cancel add car")) {
			closeAddCar();
		} else if (comando.equals("Edit car")) {
			vistaEditCar();
		} else if (comando.equals("save edit car")) {
			editCar();
		} else if (comando.equals("Cancel edit car")) {
			closeEditCar();
		} else if (comando.equals("Add van")) {
			vistaAddVan();
		} else if (comando.equals("Add new van")) {
			addVan();
		} else if (comando.equals("Edit van")) {
			vistaEditVan();
		}

	}

	private void vistaEditVan() {
		
		if (vista.getPanelVan().getTable().getSelectedRow() == -1) {

			JOptionPane.showMessageDialog(null, "You must select one row", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {

			if (!Controlador.isOpen(vistaEditVan)) {
				vistaEditVan = new vistaEditVan();
				Controlador.addJInternalFrame(vistaEditVan);
				Controlador.centrar(vistaEditVan);
			}

			String engine;

			engineVan = new ArrayList<String>();

			for (Van v : furgonetas) {
				engine = v.getMotor();
				if (!engineVan.contains(engine)) {
					engineVan.add(engine);
				}
			}
//
//			dcmec = new DefaultComboBoxModel();
//			dcmec.addAll(engineCar);
//			vistaEditVan.getComboBoxMotor().setModel(dcmec);
//
//			vistaEditVan.getBtnAdd().addActionListener(this);
//			vistaEditVan.getBtnCancel().addActionListener(this);
//			vistaEditVan.getBtnAdd().setActionCommand("save edit car");
//			vistaEditVan.getBtnCancel().setActionCommand("Cancel edit car");
//
//			String[] carnetss = new String[] { "B" };
//
//			Vector<String> carnets = new Vector<String>();
//			for (String c : carnetss) {
//				carnets.add(c);
//			}
//
//			DefaultComboBoxModel dcbCarnets = new DefaultComboBoxModel();
//			dcbCarnets.addAll(carnets);
//			dcbCarnets.setSelectedItem("B");
//			vistaEditVan.getComboBoxCarnet().setModel(dcbCarnets);
//
//			String[] estadoo = new String[] { "preparado", "taller", "baja", "alquilado", "reservado" };
//
//			Vector<String> estado = new Vector<String>();
//			for (String c : estadoo) {
//				estado.add(c);
//			}
//
//			dcbEstadoAdd = new DefaultComboBoxModel();
//			dcbEstadoAdd.addAll(estado);
//
//			int[] rows = new int[vista.getPanelCar().getTable().getRowCount()];
//			rows = vista.getPanelCar().getTable().getSelectedRows();
//			ArrayList<Car> carEdit = new ArrayList<Car>();
//
//			carEdit = ((MyTableModel) vista.getPanelCar().getTable().getModel()).get(rows);
//
//			Car c = carEdit.get(0);
//
//			vistaEditVan.getTxtFieldMarca().setText(c.getMarca());
//			if (c.getCilindrada() == 0.0) {
//				vistaEditVan.getTxtFieldCilindrada().setText("");
//			} else {
//				vistaEditVan.getTxtFieldCilindrada().setText(String.valueOf(c.getCilindrada()));
//			}
//			vistaEditVan.getTxtFieldColor().setText(c.getColor());
//			vistaEditVan.getTxtFieldDate().setDate(c.getFechaadq());
//			vistaEditVan.getTxtFieldDescripcion().setText(c.getDescripcion());
//			vistaEditVan.getTxtFieldMatricula().setText(c.getMatricula());
//			vistaEditVan.getTxtFielMMA().setText(String.valueOf(c.getNumPlazas()));
//			vistaEditVan.getTxtFieldPrecio().setText(String.valueOf(c.getPrecioDia()));
//			dcbEstadoAdd.setSelectedItem(c.getEstado());
//			vistaEditVan.getComboBoxEstado().setModel(dcbEstadoAdd);
//			vistaEditVan.getComboBoxMotor().setSelectedItem(c.getMotor());
//
//		}
		
	}

	private void addVan() {
		
		String matricula = vistaAddVan.getTxtFieldMatricula().getText();

		String marca = vistaAddVan.getTxtFieldMarca().getText();
		String descripcion;
		String color = vistaAddVan.getTxtFieldColor().getText();
		String motor = String.valueOf(vistaAddVan.getComboBoxMotor().getSelectedItem());

		String estado = String.valueOf(vistaAddVan.getComboBoxEstado().getSelectedItem());
		char carnet = String.valueOf(vistaAddVan.getComboBoxCarnet().getSelectedItem()).charAt(0);


		if (vistaAddVan.getTxtFieldDate().getDate() == null | matricula.equals("")
				| vistaAddVan.getTxtFieldPrecio().getText() == null | marca.equals("")
				| vistaAddVan.getTxtFieldCilindrada().getText() == null | color.equals("") | motor.equals("")
				| estado.equals("") | carnet == ' ' |  vistaAddVan.getTxtFielMMA().getText() == null) {

			JOptionPane.showMessageDialog(null, "You must fill in all the required fields (*)", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		} else {

			java.sql.Date fechaNac = new java.sql.Date(vistaAddVan.getTxtFieldDate().getDate().getTime());
			Double precioDia = Double.parseDouble(vistaAddVan.getTxtFieldPrecio().getText());
			Double cilindrada;
			Double mma = Double.valueOf(vistaAddVan.getTxtFielMMA().getText());

			if (vistaAddVan.getTxtFieldCilindrada().getText().equals("")
					&& vistaAddVan.getTxtFieldDescripcion().getText().equals("")) {

				if (vistaAddVan.getTxtFieldCilindrada().getText().equals("")) {
					cilindrada = null;
				} else {
					cilindrada = Double.parseDouble(vistaAddVan.getTxtFieldCilindrada().getText());
				}

				if (vistaAddVan.getTxtFieldDescripcion().getText().equals("")) {
					descripcion = null;
				} else {
					descripcion = vistaAddVan.getTxtFieldDescripcion().getText();
				}

				Van v = new Van(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaNac, estado, carnet, mma);
				
				try {
					if (modelo.addVan(v)) {
						JOptionPane.showMessageDialog(null, "Van created correctly", "CORRECTLY",
								JOptionPane.INFORMATION_MESSAGE);
						vistaAddVan.dispose();
						recargarTablaVan();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				if (vistaAddVan.getTxtFieldCilindrada().getText().equals("")) {
					cilindrada = null;
				} else {
					cilindrada = Double.parseDouble(vistaAddVan.getTxtFieldCilindrada().getText());
				}

				if (vistaAddVan.getTxtFieldDescripcion().getText().equals("")) {
					descripcion = null;
				} else {
					descripcion = vistaAddVan.getTxtFieldDescripcion().getText();
				}

				Van v = new Van(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaNac, estado, carnet, mma);
				try {
					if (modelo.addVan(v)) {
						JOptionPane.showMessageDialog(null, "Van created correctly", "CORRECTLY",
								JOptionPane.INFORMATION_MESSAGE);
						vistaAddVan.dispose();
						recargarTablaVan();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}

		}
		
	}

	private void recargarTablaVan() {
		
		if (!Controlador.isOpen(vistaCarga)) {
			vistaCarga = new vistaCarga("Updating the data from the database");
			Controlador.addJInternalFrame(vistaCarga);
			Controlador.centrar(vistaCarga);

			vistaCarga.getBtnCancel().addActionListener(this);
			vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");
		}

		task6 = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {

				furgonetas = modelo.getFurgonetas();
				rellenarTablaVan();

				return null;

			}

			@Override
			protected void done() {

				vistaCarga.dispose();

			}
		};

		task6.execute();
		
	}

	private void vistaAddVan() {
		
		if (!Controlador.isOpen(vistaAddVan)) {

			vistaAddVan = new vistaAddVan();
			Controlador.addJInternalFrame(vistaAddVan);
			Controlador.centrar(vistaAddVan);

		}

		String[] carnetss = new String[] { "C" };

		Vector<String> carnets = new Vector<String>();
		for (String c : carnetss) {
			carnets.add(c);
		}

		dcbCarnetsAdd = new DefaultComboBoxModel();
		dcbCarnetsAdd.addAll(carnets);
		dcbCarnetsAdd.setSelectedItem("C");
		vistaAddVan.getComboBoxCarnet().setModel(dcbCarnetsAdd);

		String[] estadoo = new String[] { "preparado", "taller", "baja", "alquilado", "reservado" };

		Vector<String> estado = new Vector<String>();
		for (String c : estadoo) {
			estado.add(c);
		}

		dcbEstadoAdd = new DefaultComboBoxModel();
		dcbEstadoAdd.addAll(estado);
		dcbEstadoAdd.setSelectedItem("preparado");
		vistaAddVan.getComboBoxEstado().setModel(dcbEstadoAdd);

		String engine;

		engineVan = new ArrayList<String>();

		for (Van v : furgonetas) {
			engine = v.getMotor();
			if (!engineVan.contains(engine)) {
				engineVan.add(engine);
			}
		}

		dcmec = new DefaultComboBoxModel();
		dcmec.addAll(engineVan);
		dcmec.setSelectedItem("gasolina");
		vistaAddVan.getComboBoxMotor().setModel(dcmec);

		vistaAddVan.getBtnAdd().addActionListener(this);
		vistaAddVan.getBtnCancel().addActionListener(this);
		vistaAddVan.getBtnAdd().setActionCommand("Add new van");
		vistaAddVan.getBtnCancel().setActionCommand("Cancel add van");

		
	}

	private void closeEditCar() {
		
		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel Edit?", "Warning!",
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			vistaEditCar.dispose();
		}

		
	}

	private void editCar() {
		
		String matricula = vistaEditCar.getTxtFieldMatricula().getText();

		String marca = vistaEditCar.getTxtFieldMarca().getText();
		String descripcion;
		String color = vistaEditCar.getTxtFieldColor().getText();
		String motor = String.valueOf(vistaEditCar.getComboBoxMotor().getSelectedItem());

		String estado = String.valueOf(vistaEditCar.getComboBoxEstado().getSelectedItem());
		char carnet = String.valueOf(vistaEditCar.getComboBoxCarnet().getSelectedItem()).charAt(0);

		if (vistaEditCar.getTxtFieldDate().getDate() == null | matricula.equals("")
				| vistaEditCar.getTxtFieldPrecio().getText() == null | marca.equals("")
				| vistaEditCar.getTxtFieldCilindrada().getText() == null | color.equals("") | motor.equals("")
				| estado.equals("") | carnet == ' ' | vistaEditCar.getTxtFieldNumPlazas().getText() == null
				| vistaEditCar.getTxtFieldNumPuertas().getText() == null) {

			JOptionPane.showMessageDialog(null, "You must fill in all the required fields (*)", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		} else {

			java.sql.Date fechaNac = new java.sql.Date(vistaEditCar.getTxtFieldDate().getDate().getTime());
			Double precioDia = Double.parseDouble(vistaEditCar.getTxtFieldPrecio().getText());
			Double cilindrada;
			int numPlazas = Integer.parseInt(vistaEditCar.getTxtFieldNumPlazas().getText());
			int numPuertas = Integer.parseInt(vistaEditCar.getTxtFieldNumPuertas().getText());
			if (vistaEditCar.getTxtFieldCilindrada().getText().equals("")
					&& vistaEditCar.getTxtFieldDescripcion().getText().equals("")) {

				if (vistaEditCar.getTxtFieldCilindrada().getText().equals("")) {
					cilindrada = null;
				} else {
					cilindrada = Double.parseDouble(vistaEditCar.getTxtFieldCilindrada().getText());
				}

				if (vistaEditCar.getTxtFieldDescripcion().getText().equals("")) {
					descripcion = null;
				} else {
					descripcion = vistaEditCar.getTxtFieldDescripcion().getText();
				}

				Car c = new Car(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaNac, estado,
						carnet, numPlazas, numPuertas);
				try {
					if (modelo.editCar(c)) {
						JOptionPane.showMessageDialog(null, "Car updated correctly", "CORRECTLY",
								JOptionPane.INFORMATION_MESSAGE);
						vistaEditCar.dispose();
						recargarTablaCar();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				if (vistaEditCar.getTxtFieldCilindrada().getText().equals("")) {
					cilindrada = null;
				} else {
					cilindrada = Double.parseDouble(vistaEditCar.getTxtFieldCilindrada().getText());
				}

				if (vistaEditCar.getTxtFieldDescripcion().getText().equals("")) {
					descripcion = null;
				} else {
					descripcion = vistaEditCar.getTxtFieldDescripcion().getText();
				}

				Car c = new Car(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaNac, estado,
						carnet, numPlazas, numPuertas);
				try {
					if (modelo.editCar(c)) {
						JOptionPane.showMessageDialog(null, "Car updated correctly", "CORRECTLY",
								JOptionPane.INFORMATION_MESSAGE);
						vistaEditCar.dispose();
						recargarTablaCar();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}

		}

		
	}

	private void closeAddCar() {

		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel Add?", "Warning!",
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			vistaAddCar.dispose();
		}

	}

	private void closeTable() {

		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the window?", "Warning!",
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			vista.dispose();
		}

	}

	private void addCar() {

		String matricula = vistaAddCar.getTxtFieldMatricula().getText();

		String marca = vistaAddCar.getTxtFieldMarca().getText();
		String descripcion;
		String color = vistaAddCar.getTxtFieldColor().getText();
		String motor = String.valueOf(vistaAddCar.getComboBoxMotor().getSelectedItem());

		String estado = String.valueOf(vistaAddCar.getComboBoxEstado().getSelectedItem());
		char carnet = String.valueOf(vistaAddCar.getComboBoxCarnet().getSelectedItem()).charAt(0);

		if (vistaAddCar.getTxtFieldDate().getDate() == null | matricula.equals("")
				| vistaAddCar.getTxtFieldPrecio().getText() == null | marca.equals("")
				| vistaAddCar.getTxtFieldCilindrada().getText() == null | color.equals("") | motor.equals("")
				| estado.equals("") | carnet == ' ' | vistaAddCar.getTxtFieldNumPlazas().getText() == null
				| vistaAddCar.getTxtFieldNumPuertas().getText() == null) {

			JOptionPane.showMessageDialog(null, "You must fill in all the required fields (*)", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		} else {

			java.sql.Date fechaNac = new java.sql.Date(vistaAddCar.getTxtFieldDate().getDate().getTime());
			Double precioDia = Double.parseDouble(vistaAddCar.getTxtFieldPrecio().getText());
			Double cilindrada;
			int numPlazas = Integer.parseInt(vistaAddCar.getTxtFieldNumPlazas().getText());
			int numPuertas = Integer.parseInt(vistaAddCar.getTxtFieldNumPuertas().getText());
			if (vistaAddCar.getTxtFieldCilindrada().getText().equals("")
					&& vistaAddCar.getTxtFieldDescripcion().getText().equals("")) {

				if (vistaAddCar.getTxtFieldCilindrada().getText().equals("")) {
					cilindrada = null;
				} else {
					cilindrada = Double.parseDouble(vistaAddCar.getTxtFieldCilindrada().getText());
				}

				if (vistaAddCar.getTxtFieldDescripcion().getText().equals("")) {
					descripcion = null;
				} else {
					descripcion = vistaAddCar.getTxtFieldDescripcion().getText();
				}

				Car c = new Car(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaNac, estado,
						carnet, numPlazas, numPuertas);
				try {
					if (modelo.addCar(c)) {
						JOptionPane.showMessageDialog(null, "Car created correctly", "CORRECTLY",
								JOptionPane.INFORMATION_MESSAGE);
						vistaAddCar.dispose();
						recargarTablaCar();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				if (vistaAddCar.getTxtFieldCilindrada().getText().equals("")) {
					cilindrada = null;
				} else {
					cilindrada = Double.parseDouble(vistaAddCar.getTxtFieldCilindrada().getText());
				}

				if (vistaAddCar.getTxtFieldDescripcion().getText().equals("")) {
					descripcion = null;
				} else {
					descripcion = vistaAddCar.getTxtFieldDescripcion().getText();
				}

				Car c = new Car(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaNac, estado,
						carnet, numPlazas, numPuertas);
				try {
					if (modelo.addCar(c)) {
						JOptionPane.showMessageDialog(null, "Car created correctly", "CORRECTLY",
								JOptionPane.INFORMATION_MESSAGE);
						vistaAddCar.dispose();
						recargarTablaCar();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}

		}

	}

	private void vistaEditCar() {

		if (vista.getPanelCar().getTable().getSelectedRow() == -1) {

			JOptionPane.showMessageDialog(null, "You must select one row", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {

			if (!Controlador.isOpen(vistaEditCar)) {
				vistaEditCar = new vistaEditCar();
				Controlador.addJInternalFrame(vistaEditCar);
				Controlador.centrar(vistaEditCar);
			}

			String engine;

			engineCar = new ArrayList<String>();

			for (Car c : coches) {
				engine = c.getMotor();
				if (!engineCar.contains(engine)) {
					engineCar.add(engine);
				}
			}

			dcmec = new DefaultComboBoxModel();
			dcmec.addAll(engineCar);
			vistaEditCar.getComboBoxMotor().setModel(dcmec);

			vistaEditCar.getBtnAdd().addActionListener(this);
			vistaEditCar.getBtnCancel().addActionListener(this);
			vistaEditCar.getBtnAdd().setActionCommand("save edit car");
			vistaEditCar.getBtnCancel().setActionCommand("Cancel edit car");

			String[] carnetss = new String[] { "B" };

			Vector<String> carnets = new Vector<String>();
			for (String c : carnetss) {
				carnets.add(c);
			}

			DefaultComboBoxModel dcbCarnets = new DefaultComboBoxModel();
			dcbCarnets.addAll(carnets);
			dcbCarnets.setSelectedItem("B");
			vistaEditCar.getComboBoxCarnet().setModel(dcbCarnets);

			String[] estadoo = new String[] { "preparado", "taller", "baja", "alquilado", "reservado" };

			Vector<String> estado = new Vector<String>();
			for (String c : estadoo) {
				estado.add(c);
			}

			dcbEstadoAdd = new DefaultComboBoxModel();
			dcbEstadoAdd.addAll(estado);

			int[] rows = new int[vista.getPanelCar().getTable().getRowCount()];
			rows = vista.getPanelCar().getTable().getSelectedRows();
			ArrayList<Car> carEdit = new ArrayList<Car>();

			carEdit = ((MyTableModel) vista.getPanelCar().getTable().getModel()).get(rows);

			Car c = carEdit.get(0);

			vistaEditCar.getTxtFieldMarca().setText(c.getMarca());
			if (c.getCilindrada() == 0.0) {
				vistaEditCar.getTxtFieldCilindrada().setText("");
			} else {
				vistaEditCar.getTxtFieldCilindrada().setText(String.valueOf(c.getCilindrada()));
			}
			vistaEditCar.getTxtFieldColor().setText(c.getColor());
			vistaEditCar.getTxtFieldDate().setDate(c.getFechaadq());
			vistaEditCar.getTxtFieldDescripcion().setText(c.getDescripcion());
			vistaEditCar.getTxtFieldMatricula().setText(c.getMatricula());
			vistaEditCar.getTxtFieldNumPlazas().setText(String.valueOf(c.getNumPlazas()));
			vistaEditCar.getTxtFieldNumPuertas().setText(String.valueOf(c.getNumPuertas()));
			vistaEditCar.getTxtFieldPrecio().setText(String.valueOf(c.getPrecioDia()));
			dcbEstadoAdd.setSelectedItem(c.getEstado());
			vistaEditCar.getComboBoxEstado().setModel(dcbEstadoAdd);
			vistaEditCar.getComboBoxMotor().setSelectedItem(c.getMotor());

		}
	}

	private void vistaAddCar() {

		if (!Controlador.isOpen(vistaAddCar)) {

			vistaAddCar = new vistaAddCar();
			Controlador.addJInternalFrame(vistaAddCar);
			Controlador.centrar(vistaAddCar);

		}

		String[] carnetss = new String[] { "B" };

		Vector<String> carnets = new Vector<String>();
		for (String c : carnetss) {
			carnets.add(c);
		}

		dcbCarnetsAdd = new DefaultComboBoxModel();
		dcbCarnetsAdd.addAll(carnets);
		dcbCarnetsAdd.setSelectedItem("B");
		vistaAddCar.getComboBoxCarnet().setModel(dcbCarnetsAdd);

		String[] estadoo = new String[] { "preparado", "taller", "baja", "alquilado", "reservado" };

		Vector<String> estado = new Vector<String>();
		for (String c : estadoo) {
			estado.add(c);
		}

		dcbEstadoAdd = new DefaultComboBoxModel();
		dcbEstadoAdd.addAll(estado);
		dcbEstadoAdd.setSelectedItem("preparado");
		vistaAddCar.getComboBoxEstado().setModel(dcbEstadoAdd);

		String engine;

		engineCar = new ArrayList<String>();

		for (Car c : coches) {
			engine = c.getMotor();
			if (!engineCar.contains(engine)) {
				engineCar.add(engine);
			}
		}

		dcmec = new DefaultComboBoxModel();
		dcmec.addAll(engineCar);
		dcmec.setSelectedItem("gasolina");
		vistaAddCar.getComboBoxMotor().setModel(dcmec);

		vistaAddCar.getBtnAdd().addActionListener(this);
		vistaAddCar.getBtnCancel().addActionListener(this);
		vistaAddCar.getBtnAdd().setActionCommand("Add new car");
		vistaAddCar.getBtnCancel().setActionCommand("Cancel add car");

	}

	private void deleteBus() {

		if (vista.getPanelMinibus().getTable().getSelectedRow() == -1) {

			JOptionPane.showMessageDialog(null, "You must select at least one row", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {

			if (!Controlador.isOpen(vistaCarga)) {

				vistaCarga = new vistaCarga("Removing cars from the database");
				Controlador.addJInternalFrame(vistaCarga);
				Controlador.centrar(vistaCarga);

				vistaCarga.getBtnCancel().addActionListener(this);
				vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");

			}

			task4 = new SwingWorker<Boolean, Void>() {

				ArrayList<Minibus> buses;

				@Override
				protected Boolean doInBackground() throws Exception {

					int[] rows = new int[vista.getPanelMinibus().getTable().getRowCount()];
					rows = vista.getPanelMinibus().getTable().getSelectedRows();

					buses = ((MyTableModel) vista.getPanelMinibus().getTable().getModel()).get(rows);

					for (Minibus b : buses)
						modelo.deleteBus(b);

					return true;

				}

				@Override
				protected void done() {

					for (Minibus b : buses)
						((MyTableModel) vista.getPanelMinibus().getTable().getModel()).delete(b);
					vistaCarga.dispose();
				}
			};

			task4.execute();

		}

	}

	private void deleteTruck() {

		if (vista.getPanelTruck().getTable().getSelectedRow() == -1) {

			JOptionPane.showMessageDialog(null, "You must select at least one row", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {

			if (!Controlador.isOpen(vistaCarga)) {

				vistaCarga = new vistaCarga("Removing cars from the database");
				Controlador.addJInternalFrame(vistaCarga);
				Controlador.centrar(vistaCarga);

				vistaCarga.getBtnCancel().addActionListener(this);
				vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");

			}

			task3 = new SwingWorker<Boolean, Void>() {

				ArrayList<Truck> trucks;

				@Override
				protected Boolean doInBackground() throws Exception {

					int[] rows = new int[vista.getPanelTruck().getTable().getRowCount()];
					rows = vista.getPanelTruck().getTable().getSelectedRows();

					trucks = ((MyTableModel) vista.getPanelTruck().getTable().getModel()).get(rows);

					for (Truck t : trucks)
						modelo.deleteTruck(t);

					return true;

				}

				@Override
				protected void done() {

					for (Truck t : trucks)
						((MyTableModel) vista.getPanelTruck().getTable().getModel()).delete(t);
					vistaCarga.dispose();
				}
			};

			task3.execute();

		}

	}

	private void deleteVan() {

		if (vista.getPanelVan().getTable().getSelectedRow() == -1) {

			JOptionPane.showMessageDialog(null, "You must select at least one row", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {

			if (!Controlador.isOpen(vistaCarga)) {

				vistaCarga = new vistaCarga("Removing cars from the database");
				Controlador.addJInternalFrame(vistaCarga);
				Controlador.centrar(vistaCarga);

				vistaCarga.getBtnCancel().addActionListener(this);
				vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");

			}

			task2 = new SwingWorker<Boolean, Void>() {

				ArrayList<Van> vans;

				@Override
				protected Boolean doInBackground() throws Exception {

					int[] rows = new int[vista.getPanelVan().getTable().getRowCount()];
					rows = vista.getPanelVan().getTable().getSelectedRows();

					vans = ((MyTableModel) vista.getPanelVan().getTable().getModel()).get(rows);

					for (Van v : vans)
						modelo.deleteVan(v);

					return true;

				}

				@Override
				protected void done() {

					for (Van v : vans)
						((MyTableModel) vista.getPanelVan().getTable().getModel()).delete(v);
					vistaCarga.dispose();
				}
			};

			task2.execute();

		}

	}

	private void deleteCar() {

		if (vista.getPanelCar().getTable().getSelectedRow() == -1) {

			JOptionPane.showMessageDialog(null, "You must select at least one row", "ERROR", JOptionPane.ERROR_MESSAGE);

		} else {

			if (!Controlador.isOpen(vistaCarga)) {

				vistaCarga = new vistaCarga("Removing cars from the database");
				Controlador.addJInternalFrame(vistaCarga);
				Controlador.centrar(vistaCarga);

				vistaCarga.getBtnCancel().addActionListener(this);
				vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");

			}

			task = new SwingWorker<Boolean, Void>() {

				ArrayList<Car> cars;

				@Override
				protected Boolean doInBackground() throws Exception {

					int[] rows = new int[vista.getPanelCar().getTable().getRowCount()];
					rows = vista.getPanelCar().getTable().getSelectedRows();

					cars = ((MyTableModel) vista.getPanelCar().getTable().getModel()).get(rows);

					for (Car c : cars)
						modelo.deleteCar(c);

					return true;

				}

				@Override
				protected void done() {

					for (Car c : cars)
						((MyTableModel) vista.getPanelCar().getTable().getModel()).delete(c);
					vistaCarga.dispose();
				}
			};

			task.execute();

		}

	}

	private void recargarTablaCar() {

		if (!Controlador.isOpen(vistaCarga)) {
			vistaCarga = new vistaCarga("Updating the data from the database");
			Controlador.addJInternalFrame(vistaCarga);
			Controlador.centrar(vistaCarga);

			vistaCarga.getBtnCancel().addActionListener(this);
			vistaCarga.getBtnCancel().setActionCommand("Cancelar carga");
		}

		task5 = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {

				coches = modelo.getCoches();
				rellenarTablaCar();

				return null;

			}

			@Override
			protected void done() {

				vistaCarga.dispose();

			}
		};

		task5.execute();

	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {

		if (arg0.getDocument().getProperty("owner") == vista.getPanelCar().getTxtFieldModel()
				|| arg0.getDocument().getProperty("owner") == vista.getPanelCar().getTxtFieldRegistration()) {
			cambiarOrdenCar();
		} else if (arg0.getDocument().getProperty("owner") == vista.getPanelVan().getTxtFieldModel()
				|| arg0.getDocument().getProperty("owner") == vista.getPanelVan().getTxtFieldRegistration()) {
			cambiarOrdenVan();
		} else if (arg0.getDocument().getProperty("owner") == vista.getPanelTruck().getTxtFieldModel()
				|| arg0.getDocument().getProperty("owner") == vista.getPanelTruck().getTxtFieldRegistration()) {
			cambiarOrdenTruck();
		} else if (arg0.getDocument().getProperty("owner") == vista.getPanelMinibus().getTxtFieldModel()
				|| arg0.getDocument().getProperty("opwner") == vista.getPanelMinibus().getTxtFieldRegistration()) {
			cambiarOrdenBus();
		}

	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {

		if (arg0.getDocument().getProperty("owner") == vista.getPanelCar().getTxtFieldModel()
				|| arg0.getDocument().getProperty("owner") == vista.getPanelCar().getTxtFieldRegistration()) {
			cambiarOrdenCar();
		} else if (arg0.getDocument().getProperty("owner") == vista.getPanelVan().getTxtFieldModel()
				|| arg0.getDocument().getProperty("owner") == vista.getPanelVan().getTxtFieldRegistration()) {
			cambiarOrdenVan();
		} else if (arg0.getDocument().getProperty("owner") == vista.getPanelTruck().getTxtFieldModel()
				|| arg0.getDocument().getProperty("owner") == vista.getPanelTruck().getTxtFieldRegistration()) {
			cambiarOrdenTruck();
		} else if (arg0.getDocument().getProperty("owner") == vista.getPanelMinibus().getTxtFieldModel()
				|| arg0.getDocument().getProperty("opwner") == vista.getPanelMinibus().getTxtFieldRegistration()) {
			cambiarOrdenBus();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {

		if (arg0.getDocument().getProperty("owner") == vista.getPanelCar().getTxtFieldModel()
				|| arg0.getDocument().getProperty("owner") == vista.getPanelCar().getTxtFieldRegistration()) {
			cambiarOrdenCar();
		} else if (arg0.getDocument().getProperty("owner") == vista.getPanelVan().getTxtFieldModel()
				|| arg0.getDocument().getProperty("owner") == vista.getPanelVan().getTxtFieldRegistration()) {
			cambiarOrdenVan();
		} else if (arg0.getDocument().getProperty("owner") == vista.getPanelTruck().getTxtFieldModel()
				|| arg0.getDocument().getProperty("owner") == vista.getPanelTruck().getTxtFieldRegistration()) {
			cambiarOrdenTruck();
		} else if (arg0.getDocument().getProperty("owner") == vista.getPanelMinibus().getTxtFieldModel()
				|| arg0.getDocument().getProperty("opwner") == vista.getPanelMinibus().getTxtFieldRegistration()) {
			cambiarOrdenBus();
		}

	}

}
