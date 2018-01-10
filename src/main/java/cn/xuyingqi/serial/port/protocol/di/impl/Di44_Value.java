package cn.xuyingqi.serial.port.protocol.di.impl;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 阀门_值
 * 
 * @author XuYQ
 *
 */
public class Di44_Value extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x44;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 阀门_值
	 */
	public Di44_Value() {

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

		return "阀门_值";
	}

	/**
	 * 获取控制
	 * 
	 * @return
	 */
	public Short getControl() {

		return ByteUtils.byteArray2Short(this.prototype.control);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("控制：");
		sb.append(this.getControl());
		sb.append(" [");
		for (int i = 0, length = this.prototype.control.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.control[i])));
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
		 * 控制默认长度
		 */
		private static final int CONTROL_LENGTH = 1;

		/**
		 * 控制
		 */
		private byte[] control = new byte[CONTROL_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.control, 0, this.control.length);
			index += this.control.length;

			return index;
		}
	}
}