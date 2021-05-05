package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class vistaPreferencias extends JInternalFrame {
	private JTextField txtFieldDriver;
	private JTextField txtFieldDireccion;
	private JTextField txtFieldUsuario;
	private JPasswordField passfieldContraseña;


	/**
	 * Create the frame.
	 */
	public vistaPreferencias() {
		setClosable(true);
		setFrameIcon(new ImageIcon(vistaPreferencias.class.getResource("/com/mordor/mordorLloguer/assets/settings.png")));
		setBounds(100, 100, 450, 261);
		setTitle("Propiedades Base de Datos");
		getContentPane().setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][][][][]"));
		
		JLabel lblDriver = new JLabel("Driver");
		getContentPane().add(lblDriver, "cell 1 1");
		
		txtFieldDriver = new JTextField();
		getContentPane().add(txtFieldDriver, "cell 3 1 2 1,growx");
		txtFieldDriver.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("URL");
		getContentPane().add(lblNewLabel, "cell 1 3");
		
		txtFieldDireccion = new JTextField();
		getContentPane().add(txtFieldDireccion, "cell 3 3 2 1,growx");
		txtFieldDireccion.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		getContentPane().add(lblUsuario, "cell 1 5");
		
		txtFieldUsuario = new JTextField();
		getContentPane().add(txtFieldUsuario, "cell 3 5 2 1,growx");
		txtFieldUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		getContentPane().add(lblContrasea, "cell 1 7");
		
		passfieldContraseña = new JPasswordField();
		getContentPane().add(passfieldContraseña, "cell 3 7 2 1,growx");
		
		JButton btnGuardar = new JButton("Guardar");
		getContentPane().add(btnGuardar, "cell 0 9 5 1,alignx center");

	}

	public JTextField getTxtFieldDriver() {
		return txtFieldDriver;
	}


	public JTextField getTxtFieldDireccion() {
		return txtFieldDireccion;
	}


	public JTextField getTxtFieldUsuario() {
		return txtFieldUsuario;
	}


	public JPasswordField getPassfieldContraseña() {
		return passfieldContraseña;
	}
	
}
