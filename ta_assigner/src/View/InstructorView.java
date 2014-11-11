package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.*;
import Helper.*;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
public class InstructorView extends JFrame {

	private JPanel contentPane;
	private DatabaseHelper dbh;

	private Instructor instructor;
	private Course course;
	/**
	 * Create the frame.
	 */
	public InstructorView(Instructor ins) {

		dbh = new DatabaseHelper();

		instructor = ins;
		instructor.teaches = dbh.getTeachingInformationForInstructor(instructor.id);
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
		lblCourses.setBounds(30, 40, 61, 16);
		contentPane.add(lblCourses);

		ArrayList<Instructor> c =null;
		
		for(int i = 0; i < instructor.teaches.size(); i++){ // Bence burada dersi kadar alt alta Combo Box koyalim
			int course_id= instructor.teaches.get(i).id;
			c = dbh.getInstructorFromCourseID(course_id);
			course = dbh.getCourseInfoFromID(course_id);
		}
		
		
		int AssistantCount = course.setMaxAssistantCount();
		System.out.println(AssistantCount);
		Integer []asstCountArray = new Integer[AssistantCount];
		int dummy = 0;
		for (int i =0; i<AssistantCount; i++){
			
			dummy++;
			asstCountArray[i]= dummy;
			
			System.out.println(dummy + " ");
		}


		

		//System.out.println(c);

		JComboBox cbCourses = new JComboBox(instructor.teaches.toArray()); // TODO
		cbCourses.setBounds(103, 36, 107, 27);

		contentPane.add(cbCourses);

		JComboBox cbAsstCount = new JComboBox(asstCountArray);
		cbAsstCount.setBounds(368, 34, 52, 27);
		
		contentPane.add(cbAsstCount);

		JLabel lblAsstCount = new JLabel("Assistant Count");
		lblAsstCount.setBounds(240, 40, 101, 16);
		contentPane.add(lblAsstCount);
		if(ins.isAdmin){
			JButton btnSettings = new JButton("Settings");
			btnSettings.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					SettingsView sv = new SettingsView();
					sv.setVisible(true);
				}
			});
			btnSettings.setBounds(327, 243, 117, 29);
			contentPane.add(btnSettings);
		}
	}
}
