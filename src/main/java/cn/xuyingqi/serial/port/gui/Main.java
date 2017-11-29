package cn.xuyingqi.serial.port.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * 主窗口
 * 
 * @author XuYQ
 *
 */
public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主窗口
	 */
	public Main() {

		// 初始化
		this.init();
	}

	/**
	 * 初始化
	 */
	private void init() {

		// 设置标题
		this.setTitle("XuYQ's Swing 第一课");
		// 设置宽高
		this.setSize(820, 540);
		// 设置是否可改变大小
		this.setResizable(true);
		// 设置居中
		this.setLocationRelativeTo(null);

		// 设置关闭按钮动作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 初始化内容
		this.initContent();
	}

	/**
	 * 初始化内容
	 */
	private void initContent() {

		// 设置布局
		this.setLayout(new BorderLayout(5, 5));

		// 添加配置面板
		this.add(new Config(), BorderLayout.WEST);
		// 添加内容面板
		this.add(new Content(), BorderLayout.CENTER);
	}

	/**
	 * 显示窗口
	 */
	public void open() {

		// 设置显示
		this.setVisible(true);
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Main main = new Main();
		main.open();
	}
}