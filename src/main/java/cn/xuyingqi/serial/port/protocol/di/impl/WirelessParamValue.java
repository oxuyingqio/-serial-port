package cn.xuyingqi.serial.port.protocol.di.impl;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 无线参数_值
 * 
 * @author XuYQ
 *
 */
public class WirelessParamValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x25;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 无线参数_值
	 */
	public WirelessParamValue() {

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

		return "无线参数_值";
	}

	/**
	 * 获取频率
	 * 
	 * @return
	 */
	public Short getRate() {

		return ByteUtils.byteArray2Short(this.prototype.rate);
	}

	/**
	 * 获取带宽
	 * 
	 * @return
	 */
	public Short getBandwidth() {

		return ByteUtils.byteArray2Short(this.prototype.bandwidth);
	}

	/**
	 * 获取扩频码
	 * 
	 * @return
	 */
	public Short getSpreadingCode() {

		return ByteUtils.byteArray2Short(this.prototype.spreadingCode);
	}

	/**
	 * 获取编码率
	 * 
	 * @return
	 */
	public Short getCodingRate() {

		return ByteUtils.byteArray2Short(this.prototype.codingRate);
	}

	/**
	 * 获取功率
	 * 
	 * @return
	 */
	public Short getPower() {

		return ByteUtils.byteArray2Short(this.prototype.power);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("频率：");
		sb.append(this.getRate());
		sb.append(" [");
		for (int i = 0, length = this.prototype.rate.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.rate[i])));
		}
		sb.append("] # ");

		sb.append("带宽：");
		sb.append(this.getBandwidth());
		sb.append(" [");
		for (int i = 0, length = this.prototype.bandwidth.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.bandwidth[i])));
		}
		sb.append("] # ");

		sb.append("扩频码：");
		sb.append(this.getSpreadingCode());
		sb.append(" [");
		for (int i = 0, length = this.prototype.spreadingCode.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.spreadingCode[i])));
		}
		sb.append("] # ");

		sb.append("编码率：");
		sb.append(this.getCodingRate());
		sb.append(" [");
		for (int i = 0, length = this.prototype.codingRate.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.codingRate[i])));
		}
		sb.append("] # ");

		sb.append("功率：");
		sb.append(this.getPower());
		sb.append(" [");
		for (int i = 0, length = this.prototype.power.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.power[i])));
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
		 * 频率默认长度
		 */
		private static final int RATE_LENGTH = 1;
		/**
		 * 带宽默认长度
		 */
		private static final int BANDWIDTH_LENGTH = 1;
		/**
		 * 扩频码默认长度
		 */
		private static final int SPREADING_CODE_LENGTH = 1;
		/**
		 * 编码率默认长度
		 */
		private static final int CODING_RATE_LENGTH = 1;
		/**
		 * 功率默认长度
		 */
		private static final int POWER_LENGTH = 1;

		/**
		 * 频率
		 */
		private byte[] rate = new byte[RATE_LENGTH];
		/**
		 * 带宽
		 */
		private byte[] bandwidth = new byte[BANDWIDTH_LENGTH];
		/**
		 * 扩频码
		 */
		private byte[] spreadingCode = new byte[SPREADING_CODE_LENGTH];
		/**
		 * 编码率
		 */
		private byte[] codingRate = new byte[CODING_RATE_LENGTH];
		/**
		 * 功率
		 */
		private byte[] power = new byte[POWER_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.rate, 0, this.rate.length);
			index += this.rate.length;

			System.arraycopy(data, index, this.bandwidth, 0, this.bandwidth.length);
			index += this.bandwidth.length;

			System.arraycopy(data, index, this.spreadingCode, 0, this.spreadingCode.length);
			index += this.spreadingCode.length;

			System.arraycopy(data, index, this.codingRate, 0, this.codingRate.length);
			index += this.codingRate.length;

			System.arraycopy(data, index, this.power, 0, this.power.length);
			index += this.power.length;

			return index;
		}
	}
}