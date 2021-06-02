package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Vehicle {

	private String matricula;
	private double precioDia;
	private String marca;
	private String descripcion;
	private String color;
	private String motor;
	private Double cilindrada;
	private Date fechaadq;
	private String estado;
	private char carnet;
	
	public Vehicle(String matricula, double precioDia, String marca, String descripcion, String color, String motor,
			Double cilindrada, Date fechaadq, String estado, char carnet) {
		super();
		this.matricula = matricula;
		this.precioDia = precioDia;
		this.marca = marca;
		this.descripcion = descripcion;
		this.color = color;
		this.motor = motor;
		this.cilindrada = cilindrada;
		this.fechaadq = fechaadq;
		this.estado = estado;
		this.carnet = carnet;
	}

	public Vehicle(String matricula, double precioDia, String marca, String color, String motor, Date fechaadq,
			String estado, char carnet) {
		super();
		this.matricula = matricula;
		this.precioDia = precioDia;
		this.marca = marca;
		this.color = color;
		this.motor = motor;
		this.fechaadq = fechaadq;
		this.estado = estado;
		this.carnet = carnet;
	}



	public String getMatricula() {
		return matricula;
	}

	public double getPrecioDia() {
		return precioDia;
	}

	public String getMarca() {
		return marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getColor() {
		return color;
	}

	public String getMotor() {
		return motor;
	}

	public Double getCilindrada() {
		return cilindrada;
	}

	public Date getFechaadq() {
		return fechaadq;
	}

	public String getEstado() {
		return estado;
	}

	public char getCarnet() {
		return carnet;
	}

	@Override
	public String toString() {
		return "Vehicle [matricula=" + matricula + ", precioDia=" + precioDia + ", marca=" + marca + ", descripcion="
				+ descripcion + ", color=" + color + ", motor=" + motor + ", cilindrada=" + cilindrada + ", fechaadq="
				+ fechaadq + ", estado=" + estado + ", carnet=" + carnet + "]";
	}
	
}
