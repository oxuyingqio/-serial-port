package cn.xuyingqi.serial.port.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.xuyingqi.serial.port.protocol.Datagram;
import cn.xuyingqi.serial.port.util.SerialPortUtil;
import cn.xuyingqi.util.util.ListFactory;
import gnu.io.SerialPort;

/**
 * 测试
 * 
 * @author XuYQ
 *
 */
public class Test {

	/**
	 * 串口
	 */
	private SerialPort serialPort;
	/**
	 * 数据报文对象
	 */
	private List<Datagram> datagrams = ListFactory.newInstance();

	/**
	 * Test
	 * 
	 * @param serialPort
	 */
	public Test(SerialPort serialPort) {

		this.serialPort = serialPort;
	}

	/**
	 * 处理
	 */
	public void deal() {

		// 串口输入流
		InputStream is = null;
		// 串口缓冲输入流
		BufferedInputStream bis = null;

		try {

			// 获取串口输入流
			is = this.serialPort.getInputStream();
			// 获取串口缓冲输入流
			bis = new BufferedInputStream(is);

			// 获取终端输入的字节数组
			byte[] msg = new byte[1024 * 1024];
			// 读取的字节数组长度
			int length = 0;
			// 阻塞式读取终端数据
			while ((length = bis.read(msg)) != -1) {

				if (length != 0) {

					// 打印每个字节
					for (int i = 0; i < length; i++) {

						byte data = msg[i];

						Datagram datagram = Datagram.newInstance(data);
						if (datagram != null) {

							this.datagrams.add(datagram);
						}

						for (int j = 0, jLength = this.datagrams.size(); j < jLength; j++) {

							Datagram datagram1 = this.datagrams.get(j);

							if (datagram1.fill(data)) {

								System.out.println("接收数据报文：" + datagram1);
							}
						}
					}
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
		System.out.println("串口" + serialPort + "已打开");

		// 新开线程发送报文
		new Thread(new Runnable() {

			@Override
			public void run() {

				SerialPortUtil.sendToPort(serialPort, new byte[] { 0x68, 0x70, (byte) 0x9F, 0x08, 0x01, 0x02, 0x0D,
						0x4B, 0x01, 0x00, 0x00, 0x00, 0x32, 0x01, 0x01, 0x01, 0x02, 0x03, 0x04, 0x05, 0x1E, 0x16 });
			}
		}).start();

		new Test(serialPort).deal();

		System.out.println("应该来不了");
		serialPort.close();
		System.out.println("串口已关闭");
	}
}