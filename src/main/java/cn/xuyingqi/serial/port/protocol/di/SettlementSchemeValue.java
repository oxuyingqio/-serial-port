package cn.xuyingqi.serial.port.protocol.di;

import cn.xuyingqi.util.util.ByteUtils;

/**
 * 结算方案_值
 * 
 * @author XuYQ
 *
 */
public class SettlementSchemeValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x2B;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 结算方案_值
	 */
	public SettlementSchemeValue() {

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
	 * 获取序号
	 * 
	 * @return
	 */
	public Short getSerialNumber() {

		return ByteUtils.byteArray2Short(this.prototype.serialNumber);
	}

	/**
	 * 获取单价1
	 * 
	 * @return
	 */
	public Integer getPrice1() {

		return ByteUtils.byteArray2Int(this.prototype.price1);
	}

	/**
	 * 获取气量1
	 * 
	 * @return
	 */
	public Integer getVolume1() {

		return ByteUtils.byteArray2Int(this.prototype.volume1);
	}

	/**
	 * 获取单价2
	 * 
	 * @return
	 */
	public Integer getPrice2() {

		return ByteUtils.byteArray2Int(this.prototype.price2);
	}

	/**
	 * 获取气量2
	 * 
	 * @return
	 */
	public Integer getVolume2() {

		return ByteUtils.byteArray2Int(this.prototype.volume2);
	}

	/**
	 * 获取单价3
	 * 
	 * @return
	 */
	public Integer getPrice3() {

		return ByteUtils.byteArray2Int(this.prototype.price3);
	}

	/**
	 * 获取调价执行日期
	 * 
	 * @return
	 */
	public String getDate() {

		return ByteUtils.byte2Short(this.prototype.date[0]) + ByteUtils.byte2Short(this.prototype.date[1]) + "";
	}

	/**
	 * 获取结算周期起始月份
	 * 
	 * @return
	 */
	public Short getMonth() {

		return ByteUtils.byteArray2Short(this.prototype.month);
	}

	/**
	 * 获取调价标识
	 * 
	 * @return
	 */
	public Short getFlag() {

		return ByteUtils.byteArray2Short(this.prototype.flag);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("		序号：");
		sb.append(this.getSerialNumber());
		sb.append(" [");
		for (int i = 0, length = this.prototype.serialNumber.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.serialNumber[i])));
		}
		sb.append("]");

		sb.append("		单价1：");
		sb.append(this.getPrice1());
		sb.append(" [");
		for (int i = 0, length = this.prototype.price1.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.price1[i])));
		}
		sb.append("]");

		sb.append("		气量1：");
		sb.append(this.getVolume1());
		sb.append(" [");
		for (int i = 0, length = this.prototype.volume1.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.volume1[i])));
		}
		sb.append("]");

		sb.append("		单价2：");
		sb.append(this.getPrice2());
		sb.append(" [");
		for (int i = 0, length = this.prototype.price2.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.price2[i])));
		}
		sb.append("]");

		sb.append("		气量2：");
		sb.append(this.getVolume2());
		sb.append(" [");
		for (int i = 0, length = this.prototype.volume2.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.volume2[i])));
		}
		sb.append("]");

		sb.append("		单价3：");
		sb.append(this.getPrice3());
		sb.append(" [");
		for (int i = 0, length = this.prototype.price3.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.price3[i])));
		}
		sb.append("]");

		sb.append("		调价执行日期：");
		sb.append(this.getDate());
		sb.append(" [");
		for (int i = 0, length = this.prototype.date.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.date[i])));
		}
		sb.append("]");

		sb.append("		结算周期起始月份：");
		sb.append(this.getMonth());
		sb.append(" [");
		for (int i = 0, length = this.prototype.month.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.month[i])));
		}
		sb.append("]");

		sb.append("		调价标识：");
		sb.append(this.getFlag());
		sb.append(" [");
		for (int i = 0, length = this.prototype.flag.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.flag[i])));
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
		 * 序号默认长度
		 */
		private static final int SERIAL_NUMBER_LENGTH = 1;
		/**
		 * 单价1默认长度
		 */
		private static final int PRICE_1_LENGTH = 2;
		/**
		 * 气量1默认长度
		 */
		private static final int VOLUME_1_LENGTH = 2;
		/**
		 * 单价2默认长度
		 */
		private static final int PRICE_2_LENGTH = 2;
		/**
		 * 气量2默认长度
		 */
		private static final int VOLUME_2_LENGTH = 2;
		/**
		 * 单价3默认长度
		 */
		private static final int PRICE_3_LENGTH = 2;
		/**
		 * 调价执行日期默认长度
		 */
		private static final int DATE_LENGTH = 2;
		/**
		 * 结算周期起始月份默认长度
		 */
		private static final int MONTH_LENGTH = 1;
		/**
		 * 调价标识默认长度
		 */
		private static final int FLAG_LENGTH = 1;

		/**
		 * 序号
		 */
		private byte[] serialNumber = new byte[SERIAL_NUMBER_LENGTH];
		/**
		 * 单价1
		 */
		private byte[] price1 = new byte[PRICE_1_LENGTH];
		/**
		 * 气量1
		 */
		private byte[] volume1 = new byte[VOLUME_1_LENGTH];
		/**
		 * 单价2
		 */
		private byte[] price2 = new byte[PRICE_2_LENGTH];
		/**
		 * 气量2
		 */
		private byte[] volume2 = new byte[VOLUME_2_LENGTH];
		/**
		 * 单价3
		 */
		private byte[] price3 = new byte[PRICE_3_LENGTH];
		/**
		 * 调价执行日期
		 */
		private byte[] date = new byte[DATE_LENGTH];
		/**
		 * 结算周期起始月份
		 */
		private byte[] month = new byte[MONTH_LENGTH];
		/**
		 * 调价标识
		 */
		private byte[] flag = new byte[FLAG_LENGTH];

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

			System.arraycopy(data, index, this.price1, 0, this.price1.length);
			index += this.price1.length;

			System.arraycopy(data, index, this.volume1, 0, this.volume1.length);
			index += this.volume1.length;

			System.arraycopy(data, index, this.price2, 0, this.price2.length);
			index += this.price2.length;

			System.arraycopy(data, index, this.volume2, 0, this.volume2.length);
			index += this.volume2.length;

			System.arraycopy(data, index, this.price3, 0, this.price3.length);
			index += this.price3.length;

			System.arraycopy(data, index, this.date, 0, this.date.length);
			index += this.date.length;

			System.arraycopy(data, index, this.month, 0, this.month.length);
			index += this.month.length;

			System.arraycopy(data, index, this.flag, 0, this.flag.length);
			index += this.flag.length;

			return index;
		}
	}
}