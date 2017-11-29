package cn.xuyingqi.serial.port.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import cn.xuyingqi.serial.port.model.Value;
import cn.xuyingqi.serial.port.model.Subject;
import cn.xuyingqi.serial.port.util.SerialPortUtil;
import cn.xuyingqi.util.util.ByteUtils;

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

		// 实例化多行文本输入框
		JTextArea jta = new JTextArea();
		// 添加多行文本输入框
		this.add(new JScrollPane(jta), BorderLayout.CENTER);

		// 实例化发送按钮
		JButton jb = new JButton("发送");
		// 添加事件
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == jb) {

					new Thread(new Runnable() {

						@Override
						public void run() {

							if (Value.getInstance().getSerialPort() != null) {

								try {

									SerialPortUtil.sendToPort(Value.getInstance().getSerialPort(),
											ByteUtils.doubleHexString2ByteArray(jta.getText().trim().replace(" ", "")));

									jta.setText("");
								} catch (Exception ex) {

									// 通知异常
									Subject.getInstance().notifyObservers(ex.getMessage());
								}
							} else {

								// 通知异常
								Subject.getInstance().notifyObservers("串口未打开");
							}
						}
					}).start();
				}
			}
		});
		// 添加发送按钮
		this.add(jb, BorderLayout.EAST);
	}
}