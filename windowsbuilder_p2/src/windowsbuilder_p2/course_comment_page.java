package windowsbuilder_p2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;

public class course_comment_page extends JFrame{

	static  JFrame frame;
	public static JLabel label_Year_Semester_1;
	public static JLabel label_Course_ID_1;
	public static JLabel label_CourseName_1;
	public static JLabel label_Class_Session_1;
	public static JLabel label_Credit_1;
	public static JLabel label_Teacher_1;
	public static String comment_course_ID;
	public static String comment_ID;
	public static JTextArea textArea;
	public static JComboBox comboBox_Recommended_Rate;
	public static JComboBox comboBox_Sweetness;
	public static JComboBox comboBox_Loading;
	public static boolean comment_change;
	
	static course_comment_page window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new course_comment_page();
					window.frame.setVisible(true);
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
	public course_comment_page() throws ClassNotFoundException {
		initialize();
	}
	
	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public course_comment_page(String comment_course_ID) throws ClassNotFoundException {
		this.comment_course_ID = comment_course_ID;
		this.comment_change = false;
		initialize();
		comment_course_information(comment_course_ID);
	}
	
	public course_comment_page(String comment_course_ID,String comment_ID) throws ClassNotFoundException {
		this.comment_course_ID = comment_course_ID;
		this.comment_ID = comment_ID;
		this.comment_change = true;
		initialize();
		comment_course_information(comment_course_ID);
		comment_information(comment_ID);
	}
	
	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("課程評論填寫及修改");
		frame.getContentPane().setBackground(new Color(255, 204, 204));
		frame.setBounds(300, 50, 550, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		textArea.setBackground(new Color(255, 245, 238));
		textArea.setBounds(35, 272, 465, 140);
		frame.getContentPane().add(textArea);
		
		JButton button_send = new JButton("\u9001\u51FA");
		button_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comment_change) {
						changecomment();
					}else {
						addcomment();
					}
					course_comment_page.frame.dispose();
					info.frame.dispose();
					info newinfos = new info();
					newinfos.frame.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_send.setForeground(Color.DARK_GRAY);
		button_send.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		button_send.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 153, 0), new Color(204, 204, 204)));
		button_send.setBackground(new Color(255, 153, 102));
		button_send.setAlignmentX(0.5f);
		button_send.setBounds(197, 520, 139, 33);
		frame.getContentPane().add(button_send);
		
		JLabel label_Recommended_Rate = new JLabel("\u63A8\u85A6\u5EA6");
		label_Recommended_Rate.setBorder(new EmptyBorder(0, 0, 0, 0));
		label_Recommended_Rate.setHorizontalAlignment(SwingConstants.CENTER);
		label_Recommended_Rate.setForeground(Color.DARK_GRAY);
		label_Recommended_Rate.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Recommended_Rate.setOpaque(true); 
		label_Recommended_Rate.setBackground(new Color(176, 224, 230));
		label_Recommended_Rate.setAlignmentX(0.5f);
		label_Recommended_Rate.setBounds(35, 422, 139, 33);
		frame.getContentPane().add(label_Recommended_Rate);
		
		JLabel label_Recommended_Rate_1 = new JLabel("\u8AB2\u7A0B\u8A55\u8AD6");
		label_Recommended_Rate_1.setOpaque(true);
		label_Recommended_Rate_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_Recommended_Rate_1.setForeground(Color.DARK_GRAY);
		label_Recommended_Rate_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Recommended_Rate_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		label_Recommended_Rate_1.setBackground(new Color(176, 224, 230));
		label_Recommended_Rate_1.setAlignmentX(0.5f);
		label_Recommended_Rate_1.setBounds(35, 229, 465, 33);
		frame.getContentPane().add(label_Recommended_Rate_1);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(255, 245, 238));
		panel.setBounds(35, 62, 465, 151);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_Year_Semester = new JLabel("\u4FEE\u8AB2\u5B78\u671F");
		label_Year_Semester.setBounds(22, 22, 91, 23);
		panel.add(label_Year_Semester);
		label_Year_Semester.setBackground(new Color(102, 153, 204));
		label_Year_Semester.setHorizontalAlignment(SwingConstants.CENTER);
		label_Year_Semester.setForeground(Color.DARK_GRAY);
		label_Year_Semester.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Year_Semester.setAlignmentX(0.5f);
		
		JLabel label_Course_ID = new JLabel("\u8AB2\u7A0B\u7DE8\u865F");
		label_Course_ID.setBounds(22, 66, 91, 23);
		panel.add(label_Course_ID);
		label_Course_ID.setBackground(new Color(102, 153, 204));
		label_Course_ID.setHorizontalAlignment(SwingConstants.CENTER);
		label_Course_ID.setForeground(Color.DARK_GRAY);
		label_Course_ID.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Course_ID.setAlignmentX(0.5f);
		
		JLabel label_CourseName = new JLabel("\u8AB2\u7A0B\u540D\u7A31");
		label_CourseName.setBounds(22, 107, 91, 23);
		panel.add(label_CourseName);
		label_CourseName.setBackground(new Color(102, 153, 204));
		label_CourseName.setHorizontalAlignment(SwingConstants.CENTER);
		label_CourseName.setForeground(Color.DARK_GRAY);
		label_CourseName.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_CourseName.setAlignmentX(0.5f);
		
		JLabel label_Class_Session = new JLabel("\u4E0A\u8AB2\u6642\u9593");
		label_Class_Session.setBounds(243, 22, 85, 23);
		panel.add(label_Class_Session);
		label_Class_Session.setBackground(new Color(102, 153, 204));
		label_Class_Session.setHorizontalAlignment(SwingConstants.CENTER);
		label_Class_Session.setForeground(Color.DARK_GRAY);
		label_Class_Session.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Class_Session.setAlignmentX(0.5f);
		
		JLabel label_Credit = new JLabel("\u8AB2\u7A0B\u5B78\u5206");
		label_Credit.setBounds(243, 66, 85, 23);
		panel.add(label_Credit);
		label_Credit.setBackground(new Color(102, 153, 204));
		label_Credit.setHorizontalAlignment(SwingConstants.CENTER);
		label_Credit.setForeground(Color.DARK_GRAY);
		label_Credit.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Credit.setAlignmentX(0.5f);
		
		JLabel label_Teacher = new JLabel("\u6388\u8AB2\u6559\u5E2B");
		label_Teacher.setBounds(243, 107, 85, 23);
		panel.add(label_Teacher);
		label_Teacher.setBackground(new Color(102, 153, 204));
		label_Teacher.setHorizontalAlignment(SwingConstants.CENTER);
		label_Teacher.setForeground(Color.DARK_GRAY);
		label_Teacher.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Teacher.setAlignmentX(0.5f);
		
		label_Year_Semester_1 = new JLabel("");
		label_Year_Semester_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_Year_Semester_1.setForeground(new Color(51, 102, 153));
		label_Year_Semester_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Year_Semester_1.setBackground(new Color(102, 153, 204));
		label_Year_Semester_1.setAlignmentX(0.5f);
		label_Year_Semester_1.setBounds(125, 22, 91, 23);
		panel.add(label_Year_Semester_1);
		
		label_Course_ID_1 = new JLabel("");
		label_Course_ID_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_Course_ID_1.setForeground(new Color(51, 102, 153));
		label_Course_ID_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Course_ID_1.setBackground(new Color(102, 153, 204));
		label_Course_ID_1.setAlignmentX(0.5f);
		label_Course_ID_1.setBounds(123, 66, 91, 23);
		panel.add(label_Course_ID_1);
		
		label_CourseName_1 = new JLabel("");
		label_CourseName_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_CourseName_1.setForeground(new Color(51, 102, 153));
		label_CourseName_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_CourseName_1.setBackground(new Color(102, 153, 204));
		label_CourseName_1.setAlignmentX(0.5f);
		label_CourseName_1.setBounds(125, 107, 91, 23);
		panel.add(label_CourseName_1);
		
		label_Class_Session_1 = new JLabel("");
		label_Class_Session_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_Class_Session_1.setForeground(new Color(51, 102, 153));
		label_Class_Session_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Class_Session_1.setBackground(new Color(102, 153, 204));
		label_Class_Session_1.setAlignmentX(0.5f);
		label_Class_Session_1.setBounds(349, 22, 85, 23);
		panel.add(label_Class_Session_1);
		
		label_Credit_1 = new JLabel("");
		label_Credit_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_Credit_1.setForeground(new Color(51, 102, 153));
		label_Credit_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Credit_1.setBackground(new Color(102, 153, 204));
		label_Credit_1.setAlignmentX(0.5f);
		label_Credit_1.setBounds(349, 66, 85, 23);
		panel.add(label_Credit_1);
		
		label_Teacher_1 = new JLabel("");
		label_Teacher_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_Teacher_1.setForeground(new Color(51, 102, 153));
		label_Teacher_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Teacher_1.setBackground(new Color(102, 153, 204));
		label_Teacher_1.setAlignmentX(0.5f);
		label_Teacher_1.setBounds(349, 107, 85, 23);
		panel.add(label_Teacher_1);
		
		JLabel label_Recommended_Rate_1_1 = new JLabel("\u8AB2\u7A0B\u8A55\u8AD6");
		label_Recommended_Rate_1_1.setOpaque(true);
		label_Recommended_Rate_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_Recommended_Rate_1_1.setForeground(Color.DARK_GRAY);
		label_Recommended_Rate_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Recommended_Rate_1_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		label_Recommended_Rate_1_1.setBackground(new Color(176, 224, 230));
		label_Recommended_Rate_1_1.setAlignmentX(0.5f);
		label_Recommended_Rate_1_1.setBounds(35, 23, 465, 33);
		frame.getContentPane().add(label_Recommended_Rate_1_1);
		
		JLabel label_Recommended_Rate_2 = new JLabel("\u751C\u5EA6");
		label_Recommended_Rate_2.setOpaque(true);
		label_Recommended_Rate_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_Recommended_Rate_2.setForeground(Color.DARK_GRAY);
		label_Recommended_Rate_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Recommended_Rate_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		label_Recommended_Rate_2.setBackground(new Color(176, 224, 230));
		label_Recommended_Rate_2.setAlignmentX(0.5f);
		label_Recommended_Rate_2.setBounds(197, 422, 139, 33);
		frame.getContentPane().add(label_Recommended_Rate_2);
		
		JLabel label_Recommended_Rate_3 = new JLabel("\u6DBC\u5EA6");
		label_Recommended_Rate_3.setOpaque(true);
		label_Recommended_Rate_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_Recommended_Rate_3.setForeground(Color.DARK_GRAY);
		label_Recommended_Rate_3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		label_Recommended_Rate_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		label_Recommended_Rate_3.setBackground(new Color(176, 224, 230));
		label_Recommended_Rate_3.setAlignmentX(0.5f);
		label_Recommended_Rate_3.setBounds(361, 422, 139, 33);
		frame.getContentPane().add(label_Recommended_Rate_3);
		
		comboBox_Recommended_Rate = new JComboBox();
		comboBox_Recommended_Rate.setModel(new DefaultComboBoxModel(new String[] {"\u2605", "\u2605\u2605", "\u2605\u2605\u2605", "\u2605\u2605\u2605\u2605", "\u2605\u2605\u2605\u2605\u2605"}));
		comboBox_Recommended_Rate.setBorder(new LineBorder(new Color(128, 128, 128), 0, true));
		comboBox_Recommended_Rate.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		comboBox_Recommended_Rate.setBackground(new Color(211, 211, 211));
		comboBox_Recommended_Rate.setBounds(35, 465, 139, 33);
		frame.getContentPane().add(comboBox_Recommended_Rate);
		
		
		comboBox_Sweetness = new JComboBox();
		comboBox_Sweetness.setModel(new DefaultComboBoxModel(new String[] {"\u2605", "\u2605\u2605", "\u2605\u2605\u2605", "\u2605\u2605\u2605\u2605", "\u2605\u2605\u2605\u2605\u2605"}));
		comboBox_Sweetness.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		comboBox_Sweetness.setBorder(new LineBorder(new Color(128, 128, 128), 0, true));
		comboBox_Sweetness.setBackground(new Color(211, 211, 211));
		comboBox_Sweetness.setBounds(197, 465, 139, 33);
		frame.getContentPane().add(comboBox_Sweetness);
		
		comboBox_Loading = new JComboBox();
		comboBox_Loading.setModel(new DefaultComboBoxModel(new String[] {"\u2605", "\u2605\u2605", "\u2605\u2605\u2605", "\u2605\u2605\u2605\u2605", "\u2605\u2605\u2605\u2605\u2605"}));
		comboBox_Loading.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		comboBox_Loading.setBorder(new LineBorder(new Color(128, 128, 128), 0, true));
		comboBox_Loading.setBackground(new Color(211, 211, 211));
		comboBox_Loading.setBounds(361, 465, 139, 33);
		frame.getContentPane().add(comboBox_Loading);
		
	}
	
	public static void comment_course_information(String comment_course_ID)  throws ClassNotFoundException {
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";
		try  {
			//sqlserver連線
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded!");
			Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");
			
			Statement statement = connection.createStatement();
			String course_info_selectSql = "SELECT Year_Semester,[FinalProject].[dbo].[ENROLL].Course_ID,CourseName,Class_Session,Credit,Teacher FROM [FinalProject].[dbo].[ENROLL],[FinalProject].[dbo].[STUDENT],[FinalProject].[dbo].[COURSE] WHERE [FinalProject].[dbo].[ENROLL].Student_ID ="+login_page.getStudent_id()+" AND [FinalProject].[dbo].[ENROLL].Course_ID = "+comment_course_ID;
			
			ResultSet course_info_resultSet = statement.executeQuery(course_info_selectSql);
			JLabel [] course_info_label = {label_Year_Semester_1,label_Course_ID_1,label_CourseName_1,label_Class_Session_1,label_Credit_1,label_Teacher_1};
			while (course_info_resultSet.next()) {
				for (int a =1;a<7;a++) {
					course_info_label[a-1].setText(course_info_resultSet.getString(a));
				}				
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
            label_Year_Semester_1.setText("Error");
            label_Year_Semester_1.setForeground(Color.RED);
            label_Course_ID_1.setText("Error");
            label_Course_ID_1.setForeground(Color.RED);
            label_CourseName_1.setText("Error");
            label_CourseName_1.setForeground(Color.RED);
            label_Class_Session_1.setText("Error");
            label_Class_Session_1.setForeground(Color.RED);
            label_Credit_1.setText("Error");
            label_Credit_1.setForeground(Color.RED);		
            label_Teacher_1.setText("Error");
            label_Teacher_1.setForeground(Color.RED);
        }
	}
	public static void addcomment() throws ClassNotFoundException{
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";
		try  {
			//sqlserver連線
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded!");
			Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");
			Statement statement = connection.createStatement();
			
			//取得送出時間
			DateTimeFormatter comment_time = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			
			//comment
			String comment_ID_selectSql = "SELECT Max(Comment_ID) FROM [FinalProject].[dbo].[COMMENT]";
			ResultSet comment_ID_resultSet = statement.executeQuery(comment_ID_selectSql);
			String db_max_comment_ID = "0000000000";
			int int_comment_ID;
			while(comment_ID_resultSet.next()) {
				db_max_comment_ID = comment_ID_resultSet.getString(1);
			}
			if(db_max_comment_ID!=null) {
				int_comment_ID = Integer.parseInt(db_max_comment_ID)+1;
			}
			else {
				int_comment_ID = 1;
			}
			String comment_ID = String.format("%010d", int_comment_ID);

			PreparedStatement comment_insertSql= connection.prepareStatement("INSERT INTO COMMENT VALUES(?,?,?,?,?,?,?,?,?)");
			comment_insertSql.setString(1,comment_ID);
			comment_insertSql.setString(2,comment_course_ID);
			comment_insertSql.setString(3,login_page.getStudent_id());
			comment_insertSql.setString(4,comment_time.format(LocalDateTime.now()));
			comment_insertSql.setString(5,label_Year_Semester_1.getText());
			comment_insertSql.setString(6,textArea.getText());	
			comment_insertSql.setString(7,comboBox_Recommended_Rate.getSelectedItem().toString());	
			comment_insertSql.setString(8,comboBox_Sweetness.getSelectedItem().toString());	
			comment_insertSql.setString(9,comboBox_Loading.getSelectedItem().toString());
			comment_insertSql.execute();
			
			//comment status
			String comment_status_updateSql = "UPDATE [FinalProject].[dbo].[ENROLL] SET Comment_Status = '填寫' WHERE Student_ID = "+login_page.getStudent_id()+" AND [FinalProject].[dbo].[ENROLL].Course_ID = "+comment_course_ID;
			statement.executeUpdate(comment_status_updateSql);
			
			//bonus
			String bonus_ID_selectSql = "SELECT Max(bonus_ID) FROM [FinalProject].[dbo].[BONUS]";
			ResultSet bonus_ID_resultSet = statement.executeQuery(bonus_ID_selectSql);
			
			String db_max_bonus_ID = "000000000";
			int int_bonus_ID;
			while(bonus_ID_resultSet.next()) {
				db_max_bonus_ID = bonus_ID_resultSet.getString(1);
			}
			if(db_max_bonus_ID!=null) {
				db_max_bonus_ID = db_max_bonus_ID.substring(1,10);
				int_bonus_ID = Integer.parseInt(db_max_bonus_ID)+1;
			}
			else {
				int_bonus_ID = 1;
			}
			String bonus_ID = String.format("B%09d", int_bonus_ID);
			
			int comment_bonus_point = 5; //comment 點數(可更改)
			
			int bonus_balance = Integer.parseInt(info.label_point.getText())+comment_bonus_point;
			
			PreparedStatement bonus_insertSql= connection.prepareStatement("INSERT INTO BONUS VALUES(?,?,?,?,?,?,?)");
			bonus_insertSql.setString(1,bonus_ID);
			bonus_insertSql.setString(2,login_page.getStudent_id());
			bonus_insertSql.setString(3,comment_ID);
			bonus_insertSql.setString(4,null);
			bonus_insertSql.setInt(5,comment_bonus_point);
			bonus_insertSql.setString(6,comment_time.format(LocalDateTime.now()));
			bonus_insertSql.setInt(7,bonus_balance);
			bonus_insertSql.execute();
			
			//point change 
			PreparedStatement comment_point_updateSql= connection.prepareStatement("UPDATE [FinalProject].[dbo].[STUDENT] SET Point =  (SELECT Point FROM STUDENT) +? WHERE Student_ID = ?");
			comment_point_updateSql.setString(1,Integer.toString(comment_bonus_point));
			comment_point_updateSql.setString(2,login_page.getStudent_id());
			comment_point_updateSql.executeUpdate();
			
			info.createTable();
			
		}catch (SQLException e) {
            e.printStackTrace();
            label_Year_Semester_1.setText("Error");
            label_Year_Semester_1.setForeground(Color.RED);
            label_Course_ID_1.setText("Error");
            label_Course_ID_1.setForeground(Color.RED);
            label_CourseName_1.setText("Error");
            label_CourseName_1.setForeground(Color.RED);
            label_Class_Session_1.setText("Error");
            label_Class_Session_1.setForeground(Color.RED);
            label_Credit_1.setText("Error");
            label_Credit_1.setForeground(Color.RED);		
            label_Teacher_1.setText("Error");
            label_Teacher_1.setForeground(Color.RED);
        }	
	}
	
	public static void comment_information(String comment_ID)  throws ClassNotFoundException {
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";
		try  {
			//sqlserver連線
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded!");
			Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");
			
			Statement statement = connection.createStatement();
			String comment_info_selectSql = "SELECT Comment_Text,Recommended_Rate,Sweetness,Loading FROM [FinalProject].[dbo].[COMMENT] WHERE Comment_ID = "+comment_ID;
			
			ResultSet comment_info_resultSet = statement.executeQuery(comment_info_selectSql);
			while (comment_info_resultSet.next()) {
					textArea.setText(comment_info_resultSet.getString(1));
					comboBox_Recommended_Rate.setSelectedItem(comment_info_resultSet.getString(2));
					comboBox_Sweetness.setSelectedItem(comment_info_resultSet.getString(3));
					comboBox_Loading.setSelectedItem(comment_info_resultSet.getString(4));			
			}
				
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public static void changecomment() throws ClassNotFoundException{
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";
		try  {
			//sqlserver連線
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded!");
			Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");
			Statement statement = connection.createStatement();
			
			//取得修改時間
			DateTimeFormatter comment_update_time = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");			
			
			//comment change
			PreparedStatement comment_updateSql= connection.prepareStatement( "UPDATE [FinalProject].[dbo].[COMMENT] SET Comment_Time = ? ,Comment_Text = ?,Recommended_Rate =? ,Sweetness = ?,Loading=? FROM [FinalProject].[dbo].[COMMENT] WHERE Comment_ID = "+comment_ID);
			comment_updateSql.setString(1,comment_update_time.format(LocalDateTime.now()));
			comment_updateSql.setString(2,textArea.getText());
			comment_updateSql.setString(3,comboBox_Recommended_Rate.getSelectedItem().toString());
			comment_updateSql.setString(4,comboBox_Sweetness.getSelectedItem().toString());
			comment_updateSql.setString(5,comboBox_Loading.getSelectedItem().toString());
			comment_updateSql.executeUpdate();
							
			info.createTable();
			
		}catch (SQLException e) {
            e.printStackTrace();
            label_Year_Semester_1.setText("Error");
            label_Year_Semester_1.setForeground(Color.RED);
            label_Course_ID_1.setText("Error");
            label_Course_ID_1.setForeground(Color.RED);
            label_CourseName_1.setText("Error");
            label_CourseName_1.setForeground(Color.RED);
            label_Class_Session_1.setText("Error");
            label_Class_Session_1.setForeground(Color.RED);
            label_Credit_1.setText("Error");
            label_Credit_1.setForeground(Color.RED);		
            label_Teacher_1.setText("Error");
            label_Teacher_1.setForeground(Color.RED);
        }	
	}
	
	
	
}
