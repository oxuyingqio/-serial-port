package cn.xuyingqi.serial.port.protocol.model;

import java.util.Map;

import cn.xuyingqi.util.util.MapFactory;

/**
 * 充值类型
 * 
 * @author XuYQ
 *
 */
public enum RechargeType {

	/**
	 * 气量
	 */
	GAS((short) 0, "气量"),
	/**
	 * 金额
	 */
	MONEY((short) 1, "金额");

	private static final Map<Short, RechargeType> ITEMS = MapFactory.newInstance();

	static {

		for (RechargeType item : RechargeType.values()) {

			ITEMS.put(item.getCode(), item);
		}
	}

	/**
	 * 代码
	 */
	private short code;
	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 
	 * @param code
	 * @param desc
	 */
	private RechargeType(short code, String desc) {

		this.code = code;
		this.desc = desc;
	}

	/**
	 * 获取代码
	 * 
	 * @return
	 */
	public short getCode() {

		return this.code;
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
	 * @param code
	 * @return
	 */
	public static final RechargeType codeOf(short code) {

		return ITEMS.get(code);
	}
}