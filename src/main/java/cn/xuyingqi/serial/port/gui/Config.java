package cn.xuyingqi.serial.port.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * 配置面板
 * 
 * @author XuYQ
 *
 */
public class Config extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 配置面板
	 */
	public Config() {

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
		this.setBorder(new TitledBorder("配置信息"));
		// 设置宽度
		this.setPreferredSize(new Dimension(200, 0));

		// 初始化内容
		this.initContent();
	}

	/**
	 * 初始化内容
	 */
	private void initContent() {

		// 设置布局
		this.setLayout(new GridLayout(12, 2, 5, 5));

		// 
		this.add(new JLabel("端口号："));
		this.add(new JComboBox<>());
		this.add(new JLabel("波特率："));
		this.add(new JComboBox<>());
		this.add(new JButton("打开串口"));
		this.add(new JButton("关闭串口"));

		for (int i = 0; i < (12 * 2 - 6); i++) {

			this.add(new JLabel(""));
		}
	}
}