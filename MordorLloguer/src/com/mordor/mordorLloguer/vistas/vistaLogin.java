package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.Color;

public class vistaLogin extends JInternalFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblError;
	private JProgressBar progressBar;

	/**
	 * Create the frame.
	 */
	public vistaLogin() {
//		getContentPane().setVisible(false);
		setClosable(true);
		setFrameIcon(new ImageIcon(vistaLogin.class.getResource("/com/mordor/mordorLloguer/assets/login.png")));
		setBounds(100, 100, 380, 286);
		getContentPane().setLayout(new MigLayout("", "[456.00,grow]", "[][][][][][][][][]"));
		setTitle("Login");
		
		JLabel lblMemberLogin = new JLabel("Member Login");
		lblMemberLogin.setFont(new Font("Liberation Sans", Font.BOLD, 25));
		getContentPane().add(lblMemberLogin, "cell 0 1,alignx center,aligny center");
		
		JLabel lblUser = new JLabel("User");
		getContentPane().add(lblUser, "cell 0 2,alignx left");
		
		textField = new JTextField();
		getContentPane().add(textField, "cell 0 3,growx");
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		getContentPane().add(lblPassword, "cell 0 4");
		
		passwordField = new JPasswordField();
		getContentPane().add(passwordField, "cell 0 5,growx");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Dialog", Font.BOLD, 12));
		getContentPane().add(lblError, "cell 0 6");
		
		progressBar = new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setIndeterminate(true);
		getContentPane().add(progressBar, "cell 0 7,growx");
		
		btnLogin = new JButton("Login");
		getContentPane().add(btnLogin, "cell 0 8,growx");

	}

	public JTextField getTextField() {
		return textField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JLabel getLblError() {
		return lblError;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	

}
