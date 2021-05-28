package com.mordor.mordorLloguer.model;

import java.util.List;

public class MyCarTableModel extends MyVehicleTableModel<Car> {

	public MyCarTableModel(List<Car> data) {
		super(data);
		
		HEADER.add("NumPlazas");
		HEADER.add("NumPuertas");
	}
	
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 7: return data.get(row).getNumPlazas();
		case 8: return data.get(row).getNumPuertas();
		default : return super.getValueAt(row, col);

		}
		
	}

}

