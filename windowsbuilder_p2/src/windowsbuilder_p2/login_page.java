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
import java.awt.Font;
import javax.swing.SwingConstants;

public class login_page {

	static JFrame frame;
	private JTextField id_input;
	private JTextField pw_input;
	private JLabel lblNewLabel_1;
	static login_page window;
	public static String student_id;
	private JLabel lblNewLabel_2;
	private JLabel lblNccu;
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
		frame.setTitle("�t�εn�J����");
		frame.getContentPane().setBackground(new Color(153, 204, 204));
		frame.setBounds(150, 50, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�Ǹ�");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("�L�n������", Font.BOLD, 20));
		lblNewLabel.setBounds(262, 252, 57, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("�K�X");
		lblPassword.setForeground(Color.DARK_GRAY);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("�L�n������", Font.BOLD, 20));
		lblPassword.setBounds(262, 305, 57, 19);
		frame.getContentPane().add(lblPassword);
		
		id_input = new JTextField();
		id_input.setFont(new Font("�L�n������", Font.BOLD, 18));
		id_input.setBounds(340, 246, 192, 25);
		frame.getContentPane().add(id_input);
		id_input.setColumns(10);
		
		pw_input = new JTextField();
		pw_input.setFont(new Font("�L�n������", Font.BOLD, 18));
		pw_input.setColumns(10);
		pw_input.setBounds(340, 299, 192, 25);
		frame.getContentPane().add(pw_input);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("�L�n������", Font.BOLD, 20));
		lblNewLabel_1.setBounds(262, 412, 287, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("�n�J");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(new Color(255, 153, 102));
		btnNewButton.setFont(new Font("�L�n������", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					student_id = id_input.getText().toString();
					if(login_verify(id_input.getText().toString(),pw_input.getText().toString())) {
						info newinfos = new info();
						newinfos.frame.setVisible(true);
						login_page.frame.setVisible(false);
						//window.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
					else {
						lblNewLabel_1.setText("�п�J���T���Ǹ��M�K�X");
						lblNewLabel_1.setForeground(Color.RED);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(262, 350, 280, 35);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("\u4F7F\u7528\u8005\u767B\u9304");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setBackground(new Color(255, 204, 204));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("�L�n������", Font.BOLD, 24));
		lblNewLabel_2.setBounds(252, 194, 280, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNccu = new JLabel("NCCU \u8AB2\u7A0B\u8A55\u8AD6\u7CFB\u7D71");
		lblNccu.setForeground(Color.DARK_GRAY);
		lblNccu.setBackground(new Color(255, 204, 204));
		lblNccu.setHorizontalAlignment(SwingConstants.CENTER);
		lblNccu.setFont(new Font("�L�n������", Font.BOLD, 36));
		lblNccu.setBounds(0, 68, 786, 90);
		frame.getContentPane().add(lblNccu);
		
		
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
