package cn.xuyingqi.serial.port.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.xuyingqi.serial.port.protocol.Datagram;
import cn.xuyingqi.util.util.ByteUtils;
import cn.xuyingqi.util.util.ListFactory;
import gnu.io.SerialPort;

/**
 * 端口处理
 * 
 * @author XuYQ
 *
 */
public class SerialPortDeal {

	/**
	 * 串口
	 */
	private SerialPort serialPort;
	/**
	 * 数据报文对象
	 */
	private List<Datagram> datagrams = ListFactory.newInstance();

	/**
	 * 端口处理
	 * 
	 * @param serialPort
	 */
	public SerialPortDeal(SerialPort serialPort) {

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
									Subject.getInstance().notifyObservers(sb.toString());

									Subject.getInstance().notifyObservers(datagram.toString());
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
}