package cn.xuyingqi.serial.port.protocol.di.impl;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 表运行参数_值
 * 
 * @author XuYQ
 *
 */
public class Di26_Value extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x26;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 表运行参数_值
	 */
	public Di26_Value() {

		this.prototype = new Prototype();
	}

	/**
	 * 填充数据
	 * 
	 * @param data
	 *            数据
	 * @param index
	 *            数据索引值
	 * @return
	 */
	@Override
	public int fill(byte[] data, int index) {

		index = super.fill(data, index);

		return this.prototype.fill(data, index);
	}

	@Override
	public String getDi() {

		return "表运行参数_值";
	}

	/**
	 * 获取闲置天数
	 * 
	 * @return
	 */
	public Short getIdleDays() {

		return ByteUtils.byteArray2Short(this.prototype.idleDays);
	}

	/**
	 * 获取通讯失败次数
	 * 
	 * @return
	 */
	public Short getCommunicationFailureTime() {

		return ByteUtils.byteArray2Short(this.prototype.communicationFailureTime);
	}

	/**
	 * 获取超长使用时间
	 * 
	 * @return
	 */
	public Short getLongUseTime() {

		return ByteUtils.byteArray2Short(this.prototype.longUseTime);
	}

	/**
	 * 获取大流量检测
	 * 
	 * @return
	 */
	public Integer getLargeFlow() {

		return ByteUtils.byteArray2Int(this.prototype.largeFlow);
	}

	/**
	 * 获取小流量检测
	 * 
	 * @return
	 */
	public Short getSmallFlow() {

		return ByteUtils.byteArray2Short(this.prototype.smallFlow);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("闲置天数：");
		sb.append(this.getIdleDays());
		sb.append(" [");
		for (int i = 0, length = this.prototype.idleDays.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.idleDays[i])));
		}
		sb.append("] # ");

		sb.append("通讯失败次数：");
		sb.append(this.getCommunicationFailureTime());
		sb.append(" [");
		for (int i = 0, length = this.prototype.communicationFailureTime.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.communicationFailureTime[i])));
		}
		sb.append("] # ");

		sb.append("超长使用时间：");
		sb.append(this.getLongUseTime());
		sb.append(" [");
		for (int i = 0, length = this.prototype.longUseTime.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.longUseTime[i])));
		}
		sb.append("] # ");

		sb.append("大流量检测：");
		sb.append(this.getLargeFlow());
		sb.append(" [");
		for (int i = 0, length = this.prototype.largeFlow.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.largeFlow[i])));
		}
		sb.append("] # ");

		sb.append("小流量检测：");
		sb.append(this.getSmallFlow());
		sb.append(" [");
		for (int i = 0, length = this.prototype.smallFlow.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.smallFlow[i])));
		}
		sb.append("] # ");

		return sb.toString();
	}

	/**
	 * 原型
	 * 
	 * @author XuYQ
	 *
	 */
	private class Prototype {

		/**
		 * 闲置天数默认长度
		 */
		private static final int IDLE_DAYS_LENGTH = 1;
		/**
		 * 通讯失败次数默认长度
		 */
		private static final int COMMUNICATION_FAILURE_TIME_LENGTH = 1;
		/**
		 * 超长使用时间默认长度
		 */
		private static final int LONG_USE_TIME_LENGTH = 1;
		/**
		 * 大流量检测默认长度
		 */
		private static final int LARGE_FLOW_LENGTH = 2;
		/**
		 * 小流量检测默认长度
		 */
		private static final int SMALL_FLOW_LENGTH = 1;

		/**
		 * 闲置天数
		 */
		private byte[] idleDays = new byte[IDLE_DAYS_LENGTH];
		/**
		 * 通讯失败次数
		 */
		private byte[] communicationFailureTime = new byte[COMMUNICATION_FAILURE_TIME_LENGTH];
		/**
		 * 超长使用时间
		 */
		private byte[] longUseTime = new byte[LONG_USE_TIME_LENGTH];
		/**
		 * 大流量检测
		 */
		private byte[] largeFlow = new byte[LARGE_FLOW_LENGTH];
		/**
		 * 小流量检测
		 */
		private byte[] smallFlow = new byte[SMALL_FLOW_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.idleDays, 0, this.idleDays.length);
			index += this.idleDays.length;

			System.arraycopy(data, index, this.communicationFailureTime, 0, this.communicationFailureTime.length);
			index += this.communicationFailureTime.length;

			System.arraycopy(data, index, this.longUseTime, 0, this.longUseTime.length);
			index += this.longUseTime.length;

			System.arraycopy(data, index, this.largeFlow, 0, this.largeFlow.length);
			index += this.largeFlow.length;

			System.arraycopy(data, index, this.smallFlow, 0, this.smallFlow.length);
			index += this.smallFlow.length;

			return index;
		}
	}
}