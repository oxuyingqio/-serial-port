package cn.xuyingqi.serial.port.protocol.di;

import java.text.SimpleDateFormat;

import cn.xuyingqi.util.util.ByteUtils;

/**
 * DI
 * 
 * @author XuYQ
 *
 */
public abstract class Di {

	/**
	 * 日期格式化
	 */
	protected static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 日期时间格式化
	 */
	protected static final SimpleDateFormat DATETIME_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * DI
	 */
	public Di() {

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
	public int fill(byte[] data, int index) {

		return this.prototype.fill(data, index);
	}

	/**
	 * 获取DI
	 * 
	 * @return
	 */
	public String getDi() {

		return Integer.toHexString(ByteUtils.byteArray2Int(this.prototype.di));
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("DI：");
		sb.append(this.getDi());
		sb.append(" [");
		for (int i = 0, length = this.prototype.di.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.di[i])));
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
		 * DI默认长度
		 */
		private static final int DI_LENGTH = 1;

		/**
		 * DI
		 */
		private byte[] di = new byte[DI_LENGTH];

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			System.arraycopy(data, index, this.di, 0, this.di.length);
			index += this.di.length;

			return index;
		}
	}
}