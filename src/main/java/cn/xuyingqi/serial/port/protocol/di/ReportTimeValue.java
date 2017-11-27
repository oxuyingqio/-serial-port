package cn.xuyingqi.serial.port.protocol.di;

import cn.xuyingqi.util.util.ByteUtils;

/**
 * 上报时间_值
 * 
 * @author XuYQ
 *
 */
public class ReportTimeValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x27;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 上报时间_值
	 */
	public ReportTimeValue() {

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

	/**
	 * 获取主动上报开始时间
	 * 
	 * @return
	 */
	public String getStartTime() {

		return ByteUtils.byte2Short(this.prototype.startTime[1]) + ByteUtils.byte2Short(this.prototype.startTime[0])
				+ "";
	}

	/**
	 * 获取主动上报结束时间
	 * 
	 * @return
	 */
	public String getEndTime() {

		return ByteUtils.byte2Short(this.prototype.endTime[1]) + ByteUtils.byte2Short(this.prototype.endTime[0]) + "";
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("		主动上报开始时间：");
		sb.append(this.getStartTime());
		sb.append(" [");
		for (int i = 0, length = this.prototype.startTime.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.startTime[i])));
		}
		sb.append("]");

		sb.append("		主动上报结束时间：");
		sb.append(this.getEndTime());
		sb.append(" [");
		for (int i = 0, length = this.prototype.endTime.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.endTime[i])));
		}
		sb.append("]");

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
		 * 主动上报开始时间默认长度
		 */
		private static final int START_TIME_LENGTH = 2;
		/**
		 * 主动上报结束时间默认长度
		 */
		private static final int END_TIME_LENGTH = 2;

		/**
		 * 主动上报开始时间
		 */
		private byte[] startTime = new byte[START_TIME_LENGTH];
		/**
		 * 主动上报结束时间
		 */
		private byte[] endTime = new byte[END_TIME_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.startTime, 0, this.startTime.length);
			index += this.startTime.length;

			System.arraycopy(data, index, this.endTime, 0, this.endTime.length);
			index += this.endTime.length;

			return index;
		}
	}
}