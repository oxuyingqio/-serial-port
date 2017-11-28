package cn.xuyingqi.serial.port.protocol.di.impl;

import cn.xuyingqi.serial.port.protocol.di.Di;
import cn.xuyingqi.serial.port.protocol.model.RechargeType;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 空中充值_确认
 * 
 * @author XuYQ
 *
 */
public class AerialRechargeConfirm extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x4B;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 空中充值_确认
	 */
	public AerialRechargeConfirm() {

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

		return "空中充值_确认";
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
	 * 获取随机数
	 * 
	 * @return
	 */
	public Short getRandom() {

		return ByteUtils.byteArray2Short(this.prototype.random);
	}

	/**
	 * 获取充值次数
	 * 
	 * @return
	 */
	public RechargeType getRechargeTime() {

		return RechargeType.codeOf(ByteUtils.byteArray2Short(this.prototype.rechargeTime));
	}

	/**
	 * 获取总充值量
	 * 
	 * @return
	 */
	public Long getTotalRechargeData() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.totalRechargeData));
	}

	/**
	 * 获取剩余量
	 * 
	 * @return
	 */
	public Long getResidualQuantity() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.residualQuantity));
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

		sb.append("		随机数：");
		sb.append(this.getRandom());
		sb.append(" [");
		for (int i = 0, length = this.prototype.random.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.random[i])));
		}
		sb.append("]");

		sb.append("		充值次数：");
		sb.append(this.getRechargeTime().getDesc());
		sb.append(" [");
		for (int i = 0, length = this.prototype.rechargeTime.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.rechargeTime[i])));
		}
		sb.append("]");

		sb.append("		总充值量：");
		sb.append(this.getTotalRechargeData());
		sb.append(" [");
		for (int i = 0, length = this.prototype.totalRechargeData.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.totalRechargeData[i])));
		}
		sb.append("]");

		sb.append("		剩余量：");
		sb.append(this.getResidualQuantity());
		sb.append(" [");
		for (int i = 0, length = this.prototype.residualQuantity.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.residualQuantity[i])));
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
		 * 随机数默认长度
		 */
		private static final int RANDOM_LENGTH = 1;
		/**
		 * 充值次数默认长度
		 */
		private static final int RECHARGE_TIME_LENGTH = 1;
		/**
		 * 总充值量默认长度
		 */
		private static final int TOTAL_RECHARGE_DATA_LENGTH = 4;
		/**
		 * 剩余量默认长度
		 */
		private static final int RESIDUAL_QUANTITY_LENGTH = 4;

		/**
		 * 序号
		 */
		private byte[] serialNumber = new byte[SERIAL_NUMBER_LENGTH];
		/**
		 * 随机数
		 */
		private byte[] random = new byte[RANDOM_LENGTH];
		/**
		 * 充值次数
		 */
		private byte[] rechargeTime = new byte[RECHARGE_TIME_LENGTH];
		/**
		 * 充值量
		 */
		private byte[] totalRechargeData = new byte[TOTAL_RECHARGE_DATA_LENGTH];
		/**
		 * 剩余量
		 */
		private byte[] residualQuantity = new byte[RESIDUAL_QUANTITY_LENGTH];

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

			System.arraycopy(data, index, this.random, 0, this.random.length);
			index += this.random.length;

			System.arraycopy(data, index, this.rechargeTime, 0, this.rechargeTime.length);
			index += this.rechargeTime.length;

			System.arraycopy(data, index, this.totalRechargeData, 0, this.totalRechargeData.length);
			index += this.totalRechargeData.length;

			System.arraycopy(data, index, this.residualQuantity, 0, this.residualQuantity.length);
			index += this.residualQuantity.length;

			return index;
		}
	}
}