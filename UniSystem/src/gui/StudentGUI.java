package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Session;
import users.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class StudentGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGUI frame = new StudentGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		});
	}
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Create the frame.
	 */
	public StudentGUI(Session s) {
		setTitle("Student Page");
		Session currSession = s;
		Student student = new Student(s.getUsername());
		student.completeFromDB();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//create a Registration Number Label and arrange its properties
		JLabel lblReg = new JLabel("Registration Number:");
		lblReg.setBounds(30, 84, 184, 21);
		lblReg.setFont(new Font("Calibri", Font.PLAIN, 17));
		
		//create an E-mail Label and arrange its properties
		JLabel lblEm = new JLabel("Email:");
		lblEm.setBounds(30, 111, 52, 21);
		lblEm.setFont(new Font("Calibri", Font.PLAIN, 17));
		
		//create a Degree Label and arrange its properties
		JLabel lblDeg = new JLabel("Degree:");
		lblDeg.setBounds(30, 165, 67, 21);
		lblDeg.setFont(new Font("Calibri", Font.PLAIN, 17));
		
		//create a Current Level Label and arrange its properties
		JLabel lblLvl = new JLabel("Current Level:");
		lblLvl.setBounds(30, 192, 120, 21);
		lblLvl.setFont(new Font("Calibri", Font.PLAIN, 17));
		
		//create a Personal Tutor and arrange its properties
		JLabel lblPerT = new JLabel("Personal Tutor:");
		lblPerT.setBounds(30, 219, 131, 21);
		lblPerT.setFont(new Font("Calibri", Font.PLAIN, 17));
		
		//create a Department Label and arrange its properties
		JLabel lblDep = new JLabel("Department:");
		lblDep.setBounds(30, 138, 108, 21);
		lblDep.setFont(new Font("Calibri", Font.PLAIN, 17));
		
		// labels to set : 
		
		JLabel lblSurnameForename = new JLabel("Mr Name Surname");
		lblSurnameForename.setBounds(30, 28, 442, 41);
		lblSurnameForename.setFont(new Font("Nirmala UI", Font.PLAIN, 30));
		
		JLabel lblRegistrationNumber = new JLabel("100000");  
		lblRegistrationNumber.setBounds(240, 84, 274, 21);
		lblRegistrationNumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
				
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(240, 106, 421, 31);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
				
		JLabel lblDepartment = new JLabel("department");
		lblDepartment.setBounds(240, 133, 421, 31);
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 17));
				
		JLabel lblDegree = new JLabel("degree");
		lblDegree.setBounds(240, 160, 421, 31);
		lblDegree.setFont(new Font("Tahoma", Font.PLAIN, 17));
				
		JLabel lblCurrentLevel = new JLabel("level");
		lblCurrentLevel.setBounds(240, 186, 274, 33);
		lblCurrentLevel.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
		JLabel lblPersonalTutor = new JLabel("tutor");
		lblPersonalTutor.setBounds(240, 213, 421, 33);
		lblPersonalTutor.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
		contentPane.setLayout(null);
		
		//add differnent panes		
		JSeparator separator = new JSeparator();
		separator.setBounds(30, 71, 631, 2);
		separator.setForeground(Color.DARK_GRAY);
		contentPane.add(separator);
		contentPane.add(lblReg);
		contentPane.add(lblEm);
		contentPane.add(lblDep);
		contentPane.add(lblDeg);
		contentPane.add(lblLvl);
		contentPane.add(lblPerT);
		contentPane.add(lblDepartment);
		contentPane.add(lblEmail);
		contentPane.add(lblRegistrationNumber);
		contentPane.add(lblPersonalTutor);
		contentPane.add(lblCurrentLevel);
		contentPane.add(lblDegree);
		contentPane.add(lblSurnameForename);
		
		//assign different values to contents
		lblSurnameForename.setText(student.getTitle() + " " + student.getForename() + " " + student.getSurname());
		lblRegistrationNumber.setText(Integer.toString(student.getRegistrationID()));
		lblDepartment.setText(student.getDegree().getLeadDepartment().getCode() + " " + student.getDegree().getLeadDepartment().getName());
		lblDegree.setText(student.getDegree().getCode() + " - " + student.getDegree().getName());
		lblCurrentLevel.setText(student.getCurrentLevel());
		lblPersonalTutor.setText(student.getPersonalTutor());
		lblEmail.setText(student.getEmail());
		
		//create a Show Status Button and arrange its properties
		JButton btnStatus = new JButton("Show Status");
		btnStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openStudentStatus(currSession);
			}
		});
		btnStatus.setBounds(30, 276, 168, 31);
		contentPane.add(btnStatus);
		
		//create a Log Out Button
		Button button = new Button("Log out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Log out confirmation and warning
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to logout? ","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					
					try {
						currSession.endSession();
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
					dispose();
				}
			}
		});
		button.setBounds(582, 10, 79, 22);
		contentPane.add(button);
	}
	
	//return the StudentStatusGUI page
	protected void openStudentStatus( Session s ) {
		// TODO Auto-generated method stub
		StudentStatusGUI frame = new StudentStatusGUI(s);
		frame.setVisible(true);
		dispose();
	}
}
