package com.mordor.mordorLloguer.vistas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.laf.table.WebTable;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class JPVehicle extends JPanel{
	private JComboBox comboBoxLicense;
	private JComboBox comboBoxEngine;
	private JTextField txtFieldRegistration;
	private JTextField textField;
	private JButton btnCancel;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private WebTable table;
	private JPanel panelInferior;
	private JPanel panelSuperior;
	public JPVehicle() {
		
		panelSuperior = new JPanel();
		
		panelInferior = new JPanel();
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelInferior, 0, 0, Short.MAX_VALUE)
						.addComponent(panelSuperior, GroupLayout.PREFERRED_SIZE, 657, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 655, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelSuperior, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(panelInferior, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new WebTable();
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		
		btnCancel = new JButton("Cancel");
		
		btnEdit = new JButton("Edit");
		
		btnDelete = new JButton("Delete");
		
		btnAdd = new JButton("Add");
		GroupLayout gl_panelInferior = new GroupLayout(panelInferior);
		gl_panelInferior.setHorizontalGroup(
			gl_panelInferior.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelInferior.createSequentialGroup()
					.addContainerGap(287, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEdit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap())
		);
		gl_panelInferior.setVerticalGroup(
			gl_panelInferior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferior.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInferior.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnEdit)
						.addComponent(btnDelete)
						.addComponent(btnAdd))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panelInferior.setLayout(gl_panelInferior);
		panelSuperior.setLayout(new MigLayout("", "[][grow][][grow][grow][48.00][50.00][grow][][][grow]", "[]"));
		
		JLabel lblRegistration = new JLabel("Registration");
		panelSuperior.add(lblRegistration, "cell 0 0,alignx trailing");
		
		txtFieldRegistration = new JTextField();
		panelSuperior.add(txtFieldRegistration, "cell 1 0,growx");
		txtFieldRegistration.setColumns(10);
		
		JLabel lblModel = new JLabel("Model");
		panelSuperior.add(lblModel, "cell 2 0,alignx trailing");
		
		textField = new JTextField();
		panelSuperior.add(textField, "cell 3 0 4 1,growx");
		textField.setColumns(10);
		
		JLabel lblEngine = new JLabel("Engine");
		panelSuperior.add(lblEngine, "cell 7 0,alignx trailing");
		
		comboBoxEngine = new JComboBox();
		panelSuperior.add(comboBoxEngine, "cell 8 0,alignx left");
		
		JLabel lblLicense = new JLabel("License");
		panelSuperior.add(lblLicense, "cell 9 0,alignx trailing");
		
		comboBoxLicense = new JComboBox();
		panelSuperior.add(comboBoxLicense, "cell 10 0,alignx left");
		setLayout(groupLayout);
	}
	public WebTable getTable() {
		return table;
	}
	public void setTable(WebTable table) {
		this.table = table;
	}
	public JComboBox getComboBoxLicense() {
		return comboBoxLicense;
	}
	public JComboBox getComboBoxEngine() {
		return comboBoxEngine;
	}
	public JTextField getTxtFieldRegistration() {
		return txtFieldRegistration;
	}
	public JTextField getTxtFieldModel() {
		return textField;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JPanel getPanelInferior() {
		return panelInferior;
	}
	public JPanel getPanelSuperior() {
		return panelSuperior;
	}
	
}
