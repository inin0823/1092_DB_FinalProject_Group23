package windowsbuilder_p2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;

public class info {
	
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
		frame.setTitle("課程評論管理");
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
        		
        		JLabel lblNewLabel_1 = new JLabel("課程評論管理");
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
        
        JButton button_point = new JButton("點數記錄查詢");
        button_point.setForeground(Color.DARK_GRAY);
        button_point.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 153, 0), new Color(204, 204, 204)));
        button_point.setBackground(new Color(255, 153, 102));
        button_point.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_point.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        button_point.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						point_page newpoint_page = new point_page();
						newpoint_page.frame.setVisible(true);
						window.frame.setVisible(false);
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
        				.addComponent(button_point, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
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
        			.addComponent(button_point, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
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
			
	        temptable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int tableColumn=temptable.columnAtPoint(e.getPoint());
					int tableRow=temptable.rowAtPoint(e.getPoint());
					String comment_course_ID = temptable.getValueAt(tableRow,1).toString();
					String comment_ID = "";

					String comment_ID_selectSql = "SELECT Comment_ID FROM [FinalProject].[dbo].[COMMENT] WHERE Student_ID = "+login_page.getStudent_id()+" AND Course_ID = "+comment_course_ID;
					ResultSet comment_ID_resultSet;
					try {
						comment_ID_resultSet = statement.executeQuery(comment_ID_selectSql);
						while(comment_ID_resultSet.next()) {
							comment_ID = comment_ID_resultSet.getString("Comment_ID");
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					if (tableColumn ==6) {					
						if(temptable.getValueAt(tableRow,tableColumn).equals("未填寫")) {
							try {
								course_comment_page course_comment_page = new course_comment_page(comment_course_ID);
								course_comment_page.frame.setVisible(true);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
						}
						else {
							try {
								course_comment_page course_comment_page=new course_comment_page(comment_course_ID,comment_ID);	
								course_comment_page.frame.setVisible(true);
							}
							catch (Exception ex) {
						           ex.getMessage();
						       }
						}
					}		
				   }
			});
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
			String table_selectSql = "SELECT Year_Semester,[FinalProject].[dbo].[ENROLL].Course_ID,CourseName,Class_Session,Credit,Teacher,Comment_Status" + 
					" FROM [FinalProject].[dbo].[ENROLL] " + 
					" INNER JOIN [FinalProject].[dbo].[COURSE]" + 
					" ON ENROLL.Course_ID = COURSE.Course_ID" + 
					" WHERE [FinalProject].[dbo].[ENROLL].Student_ID = "+login_page.getStudent_id();
			System.out.println(table_selectSql);
	
			ResultSet resultSet = statement.executeQuery(table_selectSql);
			String[] columnNames = {"學期","課程編號","課程名稱","上課時間","學分數","授課教師","評論"};
			String[][] datas = new String[20][20];
			int i = 0;
			while (resultSet.next()) {
				//System.out.println(resultSet.getString("Year_Semester") + "    " + resultSet.getString("Course_ID") + "   "+ resultSet.getString("CourseName") + "   " + resultSet.getString("Teacher")+ "   " + resultSet.getString("Lecture_Language"));
				//System.out.println();
				String[] tempdata = new String[20];
				for (int ii = 1; ii < 8; ii++) {			
					System.out.println(resultSet.getString(ii));
					tempdata[ii-1] = resultSet.getString(ii).replace(" ","");
				}
				if (tempdata[6].equals("填寫")) {
					tempdata[6] = "修改";
				}else {
					tempdata[6] = "未填寫";
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
			for (int a=0;a<=6;a++) {
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
