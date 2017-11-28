package cn.xuyingqi.serial.port.protocol.di.impl;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 计量运行参数_值
 * 
 * @author XuYQ
 *
 */
public class MeteringOperationParamValue extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x2A;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 计量运行参数_值
	 */
	public MeteringOperationParamValue() {

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

		return "计量运行参数_值";
	}

	/**
	 * 获取付费类型
	 * 
	 * @return
	 */
	public Short getPaymentType() {

		return ByteUtils.byteArray2Short(this.prototype.paymentType);
	}

	/**
	 * 获取最大累加气量
	 * 
	 * @return
	 */
	public Long getMaxValue() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.maxValue));
	}

	/**
	 * 获取最小方提示
	 * 
	 * @return
	 */
	public Long getMinValue() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.minValue));
	}

	/**
	 * 获取关阀气量
	 * 
	 * @return
	 */
	public Long getCloseValue() {

		return ByteUtils.byteArray2Long(this.prototype.closeValue);
	}

	/**
	 * 获取单价
	 * 
	 * @return
	 */
	public Integer getPrice() {

		return ByteUtils.byteArray2Int(ByteUtils.reverse(this.prototype.price));
	}

	/**
	 * 获取结算类型
	 * 
	 * @return
	 */
	public Short getSettlementType() {

		return ByteUtils.byteArray2Short(this.prototype.settlementType);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

		sb.append("		付费类型：");
		sb.append(this.getPaymentType());
		sb.append(" [");
		for (int i = 0, length = this.prototype.paymentType.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.paymentType[i])));
		}
		sb.append("]");

		sb.append("		最大累加气量：");
		sb.append(this.getMaxValue());
		sb.append(" [");
		for (int i = 0, length = this.prototype.maxValue.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.maxValue[i])));
		}
		sb.append("]");

		sb.append("		最小方提示：");
		sb.append(this.getMinValue());
		sb.append(" [");
		for (int i = 0, length = this.prototype.minValue.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.minValue[i])));
		}
		sb.append("]");

		sb.append("		关阀气量：");
		sb.append(this.getCloseValue());
		sb.append(" [");
		for (int i = 0, length = this.prototype.closeValue.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.closeValue[i])));
		}
		sb.append("]");

		sb.append("		单价：");
		sb.append(this.getPrice());
		sb.append(" [");
		for (int i = 0, length = this.prototype.price.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.price[i])));
		}
		sb.append("]");

		sb.append("		结算类型：");
		sb.append(this.getSettlementType());
		sb.append(" [");
		for (int i = 0, length = this.prototype.settlementType.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.settlementType[i])));
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
		 * 付费类型默认长度
		 */
		private static final int PAYMENT_TYPE_LENGTH = 1;
		/**
		 * 最大累加气量默认长度
		 */
		private static final int MAX_VALUE_LENGTH = 4;
		/**
		 * 最小方提示默认长度
		 */
		private static final int MIN_VALUE_LENGTH = 4;
		/**
		 * 关阀气量默认长度
		 */
		private static final int CLOSE_VALUE_LENGTH = 4;
		/**
		 * 单价默认长度
		 */
		private static final int PRICE_LENGTH = 2;
		/**
		 * 结算类型默认长度
		 */
		private static final int SETTLEMENT_TYPE_LENGTH = 1;

		/**
		 * 付费类型
		 */
		private byte[] paymentType = new byte[PAYMENT_TYPE_LENGTH];
		/**
		 * 最大累加气量
		 */
		private byte[] maxValue = new byte[MAX_VALUE_LENGTH];
		/**
		 * 最小方提示
		 */
		private byte[] minValue = new byte[MIN_VALUE_LENGTH];
		/**
		 * 关阀气量
		 */
		private byte[] closeValue = new byte[CLOSE_VALUE_LENGTH];
		/**
		 * 单价
		 */
		private byte[] price = new byte[PRICE_LENGTH];
		/**
		 * 结算类型
		 */
		private byte[] settlementType = new byte[SETTLEMENT_TYPE_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.paymentType, 0, this.paymentType.length);
			index += this.paymentType.length;

			System.arraycopy(data, index, this.maxValue, 0, this.maxValue.length);
			index += this.maxValue.length;

			System.arraycopy(data, index, this.minValue, 0, this.minValue.length);
			index += this.minValue.length;

			System.arraycopy(data, index, this.closeValue, 0, this.closeValue.length);
			index += this.closeValue.length;

			System.arraycopy(data, index, this.price, 0, this.price.length);
			index += this.price.length;

			System.arraycopy(data, index, this.settlementType, 0, this.settlementType.length);
			index += this.settlementType.length;

			return index;
		}
	}
}