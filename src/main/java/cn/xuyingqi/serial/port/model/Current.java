package cn.xuyingqi.serial.port.model;

import gnu.io.SerialPort;

/**
 * 当前
 * 
 * @author XuYQ
 *
 */
public class Current {

	/**
	 * 当前
	 */
	private static Current current;

	/**
	 * 波特率集合
	 */
	private int[] baudRates;
	/**
	 * 最后波特率
	 */
	private int lastBaudRate;
	/**
	 * 打开串口超时时间
	 */
	private int timeout;
	/**
	 * 串口
	 */
	private SerialPort serialPort;

	/**
	 * 私有构造方法
	 * 
	 * @return
	 */
	private Current() {

		this.baudRates = new int[] { 110, 300, 600, 1200, 2400, 4800, 9600, 14400, 19200, 38400, 56000, 57600, 115200 };
		this.lastBaudRate = 1200;
		this.timeout = 10;
	}

	/**
	 * 获取当前实例
	 * 
	 * @return
	 */
	public static final synchronized Current getInstance() {

		if (current == null) {

			current = new Current();
		}

		return current;
	}

	/**
	 * 获取波特率集合
	 * 
	 * @return
	 */
	public int[] getBaudRates() {

		return this.baudRates;
	}

	/**
	 * 设置波特率集合
	 * 
	 * @param baudRates
	 */
	public void setBaudRates(int[] baudRates) {

		this.baudRates = baudRates;
	}

	/**
	 * 获取最后波特率
	 * 
	 * @return
	 */
	public int getLastBaudRate() {

		return this.lastBaudRate;
	}

	/**
	 * 设置最后波特率
	 * 
	 * @param lastBaudRate
	 */
	public void setLastBaudRate(int lastBaudRate) {

		this.lastBaudRate = lastBaudRate;
	}

	/**
	 * 获取打开串口超时时间
	 * 
	 * @return
	 */
	public int getTimeout() {

		return this.timeout;
	}

	/**
	 * 设置打开串口超时时间
	 * 
	 * @param timeout
	 */
	public void setTimeout(int timeout) {

		this.timeout = timeout;
	}

	/**
	 * 获取串口
	 * 
	 * @return
	 */
	public SerialPort getSerialPort() {

		return this.serialPort;
	}

	/**
	 * 设置串口
	 * 
	 * @param serialPort
	 */
	public void setSerialPort(SerialPort serialPort) {

		this.serialPort = serialPort;
	}
}