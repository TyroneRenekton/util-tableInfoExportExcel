package com.lonely.frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lonely.pojo.MySqlEntity;
import com.lonely.service.BaseService;
import com.lonely.service.MySqlService;
import com.lonely.util.StringUtils;

/**
 * mysql新连接界面
 * 
 * @author 15072
 *
 */
@SuppressWarnings("serial")
public class NewMySqlConnInternalFrame extends JInternalFrame {
	private JTextField addressTxt;
	private JTextField usernameTxt;
	private JTextField portTxt;
	private JTextField dbTxt;
	private JPasswordField passwordTxt;
	private JButton connBtn;
	private JButton cancelBtn;
	private JButton testConnBtn;

	@SuppressWarnings("unused")
	private JFrame parentFrame;
	private BaseService baseService = new MySqlService();
	private JTextField tableNameTxt;;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// MyFrame.setUpSkin();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewMySqlConnInternalFrame frame = new NewMySqlConnInternalFrame();
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
	public NewMySqlConnInternalFrame() {
		setBounds(100, 100, 812, 571);
		getContentPane().setLayout(null);

		JLabel addressLbl = new JLabel("主机地址：");
		addressLbl.setFont(new Font("Dialog", Font.BOLD, 16));
		addressLbl.setBounds(178, 89, 100, 31);
		getContentPane().add(addressLbl);

		addressTxt = new JTextField();
		addressTxt.setText("localhost");
		addressTxt.setFont(new Font("Dialog", Font.BOLD, 16));
		addressTxt.setColumns(10);
		addressTxt.setBounds(282, 86, 285, 36);
		getContentPane().add(addressTxt);

		JLabel usernameLbl = new JLabel("用户名：");
		usernameLbl.setFont(new Font("Dialog", Font.BOLD, 16));
		usernameLbl.setBounds(195, 150, 83, 31);
		getContentPane().add(usernameLbl);

		usernameTxt = new JTextField();
		usernameTxt.setText("root");
		usernameTxt.setFont(new Font("Dialog", Font.BOLD, 16));
		usernameTxt.setColumns(10);
		usernameTxt.setBounds(282, 147, 285, 36);
		getContentPane().add(usernameTxt);

		JLabel passwordLbl = new JLabel("密    码：");
		passwordLbl.setFont(new Font("Dialog", Font.BOLD, 16));
		passwordLbl.setBounds(195, 205, 83, 31);
		getContentPane().add(passwordLbl);

		JLabel portLbl = new JLabel("端    口：");
		portLbl.setFont(new Font("Dialog", Font.BOLD, 16));
		portLbl.setBounds(195, 261, 83, 31);
		getContentPane().add(portLbl);

		portTxt = new JTextField();
		portTxt.setText("3306");
		portTxt.setFont(new Font("Dialog", Font.BOLD, 16));
		portTxt.setColumns(10);
		portTxt.setBounds(282, 258, 186, 36);
		getContentPane().add(portTxt);

		JLabel dbLbl = new JLabel("数据库：");
		dbLbl.setFont(new Font("Dialog", Font.BOLD, 16));
		dbLbl.setBounds(195, 317, 83, 31);
		getContentPane().add(dbLbl);

		dbTxt = new JTextField();
		dbTxt.setFont(new Font("Dialog", Font.BOLD, 16));
		dbTxt.setColumns(10);
		dbTxt.setBounds(282, 314, 285, 36);
		getContentPane().add(dbTxt);

		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("Dialog", Font.BOLD, 16));
		passwordTxt.setBounds(282, 202, 186, 36);
		getContentPane().add(passwordTxt);

		JCheckBox savePassCbox = new JCheckBox("保存密码");
		savePassCbox.setFont(new Font("Dialog", Font.BOLD, 16));
		savePassCbox.setBounds(473, 202, 107, 36);
		getContentPane().add(savePassCbox);

		JLabel label_5 = new JLabel(
				"------------------------------------------------------------------------------------");
		label_5.setBounds(51, 442, 696, 31);
		getContentPane().add(label_5);

		connBtn = new JButton("导出excel");

		connBtn.setBounds(119, 486, 120, 36);
		getContentPane().add(connBtn);

		cancelBtn = new JButton("取消");

		cancelBtn.setBounds(333, 486, 120, 36);
		getContentPane().add(cancelBtn);

		testConnBtn = new JButton("测试连接");

		testConnBtn.setBounds(543, 486, 120, 36);
		getContentPane().add(testConnBtn);

		JLabel label_6 = new JLabel("MySql");
		label_6.setFont(new Font("Dialog", Font.BOLD, 36));
		label_6.setBounds(361, 13, 107, 46);
		getContentPane().add(label_6);

		JLabel tableNameLbl = new JLabel("表    名：");
		tableNameLbl.setFont(new Font("Dialog", Font.BOLD, 16));
		tableNameLbl.setBounds(195, 378, 83, 31);
		getContentPane().add(tableNameLbl);

		tableNameTxt = new JTextField();
		tableNameTxt.setFont(new Font("Dialog", Font.BOLD, 16));
		tableNameTxt.setColumns(10);
		tableNameTxt.setBounds(282, 375, 285, 36);
		getContentPane().add(tableNameTxt);
	}

	private boolean validate(String address, String username, String password) {
		if (address == null || address.trim().equals("")) {
			JOptionPane.showInternalMessageDialog(getParent(), "主机地址不能为空");
		} else if (username == null || username.trim().equals("")) {
			JOptionPane.showInternalMessageDialog(getParent(), "用户名不能为空");
		} else if (password == null || password.trim().equals("")) {
			JOptionPane.showInternalMessageDialog(getParent(), "密码不能为空");
		} else {
			return true;
		}
		return false;
	}

	public NewMySqlConnInternalFrame(JFrame parentFrame) {
		this();
		this.parentFrame = parentFrame;

		// 点击连接按钮
		connBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String address = addressTxt.getText();
				String username = usernameTxt.getText();
				String password = new String(passwordTxt.getPassword());
				String port = portTxt.getText();
				String db = dbTxt.getText();
				String tableName = tableNameTxt.getText();
				List<String> dataBases = null;
				List<String> tables = null;
				boolean isVali = NewMySqlConnInternalFrame.this.validate(address, username, password);
				if (isVali) {
					String url = "jdbc:mysql://" + address + ":" + port + "/" + db + "?serverTimezone=UTC";
					String msg = null;
					try {
						// 获取数据库连接
						Connection connection = baseService.getConnection(new MySqlEntity(url, username, password));
						// 判断是否有指定数据库
						if (StringUtils.isNullOrEmpty(db)) {
							// 没有指定数据库，则显示所有数据库信息
							dataBases = baseService.showDataBases(connection);
							dataBases.stream().forEach(o -> System.out.println(o));
						} else {
							dataBases = new ArrayList<>();
							dataBases.add(db);
						}

						// 判断是否有指定表名
						if (!StringUtils.isNullOrEmpty(tableName)) {
							tables = new ArrayList<>();
							String[] tt = tableName.split(",");
							for (String string : tt) {
								tables.add(string);
							}
						}

						// 隐藏自己
						// NewMySqlConnInternalFrame.this.setVisible(false);

						// 输出
						baseService.exportToExcel(connection, dataBases, tables);

						// 显示body
						/*
						 * BodyFrame bodyFrame = new BodyFrame();
						 * bodyFrame.setVisible(true); bodyFrame.show();
						 * parentFrame.add(bodyFrame);
						 */
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
						msg = e1.getMessage();
					} catch (SQLException e1) {
						e1.printStackTrace();
						msg = e1.getMessage();
					}
					if (msg != null) {
						JOptionPane.showInternalMessageDialog(getParent(), msg);
					}
				}
			}
		});

		// 点击测试连接按钮
		testConnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String address = addressTxt.getText();
				String username = usernameTxt.getText();
				String password = new String(passwordTxt.getPassword());
				String port = portTxt.getText();
				String db = dbTxt.getText();

				boolean isVali = NewMySqlConnInternalFrame.this.validate(address, username, password);
				if (isVali) {
					String url = "jdbc:mysql://" + address + ":" + port + "/" + db + "?serverTimezone=UTC";
					String msg = baseService.prepareConnection(new MySqlEntity(url, username, password));
					JOptionPane.showInternalMessageDialog(getParent(), msg);
				}
			}
		});

		// 点击取消按钮
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 关闭当前窗口
				NewMySqlConnInternalFrame.this.setVisible(false);
			}
		});
	}
}
