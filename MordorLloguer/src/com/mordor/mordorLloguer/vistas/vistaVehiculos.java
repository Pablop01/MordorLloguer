package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class vistaVehiculos extends JInternalFrame {
	
	private JPVehicle panelCar;
	private JPVehicle panelVan;
	private JPVehicle panelTruck;
	private JPVehicle panelMinibus;
	/**
	 * Create the frame.
	 */
	public vistaVehiculos() {
		setFrameIcon(new ImageIcon(vistaVehiculos.class.getResource("/com/mordor/mordorLloguer/assets/car.png")));
		setClosable(true);
		setBounds(100, 100, 705, 500);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		panelCar = new JPVehicle();
		tabbedPane.addTab("Car", null, panelCar, null);
		panelVan = new JPVehicle();
		tabbedPane.addTab("Van", null, panelVan, null);
		panelTruck = new JPVehicle();
		tabbedPane.addTab("Truck", null, panelTruck, null);
		panelMinibus = new JPVehicle();
		tabbedPane.addTab("Minibus", null, panelMinibus, null);

	}
	public JPVehicle getPanelCar() {
		return panelCar;
	}
	public JPVehicle getPanelVan() {
		return panelVan;
	}
	public JPVehicle getPanelTruck() {
		return panelTruck;
	}
	public JPVehicle getPanelMinibus() {
		return panelMinibus;
	}
	
}
