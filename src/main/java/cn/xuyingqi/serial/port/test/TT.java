package cn.xuyingqi.serial.port.test;

public class TT {

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {

			new Thread(new Runnable() {

				@Override
				public void run() {

					System.out.println(T.getInstance());
				}
			}).start();
		}
	}
}