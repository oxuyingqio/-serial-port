package cn.xuyingqi.serial.port.protocol.di.impl;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 终端RSSI
 * 
 * @author XuYQ
 *
 */
public class TerminalRssi extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x16;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 终端RSSI
	 */
	public TerminalRssi() {

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

		return "终端RSSI";
	}

	/**
	 * 获取管理地址
	 * 
	 * @return
	 */
	public Long getManageAddress() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.manageAddress));
	}

	/**
	 * 获取理论信号
	 * 
	 * @return
	 */
	public Short getTheorySignal() {

		return ByteUtils.byteArray2Short(this.prototype.theorySignal);
	}

	/**
	 * 获取实际信号
	 * 
	 * @return
	 */
	public Short getActualSignal() {

		return ByteUtils.byteArray2Short(this.prototype.actualSignal);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("集中器/中继器地址：");
		sb.append(this.getManageAddress());
		sb.append(" [");
		for (int i = 0, length = this.prototype.manageAddress.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.manageAddress[i])));
		}
		sb.append("] # ");

		sb.append("信号强度绝对值：");
		sb.append(this.getTheorySignal());
		sb.append(" [");
		for (int i = 0, length = this.prototype.theorySignal.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.theorySignal[i])));
		}
		sb.append("] # ");

		sb.append("信号强度：");
		sb.append(this.getActualSignal());
		sb.append(" [");
		for (int i = 0, length = this.prototype.actualSignal.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.actualSignal[i])));
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
		 * 管理地址默认长度
		 */
		private static final int MANAGE_ADDRESS_LENGTH = 4;
		/**
		 * 理论信号默认长度
		 */
		private static final int THEORY_SIGNAL_LENGTH = 1;
		/**
		 * 实际信号默认长度
		 */
		private static final int ACTUAL_SIGNAL_LENGTH = 1;

		/**
		 * 管理地址
		 */
		private byte[] manageAddress = new byte[MANAGE_ADDRESS_LENGTH];
		/**
		 * 理论信号
		 */
		private byte[] theorySignal = new byte[THEORY_SIGNAL_LENGTH];
		/**
		 * 实际信号
		 */
		private byte[] actualSignal = new byte[ACTUAL_SIGNAL_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.manageAddress, 0, this.manageAddress.length);
			index += this.manageAddress.length;

			System.arraycopy(data, index, this.theorySignal, 0, this.theorySignal.length);
			index += this.theorySignal.length;

			System.arraycopy(data, index, this.actualSignal, 0, this.actualSignal.length);
			index += this.actualSignal.length;

			return index;
		}
	}
}