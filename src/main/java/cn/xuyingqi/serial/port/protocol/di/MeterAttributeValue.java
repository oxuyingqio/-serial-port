package cn.xuyingqi.serial.port.protocol.di;

import java.util.Arrays;

import cn.xuyingqi.util.util.ByteUtils;

/**
 * 表属性_值
 * 
 * @author XuYQ
 *
 */
public class MeterAttributeValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x1F;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 表属性_值
	 */
	public MeterAttributeValue() {

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

	/**
	 * 获取主动上报开始时间
	 * 
	 * @return
	 */
	public String getStartTime() {

		return ByteUtils.byte2Short(this.prototype.startTime[1]) + ByteUtils.byte2Short(this.prototype.startTime[0])
				+ "";
	}

	/**
	 * 获取主动上报结束时间
	 * 
	 * @return
	 */
	public String getEndTime() {

		return ByteUtils.byte2Short(this.prototype.endTime[1]) + ByteUtils.byte2Short(this.prototype.endTime[0]) + "";
	}

	/**
	 * 获取组序号
	 * 
	 * @return
	 */
	public Integer getGroupSerialNumber() {

		return ByteUtils.byteArray2Int(ByteUtils.reverse(this.prototype.groupSerialNumber));
	}

	/**
	 * 获取测量点号
	 * 
	 * @return
	 */
	public Integer getMeasuringPoint() {

		return ByteUtils.byteArray2Int(ByteUtils.reverse(this.prototype.measuringPoint));
	}

	/**
	 * 获取表地址
	 * 
	 * @return
	 */
	public Long getMeterAddress() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.meterAddress));
	}

	/**
	 * 获取是否被管理
	 * 
	 * @return
	 */
	public boolean getManaged() {

		return Arrays.equals(this.prototype.managed, new byte[] { (byte) 0xAA });
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("		频率：");
		sb.append(this.getRate());
		sb.append(" [");
		for (int i = 0, length = this.prototype.rate.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.rate[i])));
		}
		sb.append("]");

		sb.append("		带宽：");
		sb.append(this.getBandwidth());
		sb.append(" [");
		for (int i = 0, length = this.prototype.bandwidth.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.bandwidth[i])));
		}
		sb.append("]");

		sb.append("		扩频码：");
		sb.append(this.getSpreadingCode());
		sb.append(" [");
		for (int i = 0, length = this.prototype.spreadingCode.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.spreadingCode[i])));
		}
		sb.append("]");

		sb.append("		编码率：");
		sb.append(this.getCodingRate());
		sb.append(" [");
		for (int i = 0, length = this.prototype.codingRate.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.codingRate[i])));
		}
		sb.append("]");

		sb.append("		功率：");
		sb.append(this.getPower());
		sb.append(" [");
		for (int i = 0, length = this.prototype.power.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.power[i])));
		}
		sb.append("]");

		sb.append("		主动上报开始时间：");
		sb.append(this.getStartTime());
		sb.append(" [");
		for (int i = 0, length = this.prototype.startTime.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.startTime[i])));
		}
		sb.append("]");

		sb.append("		主动上报结束时间：");
		sb.append(this.getEndTime());
		sb.append(" [");
		for (int i = 0, length = this.prototype.endTime.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.endTime[i])));
		}
		sb.append("]");

		sb.append("		组序号：");
		sb.append(this.getGroupSerialNumber());
		sb.append(" [");
		for (int i = 0, length = this.prototype.groupSerialNumber.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.groupSerialNumber[i])));
		}
		sb.append("]");

		sb.append("		测量点号：");
		sb.append(this.getMeasuringPoint());
		sb.append(" [");
		for (int i = 0, length = this.prototype.measuringPoint.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.measuringPoint[i])));
		}
		sb.append("]");

		sb.append("		表地址：");
		sb.append(this.getMeterAddress());
		sb.append(" [");
		for (int i = 0, length = this.prototype.meterAddress.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.meterAddress[i])));
		}
		sb.append("]");

		sb.append("		是否被管理：");
		sb.append(this.getManaged());
		sb.append(" [");
		for (int i = 0, length = this.prototype.managed.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.managed[i])));
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
		 * 主动上报开始时间默认长度
		 */
		private static final int START_TIME_LENGTH = 2;
		/**
		 * 主动上报结束时间默认长度
		 */
		private static final int END_TIME_LENGTH = 2;
		/**
		 * 组序号默认长度
		 */
		private static final int GROUP_SERIAL_NUMBER_LENGTH = 2;
		/**
		 * 测量点号默认长度
		 */
		private static final int MEASURING_POINT_LENGTH = 2;
		/**
		 * 表地址默认长度
		 */
		private static final int METER_ADDRESS_LENGTH = 4;
		/**
		 * 是否被管理默认长度
		 */
		private static final int MANAGED_LENGTH = 1;

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
		 * 主动上报开始时间
		 */
		private byte[] startTime = new byte[START_TIME_LENGTH];
		/**
		 * 主动上报结束时间
		 */
		private byte[] endTime = new byte[END_TIME_LENGTH];
		/**
		 * 组序号
		 */
		private byte[] groupSerialNumber = new byte[GROUP_SERIAL_NUMBER_LENGTH];
		/**
		 * 测量点号
		 */
		private byte[] measuringPoint = new byte[MEASURING_POINT_LENGTH];
		/**
		 * 表地址
		 */
		private byte[] meterAddress = new byte[METER_ADDRESS_LENGTH];
		/**
		 * 是否被管理
		 */
		private byte[] managed = new byte[MANAGED_LENGTH];

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

			System.arraycopy(data, index, this.startTime, 0, this.startTime.length);
			index += this.startTime.length;

			System.arraycopy(data, index, this.endTime, 0, this.endTime.length);
			index += this.endTime.length;

			System.arraycopy(data, index, this.groupSerialNumber, 0, this.groupSerialNumber.length);
			index += this.groupSerialNumber.length;

			System.arraycopy(data, index, this.measuringPoint, 0, this.measuringPoint.length);
			index += this.measuringPoint.length;

			System.arraycopy(data, index, this.meterAddress, 0, this.meterAddress.length);
			index += this.meterAddress.length;

			System.arraycopy(data, index, this.managed, 0, this.managed.length);
			index += this.managed.length;

			return index;
		}
	}
}