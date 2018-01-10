package cn.xuyingqi.serial.port.gui;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import cn.xuyingqi.serial.port.model.Observer;
import cn.xuyingqi.serial.port.model.Subject;

/**
 * 接收面板
 * 
 * @author XuYQ
 *
 */
public class Receive extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(Observer.class);

	/**
	 * 多行文本框
	 */
	private JTextArea jTextArea;

	/**
	 * 接收面板
	 */
	public Receive() {

		// 初始化
		this.init();

		// 注册观察者
		Subject.getInstance().addObserver(this);
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

		// 实例化多行文本框
		this.jTextArea = new JTextArea();
		// 添加多行文本输入框
		this.add(new JScrollPane(this.jTextArea), BorderLayout.CENTER);
	}

	@Override
	public void notify(String msg) {

		LOGGER.info(msg);
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		sb.append("] ");
		sb.append(msg);
		sb.append("\n");
		this.jTextArea.append(sb.toString());
	}
}