package example.study.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicDelegateDemo {

	public static void main(String[] args) {
		Subject s = new SuperMan();
		MyProxy p = new MyProxy(s);
		// 动态创建代理对象
		Subject delegator = (Subject) Proxy.newProxyInstance(s.getClass().getClassLoader(), s.getClass().getInterfaces(), p);
		delegator.shopping();
	}
	
	static class SuperMan implements Subject {
		@Override
		public void shopping() {
			System.out.println("哥有钱，给媳妇买进口化妆品");
		}
	}
	
	// 代理类
	static class MyProxy implements InvocationHandler {
		private Subject target;
		
		public MyProxy(Subject target) {
			this.target = target;
		}
		
		// 参数：代理类，被代理的方法，被代理的方法的参数组
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("做大量的商品专业评估");
			
			method.invoke(target, args);
			
			// 代购之后要做的事情
			System.out.println("客户满意度调查");
			
			return null;
		}
	}
}

interface Subject {
	public void shopping();
}
