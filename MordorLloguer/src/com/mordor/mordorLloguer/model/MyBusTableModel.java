package com.mordor.mordorLloguer.model;

import java.util.List;

public class MyBusTableModel extends MyVehicleTableModel<Minibus>{

	public MyBusTableModel(List<Minibus> data) {
		super(data);
		
		HEADER.add("NumPlazas");
		HEADER.add("MEDIDA");
	}
	
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 7: return data.get(row).getNumPlazas();
		case 8: return data.get(row).getMedida();
		default : return super.getValueAt(row, col);

		}
		
	}
	
}
