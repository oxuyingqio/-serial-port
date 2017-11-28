package cn.xuyingqi.serial.port.protocol.di.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.ArrayUtils;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 日冻结_值
 * 
 * @author XuYQ
 *
 */
public class DiurnalFreezingDataValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x49;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 日冻结_值
	 */
	public DiurnalFreezingDataValue() {

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

		return "日冻结_值";
	}

	/**
	 * 获取累积用气量
	 * 
	 * @return
	 */
	public Short getGasConsumption() {

		return ByteUtils.byteArray2Short(this.prototype.gasConsumption);
	}

	/**
	 * 获取运行状态
	 * 
	 * @return
	 */
	public Short getRunningState() {

		return ByteUtils.byteArray2Short(this.prototype.runningState);
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

	/**
	 * 获取日期时间
	 * 
	 * @return
	 */
	public Date getDateTime() {

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR,
				ByteUtils.byteArray2Int(ByteUtils.reverse(ArrayUtils.subarray(this.prototype.dateTime, 0, 2))));
		gc.set(Calendar.MONTH, ByteUtils.byteArray2Int(ArrayUtils.subarray(this.prototype.dateTime, 2, 3)) - 1);
		gc.set(Calendar.DAY_OF_MONTH, ByteUtils.byteArray2Int(ArrayUtils.subarray(this.prototype.dateTime, 3, 4)));
		gc.set(Calendar.HOUR_OF_DAY, ByteUtils.byteArray2Int(ArrayUtils.subarray(this.prototype.dateTime, 4, 5)));
		gc.set(Calendar.MINUTE, ByteUtils.byteArray2Int(ArrayUtils.subarray(this.prototype.dateTime, 5, 6)));
		gc.set(Calendar.SECOND, ByteUtils.byteArray2Int(ArrayUtils.subarray(this.prototype.dateTime, 6, 7)));

		return gc.getTime();
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("		累积用气量：");
		sb.append(this.getGasConsumption());
		sb.append(" [");
		for (int i = 0, length = this.prototype.gasConsumption.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.gasConsumption[i])));
		}
		sb.append("]");

		sb.append("		运行状态：");
		sb.append(this.getRunningState());
		sb.append(" [");
		for (int i = 0, length = this.prototype.runningState.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.runningState[i])));
		}
		sb.append("]");

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

		sb.append("		日期时间：");
		sb.append(DATETIME_SDF.format(this.getDateTime()));
		sb.append(" [");
		for (int i = 0, length = this.prototype.dateTime.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.dateTime[i])));
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
		 * 累积用气量默认长度
		 */
		private static final int GAS_CONSUMPTION_LENGTH = 1;
		/**
		 * 运行状态默认长度
		 */
		private static final int RUNNING_STATE_LENGTH = 1;
		/**
		 * 硬件版本默认长度
		 */
		private static final int HARDWARE_VERSION_LENGTH = 1;
		/**
		 * 软件版本默认长度
		 */
		private static final int SOFTWARE_VERSION_LENGTH = 1;
		/**
		 * 日期时间默认长度
		 */
		private static final int DATE_TIME_LENGTH = 7;

		/**
		 * 累积用气量
		 */
		private byte[] gasConsumption = new byte[GAS_CONSUMPTION_LENGTH];
		/**
		 * 运行状态
		 */
		private byte[] runningState = new byte[RUNNING_STATE_LENGTH];
		/**
		 * 硬件版本
		 */
		private byte[] hardwareVersion = new byte[HARDWARE_VERSION_LENGTH];
		/**
		 * 软件版本
		 */
		private byte[] softwareVersion = new byte[SOFTWARE_VERSION_LENGTH];
		/**
		 * 日期时间
		 */
		private byte[] dateTime = new byte[DATE_TIME_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.gasConsumption, 0, this.gasConsumption.length);
			index += this.gasConsumption.length;

			System.arraycopy(data, index, this.runningState, 0, this.runningState.length);
			index += this.runningState.length;

			System.arraycopy(data, index, this.hardwareVersion, 0, this.hardwareVersion.length);
			index += this.hardwareVersion.length;

			System.arraycopy(data, index, this.softwareVersion, 0, this.softwareVersion.length);
			index += this.softwareVersion.length;

			System.arraycopy(data, index, this.dateTime, 0, this.dateTime.length);
			index += this.dateTime.length;

			return index;
		}
	}
}