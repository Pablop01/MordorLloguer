package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class vistaAddCliente extends JInternalFrame {
	private JTextField txtFieldEmail;
	private JTextField txtFieldBirthday;
	private JTextField txtFieldCP;
	private JTextField txtFieldDNI;
	private JTextField txtFieldName;
	private JTextField txtFieldSurname;
	private JTextField txtFieldAdress;
	/**
	 * Create the frame.
	 */
	public vistaAddCliente() {
		setClosable(true);
		setBounds(100, 100, 538, 461);
		getContentPane().setLayout(new MigLayout("", "[][][grow][][][grow]", "[][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(vistaTablaClientes.class.getResource("/com/mordor/mordorLloguer/assets/userImg.png")));
		getContentPane().add(lblNewLabel, "cell 0 0 6 3,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("Click on the picture to change it");
		getContentPane().add(lblNewLabel_1, "cell 0 3 6 1,alignx center");
		
		JLabel lblDni = new JLabel("DNI: ");
		getContentPane().add(lblDni, "cell 0 5,alignx left");
		
		txtFieldDNI = new JTextField();
		getContentPane().add(txtFieldDNI, "cell 1 5,growx");
		txtFieldDNI.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		getContentPane().add(lblEmail, "cell 3 5,alignx left");
		
		txtFieldEmail = new JTextField();
		getContentPane().add(txtFieldEmail, "cell 4 5,growx");
		txtFieldEmail.setColumns(10);
		
		JLabel lblName = new JLabel("Name: ");
		getContentPane().add(lblName, "cell 0 7,alignx left");
		
		txtFieldName = new JTextField();
		getContentPane().add(txtFieldName, "cell 1 7,growx");
		txtFieldName.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Birthday:");
		getContentPane().add(lblBirthday, "cell 3 7,alignx left");
		
		txtFieldBirthday = new JTextField();
		getContentPane().add(txtFieldBirthday, "cell 4 7,growx");
		txtFieldBirthday.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname:");
		getContentPane().add(lblSurname, "cell 0 9,alignx left");
		
		txtFieldSurname = new JTextField();
		getContentPane().add(txtFieldSurname, "cell 1 9,growx");
		txtFieldSurname.setColumns(10);
		
		JLabel lblDrivingLicense = new JLabel("Driving License:");
		getContentPane().add(lblDrivingLicense, "cell 3 9,alignx left");
		
		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox, "cell 4 9,growx");
		
		JLabel lblAdress = new JLabel("Adress:");
		getContentPane().add(lblAdress, "cell 0 11,alignx left");
		
		txtFieldAdress = new JTextField();
		getContentPane().add(txtFieldAdress, "cell 1 11,growx");
		txtFieldAdress.setColumns(10);
		
		JLabel lblCp = new JLabel("CP:");
		getContentPane().add(lblCp, "cell 3 11,alignx left");
		
		txtFieldCP = new JTextField();
		getContentPane().add(txtFieldCP, "cell 4 11,growx");
		txtFieldCP.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		getContentPane().add(btnAdd, "cell 3 13");
		
		JButton btnNewButton_1 = new JButton("New button");
		getContentPane().add(btnNewButton_1, "cell 4 13");

	}

}
