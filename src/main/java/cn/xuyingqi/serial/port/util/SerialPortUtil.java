package cn.xuyingqi.serial.port.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import cn.xuyingqi.util.util.ListFactory;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

/**
 * 串口工具
 * 
 * @author XuYQ
 *
 */
public class SerialPortUtil {

	/**
	 * 查找可用端口名称集合
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final List<String> findCommPorts() {

		// 可用端口名称集合
		List<String> commPorts = ListFactory.newInstance();

		// 获得可用端口集合
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		// 遍历可用端口集合
		while (portList.hasMoreElements()) {

			commPorts.add(portList.nextElement().getName());
		}

		return commPorts;
	}

	/**
	 * 打开串口
	 * 
	 * @param commPortName
	 * @param baudRate
	 * @param timeout
	 * @return
	 */
	public static final SerialPort openSerialPort(String commPortName, int baudRate, int timeout) {

		try {

			// 通过端口名称识别端口
			CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(commPortName);
			// 打开端口
			CommPort commPort = commPortIdentifier.open(commPortName, timeout);

			// 判断端口是否为串口
			if (commPort instanceof SerialPort) {

				// 强转为串口对象
				SerialPort serialPort = (SerialPort) commPort;

				// 设置串口波特率等参数
				serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);

				return serialPort;
			} else {

				throw new RuntimeException("端口指向设备非串口类型");
			}
		} catch (UnsupportedCommOperationException e) {

			throw new RuntimeException("设置串口参数失败");
		} catch (NoSuchPortException e) {

			throw new RuntimeException("端口无对应串口设备");
		} catch (PortInUseException e) {

			throw new RuntimeException("端口已被占用");
		}
	}

	/**
	 * 关闭串口
	 * 
	 * @param serialPort
	 *            串口
	 */
	public static void closeSerialPort(SerialPort serialPort) {

		if (serialPort != null) {

			try {

				serialPort.getInputStream().close();
				serialPort.getOutputStream().close();
				serialPort.close();
				serialPort = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 往串口发送数据
	 * 
	 * @param serialPort
	 *            串口对象
	 * @param data
	 *            待发送数据
	 */
	public static void sendToPort(SerialPort serialPort, byte[] data) {

		// 串口输出流
		OutputStream out = null;

		try {

			// 获取串口输出流
			out = serialPort.getOutputStream();
			// 发送数据
			out.write(data);
			// 提交数据
			out.flush();
		} catch (IOException e) {

			throw new RuntimeException("向串口发送数据异常");
		} finally {

			try {

				if (out != null) {

					out.close();
					out = null;
				}
			} catch (IOException e) {

				throw new RuntimeException("关闭串口输出流异常");
			}
		}
	}

	/**
	 * 从串口读取数据
	 * 
	 * @param serialPort
	 *            当前已建立连接的SerialPort对象
	 */
	public static final void readSerialPort(SerialPort serialPort) {

		// 串口输入流
		InputStream is = null;
		// 串口缓冲输入流
		BufferedInputStream bis = null;

		try {

			// 获取串口输入流
			is = serialPort.getInputStream();
			// 获取串口缓冲输入流
			bis = new BufferedInputStream(is);

			// 获取终端输入的字节数组
			byte[] msg = new byte[1024 * 1024];
			// 读取的字节数组长度
			int length = 0;
			// 阻塞式读取终端数据
			while ((length = bis.read(msg)) != -1) {

				if (length != 0) {

					System.out.println(Arrays.toString(ArrayUtils.subarray(msg, 0, length)));
				}
			}
		} catch (IOException e) {

			throw new RuntimeException("读取串口数据异常");
		} finally {

			try {

				if (is != null) {

					is.close();
					is = null;
				}
			} catch (IOException e) {

				throw new RuntimeException("关闭串口输入流异常");
			}
		}
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> commPorts = SerialPortUtil.findCommPorts();
		System.out.println("当前可用端口：" + commPorts);
		SerialPort serialPort = SerialPortUtil.openSerialPort(commPorts.get(0), 1200, 10);
		System.out.println("打开了？" + serialPort);

		new Thread(new Runnable() {

			@Override
			public void run() {

				int i = 0;
				while (i < 100) {

					System.out.println("第" + i + "次");
					SerialPortUtil.sendToPort(serialPort,
							new byte[] { (byte) i, (byte) i, (byte) i, (byte) i, (byte) i });
					i++;

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		SerialPortUtil.readSerialPort(serialPort);
	}
}