package com.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MyInvoceTableModel extends MyTableModel<Alquiler> {

	private List<Vehicle> vehiculos;

	public MyInvoceTableModel(List alquileres, List vehiculos) {
		super(alquileres, new ArrayList<String>());
		List<String> headerr = Arrays.asList(new String[] { "MATRICULA", "MODELO", "PRECIO", "F.Inicio", "F.Fin" });
		this.vehiculos = vehiculos;
		HEADER = headerr;

		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getValueAt(int row, int col) {

		switch (col) {
		case 0:
			return data.get(row).getMatricula();
		case 1:
			return obtenerModelo(data.get(row).getMatricula());
		case 2:
			return data.get(row).getPrecio();
		case 3:
			return data.get(row).getFechaInicio();
		case 4:
			return data.get(row).getFechaFin();
		default:
			return null;
		}

	}

	private String obtenerModelo(String matricula) {

		String modelo = null;

		Optional<Vehicle> ve = vehiculos.stream()
				.filter(v -> v.getMatricula().toLowerCase().equals(matricula.toLowerCase()))
				.findFirst();
		
		if (ve.isPresent())
			modelo = ve.get().getMarca();

		return modelo;
	}

}
