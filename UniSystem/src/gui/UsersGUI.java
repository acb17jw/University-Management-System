package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import database.DatabaseSelector;
import database.Session;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import java.awt.Font;

public class UsersGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersGUI frame = new UsersGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UsersGUI(Session s) {
		Session currSession =s;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		DatabaseSelector dbSelector = new DatabaseSelector();
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setBounds(605, 422, 168, 54);
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openAddUser(currSession);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnAddUser);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openAdmin(currSession);
			}
		});
		btnBack.setBounds(31, 422, 181, 54);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 788, 368);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {				
			},
			new String[] {
				"Username", "Level of access"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i>=0) {			

					boolean isNotStudent = dbSelector.deleteUser(table.getValueAt(i, 0).toString());
					if(isNotStudent) {
						model.removeRow(i);
						JOptionPane.showMessageDialog(null, "User has been successfuly deleted.");
					}else {
						JOptionPane.showMessageDialog(null, "Unable to delete user. First ask registar to delete this student.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Unable to delete. Select user.");
				}		
				
			}
		});
		btnDeleteUser.setBounds(324, 422, 181, 54);
		contentPane.add(btnDeleteUser);
		
		
		//getting list of users.
		
		List <String[]> usersList = dbSelector.GetUsersList();
		for( String[] row : usersList) {			
			model.addRow(new String[] {row[0], row[2]});
		}
		
		
	}
	
	protected void openAddUser(Session s) {		
		AddUserGUI frame = new AddUserGUI(s);
		frame.setVisible(true);
		dispose();		
	}
	
	protected void openAdmin(Session s) {
		AdminGUI frame = new AdminGUI(s);
		frame.setVisible(true);
		dispose();
	}
}