package cn.xuyingqi.serial.port.protocol.di.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.ArrayUtils;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 月冻结_值
 * 
 * @author XuYQ
 *
 */
public class MonthlyFrozenDataValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x4A;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 月冻结_值
	 */
	public MonthlyFrozenDataValue() {

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

		return "月冻结_值";
	}

	/**
	 * 获取累积用气量
	 * 
	 * @return
	 */
	public Long getGasConsumption() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.gasConsumption));
	}

	/**
	 * 获取运行状态
	 * 
	 * @return
	 */
	public Integer getRunningState() {

		return ByteUtils.byteArray2Int(ByteUtils.reverse(this.prototype.runningState));
	}

	/**
	 * 获取干电池电压
	 * 
	 * @return
	 */
	public Short getDryCellVoltage() {

		return ByteUtils.byteArray2Short(this.prototype.dryCellVoltage);
	}

	/**
	 * 获取锂电池电压
	 * 
	 * @return
	 */
	public Short getLithiumBatteryVoltage() {

		return ByteUtils.byteArray2Short(this.prototype.lithiumBatteryVoltage);
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

		return gc.getTime();
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("累积用气量：");
		sb.append(this.getGasConsumption());
		sb.append(" [");
		for (int i = 0, length = this.prototype.gasConsumption.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.gasConsumption[i])));
		}
		sb.append("] # ");

		sb.append("运行状态：");
		sb.append(this.getRunningState());
		sb.append(" [");
		for (int i = 0, length = this.prototype.runningState.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.runningState[i])));
		}
		sb.append("] # ");

		sb.append("干电池电压：");
		sb.append(this.getDryCellVoltage());
		sb.append(" [");
		for (int i = 0, length = this.prototype.dryCellVoltage.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.dryCellVoltage[i])));
		}
		sb.append("] # ");

		sb.append("锂电池电压：");
		sb.append(this.getLithiumBatteryVoltage());
		sb.append(" [");
		for (int i = 0, length = this.prototype.lithiumBatteryVoltage.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.lithiumBatteryVoltage[i])));
		}
		sb.append("] # ");

		sb.append("日期时间：");
		sb.append(DATE_SDF.format(this.getDateTime()));
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
		 * 累积用气量默认长度
		 */
		private static final int GAS_CONSUMPTION_LENGTH = 4;
		/**
		 * 运行状态默认长度
		 */
		private static final int RUNNING_STATE_LENGTH = 2;
		/**
		 * 干电池电压默认长度
		 */
		private static final int DRY_CELL_VOLTAGE_LENGTH = 1;
		/**
		 * 锂电池电压默认长度
		 */
		private static final int LITHIUM_BATTERY_VOLTAGE_LENGTH = 1;
		/**
		 * 日期时间默认长度
		 */
		private static final int DATE_TIME_LENGTH = 4;

		/**
		 * 累积用气量
		 */
		private byte[] gasConsumption = new byte[GAS_CONSUMPTION_LENGTH];
		/**
		 * 运行状态
		 */
		private byte[] runningState = new byte[RUNNING_STATE_LENGTH];
		/**
		 * 干电池电压
		 */
		private byte[] dryCellVoltage = new byte[DRY_CELL_VOLTAGE_LENGTH];
		/**
		 * 锂电池电压
		 */
		private byte[] lithiumBatteryVoltage = new byte[LITHIUM_BATTERY_VOLTAGE_LENGTH];
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

			System.arraycopy(data, index, this.dryCellVoltage, 0, this.dryCellVoltage.length);
			index += this.dryCellVoltage.length;

			System.arraycopy(data, index, this.lithiumBatteryVoltage, 0, this.lithiumBatteryVoltage.length);
			index += this.lithiumBatteryVoltage.length;

			System.arraycopy(data, index, this.dateTime, 0, this.dateTime.length);
			index += this.dateTime.length;

			return index;
		}
	}
}