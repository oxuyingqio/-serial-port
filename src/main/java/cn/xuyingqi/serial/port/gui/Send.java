package cn.xuyingqi.serial.port.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * 发送面板
 * 
 * @author XuYQ
 *
 */
public class Send extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 发送面板
	 */
	public Send() {

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
		this.setBorder(new TitledBorder("发送"));

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
		this.add(new JButton("发送"), BorderLayout.EAST);
	}
}