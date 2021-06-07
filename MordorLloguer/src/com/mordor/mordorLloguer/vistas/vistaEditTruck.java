package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.extended.date.WebDateField;

import javax.swing.ImageIcon;

public class vistaEditTruck extends JInternalFrame {
	private JTextField txtFieldCilindrada;
	private WebDateField txtFieldDate;
	private JTextField txtFieldNumPlazas;
	private JTextField txtFieldNumPuertas;
	private JTextField txtFieldMatricula;
	private JTextField txtFieldPrecio;
	private JTextField txtFieldMarca;
	private JTextField txtFieldDescripcion;
	private JTextField txtFieldColor;
	private JButton btnAdd;
	private JButton btnCancel;
	private JComboBox comboBoxEstado;
	private JComboBox comboBoxCarnet;
	private JComboBox comboBoxMotor;

	/**
	 * Create the frame.
	 */
	public vistaEditTruck() {
		setFrameIcon(new ImageIcon(vistaEditTruck.class.getResource("/com/mordor/mordorLloguer/assets/car.png")));
		setClosable(true);
		setBounds(100, 100, 656, 325);
		getContentPane().setLayout(new MigLayout("", "[90.00,left][grow][][][left][grow]", "[][][][][][][][][][][][grow]"));
		
		JLabel lblMatricula = new JLabel("Matricula:*");
		getContentPane().add(lblMatricula, "cell 0 0,alignx left");
		
		txtFieldMatricula = new JTextField();
		getContentPane().add(txtFieldMatricula, "cell 1 0,growx");
		txtFieldMatricula.setColumns(10);
		
		JLabel lblCilindrada = new JLabel("Cilindrada:");
		getContentPane().add(lblCilindrada, "cell 4 0,alignx trailing");
		
		txtFieldCilindrada = new JTextField();
		getContentPane().add(txtFieldCilindrada, "cell 5 0,growx");
		txtFieldCilindrada.setColumns(10);
		
		JLabel lblPrecioDia = new JLabel("Precio Dia:*");
		getContentPane().add(lblPrecioDia, "cell 0 2,alignx left");
		
		txtFieldPrecio = new JTextField();
		getContentPane().add(txtFieldPrecio, "cell 1 2,growx");
		txtFieldPrecio.setColumns(10);
		
		JLabel lblFechaAdq = new JLabel("Fecha Adq:*");
		getContentPane().add(lblFechaAdq, "cell 4 2,alignx trailing");
		
		txtFieldDate = new WebDateField();
		getContentPane().add(txtFieldDate, "cell 5 2,growx,aligny top");
		
		JLabel lblMarca = new JLabel("Marca:*");
		getContentPane().add(lblMarca, "cell 0 4,alignx left");
		
		txtFieldMarca = new JTextField();
		getContentPane().add(txtFieldMarca, "cell 1 4,growx");
		txtFieldMarca.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:*");
		getContentPane().add(lblEstado, "cell 4 4,alignx trailing");
		
		comboBoxEstado = new JComboBox();
		getContentPane().add(comboBoxEstado, "cell 5 4,alignx left");
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		getContentPane().add(lblDescripcin, "cell 0 6,alignx left");
		
		txtFieldDescripcion = new JTextField();
		getContentPane().add(txtFieldDescripcion, "cell 1 6,growx");
		txtFieldDescripcion.setColumns(10);
		
		JLabel lblCarnet = new JLabel("Carnet:*");
		getContentPane().add(lblCarnet, "cell 4 6,alignx trailing");
		
		comboBoxCarnet = new JComboBox();
		getContentPane().add(comboBoxCarnet, "cell 5 6,alignx left");
		
		JLabel lblColor = new JLabel("Color:*");
		getContentPane().add(lblColor, "cell 0 8,alignx left");
		
		txtFieldColor = new JTextField();
		getContentPane().add(txtFieldColor, "cell 1 8,growx");
		txtFieldColor.setColumns(10);
		
		JLabel lblNumplazas = new JLabel("NumRuedas:*");
		getContentPane().add(lblNumplazas, "cell 4 8,alignx trailing");
		
		txtFieldNumPlazas = new JTextField();
		getContentPane().add(txtFieldNumPlazas, "cell 5 8,growx");
		txtFieldNumPlazas.setColumns(10);
		
		JLabel lblMotor = new JLabel("Motor:*");
		getContentPane().add(lblMotor, "cell 0 10,alignx left");
		
		comboBoxMotor = new JComboBox();
		getContentPane().add(comboBoxMotor, "cell 1 10,growx");
		
		JLabel lblNumpuertas = new JLabel("MMA:*");
		getContentPane().add(lblNumpuertas, "cell 4 10,alignx trailing");
		
		txtFieldNumPuertas = new JTextField();
		getContentPane().add(txtFieldNumPuertas, "cell 5 10,growx");
		txtFieldNumPuertas.setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "cell 0 11 6 1,grow");
		
		btnAdd = new JButton("Edit");
		
		btnCancel = new JButton("Cancel");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(261, Short.MAX_VALUE)
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancel)
					.addGap(210))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel)))
		);
		panel.setLayout(gl_panel);

	}

	public JTextField getTxtFieldCilindrada() {
		return txtFieldCilindrada;
	}

	public WebDateField getTxtFieldDate() {
		return txtFieldDate;
	}

	public JTextField getTxtFieldNumPlazas() {
		return txtFieldNumPlazas;
	}

	public JTextField getTxtFieldNumPuertas() {
		return txtFieldNumPuertas;
	}

	public JTextField getTxtFieldMatricula() {
		return txtFieldMatricula;
	}

	public JTextField getTxtFieldPrecio() {
		return txtFieldPrecio;
	}

	public JTextField getTxtFieldMarca() {
		return txtFieldMarca;
	}

	public JTextField getTxtFieldDescripcion() {
		return txtFieldDescripcion;
	}

	public JTextField getTxtFieldColor() {
		return txtFieldColor;
	}

	public JComboBox getComboBoxMotor() {
		return comboBoxMotor;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JComboBox getComboBoxEstado() {
		return comboBoxEstado;
	}

	public JComboBox getComboBoxCarnet() {
		return comboBoxCarnet;
	}
}
