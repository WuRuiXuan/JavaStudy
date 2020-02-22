package example.study.reflection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 内省
 */

public class IntrospectorDemo {

	public static void main(String[] args) {
		User user = UserFactory.newUser();
		System.out.println(user);
	}
	
	public static class UserFactory {
		private static Properties prop = new Properties();
		
		static {
			// 加载配置文件
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("example/study/reflection/user.properties");
			try {
				prop.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static User newUser() {
			String userProp = prop.getProperty("user");
			User user = null;
			try {
				// example.study.reflection.IntrospectorDemo.User
				Class beanClass = Class.forName(userProp);
				user = (User) beanClass.newInstance();
				BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
				// 获取属性描述器
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					String name = pd.getName();
					Method m = pd.getWriteMethod();
					if (name.equals("name")) {
						m.invoke(user, prop.getProperty("name"));
					}
					else if (name.equals("nums")) {
						m.invoke(user, Integer.parseInt(prop.getProperty("nums")));
					}
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			return user;
		}
	}
}
