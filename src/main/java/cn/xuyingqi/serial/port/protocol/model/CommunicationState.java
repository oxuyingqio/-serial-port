package cn.xuyingqi.serial.port.protocol.model;

/**
 * 通讯状态
 * 
 * @author XuYQ
 *
 */
public enum CommunicationState {

	/**
	 * 确认帧
	 */
	CONFIRM("确认帧"),
	/**
	 * 否认帧
	 */
	DENY("否认帧");

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 通讯状态
	 * 
	 * @param desc
	 */
	private CommunicationState(String desc) {

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
	public static final CommunicationState shortOf(short data) {

		return (data & 0x40) == 0x40 ? CommunicationState.DENY : CommunicationState.CONFIRM;
	}
}