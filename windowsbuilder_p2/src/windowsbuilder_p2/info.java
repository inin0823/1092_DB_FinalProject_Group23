package windowsbuilder_p2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JSplitPane;
import java.awt.Panel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class info {

	// sql server

	static final String ms_sql_conn_string = "jdbc:sqlserver://localhost;";
	static final String ms_sql_db = "database=" + ";";
	static final String ms_sql_user = "user=" + ";";
	static final String ms_sql_pass = "password=" + ";";
	
	
	
	static JFrame frame;
	public static JLabel readstatus;
	static info window;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new info();
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
	public info() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		readstatus = new JLabel("no data",JLabel.CENTER);
		//frame.getContentPane().add(readstatus);

		JButton back = new JButton("back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				login_page newinfos = new login_page();
				newinfos.frame.setVisible(true);
				window.frame.setVisible(false);
			}

			
		});
		
		JButton readdata = new JButton("readdata");
		readdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					connection_sqlserver();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

			
		});
		//frame.getContentPane().add(readdata);
		
		JPanel topPane = new JPanel();
        topPane.setLayout(new BorderLayout());

        topPane.add(readdata, BorderLayout.EAST);
        topPane.add(readstatus, BorderLayout.CENTER);
        topPane.add(back, BorderLayout.WEST);
        frame.getContentPane().add(topPane, BorderLayout.NORTH);
		
		
	}
	
	public static void connection_sqlserver() throws ClassNotFoundException  {
		//sql server authentication
		/*String connectionUrl =
				ms_sql_conn_string
				+ms_sql_db
				+ms_sql_user
				+ms_sql_pass
				+ "encrypt=true;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;"
                +"integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
		*/
		//windows authentication
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";

		
		try  {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded!");
			readstatus.setText("MSSQL Driver loaded!");
			Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");
			readstatus.setText("MSSQL Connection Success!");
			Statement statement = connection.createStatement();
			String selectSql = "SELECT Year_Semester,[FinalProject].[dbo].[ENROLL].Course_ID,CourseName,Teacher,Lecture_Language FROM [FinalProject].[dbo].[ENROLL],[FinalProject].[dbo].[STUDENT],[FinalProject].[dbo].[COURSE] WHERE [FinalProject].[dbo].[ENROLL].Student_ID ="+login_page.getStudent_id();
			System.out.println(selectSql);

			//SELECT Year_Semester,ENROLL.Course_ID,CourseName,Teacher,Lecture_Language FROM ENROLL,STUDENT,COURSE WHERE ENROLL.Student_ID = 107304018;
			ResultSet resultSet = statement.executeQuery(selectSql);
			readstatus.setText("read data Success!");
			String[] columnNames = {"Year_Semester","Course_ID","CourseName","Teacher","Lecture_Language"};
			String[][] datas = new String[10][10];
			int i = 0;
			while (resultSet.next()) {
				//System.out.println(resultSet.getString("Year_Semester") + "    " + resultSet.getString("Course_ID") + "   "+ resultSet.getString("CourseName") + "   " + resultSet.getString("Teacher")+ "   " + resultSet.getString("Lecture_Language"));
				//System.out.println();
				String[] tempdata = new String[10];
				for (int ii = 1; ii < 6; ii++) {
					System.out.println(resultSet.getString(ii));
					tempdata[ii-1] = resultSet.getString(ii);
				}
				datas[i]=tempdata;
				i++;
				//System.out.println();
			}
			
			for(int j=0;j<datas.length;j++) {
				for(int k=0;k<datas[0].length;k++) {
					//System.out.println(datas[j][k]);
				}
			}

			JTable temptable = new JTable(datas, columnNames); 
			//temptable.setBounds(30, 94, 315, 157);
			JScrollPane scroll = new JScrollPane(temptable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			temptable.setPreferredScrollableViewportSize(new Dimension(420, 250));
			temptable.setFillsViewportHeight(true); 
			JPanel tablePane = new JPanel();
	        tablePane.add(scroll);
	        frame.getContentPane().add(tablePane, BorderLayout.CENTER);
			//frame.setSize(300, 400);  

			//frame.setVisible(true);
	        
	        JLabel test = new JLabel("test lable",JLabel.CENTER);
	        test.setBounds(200, 200, 276, 15);
	        JPanel downPane = new JPanel();
	        downPane.setLayout(new BorderLayout());

	        downPane.add(test, BorderLayout.WEST);
	        frame.getContentPane().add(downPane, BorderLayout.SOUTH);
	        
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            readstatus.setText("Error");
			readstatus.setForeground(Color.RED);
        }
	}
}
