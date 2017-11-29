package cn.xuyingqi.serial.port.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * 内容面板
 * 
 * @author XuYQ
 *
 */
public class Content extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 内容面板
	 */
	public Content() {

		// 初始化
		this.init();
	}

	/**
	 * 初始化
	 */
	private void init() {

		// 设置显示
		this.setVisible(true);

		// 添加面板
		this.addPanel();
	}

	/**
	 * 添加面板
	 */
	private void addPanel() {

		// 实例化接收面板
		Receive receive = new Receive();
		// 添加接收面板
		this.add(receive);

		// 实例化发送面板
		Send send = new Send();
		// 添加发送面板
		this.add(send);

		// 实例化网格包布局
		GridBagLayout layout = new GridBagLayout();
		// 设置布局
		this.setLayout(layout);
		// 布局配置
		GridBagConstraints gbc = new GridBagConstraints();

		//
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		layout.setConstraints(receive, gbc);

		gbc.gridwidth = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;
		layout.setConstraints(send, gbc);
	}
}