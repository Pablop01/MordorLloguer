package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.alee.extended.date.WebDateField;
import javax.swing.JPanel;
import javax.swing.JButton;

public class vistaNewInvoce extends JInternalFrame {
	private JTextField txtFieldIdCliente;
	private JTextField txtFieldMatricula;
	private WebDateField txtFieldFInicio;
	private WebDateField txtFieldFFin;
	private JButton btnAdd;
	/**
	 * Create the frame.
	 */
	public vistaNewInvoce() {
		setClosable(true);
		setBounds(100, 100, 386, 236);
		getContentPane().setLayout(new MigLayout("", "[][grow][][grow]", "[][][][][][][][][grow]"));
		
		JLabel lblCustomerId = new JLabel("ID Cliente");
		getContentPane().add(lblCustomerId, "cell 1 1");
		
		txtFieldIdCliente = new JTextField();
		getContentPane().add(txtFieldIdCliente, "cell 3 1,growx");
		txtFieldIdCliente.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Matricula");
		getContentPane().add(lblNewLabel, "cell 1 3");
		
		txtFieldMatricula = new JTextField();
		getContentPane().add(txtFieldMatricula, "cell 3 3,growx");
		txtFieldMatricula.setColumns(10);
		
		JLabel lblFInicio = new JLabel("F. Inicio");
		getContentPane().add(lblFInicio, "cell 1 5");
		
		txtFieldFInicio = new WebDateField();
		getContentPane().add(txtFieldFInicio, "cell 3 5,growx");
		
		JLabel lblFFin = new JLabel("F. Fin");
		getContentPane().add(lblFFin, "cell 1 7");
		
		txtFieldFFin = new WebDateField();
		getContentPane().add(txtFieldFFin, "cell 3 7,growx");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "cell 0 8 4 1,grow");
		
		btnAdd = new JButton("Add");
		panel.add(btnAdd);

	}
	public JTextField getTxtFieldIdCliente() {
		return txtFieldIdCliente;
	}
	public JTextField getTxtFieldMatricula() {
		return txtFieldMatricula;
	}
	public WebDateField getTxtFieldFInicio() {
		return txtFieldFInicio;
	}
	public WebDateField getTxtFieldFFin() {
		return txtFieldFFin;
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}

}
