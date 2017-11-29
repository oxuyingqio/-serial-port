package cn.xuyingqi.serial.port.protocol.di.impl;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 删除测量点_值
 * 
 * @author XuYQ
 *
 */
public class DeleteMeasuringPointValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x1E;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 删除测量点_值
	 */
	public DeleteMeasuringPointValue() {

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

		return "删除测量点_值";
	}

	/**
	 * 获取测量点号
	 * 
	 * @return
	 */
	public Integer getMeasuringPoint() {

		return ByteUtils.byteArray2Int(ByteUtils.reverse(this.prototype.measuringPoint));
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("测量点号：");
		sb.append(this.getMeasuringPoint());
		sb.append(" [");
		for (int i = 0, length = this.prototype.measuringPoint.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.measuringPoint[i])));
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
		 * 测量点号默认长度
		 */
		private static final int MEASURING_POINT_LENGTH = 2;

		/**
		 * 测量点号
		 */
		private byte[] measuringPoint = new byte[MEASURING_POINT_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.measuringPoint, 0, this.measuringPoint.length);
			index += this.measuringPoint.length;

			return index;
		}
	}
}