package com.mordor.mordorLloguer.model;

import java.util.List;

public class MyTruckTableModel extends MyVehicleTableModel<Truck>{

	public MyTruckTableModel(List<Truck> data) {
		super(data);
		
		HEADER.add("NumRuedas");
		HEADER.add("MMA");
	}
	
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 7: return data.get(row).getNumRuedas();
		case 8: return data.get(row).getMma();
		default : return super.getValueAt(row, col);

		}
		
	}
	
}
