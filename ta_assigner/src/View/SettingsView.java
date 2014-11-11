package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SettingsView extends JFrame {

	private JPanel contentPane;
	private JTextField tfYear;


	/**
	 * Create the frame.
	 */
	
	public SettingsView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYear = new JLabel("Year :");
		lblYear.setBounds(66, 49, 61, 16);
		contentPane.add(lblYear);
		
		JLabel lblSemester = new JLabel("Semester :");
		lblSemester.setBounds(66, 89, 61, 16);
		contentPane.add(lblSemester);
		String[] semesters = {"FALL","SPRING","SUMMER"};
		JComboBox comboBox = new JComboBox(semesters);
		comboBox.setBounds(171, 85, 128, 27);
		contentPane.add(comboBox);
		
		tfYear = new JTextField();
		tfYear.setBounds(171, 43, 128, 28);
		contentPane.add(tfYear);
		tfYear.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(312, 132, 117, 29);
		contentPane.add(btnSave);
	}
}
