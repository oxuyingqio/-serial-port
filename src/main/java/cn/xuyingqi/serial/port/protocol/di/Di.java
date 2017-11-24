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
	 * 日期时间格式化
	 */
	protected static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
	 * 获取DI
	 * 
	 * @return
	 */
	public String getDi() {

		return Integer.toHexString(ByteUtils.byteArray2Int(this.prototype.di));
	}

	/**
	 * 设置DI
	 * 
	 * @param data
	 */
	protected int setDi(byte[] data, int index) {

		System.arraycopy(data, index, this.prototype.di, 0, this.prototype.di.length);

		return this.prototype.di.length;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("		DI：");
		sb.append(this.getDi());
		sb.append(" [");
		for (int i = 0, length = this.prototype.di.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(ByteUtils.byte2Int(this.prototype.di[i])));
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
		 * DI默认长度
		 */
		private static final int DI_LENGTH = 1;

		/**
		 * DI
		 */
		private byte[] di = new byte[DI_LENGTH];
	}
}