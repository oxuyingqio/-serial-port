package cn.xuyingqi.serial.port.protocol.model;

/**
 * 功能定义
 * 
 * @author XuYQ
 *
 */
public enum Function {

	/**
	 * 读命令
	 */
	READ("读命令"),
	/**
	 * 写命令
	 */
	WRITE("写命令"),
	/**
	 * 主动上报
	 */
	REPORT("主动上报");

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 功能定义
	 * 
	 * @param desc
	 */
	private Function(String desc) {

		this.desc = desc;
	}

	/**
	 * 获取描述
	 * 
	 * @return
	 */
	public String getDesc() {

		return this.desc;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static final Function shortOf(short data) {

		if ((data & 0x01) == 0x01) {

			return Function.READ;
		} else if ((data & 0x02) == 0x02) {

			return Function.WRITE;
		} else if ((data & 0x04) == 0x04) {

			return Function.REPORT;
		}

		return null;
	}
}