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

public class JPVehicle extends JPanel{
	private JComboBox comboBoxLicense;
	private JComboBox comboBoxEngine;
	private JTextField txtFieldRegistration;
	private JTextField textField;
	private WebTable table;
	private JButton btnCancel;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnAdd;
	public JPVehicle() {
		
		JPanel panel = new JPanel();
		
		table = new WebTable();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
							.addComponent(table, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(42, Short.MAX_VALUE))
		);
		
		btnCancel = new JButton("Cancel");
		
		btnEdit = new JButton("Edit");
		
		btnDelete = new JButton("Delete");
		
		btnAdd = new JButton("Add");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
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
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnEdit)
						.addComponent(btnDelete)
						.addComponent(btnAdd))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(new MigLayout("", "[][grow][][grow][grow][48.00][50.00][grow][][][grow]", "[]"));
		
		JLabel lblRegistration = new JLabel("Registration");
		panel.add(lblRegistration, "cell 0 0,alignx trailing");
		
		txtFieldRegistration = new JTextField();
		panel.add(txtFieldRegistration, "cell 1 0,growx");
		txtFieldRegistration.setColumns(10);
		
		JLabel lblModel = new JLabel("Model");
		panel.add(lblModel, "cell 2 0,alignx trailing");
		
		textField = new JTextField();
		panel.add(textField, "cell 3 0 4 1,growx");
		textField.setColumns(10);
		
		JLabel lblEngine = new JLabel("Engine");
		panel.add(lblEngine, "cell 7 0,alignx trailing");
		
		comboBoxEngine = new JComboBox();
		panel.add(comboBoxEngine, "cell 8 0,alignx left");
		
		JLabel lblLicense = new JLabel("License");
		panel.add(lblLicense, "cell 9 0,alignx trailing");
		
		comboBoxLicense = new JComboBox();
		panel.add(comboBoxLicense, "cell 10 0,alignx left");
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
	
	
	
}
