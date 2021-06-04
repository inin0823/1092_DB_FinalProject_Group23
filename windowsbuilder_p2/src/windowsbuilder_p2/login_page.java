package windowsbuilder_p2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class login_page {

	JFrame frame;
	private JTextField id_input;
	private JTextField pw_input;
	private JLabel lblNewLabel_1;
	static login_page window;
	public static String student_id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new login_page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("account");
		lblNewLabel.setBounds(207, 142, 57, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(207, 200, 57, 19);
		frame.getContentPane().add(lblPassword);
		
		id_input = new JTextField();
		id_input.setBounds(278, 139, 192, 25);
		frame.getContentPane().add(id_input);
		id_input.setColumns(10);
		
		pw_input = new JTextField();
		pw_input.setColumns(10);
		pw_input.setBounds(278, 197, 192, 25);
		frame.getContentPane().add(pw_input);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(207, 268, 263, 19);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("sign in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					student_id = id_input.getText().toString();
					if(login_verify(id_input.getText().toString(),pw_input.getText().toString())) {
						info newinfos = new info();
						newinfos.frame.setVisible(true);
						window.frame.setVisible(false);
						//window.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
					else {
						lblNewLabel_1.setText("error : Invalid id or password");
						lblNewLabel_1.setForeground(Color.RED);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(207, 312, 263, 35);
		frame.getContentPane().add(btnNewButton);
		
		
	}
	
	static boolean login_verify(String id,String pw) throws ClassNotFoundException {
		//you can use SQL to verify information of login.
		String dbpw="";
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";
		try  {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded!");
			Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");
			Statement statement = connection.createStatement();
			String dbpw_selectSql = "SELECT Password FROM [FinalProject].[dbo].[STUDENT] WHERE Student_ID = "+id+";";
			ResultSet  dbpw_resultSet = statement.executeQuery(dbpw_selectSql);
			while(dbpw_resultSet.next()) {
				dbpw = dbpw_resultSet.getString("Password");
				System.out.println(dbpw);
			}
			if(id.equals("")||pw.equals("")) {
				return false;
			}
			else if(pw.equals(dbpw)) {
				return true;
			}
			else {
				return false;
			}
		}
	        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	public static String getStudent_id() {
		return student_id;
	}
}
