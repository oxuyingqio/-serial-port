package cn.xuyingqi.serial.port.protocol.di.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.ArrayUtils;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.serial.port.protocol.model.RechargeType;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 空中充值_值
 * 
 * @author XuYQ
 *
 */
public class AerialRechargeValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x4B;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 空中充值_值
	 */
	public AerialRechargeValue() {

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

		return "空中充值_值";
	}

	/**
	 * 获取序号
	 * 
	 * @return
	 */
	public Short getSerialNumber() {

		return ByteUtils.byteArray2Short(this.prototype.serialNumber);
	}

	/**
	 * 获取充值量
	 * 
	 * @return
	 */
	public Long getRechargeData() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.rechargeData));
	}

	/**
	 * 获取充值类型
	 * 
	 * @return
	 */
	public RechargeType getRechargeType() {

		return RechargeType.codeOf(ByteUtils.byteArray2Short(this.prototype.rechargeType));
	}

	/**
	 * 获取随机数
	 * 
	 * @return
	 */
	public Short getRandom() {

		return ByteUtils.byteArray2Short(this.prototype.random);
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

		sb.append("序号：");
		sb.append(this.getSerialNumber());
		sb.append(" [");
		for (int i = 0, length = this.prototype.serialNumber.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.serialNumber[i])));
		}
		sb.append("] # ");

		sb.append("充值量：");
		sb.append(this.getRechargeData());
		sb.append(" [");
		for (int i = 0, length = this.prototype.rechargeData.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.rechargeData[i])));
		}
		sb.append("] # ");

		sb.append("充值类型：");
		sb.append(this.getRechargeType().getDesc());
		sb.append(" [");
		for (int i = 0, length = this.prototype.rechargeType.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.rechargeType[i])));
		}
		sb.append("] # ");

		sb.append("随机数：");
		sb.append(this.getRandom());
		sb.append(" [");
		for (int i = 0, length = this.prototype.random.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.random[i])));
		}
		sb.append("] # ");

		sb.append("日期时间：");
		sb.append(DATETIME_SDF.format(this.getDateTime()));
		sb.append(" [");
		for (int i = 0, length = this.prototype.dateTime.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.dateTime[i])));
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
		 * 序号默认长度
		 */
		private static final int SERIAL_NUMBER_LENGTH = 1;
		/**
		 * 充值量默认长度
		 */
		private static final int RECHARGE_DATA_LENGTH = 4;
		/**
		 * 充值类型默认长度
		 */
		private static final int RECHARGE_TYPE_LENGTH = 1;
		/**
		 * 随机数默认长度
		 */
		private static final int RANDOM_LENGTH = 1;
		/**
		 * 日期时间默认长度
		 */
		private static final int DATE_TIME_LENGTH = 7;

		/**
		 * 序号
		 */
		private byte[] serialNumber = new byte[SERIAL_NUMBER_LENGTH];
		/**
		 * 充值量
		 */
		private byte[] rechargeData = new byte[RECHARGE_DATA_LENGTH];
		/**
		 * 充值类型
		 */
		private byte[] rechargeType = new byte[RECHARGE_TYPE_LENGTH];
		/**
		 * 随机数
		 */
		private byte[] random = new byte[RANDOM_LENGTH];
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

			System.arraycopy(data, index, this.serialNumber, 0, this.serialNumber.length);
			index += this.serialNumber.length;

			System.arraycopy(data, index, this.rechargeData, 0, this.rechargeData.length);
			index += this.rechargeData.length;

			System.arraycopy(data, index, this.rechargeType, 0, this.rechargeType.length);
			index += this.rechargeType.length;

			System.arraycopy(data, index, this.random, 0, this.random.length);
			index += this.random.length;

			System.arraycopy(data, index, this.dateTime, 0, this.dateTime.length);
			index += this.dateTime.length;

			return index;
		}
	}
}