package cn.xuyingqi.serial.port.model;

/**
 * 观察者
 * 
 * @author XuYQ
 *
 */
public interface Observer {

	/**
	 * 通知
	 * 
	 * @param msg
	 */
	public void notify(String msg);
}