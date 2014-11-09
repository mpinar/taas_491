package View;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import Helper.DatabaseHelper;
import Model.*;

import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TAAS extends JApplet {
	private JTextField tfUsername;
	private JTextField tfPassword;
	private DatabaseHelper dbh;
	
	/**
	 * Create the applet.
	 */
	public TAAS() {
		getContentPane().setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBounds(6, 6, 750, 300);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setBounds(85, 119, 71, 16);
		panel.add(lblPassword);
		
		tfUsername = new JTextField();
	
		tfUsername.setBounds(168, 72, 218, 28);
		tfUsername.setColumns(17);
		panel.add(tfUsername);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(82, 78, 74, 16);
		panel.add(lblUsername);
		
		tfPassword = new JTextField();
	
		tfPassword.setBounds(168, 113, 218, 28);
		tfPassword.setColumns(10);
		panel.add(tfPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String username = tfUsername.getText();
				String plainPassword = tfPassword.getText();
				//System.out.println("Username = "+tfUsername.getText()+"\nPassword = "+tfPassword.getText());
				dbh = new DatabaseHelper();
				
				if(dbh.authorizeUser(username, plainPassword)){
					System.out.println("oldu bu is");
				}else {
					JOptionPane.showMessageDialog(panel,
						    "\tInvalid username/password.\n\tPlease try again.",
						    "Login Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnLogin.setBounds(236, 153, 79, 29);
		panel.add(btnLogin);

	}
}
