package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Alquiler {

	private Integer idAlquiler;
	private Integer idFactura;
	private String matricula;
	private Date fechaInicio;
	private Date fechaFin;
	private Double precio;
	
	public Alquiler(Integer idAlquiler, Integer idFactura, String matricula, Date fechaInicio, Date fechaFin, Double precio) {
		super();
		this.idAlquiler = idAlquiler;
		this.idFactura = idFactura;
		this.matricula = matricula;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}

	public Integer getIdAlquiler() {
		return idAlquiler;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public String getMatricula() {
		return matricula;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public Double getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "Alquiler [idAlquiler=" + idAlquiler + ", idFactura=" + idFactura + ", matricula=" + matricula
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", precio=" + precio + "]";
	}
	
}
