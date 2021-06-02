package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Minibus extends Vehicle{

	private String matricula;
	private int numPlazas;
	private double medida;
	
	
	public Minibus(String matricula, double precioDia, String marca, String descripcion, String color, String motor,
			Double cilindrada, Date fechaadq, String estado, char carnet, int numPlazas, double medida) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaadq, estado, carnet);
		this.matricula = super.getMatricula();
		this.numPlazas = numPlazas;
		this.medida = medida;
	}


	public String getMatricula() {
		return matricula;
	}


	public int getNumPlazas() {
		return numPlazas;
	}


	public double getMedida() {
		return medida;
	}


	@Override
	public String toString() {
		return "Minibus [matricula=" + matricula + ", numPlazas=" + numPlazas + ", medida=" + medida + "]";
	}

	
	
}
