package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.Arrays;

public class Cliente {

	private String DNI;
	private String nombre;
	private String apellidos;
	private String domicilio;
	private String CP;
	private String email;
	private Date fechaNac;
	private char carnet;
	private byte[] foto;	

	public Cliente(String dNI, String nombre, String apellidos, String domicilio, String cP, String email,
			Date fechaNac, char carnet, byte[] foto) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		CP = cP;
		this.email = email;
		this.fechaNac = fechaNac;
		this.carnet = carnet;
		this.foto = foto;
	}

	public String getDNI() {
		return DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getCP() {
		return CP;
	}

	public String getEmail() {
		return email;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public byte[] getFoto() {
		return foto;
	}

	public char getCarnet() {
		return carnet;
	}

	@Override
	public String toString() {
		return "Cliente [DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", domicilio=" + domicilio
				+ ", CP=" + CP + ", email=" + email + ", fechaNac=" + fechaNac + ", carnet=" + carnet + ", foto="
				+ Arrays.toString(foto) + "]";
	}
	
}
