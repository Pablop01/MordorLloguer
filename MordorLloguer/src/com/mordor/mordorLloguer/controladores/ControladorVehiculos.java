package com.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.SwingWorker;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.model.Vehicle;
import com.mordor.mordorLloguer.vistas.vistaAdd;
import com.mordor.mordorLloguer.vistas.vistaCarga;
import com.mordor.mordorLloguer.vistas.vistaTabla;
import com.mordor.mordorLloguer.vistas.vistaVehiculos;

public class ControladorVehiculos implements ActionListener {

	private AlmacenDatosDB modelo;
	private vistaVehiculos vista;
	private vistaCarga vistaCarga;
	private vistaAdd vistaAdd;
	private SwingWorker<Boolean, Void> task2;
	private SwingWorker<Void, Void> task;
	String[] header = { "DNI", "Nombre", "Apellidos", "Domicilio", "CP", "Email", "Nacimiento", "Cargo" };

	public ControladorVehiculos(AlmacenDatosDB modelo, vistaVehiculos vista) {

		this.modelo = modelo;
		this.vista = vista;
		inicializar();

	}

	private void inicializar() {
		
		System.out.println(modelo.getCoches());
		
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

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
