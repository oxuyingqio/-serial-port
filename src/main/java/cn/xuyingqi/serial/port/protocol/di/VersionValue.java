package cn.xuyingqi.serial.port.protocol.di;

import cn.xuyingqi.util.util.ByteUtils;

/**
 * 版本信息_值
 * 
 * @author XuYQ
 *
 */
public class VersionValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x1A;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 版本信息_值
	 */
	public VersionValue() {

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
	 * 获取硬件版本
	 * 
	 * @return
	 */
	public Short getHardwareVersion() {

		return ByteUtils.byteArray2Short(this.prototype.hardwareVersion);
	}

	/**
	 * 获取软件版本
	 * 
	 * @return
	 */
	public Short getSoftwareVersion() {

		return ByteUtils.byteArray2Short(this.prototype.softwareVersion);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("		硬件版本：");
		sb.append(this.getHardwareVersion());
		sb.append(" [");
		for (int i = 0, length = this.prototype.hardwareVersion.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.hardwareVersion[i])));
		}
		sb.append("]");

		sb.append("		软件版本：");
		sb.append(this.getSoftwareVersion());
		sb.append(" [");
		for (int i = 0, length = this.prototype.softwareVersion.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.softwareVersion[i])));
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
		 * 硬件版本默认长度
		 */
		private static final int HARDWARE_VERSION_LENGTH = 1;
		/**
		 * 软件版本默认长度
		 */
		private static final int SOFTWARE_VERSION_LENGTH = 1;

		/**
		 * 硬件版本
		 */
		private byte[] hardwareVersion = new byte[HARDWARE_VERSION_LENGTH];
		/**
		 * 软件版本
		 */
		private byte[] softwareVersion = new byte[SOFTWARE_VERSION_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.hardwareVersion, 0, this.hardwareVersion.length);
			index += this.hardwareVersion.length;

			System.arraycopy(data, index, this.softwareVersion, 0, this.softwareVersion.length);
			index += this.softwareVersion.length;

			return index;
		}
	}
}