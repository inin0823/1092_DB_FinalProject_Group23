package windowsbuilder_p2;
package search_forum;
package search_info;

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

public class search_page {
	static  JFrame frame;
	private JTextField class_name_input;
	static search_page window;
	public static String class_name;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new search_page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public search_page() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("�ҵ{�d�߭���");	
		frame.getContentPane().setBackground(new Color(153, 204, 204));
		frame.setBounds(150, 50, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�ҵ{�W��");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("�L�n������", Font.BOLD, 20));
		lblNewLabel.setBounds(200, 73, 100, 19);
		frame.getContentPane().add(lblNewLabel);
		
		class_name_input = new JTextField();
		class_name_input.setFont(new Font("�L�n������", Font.BOLD, 18));
		class_name_input.setBounds(300, 70, 192, 25);
		frame.getContentPane().add(class_name_input);
		class_name_input.setColumns(10);
	
		JButton btnNewButton = new JButton("�d��");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(new Color(225, 69, 83));
		btnNewButton.setFont(new Font("�L�n������", Font.BOLD, 20));
		btnNewButton.setBounds(510, 65, 100, 35);
		frame.getContentPane().add(btnNewButton);
	}}
