package cn.xuyingqi.serial.port.protocol.di;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.ArrayUtils;

import cn.xuyingqi.util.util.ByteUtils;

/**
 * 校时_值
 * 
 * @author XuYQ
 *
 */
public class TimingValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x12;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 校时_值
	 */
	public TimingValue() {

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

		sb.append("		日期时间：");
		sb.append(SDF.format(this.getDateTime()));
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
		 * 日期时间默认长度
		 */
		private static final int DATE_TIME_LENGTH = 7;

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

			System.arraycopy(data, index, this.dateTime, 0, this.dateTime.length);
			index += this.dateTime.length;

			return index;
		}
	}
}