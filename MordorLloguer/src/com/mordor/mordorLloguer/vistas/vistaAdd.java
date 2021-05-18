package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.alee.extended.date.WebDateField;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class vistaAdd extends JInternalFrame {
	private JTextField txtFieldDNI;
	private JTextField txtFieldName;
	private JTextField txtFieldSurname;
	private JTextField txtFieldAdress;
	private JTextField txtFieldCP;
	private JTextField txtFieldEmail;
	private WebDateField txtFieldBirthday;
	private JPasswordField passwordField;
	private JComboBox comboBox;
	private JButton btnAdd;
	/**
	 * Create the frame.
	 */
	public vistaAdd() {
		setClosable(true);
		setFrameIcon(new ImageIcon(vistaAdd.class.getResource("/com/mordor/mordorLloguer/assets/user.png")));
		setBounds(100, 100, 340, 438);
		getContentPane().setLayout(new MigLayout("", "[][90.00,left][grow]", "[][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblDni = new JLabel("DNI:*");
		getContentPane().add(lblDni, "cell 1 0,alignx left");
		
		txtFieldDNI = new JTextField();
		getContentPane().add(txtFieldDNI, "cell 2 0,growx");
		txtFieldDNI.setColumns(10);
		
		JLabel lblName = new JLabel("Name:*");
		getContentPane().add(lblName, "cell 1 2,alignx left");
		
		txtFieldName = new JTextField();
		getContentPane().add(txtFieldName, "cell 2 2,growx");
		txtFieldName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname:*");
		getContentPane().add(lblSurname, "cell 1 4,alignx left");
		
		txtFieldSurname = new JTextField();
		getContentPane().add(txtFieldSurname, "cell 2 4,growx");
		txtFieldSurname.setColumns(10);
		
		JLabel lblAdress = new JLabel("Adress:");
		getContentPane().add(lblAdress, "cell 1 6,alignx left");
		
		txtFieldAdress = new JTextField();
		getContentPane().add(txtFieldAdress, "cell 2 6,growx");
		txtFieldAdress.setColumns(10);
		
		JLabel lblCp = new JLabel("CP:");
		getContentPane().add(lblCp, "cell 1 8,alignx left");
		
		txtFieldCP = new JTextField();
		getContentPane().add(txtFieldCP, "cell 2 8,growx");
		txtFieldCP.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:*");
		getContentPane().add(lblEmail, "cell 1 10,alignx left");
		
		txtFieldEmail = new JTextField();
		getContentPane().add(txtFieldEmail, "cell 2 10,growx");
		txtFieldEmail.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Birthday:*");
		getContentPane().add(lblBirthday, "cell 1 12,alignx left");
		
		txtFieldBirthday = new WebDateField();
		getContentPane().add(txtFieldBirthday, "cell 2 12,growx");
		
		JLabel lblJobPosition = new JLabel("Job Position:*");
		getContentPane().add(lblJobPosition, "cell 1 14,alignx left");
		
		comboBox = new JComboBox();
		getContentPane().add(comboBox, "cell 2 14,growx");
		
		JLabel lblPassword = new JLabel("Password:*");
		getContentPane().add(lblPassword, "cell 1 16,alignx left");
		
		passwordField = new JPasswordField();
		getContentPane().add(passwordField, "cell 2 16,growx");
		
		btnAdd = new JButton("Add");
		getContentPane().add(btnAdd, "cell 2 18,alignx right");

	}
	public JTextField getTxtFieldDNI() {
		return txtFieldDNI;
	}
	public JTextField getTxtFieldName() {
		return txtFieldName;
	}
	public JTextField getTxtFieldSurname() {
		return txtFieldSurname;
	}
	public JTextField getTxtFieldAdress() {
		return txtFieldAdress;
	}
	public JTextField getTxtFieldCP() {
		return txtFieldCP;
	}
	public JTextField getTxtFieldEmail() {
		return txtFieldEmail;
	}
	public WebDateField getTxtFieldBirthday() {
		return txtFieldBirthday;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}
	
	

}
