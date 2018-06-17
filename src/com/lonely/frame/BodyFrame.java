package com.lonely.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

/**
 * 主体信息
 * 
 * @author 15072
 *
 */
public class BodyFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BodyFrame frame = new BodyFrame();
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
	public BodyFrame() {
		setBounds(100, 100, 878, 564);

	}

}
