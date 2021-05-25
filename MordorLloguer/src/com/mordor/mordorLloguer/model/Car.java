package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Car extends Vehicle{

	private String matricula;
	private int numPlazas;
	private int numPuertas;
	
	
	public Car(String matricula, double precioDia, String marca, String descripcion, String color, String motor,
			double cilindrada, Date fechaadq, String estado, String carnet, int numPlazas, int numPuertas) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaadq, estado, carnet);
		
		this.numPlazas = numPlazas;
		this.numPuertas = numPuertas;
		this.matricula = super.getMatricula();
		
	}


	public int getNumPlazas() {
		return numPlazas;
	}


	public int getNumPuertas() {
		return numPuertas;
	}


	@Override
	public String toString() {
		return "Car [numPlazas=" + numPlazas + ", numPuertas=" + numPuertas + "]";
	}
	
}
