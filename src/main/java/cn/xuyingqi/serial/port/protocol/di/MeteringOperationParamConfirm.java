package cn.xuyingqi.serial.port.protocol.di;

/**
 * 计量运行参数_确认
 * 
 * @author XuYQ
 *
 */
public class MeteringOperationParamConfirm extends Di {

	/**
	 * 默认DI值
	 */
	public static final byte DI = 0x2A;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 计量运行参数_确认
	 */
	public MeteringOperationParamConfirm() {

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
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());

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
		 * 填充数据
		 * 
		 * @param data
		 * @param index
		 * @return
		 */
		public int fill(byte[] data, int index) {

			return index;
		}
	}
}