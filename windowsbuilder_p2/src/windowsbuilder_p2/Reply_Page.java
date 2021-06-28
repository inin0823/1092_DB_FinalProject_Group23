package windowsbuilder_p2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JButton;

public class Reply_Page {

	JFrame frame;
	public static JTextArea textArea_Reply;
	public static int LocalCommentID=1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reply_Page window = new Reply_Page(LocalCommentID);
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
	public Reply_Page(int i) throws ClassNotFoundException{
		initialize();
		reply(i);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 323);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea_Reply = new JTextArea("");
		textArea_Reply.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		textArea_Reply.setEditable(false);
		textArea_Reply.setBounds(10, 43, 416, 210);
		frame.getContentPane().add(textArea_Reply);
		
		JLabel label_Reply = new JLabel("Reply");
		label_Reply.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Reply.setBounds(193, 10, 72, 23);
		frame.getContentPane().add(label_Reply);
			
	}


	public static void reply(int CommentID) throws ClassNotFoundException{

		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";

		try  {
			//sqlserver連線
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded!");
			final Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");	
			final Statement statement = connection.createStatement();
			
			//Reply function
			String reply_selectSql = "SELECT Contents FROM [FinalProject].[dbo].[REPLY] "
					+ "WHERE Comment_ID="+CommentID;
			ResultSet reply_resultSet = statement.executeQuery(reply_selectSql);
			int a=1;

			while(reply_resultSet.next()) {
					textArea_Reply.append("Reply_"+a+": ");
					textArea_Reply.append(reply_resultSet.getString(1));
					textArea_Reply.append("\n");
					a++;
	        }
		}   
		catch (SQLException e) {
            e.printStackTrace();		
        }
	
	}
	

	
	
}
