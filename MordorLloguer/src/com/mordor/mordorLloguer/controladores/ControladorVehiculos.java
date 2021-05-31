package com.mordor.mordorLloguer.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
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
import com.mordor.mordorLloguer.vistas.vistaTabla;
import com.mordor.mordorLloguer.vistas.vistaVehiculos;

public class ControladorVehiculos implements ActionListener, DocumentListener {

	private AlmacenDatosDB modelo;
	private vistaVehiculos vista;
	private vistaCarga vistaCarga;
	private vistaAdd vistaAdd;
	private ArrayList<Car> coches;
	private ArrayList<Minibus> buses;
	private ArrayList<Van> furgonetas;
	private ArrayList<Truck> camiones;
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

		vista.getPanelCar().getBtnAdd().setActionCommand("Add");
		vista.getPanelVan().getBtnAdd().setActionCommand("Add");
		vista.getPanelTruck().getBtnAdd().setActionCommand("Add");
		vista.getPanelMinibus().getBtnAdd().setActionCommand("Add");
		vista.getPanelCar().getBtnCancel().setActionCommand("Cancel");
		vista.getPanelVan().getBtnCancel().setActionCommand("Cancel");
		vista.getPanelTruck().getBtnCancel().setActionCommand("Cancel");
		vista.getPanelMinibus().getBtnCancel().setActionCommand("Cancel");
		vista.getPanelCar().getBtnDelete().setActionCommand("Delete");
		vista.getPanelVan().getBtnDelete().setActionCommand("Delete");
		vista.getPanelTruck().getBtnDelete().setActionCommand("Delete");
		vista.getPanelMinibus().getBtnDelete().setActionCommand("Delete");
		vista.getPanelCar().getBtnEdit().setActionCommand("Edit");
		vista.getPanelVan().getBtnEdit().setActionCommand("Edit");
		vista.getPanelTruck().getBtnEdit().setActionCommand("Edit");
		vista.getPanelMinibus().getBtnEdit().setActionCommand("Edit");
		
		//Panel Coche
		vista.getPanelCar().getTxtFieldModel().getDocument().addDocumentListener(this);
		vista.getPanelCar().getTxtFieldModel().getDocument().putProperty("owner", vista.getPanelCar().getTxtFieldModel());
		vista.getPanelCar().getTxtFieldRegistration().getDocument().addDocumentListener(this);
		vista.getPanelCar().getTxtFieldRegistration().getDocument().putProperty("owner", vista.getPanelCar().getTxtFieldRegistration());
		
		//Panel Bus
		vista.getPanelMinibus().getTxtFieldModel().getDocument().addDocumentListener(this);
		vista.getPanelMinibus().getTxtFieldModel().getDocument().putProperty("owner", vista.getPanelMinibus().getTxtFieldModel());
		vista.getPanelMinibus().getTxtFieldRegistration().getDocument().addDocumentListener(this);
		vista.getPanelMinibus().getTxtFieldRegistration().getDocument().putProperty("owner", vista.getPanelMinibus().getTxtFieldRegistration());
		
		//Panel Furgoneta
		vista.getPanelVan().getTxtFieldModel().getDocument().addDocumentListener(this);
		vista.getPanelVan().getTxtFieldModel().getDocument().putProperty("owner", vista.getPanelVan().getTxtFieldModel());
		vista.getPanelVan().getTxtFieldRegistration().getDocument().addDocumentListener(this);
		vista.getPanelVan().getTxtFieldRegistration().getDocument().putProperty("owner", vista.getPanelVan().getTxtFieldRegistration());
		
		//Panel camion
		vista.getPanelTruck().getTxtFieldModel().getDocument().addDocumentListener(this);
		vista.getPanelTruck().getTxtFieldModel().getDocument().putProperty("owner", vista.getPanelTruck().getTxtFieldModel());
		vista.getPanelTruck().getTxtFieldRegistration().getDocument().addDocumentListener(this);
		vista.getPanelTruck().getTxtFieldRegistration().getDocument().putProperty("owner", vista.getPanelTruck().getTxtFieldRegistration());
		
	}
	
//	private void cambiarOrdenCar() {
//
//		List<Car> temp = coches.stream()
//				.filter((c) -> c.getMatricula().toUpperCase().contains(vista.getPanelCar.gett().getText().toUpperCase()))
//				.filter((c) -> c.getNombre().toUpperCase().contains(vista.getTxtFieldName().getText().toUpperCase()))
//				.filter((c) -> c.getApellidos().toUpperCase()
//						.contains(vista.getTxtFieldSurname().getText().toUpperCase()))
//				.filter((c) -> String.valueOf(c.getCarnet()).toUpperCase()
//						.contains(vista.getComboBox().getSelectedItem().toString())
//						|| vista.getComboBox().getSelectedItem().toString().equals("ALL"))
//				.collect(Collectors.toList());
//
//		MyCustomerTableModel tabla = new MyCustomerTableModel(temp);
//		vista.getTable().setModel(tabla);
//
//	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		
		if(arg0.getDocument().getProperty("owner") == vista.getPanelCar().getTxtFieldModel() || 
				arg0.getDocument().getProperty("owner") == vista.getPanelCar().getTxtFieldRegistration()) {
//			cambiarOrdenCar();
		}
		
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		
		System.out.println("e");
		if(arg0.getDocument().getProperty("owner") == vista.getPanelCar().getTxtFieldModel()) {
			System.out.println("si");
		}
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		
		System.out.println("e");
		if(arg0.getDocument().getProperty("owner") == vista.getPanelCar().getTxtFieldModel()) {
			System.out.println("si");
		}
		
	}
	

	
}
