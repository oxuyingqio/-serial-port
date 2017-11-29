package cn.xuyingqi.serial.port.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * 接收面板
 * 
 * @author XuYQ
 *
 */
public class Receive extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 接收面板
	 */
	public Receive() {

		// 初始化
		this.init();
	}

	/**
	 * 初始化
	 */
	private void init() {

		// 设置显示
		this.setVisible(true);

		// 设置标题表框
		this.setBorder(new TitledBorder("接收"));

		// 添加面板
		this.addPanel();
	}

	/**
	 * 添加面板
	 */
	private void addPanel() {

		// 设置布局
		this.setLayout(new BorderLayout());

		// 添加多行文本输入框
		this.add(new JScrollPane(new JTextArea()), BorderLayout.CENTER);
	}
}