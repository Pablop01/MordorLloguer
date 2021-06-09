package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Factura {

	private Integer idFactura;
	private Date fechaFac;
	private Double importeBase;
	private Double importeIva;
	private int clienteId;
	
	public Factura(Integer idFactura, Date fehcaFac, Double importeBase, Double importeIva, int clienteId) {
		super();
		this.idFactura = idFactura;
		this.fechaFac = fehcaFac;
		this.importeBase = importeBase;
		this.importeIva = importeIva;
		this.clienteId = clienteId;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public Date getFehcaFac() {
		return fechaFac;
	}

	public Double getImporteBase() {
		return importeBase;
	}

	public Double getImporteIva() {
		return importeIva;
	}

	public int getClienteId() {
		return clienteId;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", fehcaFac=" + fechaFac + ", importeBase=" + importeBase
				+ ", importeIva=" + importeIva + ", clienteId=" + clienteId + "]";
	}
	
}
