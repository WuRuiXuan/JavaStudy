package example.study.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 封装JDBC工具类
 */

// 使用单例模式实现工具类
public class MyDbUtils {
	
	public static void main(String[] args) {
		Connection conn = MyDbUtils.getConnection();
		System.out.println(conn);
	}
	
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;

	public MyDbUtils() {
	}
	
	// 静态块只执行一次
	static {
		try {
			// 加载配置文件
			loadConfig();
			// 加载驱动程序
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadConfig() {
		Properties p = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("example/study/jdbc/db.properties");
		try {
			p.load(in);
			driver = p.getProperty("jdbc.driver");
			url = p.getProperty("jdbc.url");
			username = p.getProperty("jdbc.username");
			password = p.getProperty("jdbc.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
