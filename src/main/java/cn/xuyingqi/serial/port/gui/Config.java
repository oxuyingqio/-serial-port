package cn.xuyingqi.serial.port.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import cn.xuyingqi.serial.port.model.Current;
import cn.xuyingqi.serial.port.model.SerialPortDeal;
import cn.xuyingqi.serial.port.model.Subject;
import cn.xuyingqi.serial.port.util.SerialPortUtil;
import gnu.io.SerialPort;

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

		// 端口号
		this.add(new JLabel("端口号："));
		// 实例化下拉列表选择框
		JComboBox<String> jcb1 = new JComboBox<String>();
		// 遍历可用端口名称集合
		for (String commPort : SerialPortUtil.findCommPorts()) {

			jcb1.addItem(commPort);
		}
		// 添加下拉列表选择框
		this.add(jcb1);

		// 波特率
		this.add(new JLabel("波特率："));
		// 实例化下拉列表选择框
		JComboBox<Integer> jcb2 = new JComboBox<Integer>();
		// 遍历波特率集合
		for (int i = 0; i < Current.getInstance().getBaudRates().length; i++) {

			jcb2.addItem(Current.getInstance().getBaudRates()[i]);
		}
		// 设置默认值
		jcb2.setSelectedItem(Current.getInstance().getLastBaudRate());
		// 添加事件
		jcb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 判断对象是否为波特率下拉列表选择框
				if (e.getSource() == jcb2) {

					// 记录当前值
					Current.getInstance().setLastBaudRate((int) jcb2.getSelectedItem());
				}
			}
		});
		// 添加下拉列表选择框
		this.add(jcb2);

		// 打开串口
		JButton jb1 = new JButton("打开串口");
		// 添加事件
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == jb1) {

					// 端口名
					String commPortName = (String) jcb1.getSelectedItem();
					// 波特率
					int baudRate = (int) jcb2.getSelectedItem();

					try {

						// 打开串口
						SerialPort serialPort = SerialPortUtil.openSerialPort(commPortName, baudRate,
								Current.getInstance().getTimeout());

						// 通知
						Subject.getInstance().notifyObservers("串口已打开");

						// 设置串口
						Current.getInstance().setSerialPort(serialPort);
						// 新起线程监听端口信息
						new Thread(new Runnable() {

							@Override
							public void run() {

								try {

									new SerialPortDeal(serialPort).deal();
								} catch (Exception ex) {

									// 通知异常
									Subject.getInstance().notifyObservers(ex.getMessage());
								}
							}
						}).start();
					} catch (Exception ex) {

						// 通知异常
						Subject.getInstance().notifyObservers(ex.getMessage());
					}
				}
			}
		});
		// 添加打开串口
		this.add(jb1);

		// 关闭串口
		JButton jb2 = new JButton("关闭串口");
		// 添加事件
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == jb2) {

					if (Current.getInstance().getSerialPort() != null) {

						SerialPortUtil.closeSerialPort(Current.getInstance().getSerialPort());
						Current.getInstance().setSerialPort(null);
					}

					// 通知
					Subject.getInstance().notifyObservers("串口已关闭");
				}
			}
		});
//		// 添加关闭串口
//		this.add(jb2);

		// 添加标签,占格用
		for (int i = 0; i < (12 * 2 - 6); i++) {

			// 添加标签
			this.add(new JLabel(""));
		}
	}
}