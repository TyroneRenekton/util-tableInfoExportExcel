package com.lonely.frame;

import javax.swing.UIManager;

/**
 * Frame基础设置
 * 
 * @author 15072
 *
 */
public class MyFrame {

	public static void setUpSkin() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
