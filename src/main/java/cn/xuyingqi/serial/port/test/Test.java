package cn.xuyingqi.serial.port.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.xuyingqi.serial.port.protocol.Datagram;
import cn.xuyingqi.serial.port.util.SerialPortUtil;
import cn.xuyingqi.util.util.ByteUtils;
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

						// 当前字节
						byte data = msg[i];

						// 是否创建了数据报文
						Datagram temp = Datagram.newInstance(data);
						if (temp != null) {

							this.datagrams.add(temp);
						}

						for (Datagram datagram : this.datagrams) {

							if (datagram.fill(data)) {

								if (datagram.check()) {

									byte[] byteArray = datagram.toByteArray();
									StringBuffer sb = new StringBuffer();
									for (int j = 0, jLength = byteArray.length; j < jLength; j++) {

										sb.append(Integer.toHexString(ByteUtils.byte2Int(byteArray[j])));
										sb.append(" ");
									}
									System.out.println(sb.toString());

									System.out.println("接收数据报文：["
											+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) + "]"
											+ datagram);
								}
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

		// 新开线程接收报文
		new Thread(new Runnable() {

			@Override
			public void run() {

				System.out.println("开始接收报文==========================================");

				new Test(serialPort).deal();
			}
		}).start();

		// 新开线程发送报文
		new Thread(new Runnable() {

			@Override
			public void run() {

				System.out.println("开始发送报文=========================================================");

				StringBuffer sb = new StringBuffer();
				sb.append("68 71 9F 08 01 02 0F 4B 03 0A 00 00 00 01 02 E1 07 0A 1E 0B 2D 2A 5F 16");
				sb.append("68 71 9F 08 01 02 11 2A 01 50 C3 00 00 0A 00 00 00 00 00 00 00 03 00 01 E0 16");
				sb.append("68 71 9F 08 01 02 10 2B 07 64 00 01 00 C8 00 02 00 2C 01 2C 01 02 01 51 16");
				sb.append("68 71 9F 08 01 01 01 12 95 16");
				sb.append("68 71 9F 08 01 81 08 12 E1 07 0B 09 0C 2B 1E 6D 16");
				sb.append("68 AA AA AA AA 02 0D 1B 01 00 FF FF 12 E1 07 0B 09 0F 32 1E A6 16");
				sb.append("68 71 9F 08 01 01 01 25 A8 16");
				sb.append("68 71 9F 08 01 81 06 25 7E 07 0A 01 11 CE 16 ");
				sb.append("68 07 c6 cd 02 02 0B 1B 01 00 FF FF 25 0B 07 0A 01 11 FB 16");
				sb.append("68 71 9F 08 01 82 02 1B 25 45 16");
				sb.append("68 71 9F 08 01 01 01 27 AA 16");
				sb.append("68 71 9F 08 01 81 05 27 00 00 00 FF 2D 16");
				sb.append("68 71 9F 08 01 02 05 27 00 00 08 00 B7 16");
				sb.append("68 71 9F 08 01 82 01 27 2B 16");
				sb.append("68 71 9F 08 01 01 01 26 A9 16");
				sb.append("68 71 9F 08 01 81 07 26 00 00 00 00 00 00 2F 16");
				sb.append("68 71 9F 08 01 01 02 44 10 D8 16");
				sb.append("68 71 9F 08 01 81 01 44 47 16 ");
				sb.append("68 71 9F 08 01 84 09 48 00 00 00 00 01 08 36 00 95 16 ");
				sb.append("68 71 9F 08 01 01 02 44 11 D9 16");
				sb.append("68 71 9F 08 01 81 01 44 47 16");
				sb.append("68 71 9F 08 01 01 01 1A 9D 16");
				sb.append("68 71 9F 08 01 81 03 1A 78 64 FB 16 ");
				sb.append("68 71 9F 08 01 01 03 49 52 08 28 16");
				sb.append("68 71 9F 08 01 81 0D 49 00 00 00 00 00 00 00 00 00 01 4E 15 BC 16 ");
				sb.append("68 CB 1B 05 01 01 03 4A 52 0B FF 16 ");
				sb.append("68  CB 1B 05 01 81 0D 4A 00 00 00 00 00 00 00 00 E1 07 0B 01 20 16 ");
				sb.append("68 71 9F 08 01 81 00 02 16 ");
				sb.append("68 71 9F 08 01 01 01 48 CB 16");
				sb.append("68 71 9F 08 01 81 09 48 00 00 00 00 00 08 34 00 8F 16");
				sb.append("68 AA AA AA AA 01 15 48 53 64 19 01 FF FF FF FF 71 9F 08 01 FF FF FF FF FF FF FF FF 4C 16");
				sb.append("68 71 9F 08 01 81 09 48 00 00 00 00 00 08 35 00 90 16");
				sb.append("68 AA AA AA AA 01 15 48 53 64 19 19 FF FF FF FF 71 9F 08 01 FF FF FF FF FF FF FF FF 64 16");
				sb.append("68 71 9F 08 01 81 09 48 00 00 00 00 00 08 36 00 91 16 ");
				sb.append("68 AA AA AA AA 01 15 48 53 64 18 00 FF FF FF FF 71 9F 08 01 FF FF FF FF FF FF FF FF 4A 16");
				sb.append("68 AA AA AA AA 01 15 4C 53 64 19 01 FF FF FF FF 71 9F 08 01 FF FF FF FF FF FF FF FF 50 16");
				sb.append("68 71 9F 08 01 01 07 16 00 00 00 00 11 0F BF 16");
				sb.append("68 71 9F 08 01 81 07 16 00 00 00 00 13 00 32 16");
				sb.append("68 71 9F 08 01 81 07 16 00 00 00 00 3C 00 5B 16");
				sb.append("68 71 9F 08 01 02 08 12 E1 07 0B 09 0F 32 1E F8 16");
				sb.append("68 71 9F 08 01 82 01 12 16 16 ");
				sb.append("68 AA AA AA AA 02 0D 1B 01 00 FF FF 12 E1 07 0B 09 0F 32 1E A6 16");
				sb.append("68 71 9F 08 01 02 03 1E FF FF A2 16");
				sb.append("68 71 9F 08 01 82 01 1E 22 16 ");
				sb.append("68 AA AA AA AA 02 0D 1B 00 00 FF FF 12 E1 07 0B 11 0A 32 1E A8 16");
				sb.append("68 AA AA AA AA 02 0D 1B 00 00 FF FF 12 E1 07 0B 10 0B 32 1E A8 16");
				sb.append("68 71 9F 08 01 02 10 2B 07 64 00 01 00 C8 00 02 00 2C 01 70 23 51 00 05 16");
				sb.append("68 AA AA AA AA 02 0D 1B 00 00 FF FF 12 E1 07 0B 11 09 25 1E 9A 16");
				sb.append("68 71 9F 08 01 02 10 2B 07 64 00 01 00 C8 00 02 00 2C 01 71 23 51 00 06 16");
				sb.append("68 71 9F 08 01 02 10 2B 07 64 00 02 00 C8 00 04 00 2C 01 71 23 51 00 09 16");
				sb.append("68 71 9F 08 01 02 0F 4B 03 64 00 00 00 01 02 E1 07 0A 1E 0B 2D 2A B9 16");
				sb.append("68 71 9F 08 01 02 10 2B 07 64 00 02 00 C8 00 04 00 2C 01 71 23 51 00 09 16");
				sb.append("68 70 9F 08 01 01 03 49 52 16 35 16  ");
				sb.append("68 71 9F 08 01 01 03 49 52 64 84 16 ");

				String temp = sb.toString().replace(" ", "");
				System.out.println(temp);
				if (serialPort != null) {

					SerialPortUtil.sendToPort(serialPort, ByteUtils.doubleHexString2ByteArray(temp));
				}
			}
		}).start();

		System.out.println("应该来不了");
		serialPort.close();
		System.out.println("串口已关闭");
	}
}