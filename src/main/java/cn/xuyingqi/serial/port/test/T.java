package cn.xuyingqi.serial.port.test;

public class T {

	private static T t;

	private Long time;

	private T() {

		try {

			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		this.time = System.currentTimeMillis();
		
		try {

			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public static final T getInstance() {

		if (t == null) {

			synchronized (T.class) {

				if (t == null) {

					t = new T();
				}
			}
		}

		return t;
	}

	public String toString() {

		return this.time + "";
	}
}