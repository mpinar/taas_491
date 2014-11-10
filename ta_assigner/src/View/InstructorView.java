package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Model.*;
import Helper.*;
import javax.swing.JLabel;
import javax.swing.JComboBox;
public class InstructorView extends JFrame {

	private JPanel contentPane;

	private Instructor instructor;
	/**
	 * Create the frame.
	 */
	public InstructorView(Instructor ins) {
		
		ins.setTeaches("FALL", 2014);
		instructor = ins;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInfo = new JLabel(ins.name +" "+ ins.surname);
		lblInfo.setBounds(306, 6, 114, 16);
		contentPane.add(lblInfo);
		
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setBounds(51, 40, 61, 16);
		contentPane.add(lblCourses);

		JComboBox cbCourses = new JComboBox(ins.teaches.toArray());
		cbCourses.setBounds(124, 36, 107, 27);
		
		contentPane.add(cbCourses);
		
	}
}
