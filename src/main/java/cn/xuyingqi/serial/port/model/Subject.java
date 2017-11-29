package cn.xuyingqi.serial.port.model;

import java.util.List;

import cn.xuyingqi.util.util.ListFactory;

/**
 * 主题-被观察者
 * 
 * @author XuYQ
 *
 */
public class Subject {

	/**
	 * 主题
	 */
	private static Subject subject;

	/**
	 * 观察者集合
	 */
	private List<Observer> observers = ListFactory.newInstance();

	/**
	 * 私有构造方法
	 */
	private Subject() {

	}

	/**
	 * 获取主题实例
	 * 
	 * @return
	 */
	public static final synchronized Subject getInstance() {

		if (subject == null) {

			subject = new Subject();
		}

		return subject;
	}

	/**
	 * 添加观察者
	 * 
	 * @param observer
	 */
	public void addObserver(Observer observer) {

		this.observers.add(observer);
	}

	/**
	 * 通知观察者
	 * 
	 * @param msg
	 */
	public void notifyObservers(String msg) {

		for (Observer observer : this.observers) {

			observer.notify(msg);
		}
	}
}