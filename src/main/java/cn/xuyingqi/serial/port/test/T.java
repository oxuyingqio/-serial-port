package cn.xuyingqi.serial.port.test;

import java.util.Arrays;

import cn.xuyingqi.util.util.ByteUtils;

public class T {

	public static void main(String[] args) {

		byte[] temp = ByteUtils.doubleHexString2ByteArray(
				"00A5000B00FFF4FFFFFFFFFFFFFFFFAAFF55FFFF16176161292D0D501B2D0900006A000A7B070000000000E5070015007056384834128101000A030000FF213E");

		System.out.println(Arrays.toString(temp));
		System.out.println(temp.length);
	}
}