package cn.xuyingqi.serial.port.protocol;

import cn.xuyingqi.serial.port.protocol.model.CommunicationState;
import cn.xuyingqi.serial.port.protocol.model.Function;
import cn.xuyingqi.serial.port.protocol.model.TransmissionDirection;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 数据报文
 * 
 * @author XuYQ
 *
 */
public class Datagram {

	/**
	 * 默认开始字节
	 */
	private static final byte DEFAULT_START = 0x68;

	/**
	 * 原型
	 */
	private Prototype prototype;

	/**
	 * 私有构造方法
	 */
	private Datagram() {

		this.prototype = new Prototype();
	}

	/**
	 * 获取数据报文实例
	 * 
	 * @param data
	 * @return
	 */
	public static final Datagram newInstance(byte data) {

		if (data == DEFAULT_START) {

			return new Datagram();
		} else {

			return null;
		}
	}

	/**
	 * 填充数据
	 * 
	 * @param data
	 * @return
	 */
	public boolean fill(byte data) {

		return this.prototype.fill(data);
	}

	/**
	 * 获取开始
	 * 
	 * @return
	 */
	public String getBegin() {

		return Integer.toHexString(ByteUtils.byteArray2Int(this.prototype.begin));
	}

	/**
	 * 获取地址
	 * 
	 * @return
	 */
	public long getAddress() {

		return ByteUtils.byteArray2Long(ByteUtils.reverse(this.prototype.address));
	}

	/**
	 * 获取传输方向
	 * 
	 * @return
	 */
	public TransmissionDirection getTransmissionDirection() {

		return TransmissionDirection.shortOf(ByteUtils.byte2Short(this.prototype.controlCode[0]));
	}

	/**
	 * 获取通讯状态
	 * 
	 * @return
	 */
	public CommunicationState getCommunicationState() {

		return CommunicationState.shortOf(ByteUtils.byte2Short(this.prototype.controlCode[0]));
	}

	/**
	 * 获取控制码
	 * 
	 * @return
	 */
	public Function getFunction() {

		return Function.shortOf(ByteUtils.byte2Short(this.prototype.controlCode[0]));
	}

	/**
	 * 获取长度
	 * 
	 * @return
	 */
	public Integer getLength() {

		return ByteUtils.byteArray2Int(this.prototype.length);
	}

	/**
	 * 获取数据域
	 * 
	 * @return
	 */
	public byte[] getData() {

		return this.prototype.data;
	}

	/**
	 * 获取校验码
	 * 
	 * @return
	 */
	public String getCheckCode() {

		return Integer.toHexString(ByteUtils.byteArray2Int(this.prototype.checkCode));
	}

	/**
	 * 获取结束
	 * 
	 * @return
	 */
	public String getEnd() {

		return Integer.toHexString(ByteUtils.byteArray2Int(this.prototype.end));
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("\n	开始：");
		sb.append(this.getBegin());
		sb.append(" [");
		for (int i = 0, length = this.prototype.begin.length; i < length; i++) {

			sb.append(Integer.toHexString(this.prototype.begin[i]));
			sb.append(" ");
		}
		sb.append("]");

		sb.append("\n	地址：");
		sb.append(this.getAddress());
		sb.append(" [");
		for (int i = 0, length = this.prototype.address.length; i < length; i++) {

			sb.append(Integer.toHexString(this.prototype.address[i]));
			sb.append(" ");
		}
		sb.append("]");

		sb.append("\n	传输方向：");
		sb.append(this.getTransmissionDirection().getDesc());
		sb.append("\n	通讯状态：");
		sb.append(this.getCommunicationState().getDesc());
		sb.append("\n	功能定义：");
		sb.append(this.getFunction().getDesc());
		sb.append(" [");
		for (int i = 0, length = this.prototype.controlCode.length; i < length; i++) {

			sb.append(Integer.toHexString(this.prototype.controlCode[i]));
			sb.append(" ");
		}
		sb.append("]");

		sb.append("\n	长度：");
		sb.append(this.getLength());
		sb.append(" [");
		for (int i = 0, length = this.prototype.length.length; i < length; i++) {

			sb.append(Integer.toHexString(this.prototype.length[i]));
			sb.append(" ");
		}
		sb.append("]");

		sb.append("\n	数据域：");
		for (int i = 0, length = this.getData().length; i < length; i++) {

			sb.append(Integer.toHexString(this.getData()[i]));
			sb.append(" ");
		}

		sb.append("\n	校验码：");
		sb.append(this.getCheckCode());
		sb.append(" [");
		for (int i = 0, length = this.prototype.checkCode.length; i < length; i++) {

			sb.append(Integer.toHexString(this.prototype.checkCode[i]));
			sb.append(" ");
		}
		sb.append("]");

		sb.append("\n	结束：");
		sb.append(this.getEnd());
		sb.append(" [");
		for (int i = 0, length = this.prototype.end.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.prototype.end[i]));
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
		 * 开始默认长度
		 */
		private static final int BEGIN_LENGTH = 1;
		/**
		 * 地址默认长度
		 */
		private static final int ADDRESS_LENGTH = 4;
		/**
		 * 控制码默认长度
		 */
		private static final int CONTROL_CODE_LENGTH = 1;
		/**
		 * 长度默认长度
		 */
		private static final int LENGTH_LENGTH = 1;
		/**
		 * 校验码默认长度
		 */
		private static final int CHECK_CODE_LENGTH = 1;
		/**
		 * 结束默认长度
		 */
		private static final int END_LENGTH = 1;

		/**
		 * 开始
		 */
		private byte[] begin = new byte[BEGIN_LENGTH];
		/**
		 * 地址
		 */
		private byte[] address = new byte[ADDRESS_LENGTH];
		/**
		 * 控制码
		 */
		private byte[] controlCode = new byte[CONTROL_CODE_LENGTH];
		/**
		 * 长度
		 */
		private byte[] length = new byte[LENGTH_LENGTH];
		/**
		 * 数据域
		 */
		private byte[] data;
		/**
		 * 校验码
		 */
		private byte[] checkCode = new byte[CHECK_CODE_LENGTH];
		/**
		 * 结束
		 */
		private byte[] end = new byte[END_LENGTH];

		/**
		 * 索引值
		 */
		private int index = 0;

		/**
		 * 填充数据
		 * 
		 * @param data
		 * @return
		 */
		public boolean fill(byte data) {

			// 长度
			int length = 0;

			// 开始
			if (this.index >= length && this.index < (this.begin.length + length)) {

				this.begin[this.index - length] = data;
				this.index++;

				return false;
			}
			length += this.begin.length;

			// 地址
			if (this.index >= length && this.index < (this.address.length + length)) {

				this.address[this.index - length] = data;
				this.index++;

				return false;
			}
			length += this.address.length;

			// 控制码
			if (this.index >= length && this.index < (this.controlCode.length + length)) {

				this.controlCode[this.index - length] = data;
				this.index++;

				return false;
			}
			length += this.controlCode.length;

			// 长度
			if (this.index >= length && this.index < (this.length.length + length)) {

				this.length[this.index - length] = data;
				this.index++;

				// 长度字节接收完毕,初始化数据域
				if ((this.index - length) == this.length.length) {

					// 初始化数据域
					this.data = new byte[ByteUtils.byteArray2Int(this.length)];
				}

				return false;
			}
			length += this.length.length;

			// 数据域
			if (this.index >= length && this.index < (this.data.length + length)) {

				this.data[this.index - length] = data;
				this.index++;

				return false;
			}
			length += this.data.length;

			// 校验码
			if (this.index >= length && this.index < (this.checkCode.length + length)) {

				this.checkCode[this.index - length] = data;
				this.index++;

				return false;
			}
			length += this.checkCode.length;

			// 结束
			if (this.index >= length && this.index < (this.end.length + length)) {

				this.end[this.index - length] = data;
				this.index++;

				return (this.index - length) == this.end.length;
			}

			return false;
		}
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		short a = 0xFF;
		short b = 0x80;
		int c = (a & b);

		System.out.println(Integer.toHexString(c));
	}
}