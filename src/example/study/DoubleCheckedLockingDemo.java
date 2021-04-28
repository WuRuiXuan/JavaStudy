package example.study;

/**
 * 双重检查锁（double-checked locking）模式实现单例
 */
public class DoubleCheckedLockingDemo {

	public static void main(String[] args) {
		System.out.println(Singleton.getInstance());
	}
	
	static final class Singleton {
	    private Singleton() {}
	    // volatile防止指令重排（JDK5以后）
	    private volatile static Singleton INSTANCE = null;
	    public static Singleton getInstance() {
	        // 实例没创建，才会进入内部的synchronized代码块
	        if (INSTANCE == null) {
	            synchronized (Singleton.class) {
	                // 也许有其它线程已经创建了实例，所以再判断一次
	                if (INSTANCE == null) {
	                    INSTANCE = new Singleton();
	                }
	            }
	        }
	        return INSTANCE;
	    }
	}

}
