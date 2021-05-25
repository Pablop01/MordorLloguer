package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Van extends Vehicle{

	private String matricula;
	private double mma;
	
	public Van(String matricula, double precioDia, String marca, String descripcion, String color, String motor,
			double cilindrada, Date fechaadq, String estado, String carnet, double mma ) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaadq, estado, carnet);
		
		this.mma = mma;
		this.matricula = super.getMatricula();
		
	}

	
}
