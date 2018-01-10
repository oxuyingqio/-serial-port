package cn.xuyingqi.serial.port.protocol.di.impl;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 日期
 * 
 * @author XuYQ
 *
 */
public class Di52 extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x52;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 日期
	 */
	public Di52() {

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

		return "日期";
	}

	/**
	 * 获取日期
	 * 
	 * @return
	 */
	public Short getDay() {

		return ByteUtils.byteArray2Short(this.prototype.day);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("日期：");
		sb.append(this.getDay());
		sb.append(" [");
		for (int i = 0, length = this.prototype.day.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.day[i])));
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
		 * 日期默认长度
		 */
		private static final int DAY_LENGTH = 1;

		/**
		 * 日期
		 */
		private byte[] day = new byte[DAY_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.day, 0, this.day.length);
			index += this.day.length;

			return index;
		}
	}
}