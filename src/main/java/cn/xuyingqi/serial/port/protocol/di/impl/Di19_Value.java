package cn.xuyingqi.serial.port.protocol.di.impl;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 表地址_值
 * 
 * @author XuYQ
 *
 */
public class Di19_Value extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x19;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 表地址_值
	 */
	public Di19_Value() {

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

		return "表地址_值";
	}

	/**
	 * 获取表地址
	 * 
	 * @return
	 */
	public Long getMeterAddress() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.meterAddress));
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("表地址：");
		sb.append(this.getMeterAddress());
		sb.append(" [");
		for (int i = 0, length = this.prototype.meterAddress.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.meterAddress[i])));
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
		 * 表地址默认长度
		 */
		private static final int METER_ADDRESS_LENGTH = 4;

		/**
		 * 表地址
		 */
		private byte[] meterAddress = new byte[METER_ADDRESS_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.meterAddress, 0, this.meterAddress.length);
			index += this.meterAddress.length;

			return index;
		}
	}
}