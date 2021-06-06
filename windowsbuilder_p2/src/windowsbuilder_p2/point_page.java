package windowsbuilder_p2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class point_page {

	static JFrame frame;
	public static JTable temptable;
	public static JLabel label_student_id;
	public static JLabel label_studentName;
	public static JLabel label_major;
	public static JLabel label_grade;
	public static JLabel label_point;
	static info window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					point_page window = new point_page();
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
	public point_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("點數紀錄查詢");
		frame.getContentPane().setBackground(new Color(153, 204, 204));
		frame.setBounds(150, 50, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		//frame.getContentPane().add(readdata);
		
		JPanel topPane = new JPanel();
		topPane.setBackground(new Color(153, 204, 204));
        frame.getContentPane().add(topPane, BorderLayout.NORTH);
        		
        				JButton back = new JButton("登出");
        				back.setForeground(Color.DARK_GRAY);
        				back.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 153, 0), new Color(204, 204, 204)));
        				back.setBackground(new Color(255, 153, 102));
        				back.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        				back.setAlignmentX(Component.CENTER_ALIGNMENT);
        				back.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						login_page newinfos = new login_page();
        						newinfos.frame.setVisible(true);
        						window.frame.setVisible(false);
        					}

        					
        				});
        		
        		JLabel lblNewLabel = new JLabel("NCCU 課程評論系統");
        		lblNewLabel.setForeground(Color.DARK_GRAY);
        		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        		
        		JLabel lblNewLabel_1 = new JLabel("\u9EDE\u6578\u8A18\u9304\u67E5\u8A62");
        		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        		lblNewLabel_1.setForeground(Color.DARK_GRAY);
        		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        		GroupLayout gl_topPane = new GroupLayout(topPane);
        		gl_topPane.setHorizontalGroup(
        			gl_topPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_topPane.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
        					.addGap(133)
        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
        					.addComponent(back, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
        		);
        		gl_topPane.setVerticalGroup(
        			gl_topPane.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_topPane.createParallelGroup(Alignment.BASELINE)
        					.addComponent(back, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        		);
        		topPane.setLayout(gl_topPane);
        
        Panel panel = new Panel();
        panel.setBackground(new Color(255, 102, 102));
        frame.getContentPane().add(panel, BorderLayout.WEST);
        
        label_student_id = new JLabel("學號");
        label_student_id.setForeground(Color.DARK_GRAY);
        label_student_id.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_student_id.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label_student_id.setHorizontalAlignment(SwingConstants.CENTER);
        
        label_major = new JLabel("科系");
        label_major.setForeground(Color.DARK_GRAY);
        label_major.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_major.setHorizontalAlignment(SwingConstants.CENTER);
        label_major.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        
        label_studentName = new JLabel("姓名");
        label_studentName.setForeground(Color.DARK_GRAY);
        label_studentName.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_studentName.setHorizontalAlignment(SwingConstants.CENTER);
        label_studentName.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        
        label_grade = new JLabel("年級");
        label_grade.setForeground(Color.DARK_GRAY);
        label_grade.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_grade.setHorizontalAlignment(SwingConstants.CENTER);
        label_grade.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        
        label_point = new JLabel("");
        label_point.setForeground(Color.DARK_GRAY);
        label_point.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_point.setHorizontalAlignment(SwingConstants.CENTER);
        label_point.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        
        JButton button_back_course = new JButton("\u8AB2\u7A0B\u8A55\u8AD6\u7BA1\u7406");
        button_back_course.setForeground(Color.DARK_GRAY);
        button_back_course.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 153, 0), new Color(204, 204, 204)));
        button_back_course.setBackground(new Color(255, 153, 102));
        button_back_course.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_back_course.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        button_back_course.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.frame.setVisible(false);
				window.frame.dispose();
				info newinfos = new info();
				newinfos.frame.setVisible(true);
				//window.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));	
			}
		});
        
        JLabel label_point1 = new JLabel("點數");
        label_point1.setForeground(Color.DARK_GRAY);
        label_point1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_point1.setHorizontalAlignment(SwingConstants.CENTER);
        label_point1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(label_point, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        				.addComponent(button_back_course, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        				.addComponent(label_point1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        				.addComponent(label_student_id, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        				.addComponent(label_major, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        				.addComponent(label_grade, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        				.addComponent(label_studentName, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(label_studentName, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(label_student_id, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(label_major, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(label_grade, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        			.addGap(103)
        			.addComponent(label_point1, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(label_point, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(button_back_course, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        			.addGap(27))
        );
        panel.setLayout(gl_panel);
        
        try {
			connection_sqlserver();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		
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
			//sqlserver連線
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded!");
			final Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");	
			final Statement statement = connection.createStatement();

			createTable();
			
			//frame.setSize(300, 400);  
			//frame.setVisible(true);
	        
	        //student_info
			String student_info_selectSql = "SELECT Student_ID,Student_Name,Major,Grade,Point FROM [FinalProject].[dbo].[STUDENT] WHERE Student_ID = "+login_page.getStudent_id();

			ResultSet student_info_resultSet = statement.executeQuery(student_info_selectSql);

			JLabel [] student_info_label = {label_student_id,label_studentName,label_major,label_grade,label_point};

			while (student_info_resultSet.next()) {
				for (int a =1;a<6;a++) {
					student_info_label[a-1].setText(student_info_resultSet.getString(a));
				}				
			}
	        
	        
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
			label_student_id.setText("Error");
			label_student_id.setForeground(Color.RED);
			label_studentName.setText("Error");
			label_studentName.setForeground(Color.RED);
			label_major.setText("Error");
			label_major.setForeground(Color.RED);
			label_grade.setText("Error");
			label_grade.setForeground(Color.RED);
			label_point.setText("Error");
			label_point.setForeground(Color.RED);		
        }
	}
	
	public static void createTable() throws ClassNotFoundException {
		String connectionUrl ="jdbc:sqlserver://localhost;databaseName=FinalProject;integratedSecurity=true;";

		try  {
			//sqlserver連線
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded!");
			Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("MSSQL Connection Success");
			//enroll
			Statement statement = connection.createStatement();
			String table_selectSql = 
					"(SELECT Bonus_Time As 'Time',Bonus_ID AS Point_ID,(case when Bonus_ID is null then '空值' else 'Bonus' end) as Type,(case when Exam_ID is null then '填寫評論' else '提供考古題' end) as Title,Point,Bonus_Balance FROM BONUS WHERE Bonus.Student_ID = " +login_page.getStudent_id()+ 
					" UNION" + 
					" SELECT Purchased_Time As 'Time' ,Purchased_ID AS Point_ID,(case when Purchased_ID is null then '空值' else 'Purchase' end) as Type,(case when Purchased_ID is null then '空值' else '兌換考古題' end) as Title,Price,Purchased_Balance FROM PURCHASE,PAST_EXAM  WHERE PURCHASE.Student_ID = "+login_page.getStudent_id()+
					") ORDER BY 'Time' DESC";
			System.out.println(table_selectSql);
	
			ResultSet resultSet = statement.executeQuery(table_selectSql);
			String[] columnNames = {"時間","點數編號","類型","摘要","點數","點數餘額"};
			String[][] datas = new String[25][25];
			int i = 0;
			while (resultSet.next()) {
				//System.out.println(resultSet.getString("Year_Semester") + "    " + resultSet.getString("Course_ID") + "   "+ resultSet.getString("CourseName") + "   " + resultSet.getString("Teacher")+ "   " + resultSet.getString("Lecture_Language"));
				//System.out.println();
				String[] tempdata = new String[20];
				for (int ii = 1; ii < 7; ii++) {
					System.out.println(resultSet.getString(ii));
					tempdata[ii-1] = resultSet.getString(ii);
				}
				datas[i]=tempdata;
				i++;
			}
			
			
			temptable = new JTable(datas, columnNames){
				public boolean isCellEditable(int row, int column){ //無法編輯
					return false;
				}
			};
			
			DefaultTableCellRenderer  renderer  =  new  DefaultTableCellRenderer();   //set column align center
			renderer.setHorizontalAlignment(JTextField.CENTER);
			for (int a=0;a<=5;a++) {
				temptable.getColumnModel().getColumn(a).setCellRenderer(renderer);
			}
			
			temptable.setRowHeight(30);           
			temptable.setCellSelectionEnabled(true);
			//temptable.setBounds(30, 94, 315, 157);
			JScrollPane scroll = new JScrollPane(temptable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			temptable.setPreferredScrollableViewportSize(new Dimension(550,470));
			temptable.setFillsViewportHeight(true); 
			JPanel tablePane = new JPanel();
	        tablePane.add(scroll);
	        frame.getContentPane().add(tablePane, BorderLayout.EAST);
		
	}
    // Handle any errors that may have occurred.
    catch (SQLException e) {
        e.printStackTrace();
    }
	
	}
}
