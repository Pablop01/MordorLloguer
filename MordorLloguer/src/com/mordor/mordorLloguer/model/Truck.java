package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Truck extends Vehicle{

	private String matricula;
	private int numRuedas;
	private int mma;
	
	public Truck(String matricula, double precioDia, String marca, String descripcion, String color, String motor,
			double cilindrada, Date fechaadq, String estado, char carnet, int numRuedas, int mma) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaadq, estado, carnet);
		this.matricula = super.getMatricula();
		this.numRuedas = numRuedas;
		this.mma = mma;
	}

	public String getMatricula() {
		return matricula;
	}

	public int getNumRuedas() {
		return numRuedas;
	}

	public int getMma() {
		return mma;
	}

	@Override
	public String toString() {
		return "Truck [matricula=" + matricula + ", numRuedas=" + numRuedas + ", mma=" + mma + "]";
	}

	
	
}
