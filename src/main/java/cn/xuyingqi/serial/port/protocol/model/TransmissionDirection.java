package cn.xuyingqi.serial.port.protocol.model;

/**
 * 传输方向
 * 
 * @author XuYQ
 *
 */
public enum TransmissionDirection {

	/**
	 * 上行
	 */
	UPSTREAM("上行"),
	/**
	 * 下行
	 */
	DOWNSTREAM("下行");

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 传输方向
	 * 
	 * @param desc
	 */
	private TransmissionDirection(String desc) {

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
	 */
	public static final TransmissionDirection shortOf(short data) {

		return (data & 0x80) == 0x80 ? TransmissionDirection.UPSTREAM : TransmissionDirection.DOWNSTREAM;
	}
}