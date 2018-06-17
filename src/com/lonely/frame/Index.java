package com.lonely.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Index extends JFrame {

	/** 面板 */
	private JPanel contentPane;

	/** 菜单栏 */
	private JMenuBar menuBar;

	/** 文件菜单 */
	private JMenu fileMenu;

	/** 文件菜单下的新连接菜单 */
	private JMenu newConnMenu;

	/** 新连接菜单下得 sqlserver连接菜单项 */
	private JMenuItem sqlServerConnMenuItem;

	/** 新连接菜单下得 mysql连接菜单项 */
	private JMenuItem mysqlConnMenuItem;

	/** 新连接菜单下得 oracle连接菜单项 */
	private JMenuItem oracleConnMenuItem;

	/** 文件菜单下的打开连接选项 */
	private JMenuItem openConnMenu;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MyFrame.setUpSkin();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
					// 添加监听器
					frame.addListener();

					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Index() {
		setTitle("DUGUyog");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/1.jpg"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 930, 596);

		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(SystemColor.activeCaption);
		menuBar.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		menuBar.setFont(new Font("华文彩云", Font.BOLD, 20));
		setJMenuBar(menuBar);

		fileMenu = new JMenu("文件");
		menuBar.add(fileMenu);

		newConnMenu = new JMenu("新连接");
		newConnMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		fileMenu.add(newConnMenu);

		sqlServerConnMenuItem = new JMenuItem("SqlServer");
		newConnMenu.add(sqlServerConnMenuItem);

		mysqlConnMenuItem = new JMenuItem("MySql");
		newConnMenu.add(mysqlConnMenuItem);

		oracleConnMenuItem = new JMenuItem("Oracle");
		newConnMenu.add(oracleConnMenuItem);

		openConnMenu = new JMenuItem("打开连接");
		fileMenu.add(openConnMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		desktopPane.setBorder(null);
		desktopPane.setBackground(new Color(0, 51, 102));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
	}

	/**
	 * 添加监听事件
	 */
	public void addListener() {

		mysqlConnMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				NewMySqlConnInternalFrame newMySqlConnInternalFrame = new NewMySqlConnInternalFrame(Index.this);
				newMySqlConnInternalFrame.setVisible(true);
				desktopPane.add(newMySqlConnInternalFrame);
				try {
					newMySqlConnInternalFrame.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}

			}
		});
	}
}
