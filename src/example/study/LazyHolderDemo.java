package example.study;

/**
 * 惰性初始化模式实现单例
 */
public class LazyHolderDemo {
	
	public static void main(String[] args) {
		System.out.println(Singleton.getInstance());
	}
	
	static final class Singleton {
	    private Singleton() {}
	    // 内部类中保存单例
	    private static class LazyHolder {
	        static final Singleton INSTANCE = new Singleton();
	    }
	    // 第一次调用getInstance方法，才会导致内部类加载和初始化其静态成员
	    public static Singleton getInstance() {
	        return LazyHolder.INSTANCE;
	    }
	}
	
}
