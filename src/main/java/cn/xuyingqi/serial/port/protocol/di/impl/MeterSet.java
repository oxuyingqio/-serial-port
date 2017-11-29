package cn.xuyingqi.serial.port.protocol.di.impl;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 表集合
 * 
 * @author XuYQ
 *
 */
public class MeterSet extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x53;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 表集合
	 */
	public MeterSet() {

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

		return "表集合";
	}

	/**
	 * 获取总数
	 * 
	 * @return
	 */
	public Short getTotal() {

		return ByteUtils.byteArray2Short(this.prototype.total);
	}

	/**
	 * 获取组数
	 * 
	 * @return
	 */
	public Short getGroupSize() {

		return ByteUtils.byteArray2Short(this.prototype.groupSize);
	}

	/**
	 * 获取组
	 * 
	 * @return
	 */
	public Short getGroup() {

		return ByteUtils.byteArray2Short(this.prototype.group);
	}

	/**
	 * 获取表1
	 * 
	 * @return
	 */
	public Long getMeter1() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.meter1));
	}

	/**
	 * 获取表2
	 * 
	 * @return
	 */
	public Long getMeter2() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.meter2));
	}

	/**
	 * 获取表3
	 * 
	 * @return
	 */
	public Long getMeter3() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.meter3));
	}

	/**
	 * 获取表4
	 * 
	 * @return
	 */
	public Long getMeter4() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.meter4));
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("表总数：");
		sb.append(this.getTotal());
		sb.append(" [");
		for (int i = 0, length = this.prototype.total.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.total[i])));
		}
		sb.append("] # ");

		sb.append("组数：");
		sb.append(this.getGroupSize());
		sb.append(" [");
		for (int i = 0, length = this.prototype.groupSize.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.groupSize[i])));
		}
		sb.append("] # ");

		sb.append("组：");
		sb.append(this.getGroup());
		sb.append(" [");
		for (int i = 0, length = this.prototype.group.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.group[i])));
		}
		sb.append("] # ");

		sb.append("表1：");
		sb.append(this.getMeter1());
		sb.append(" [");
		for (int i = 0, length = this.prototype.meter1.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.meter1[i])));
		}
		sb.append("] # ");

		sb.append("表2：");
		sb.append(this.getMeter2());
		sb.append(" [");
		for (int i = 0, length = this.prototype.meter2.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.meter2[i])));
		}
		sb.append("] # ");

		sb.append("表3：");
		sb.append(this.getMeter3());
		sb.append(" [");
		for (int i = 0, length = this.prototype.meter3.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.meter3[i])));
		}
		sb.append("] # ");

		sb.append("表4：");
		sb.append(this.getMeter4());
		sb.append(" [");
		for (int i = 0, length = this.prototype.meter4.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.meter4[i])));
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
		 * 表总数默认长度
		 */
		private static final int TOTAL_LENGTH = 1;
		/**
		 * 组数默认长度
		 */
		private static final int GROUP_SIZE_LENGTH = 1;
		/**
		 * 组默认长度
		 */
		private static final int GROUP_LENGTH = 1;
		/**
		 * 表1默认长度
		 */
		private static final int METER_1_LENGTH = 4;
		/**
		 * 表2默认长度
		 */
		private static final int METER_2_LENGTH = 4;
		/**
		 * 表3默认长度
		 */
		private static final int METER_3_LENGTH = 4;
		/**
		 * 表4默认长度
		 */
		private static final int METER_4_LENGTH = 4;

		/**
		 * 表总数
		 */
		private byte[] total = new byte[TOTAL_LENGTH];
		/**
		 * 组数
		 */
		private byte[] groupSize = new byte[GROUP_SIZE_LENGTH];
		/**
		 * 组
		 */
		private byte[] group = new byte[GROUP_LENGTH];
		/**
		 * 表1
		 */
		private byte[] meter1 = new byte[METER_1_LENGTH];
		/**
		 * 表2
		 */
		private byte[] meter2 = new byte[METER_2_LENGTH];
		/**
		 * 表3
		 */
		private byte[] meter3 = new byte[METER_3_LENGTH];
		/**
		 * 表4
		 */
		private byte[] meter4 = new byte[METER_4_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.total, 0, this.total.length);
			index += this.total.length;

			System.arraycopy(data, index, this.groupSize, 0, this.groupSize.length);
			index += this.groupSize.length;

			System.arraycopy(data, index, this.group, 0, this.group.length);
			index += this.group.length;

			System.arraycopy(data, index, this.meter1, 0, this.meter1.length);
			index += this.meter1.length;

			System.arraycopy(data, index, this.meter2, 0, this.meter2.length);
			index += this.meter2.length;

			System.arraycopy(data, index, this.meter3, 0, this.meter3.length);
			index += this.meter3.length;

			System.arraycopy(data, index, this.meter4, 0, this.meter4.length);
			index += this.meter4.length;

			return index;
		}
	}
}