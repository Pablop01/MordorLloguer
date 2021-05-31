package com.mordor.mordorLloguer.model;

import java.util.List;

public class MyVanTableModel extends MyVehicleTableModel<Van>{

	public MyVanTableModel(List<Van> data) {
		super(data);
		
		HEADER.add("MMA");

	}
	
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 7: return data.get(row).getMma();
		default : return super.getValueAt(row, col);

		}
		
	}
	
}
