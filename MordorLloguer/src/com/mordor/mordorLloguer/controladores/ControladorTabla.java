package com.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.vistas.vistaTabla;


public class ControladorTabla implements ActionListener{

	private AlmacenDatosDB modelo;
	private vistaTabla vista;
	
	public ControladorTabla(AlmacenDatosDB modelo, vistaTabla vista) {
		
		this.modelo = modelo;
		this.vista = vista;
		inicializar();

	}



	private void inicializar() {
		
		vista.getBtnAdd().addActionListener(this);
		vista.getBtnClose().addActionListener(this);
		vista.getBtnDelete().addActionListener(this);
		
		vista.getBtnAdd().setActionCommand("Add");
		vista.getBtnClose().setActionCommand("Close");
		vista.getBtnDelete().setActionCommand("Delete");
		
	}

	public void go() {
		
		vista.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String comando = arg0.getActionCommand();
		
		if(comando.equals("Add")) {
			
		}
		
	}
	
}
