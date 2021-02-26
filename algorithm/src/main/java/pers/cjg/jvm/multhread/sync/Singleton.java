package pers.cjg.jvm.multhread.sync;

/*
 * 单例模式
 * 1、DCL
 * 2、饿汉模式
 * 3、静态内部类
 */
public class Singleton {
	
	private static volatile Singleton instance;//声明成 volatile
	private static class SingletonHolder{
		private static Singleton instance = new Singleton();
	}
	private Singleton(){
		
	}

	/**
	 * 静态内部类在使用的时候才会进行加载，类加载是单线程，所以是线程安全的
	 * @return
	 */
	public static Singleton getInstance1(){
		return SingletonHolder.instance;
	}

	/**
	 * synchronized
	 * 保证了原子性（同步块一整块）
	 * 可见性（加锁解锁工作内存与主内存进行同步）
	 * 顺序性（同步块上下不能指令重排）
	 *
	 * volatile保证了可见性、顺序性
	 * @return
	 */
	public static Singleton getInstance(){
		if(instance == null){
			synchronized (Singleton.class) {
				if(instance == null){
					/**
					 * 这里分三步
					 * 1、为instance分配空间
					 * 2、执行构造函数进行初始化
					 * 3、将instance与该空间进行关联 instance!=null
					 *
					 * 如果不使用volatile，执行顺序可能为1-3-2，
					 *
					 * 如果准备执行步骤3的时候有一个线程调用getInstance()，
					 * 此时instance!=null,则会直接返回，导致获取了一个未完成初始化的对象，就会出现问题
					 */
					instance = new Singleton();

				}
			}
		}
		return instance;
	}
}