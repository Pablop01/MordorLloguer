package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.alee.extended.date.WebDateField;

import javax.swing.JButton;

public class vistaRegistration extends JInternalFrame {
	private JTextField txtFieldMatricula;
	private JButton btnConfirm;
	private JLabel lblFechaInicio;
	private WebDateField txtFieldFInicio;
	private JLabel lblNewLabel;
	private WebDateField txtFieldFFin;
	/**
	 * Create the frame.
	 */
	public vistaRegistration() {
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));
		
		JLabel lblEnterTheVehicle = new JLabel("Matricula");
		getContentPane().add(lblEnterTheVehicle, "cell 1 0,alignx left");
		
		txtFieldMatricula = new JTextField();
		getContentPane().add(txtFieldMatricula, "cell 1 1,growx");
		txtFieldMatricula.setColumns(10);
		
		lblFechaInicio = new JLabel("Fecha Inicio");
		getContentPane().add(lblFechaInicio, "cell 1 3,alignx left");
		
		txtFieldFInicio = new WebDateField();
		getContentPane().add(txtFieldFInicio, "cell 1 4,growx,aligny top");
		
		lblNewLabel = new JLabel("Fecha Fin");
		getContentPane().add(lblNewLabel, "cell 1 6,alignx left,aligny baseline");
		
		txtFieldFFin = new WebDateField();
		getContentPane().add(txtFieldFFin, "cell 1 7,growx");
		
		btnConfirm = new JButton("Confirm");
		getContentPane().add(btnConfirm, "cell 1 8,alignx center");

	}
	public JTextField getTxtFieldMatricula() {
		return txtFieldMatricula;
	}
	public JButton getBtnConfirm() {
		return btnConfirm;
	}
	public WebDateField getTxtFieldFInicio() {
		return txtFieldFInicio;
	}
	public WebDateField getTxtFieldFFin() {
		return txtFieldFFin;
	}
}
