package windowsbuilder_p2;

import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class fourm_page {

	private JFrame frmFourm;
	private JFrame frame;
	
	public static JLabel label_CourseID_1;
	public static JLabel label_CourseName_1;
	public static JLabel label_Teacher_1;
	public static JLabel label_ClassSession_1;
	public static JLabel label_Classroom_1;
	public static JLabel label_Sweetness_1;
	public static JLabel label_Loading_1;
	public static JLabel label_Recommended_1;
	public static JLabel label_AvgScore_1;
	public static JLabel label_Like;
	public static JLabel label_Dislike;
	public static JButton btnNext;
	public static JButton btnPrevious;
	public static JButton btnLike;
	public static JButton btnDislike;
	public static JButton btnReply;
	public static JTextArea textArea_Comment;
	public static JTextArea textArea_Reply;
	
	public static String CourseID = "306008011";
	public static int CommentID = 0000000001;
	public static int floor = 1;

	

	/**
	 * Launch the application.
	 */ 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fourm_page window = new fourm_page();
					window.frmFourm.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public fourm_page() throws ClassNotFoundException {
		initialize();
		courseinfo();
		commentinfo(0);
		evaluationinfo();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frmFourm = new JFrame();
		frmFourm.getContentPane().setBackground(new Color(255, 239, 213));
		frmFourm.setTitle("Fourm");
		frmFourm.setBounds(100, 100, 523, 650);
		frmFourm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFourm.getContentPane().setLayout(null);
		
		JLabel label_CourseInformation = new JLabel("課程資料");
		label_CourseInformation.setOpaque(true);
		label_CourseInformation.setForeground(Color.DARK_GRAY);
		label_CourseInformation.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_CourseInformation.setBorder(new EmptyBorder(0, 0, 0, 0));
		label_CourseInformation.setBackground(new Color(175, 238, 238));
		label_CourseInformation.setHorizontalAlignment(SwingConstants.CENTER);
		label_CourseInformation.setBounds(49, 24, 411, 28);
		frmFourm.getContentPane().add(label_CourseInformation);
		
		JPanel panel_CourseInformation = new JPanel();
		panel_CourseInformation.setBounds(49, 52, 411, 226);
		frmFourm.getContentPane().add(panel_CourseInformation);
		panel_CourseInformation.setLayout(null);
		
		JLabel label_CourseName = new JLabel("課程名稱");
		label_CourseName.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_CourseName.setBounds(43, 20, 99, 23);
		panel_CourseInformation.add(label_CourseName);
		
		JLabel label_Teacher = new JLabel("授課老師");
		label_Teacher.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Teacher.setBounds(43, 53, 99, 23);
		panel_CourseInformation.add(label_Teacher);
		
		JLabel label_ClassSession = new JLabel("授課時間");
		label_ClassSession.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_ClassSession.setBounds(43, 86, 99, 23);
		panel_CourseInformation.add(label_ClassSession);
		
		JLabel label_Classroom = new JLabel("上課地點");
		label_Classroom.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Classroom.setBounds(43, 119, 99, 23);
		panel_CourseInformation.add(label_Classroom);
		
		JLabel label_Sweetness = new JLabel("甜度");
		label_Sweetness.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Sweetness.setBounds(43, 152, 54, 23);
		panel_CourseInformation.add(label_Sweetness);
		
		JLabel label_Loading = new JLabel("涼度");
		label_Loading.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Loading.setBounds(43, 185, 54, 23);
		panel_CourseInformation.add(label_Loading);
		
		JLabel label_Recommended = new JLabel("整體推薦度");
		label_Recommended.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Recommended.setBounds(210, 152, 99, 23);
		panel_CourseInformation.add(label_Recommended);
		
		JLabel label_AvgScore = new JLabel("平均學期分數");
		label_AvgScore.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_AvgScore.setBounds(210, 185, 99, 23);
		panel_CourseInformation.add(label_AvgScore);
		
		label_CourseID_1 = new JLabel("");
		label_CourseID_1.setVisible(false);
		
		label_CourseName_1 =new JLabel(""); 
		label_CourseName_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		label_CourseName_1.setBounds(136, 20, 182, 23);
		panel_CourseInformation.add(label_CourseName_1);
		
		
		label_Teacher_1 = new JLabel("");
		label_Teacher_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		label_Teacher_1.setBounds(136, 53, 182, 23);
		panel_CourseInformation.add(label_Teacher_1);
		
		label_ClassSession_1 = new JLabel("");
		label_ClassSession_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		label_ClassSession_1.setBounds(136, 86, 182, 23);
		panel_CourseInformation.add(label_ClassSession_1);
		
		label_Classroom_1 = new JLabel("");
		label_Classroom_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		label_Classroom_1.setBounds(136, 119, 182, 23);
		panel_CourseInformation.add(label_Classroom_1);
		
		label_Sweetness_1 = new JLabel("");
		label_Sweetness_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		label_Sweetness_1.setBounds(96, 152, 82, 23);
		panel_CourseInformation.add(label_Sweetness_1);
		
		label_Loading_1 = new JLabel("");
		label_Loading_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		label_Loading_1.setBounds(96, 185, 82, 23);
		panel_CourseInformation.add(label_Loading_1);
		
		label_Recommended_1 = new JLabel("");
		label_Recommended_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		label_Recommended_1.setBounds(319, 152, 82, 23);
		panel_CourseInformation.add(label_Recommended_1);
		
		label_AvgScore_1 = new JLabel("");
		label_AvgScore_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		label_AvgScore_1.setBounds(319, 185, 82, 23);
		panel_CourseInformation.add(label_AvgScore_1);
		
		JLabel label_CourseFourm = new JLabel("課程討論");
		label_CourseFourm.setOpaque(true);
		label_CourseFourm.setHorizontalAlignment(SwingConstants.CENTER);
		label_CourseFourm.setForeground(Color.DARK_GRAY);
		label_CourseFourm.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_CourseFourm.setBorder(new EmptyBorder(0, 0, 0, 0));
		label_CourseFourm.setBackground(new Color(175, 238, 238));
		label_CourseFourm.setBounds(49, 303, 411, 28);
		frmFourm.getContentPane().add(label_CourseFourm);
		
		JPanel panel_CourseFourm = new JPanel();
		panel_CourseFourm.setLayout(null);
		panel_CourseFourm.setBounds(49, 330, 411, 250);
		frmFourm.getContentPane().add(panel_CourseFourm);

		final JLabel label_floor = new JLabel(floor+"F");
		label_floor.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_floor.setBounds(35, 10, 45, 23);
		panel_CourseFourm.add(label_floor);
		
		btnNext = new JButton(">>");
		btnNext.setBounds(315, 217, 71, 23);
		panel_CourseFourm.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floor = floor+1;
				label_floor.setText(floor+"F");
				
				try{
					commentinfo(floor-1);
				}
				catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		btnPrevious = new JButton("<<");
		btnPrevious.setBounds(35, 217, 71, 23);
		panel_CourseFourm.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(floor==1) {
					
				}
				else
				floor = floor-1;
				label_floor.setText(floor+"F");
				
				try {
					commentinfo(floor-1);
				}
				catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});
		
		btnReply = new JButton("Reply");
		btnReply.setBounds(179, 217, 71, 23);
		panel_CourseFourm.add(btnReply);
		btnReply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Reply_Page window = new Reply_Page(floor);
					window.frame.setVisible(true);
				}
				 catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 39, 351, 154);
		panel_CourseFourm.add(scrollPane);
		
		textArea_Comment = new JTextArea("");
		textArea_Comment.setWrapStyleWord(true);
		textArea_Comment.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		textArea_Comment.setEditable(false);
		scrollPane.setViewportView(textArea_Comment);
		
		label_Like = new JLabel("0");
		label_Like.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Like.setBounds(260, 10, 33, 23);
		panel_CourseFourm.add(label_Like);
		
		label_Dislike = new JLabel("0");
		label_Dislike.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Dislike.setBounds(353, 10, 33, 23);
		panel_CourseFourm.add(label_Dislike);
		
		btnLike = new JButton("頂");
		btnLike.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnLike.setBounds(205, 10, 51, 23);
		panel_CourseFourm.add(btnLike);
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Likefunction(1);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					commentinfo(floor-1);
				} catch(ClassNotFoundException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		btnDislike = new JButton("踩");
		btnDislike.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnDislike.setBounds(298, 10, 51, 23);
		panel_CourseFourm.add(btnDislike);
		btnDislike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Likefunction(0);
				} catch (ClassNotFoundException e1) {
				}
				try {
					commentinfo(floor-1);
				} catch(ClassNotFoundException e2) {
				}
			}
		});
		
	}
	
	public static void courseinfo() throws ClassNotFoundException  {
		
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";

		
		try  {
			
			//sqlserver連線
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded!");
			final Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");	
			final Statement statement = connection.createStatement();

	        
	        //course_info
			String course_info_selectSql = "SELECT CourseName,Teacher,Class_Session,Classroom"
					+ " FROM [FinalProject].[dbo].[COURSE] WHERE Course_ID = "+CourseID;
			ResultSet course_info_resultSet = statement.executeQuery(course_info_selectSql);
			JLabel [] course_info_label = {label_CourseName_1,label_Teacher_1,label_ClassSession_1,label_Classroom_1};
			
			while (course_info_resultSet.next()) {
				for (int a =1;a<5;a++) {
					course_info_label[a-1].setText(course_info_resultSet.getString(a));
				}				
			}				
		
	        }
        
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();		
        }
	}
	
	public static void commentinfo(int a) throws ClassNotFoundException  {
		
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";
		
		try  {
			CommentID = CommentID+a;
			
			//sqlserver連線
			final Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");	
			final Statement statement = connection.createStatement();
			
			
	        //comment_info
			String comment_selectSql = "SELECT Comment_Text FROM [FinalProject].[dbo].[COMMENT] "
					+ "WHERE Comment_ID="+CommentID+ " AND Course_ID="+CourseID;					
			ResultSet comment_resultSet = statement.executeQuery(comment_selectSql);
			
	        while(comment_resultSet.next()) {
	        	textArea_Comment.setText(comment_resultSet.getString(1));
	        }
	        
	        //like & dislike info
	        String like_selectSql = "SELECT [Like],[Dislike] FROM [FinalProject].[dbo].[COMMENT_LIKE] "
	        		+ "WHERE Course_ID= "+CourseID+" AND Comment_ID= "+CommentID;
	        ResultSet like_resultSet = statement.executeQuery(like_selectSql);
	        JLabel [] like_label = {label_Like,label_Dislike};
	        
	        while (like_resultSet.next()) {
				for (int b =1;b<3;b++) {
					like_label[b-1].setText(like_resultSet.getString(b));
				}				
			}	
	        
	        CommentID = CommentID-a;
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();		
        }
	}
	
	public static void evaluationinfo() throws ClassNotFoundException  {
		
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";
		
		try  {
			
			//sqlserver連線
			final Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");	
			final Statement statement = connection.createStatement();
			
			
	        //evaluation_info
			String evaluation_selectSql = "SELECT AVG(Recommended_Rate),AVG(Sweetness),AVG(Loading)"
					  +"FROM [FinalProject].[dbo].[COMMENT] WHERE Course_ID= "+CourseID;					
			ResultSet evaluation_resultSet = statement.executeQuery(evaluation_selectSql);
			JLabel [] evaluation_label = {label_Recommended_1,label_Sweetness_1,label_Loading_1};
			
			while (evaluation_resultSet.next()) {
				for (int a =1;a<4;a++) {
					evaluation_label[a-1].setText(evaluation_resultSet.getString(a));
				}				
			}
	        
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();		
        }
	}

	public static void Likefunction(int parameter) throws ClassNotFoundException  {
		
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";
		
		try  {
			
			//sqlserver連線
			final Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");	
			final Statement statement = connection.createStatement();
			
					
			
	        //like_function
			if(parameter==1) {
				int like = Integer.parseInt(label_Like.getText());
				like = like+1;
				String like_selectSql = "UPDATE COMMENT_LIKE"
					  +" SET [Like] = "+like+" WHERE Course_ID= "+CourseID+" AND Comment_ID= "+CommentID;					
				statement.executeUpdate(like_selectSql);
			}
			
			else {
				int dislike = Integer.parseInt(label_Dislike.getText());
				dislike = dislike+1;
				String dislike_selectSql = "UPDATE COMMENT_LIKE"
						  +" SET [Dislike] = "+dislike+" WHERE Course_ID= "+CourseID+" AND Comment_ID= "+CommentID;					
				statement.executeUpdate(dislike_selectSql);	
			}
	        
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();		
        }
	}
}
