package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.alee.extended.date.WebDateField;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class vistaEdit extends JInternalFrame {
	private JTextField txtFieldEmail;
	private WebDateField txtFieldBirthday;
	private JTextField txtFieldCP;
	private JTextField txtFieldDNI;
	private JTextField txtFieldName;
	private JTextField txtFieldSurname;
	private JTextField txtFieldAdress;
	private JLabel lblFoto;
	private byte[] image;
	private JButton btnEdit;
	private JButton btnCancel;
	private JComboBox comboBox;

	/**
	 * Create the frame.
	 */
	public vistaEdit() {
		setFrameIcon(new ImageIcon(vistaEdit.class.getResource("/com/mordor/mordorLloguer/assets/person.png")));
		setClosable(true);
		setBounds(100, 100, 537, 423);
		getContentPane().setLayout(new MigLayout("", "[][][grow][][][grow]", "[][][][][][][][][][][][][][]"));

		lblFoto = new JLabel(
				new ImageIcon(vistaTablaClientes.class.getResource("/com/mordor/mordorLloguer/assets/userImg.png")));
		getContentPane().add(lblFoto, "cell 0 0 6 3,alignx center");

		JLabel lblNewLabel_1 = new JLabel("Click on the picture to change it");
		getContentPane().add(lblNewLabel_1, "cell 0 3 6 1,alignx center");

		JLabel lblDni = new JLabel("DNI: ");
		getContentPane().add(lblDni, "cell 0 5,alignx left");

		txtFieldDNI = new JTextField();
		getContentPane().add(txtFieldDNI, "cell 1 5,growx");
		txtFieldDNI.setColumns(10);

		JLabel lblEmail = new JLabel("Email: ");
		getContentPane().add(lblEmail, "cell 3 5,alignx left");

		txtFieldEmail = new JTextField();
		getContentPane().add(txtFieldEmail, "cell 4 5,growx");
		txtFieldEmail.setColumns(10);

		JLabel lblName = new JLabel("Name: ");
		getContentPane().add(lblName, "cell 0 7,alignx left");

		txtFieldName = new JTextField();
		getContentPane().add(txtFieldName, "cell 1 7,growx");
		txtFieldName.setColumns(10);

		JLabel lblBirthday = new JLabel("Birthday: ");
		getContentPane().add(lblBirthday, "cell 3 7,alignx left");

		txtFieldBirthday = new WebDateField();
		getContentPane().add(txtFieldBirthday, "cell 4 7,growx");

		JLabel lblSurname = new JLabel("Surname: ");
		getContentPane().add(lblSurname, "cell 0 9,alignx left");

		txtFieldSurname = new JTextField();
		getContentPane().add(txtFieldSurname, "cell 1 9,growx");
		txtFieldSurname.setColumns(10);

		JLabel lblDrivingLicense = new JLabel("Driving License: ");
		getContentPane().add(lblDrivingLicense, "cell 3 9,alignx left");

		comboBox = new JComboBox();
		getContentPane().add(comboBox, "cell 4 9,alignx left");

		JLabel lblAdress = new JLabel("Adress: ");
		getContentPane().add(lblAdress, "cell 0 11,alignx left");

		txtFieldAdress = new JTextField();
		getContentPane().add(txtFieldAdress, "cell 1 11,growx");
		txtFieldAdress.setColumns(10);

		JLabel lblCp = new JLabel("CP: ");
		getContentPane().add(lblCp, "cell 3 11,alignx left");

		txtFieldCP = new JTextField();
		getContentPane().add(txtFieldCP, "cell 4 11,growx");
		txtFieldCP.setColumns(10);

		btnEdit = new JButton("Save");
		getContentPane().add(btnEdit, "cell 3 13,alignx right");

		btnCancel = new JButton("Cancel");
		getContentPane().add(btnCancel, "cell 4 13");

	}

	public JTextField getTxtFieldEmail() {
		return txtFieldEmail;
	}

	public WebDateField getTxtFieldBirthday() {
		return txtFieldBirthday;
	}

	public JTextField getTxtFieldCP() {
		return txtFieldCP;
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

	public JLabel getLblFoto() {
		return lblFoto;
	}

	public JButton getBtnAdd() {
		return btnEdit;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setImage(byte[] image) {
		this.image = image;
		if (image != null) {
			try {
				BufferedImage ima = null;
				InputStream in = new ByteArrayInputStream(image);
				ima = ImageIO.read(in);
				ImageIcon icono = new ImageIcon(ima);
				Image imageToResize = icono.getImage();
				Image nuevaResized = imageToResize.getScaledInstance(200, 133, java.awt.Image.SCALE_SMOOTH);
				lblFoto.setIcon(new ImageIcon(nuevaResized));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			lblFoto.setIcon(
					new ImageIcon(vistaEdit.class.getResource("/com/mordor/mordorLloguer/assets/userImg.png")));
		}
	}

}
