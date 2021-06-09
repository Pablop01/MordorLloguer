package com.mordor.mordorLloguer.controladores;

import com.mordor.mordorLloguer.vistas.JIFInvoice;
import com.mordor.mordorLloguer.vistas.vistaNewInvoce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import com.mordor.mordorLloguer.model.*;

public class ControladorFacturas implements ActionListener {

	private AlmacenDatosDB modelo;
	private JIFInvoice vista;
	private vistaNewInvoce vistaNewInvoce;
	private ArrayList<Vehicle> vehiculos;
	private ArrayList<Car> coches;
	private ArrayList<Minibus> buses;
	private ArrayList<Van> furgonetas;
	private ArrayList<Truck> camiones;
	private ArrayList<Factura> facturas;
	private ArrayList<Alquiler> alquileres;
	private ArrayList<Alquiler> alquileresMostrar;
	private ArrayList<Cliente> clientes;
	private Double precioBase;
	private Double iva = 1.21;
	private int contador;

	public ControladorFacturas(AlmacenDatosDB modelo, JIFInvoice vista) throws ParseException {
		super();
		this.modelo = modelo;
		this.vista = vista;
		this.coches = modelo.getCoches();
		this.buses = modelo.getMinibus();
		this.furgonetas = modelo.getFurgonetas();
		this.camiones = modelo.getCamiones();
		this.facturas = modelo.getFacturas();
		this.alquileres = modelo.getAlquileres();
		this.clientes = modelo.getClientes();
		vehiculos = new ArrayList<Vehicle>();
		vehiculos.addAll(coches);
		vehiculos.addAll(camiones);
		vehiculos.addAll(buses);
		vehiculos.addAll(furgonetas);
		contador = 0;
		inicializar();

	}

	private void inicializar() {
		
		vista.getBtnCerrar().addActionListener(this);
		vista.getBtnNextInvoice().addActionListener(this);
		vista.getBtnPreviousInvoice().addActionListener(this);
		vista.getBtnNewInvoce().addActionListener(this);
		
		vista.getBtnCerrar().setActionCommand("cerrar vista");
		vista.getBtnNextInvoice().setActionCommand("next");
		vista.getBtnPreviousInvoice().setActionCommand("previous");
		vista.getBtnNewInvoce().setActionCommand("newinvoce");
		
		rellenarCampos(contador);
		
	}

	private void rellenarCampos(int contador) {
		
		precioBase = 0.0;
		
		if(contador == 0) {
			vista.getBtnNextInvoice().setEnabled(true);
			vista.getBtnPreviousInvoice().setEnabled(false);
		}else if(contador == facturas.size()-1) {
			vista.getBtnPreviousInvoice().setEnabled(true);
			vista.getBtnNextInvoice().setEnabled(false);
		}else {
			vista.getBtnPreviousInvoice().setEnabled(true);
			vista.getBtnNextInvoice().setEnabled(true);
		}
		
		boolean encontrado = false;
		Factura f = facturas.get(contador);
		int id = f.getClienteId();
		int idFactura = f.getIdFactura();
		int i = 0;
		Cliente c = null;
		alquileresMostrar = new ArrayList<Alquiler>();
		while(!encontrado) {
			if(clientes.get(i).getId() == id) {
				c = clientes.get(i);
				encontrado = true;
			}
			i++;
		}
		
		for(Alquiler a : alquileres) {
			if(a.getIdFactura() == idFactura) {
				this.alquileresMostrar.add(a);
				precioBase += a.getPrecio();
			}
		} 
		
		if(encontrado) {
			DecimalFormat formato1 = new DecimalFormat("#.00");
			vista.getTxtFieldNombre().setText(c.getNombre());
			vista.getTxtFieldApellidos().setText(c.getApellidos());
			vista.getTxtFieldDNI().setText(c.getDNI());
			vista.getTxtFieldNumeroFactura().setText(String.valueOf(f.getIdFactura()));
			vista.getWebDateFieldFechaFactura().setDate(f.getFehcaFac());
			vista.getTxtFieldSuma().setText(String.valueOf(formato1.format(precioBase)));
			vista.getTxtFieldTotal().setText(String.valueOf(formato1.format(precioBase*iva)));
			vista.getTxtFieldImpuestos().setText(String.valueOf(formato1.format((precioBase*iva)-precioBase)));
			
			MyInvoceTableModel tabla = new MyInvoceTableModel(alquileresMostrar,vehiculos);
			
			vista.getTableDetalles().setModel(tabla);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String comando = arg0.getActionCommand();
		
		if(comando.equals("cerrar vista")) {
			cerrarVista();
		}else if(comando.equals("next")) {
			contador++;
			rellenarCampos(contador);
		}else if (comando.equals("previous")) {
			contador--;
			rellenarCampos(contador);
		}else if (comando.equals("newinvoce")) {
			vistaNewInvoce();
		}else if (comando.equals("addnew")) {
			try {
				newInvoce();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void newInvoce() throws SQLException {
		
		boolean idEncontrado = false;
		boolean mEncontrada = false;
		Cliente c;
		String dni = "";
		
		if (vistaNewInvoce.getTxtFieldFInicio().getDate() == null || vistaNewInvoce.getTxtFieldFFin().getDate() == null 
			|| vistaNewInvoce.getTxtFieldIdCliente().getText().equals("") || vistaNewInvoce.getTxtFieldMatricula().getText().equals("")) {
			
			JOptionPane.showMessageDialog(null, "You must fill in all the fields ", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			
		}else {
			
			Optional<Cliente> cl = clientes.stream()
					.filter(c1 -> c1.getDNI().toLowerCase().equals(vistaNewInvoce.getTxtFieldIdCliente().getText().toLowerCase()))
					.findFirst();
			
			if (cl.isPresent()) {
				idEncontrado = true;
				dni = cl.get().getDNI();
			}
			
			Optional<Vehicle> ma = vehiculos.stream()
					.filter(v -> v.getMatricula().toLowerCase().equals(vistaNewInvoce.getTxtFieldMatricula().getText().toLowerCase()))
					.findFirst();
			
			if (ma.isPresent()) {
				mEncontrada = true;
			}
			
			
			if(!idEncontrado) {
				JOptionPane.showMessageDialog(null, "There is no client with that ID", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}else if(!mEncontrada) {
				JOptionPane.showMessageDialog(null, "There is no vehicle with that registration", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}else if (idEncontrado && mEncontrada) {
				
				String matricula;
				Date fechaInicio;
				Date fechaFin;
				
				matricula = vistaNewInvoce.getTxtFieldMatricula().getText();
				fechaInicio = new java.sql.Date(vistaNewInvoce.getTxtFieldFInicio().getDate().getTime());
				fechaFin = new java.sql.Date(vistaNewInvoce.getTxtFieldFFin().getDate().getTime());
				
				Alquiler a = new Alquiler(null,null,matricula,fechaInicio,fechaFin,0.0);
				
				if(modelo.addAlquiler(a, dni.toUpperCase())) {
					System.out.println("si");
				}
				
			}
			
		}
		
	}

	private void vistaNewInvoce() {
		
		if (!Controlador.isOpen(vistaNewInvoce)) {

			vistaNewInvoce = new vistaNewInvoce();
			Controlador.addJInternalFrame(vistaNewInvoce);
			Controlador.centrar(vistaNewInvoce);
			
			vistaNewInvoce.getBtnAdd().addActionListener(this);
			vistaNewInvoce.getBtnAdd().setActionCommand("addnew");
			
		}
		
	}

	private void cerrarVista() {
		
		int opcion = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Warning!",
				JOptionPane.YES_NO_OPTION);
		if (opcion == 0) {
			vista.dispose();
		}
		
	}
	
	
	
}
