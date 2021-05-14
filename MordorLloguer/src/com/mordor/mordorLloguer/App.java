package com.mordor.mordorLloguer;

import java.awt.EventQueue;

import com.alee.laf.WebLookAndFeel;
import com.mordor.mordorLloguer.config.MyConfig;
import com.mordor.mordorLloguer.controladores.Controlador;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.OracleDataBase;
import com.mordor.mordorLloguer.vistas.vistaPrincipal;

public class App {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebLookAndFeel.install();
					vistaPrincipal frame = new vistaPrincipal();
					AlmacenDatosDB modelo = new OracleDataBase();
					Controlador controlador = new Controlador(frame,modelo);
					controlador.go();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
