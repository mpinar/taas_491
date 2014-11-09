package View;

import javax.swing.JApplet;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import Model.*;

public class TAAS extends JApplet {
	private JTextField tfUsername;
	private JTextField tfPassword;

	/**
	 * Create the applet.
	 */
	public TAAS() {
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(85, 88, 95, 16);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setBounds(85, 122, 95, 16);
		getContentPane().add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(162, 82, 202, 28);
		getContentPane().add(tfUsername);
		tfUsername.setColumns(17);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(162, 116, 202, 28);
		getContentPane().add(tfPassword);
		tfPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(206, 171, 117, 29);
		getContentPane().add(btnLogin);

	}
}
