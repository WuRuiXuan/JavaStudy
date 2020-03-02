package example.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC 一套通用的使用Java操作数据库的标准 下载地址：https://dev.mysql.com/downloads/connector/j/
 * 选择Platform Independent
 */

public class JDBCDemo {

	public static void main(String[] args) {
		// 添加
//		handleSql("insert into person(name, age, description) values('小强', 18, '小强不是蟑螂')");
		// 修改
//		handleSql("update person set name='小小强', age=8, description='小小强只是一只蟑螂' where name='小强'");
		// 删除
//		handleSql("delete from person where id=1");
		// 查询（"select *"会影响数据库性能）
//		querySql("select id,name,age,description from person");
		
		// 使用字符串拼接的方法构造SQL语句有风险：1.SQL注入 2.性能问题
		// 解决方法：使用占位符+PreparedStatement（预编译功能）
//		insertPerson(new Person("小强", 18, "小强不是蟑螂"));
//		updatePerson(2, new Person("小小强", 8, "小小强只是一只蟑螂"));
//		deletePerson(2);
//		queryPerson(0, 3);
		transaction();
	}

	// 增删改
	public static void handleSql(String sql) {
		// 加载数据库驱动程序
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 通过用户名和密码连接数据库，返回连接对象（该对象返回表示连接成功）
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT", "root",
					"123456");
			// 用于传输SQL语句的载体
			Statement s = conn.createStatement();
			// 执行操作，返回有几条数据被操作
			int result = s.executeUpdate(sql);
			System.out.println("SQL语句执行成功：" + result);
			// 关闭
			s.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 查询
	public static void querySql(String sql) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT", "root",
					"123456");
			Statement s = conn.createStatement();
			// 执行查询，返回结果集
			ResultSet result = s.executeQuery(sql);
			while (result.next()) {
				int id = result.getInt("id"); // 通过列名取
				String name = result.getString(2); // 通过列序号取（序号从1开始）
				int age = result.getInt(3);
				String description = result.getString(4);
				System.out.println("id=" + id + ", name=" + name + ", age=" + age + ", description=" + description);
			}
			System.out.println("查询成功");
			s.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertPerson(Person p) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT", "root",
					"123456");
			PreparedStatement ps = conn.prepareStatement("insert into person(name, age, description) values(?, ?, ?)");
			// 给?赋值
			ps.setString(1, p.getName());
			ps.setInt(2, p.getAge());
			ps.setString(3, p.getDescription());
			int result = ps.executeUpdate();
			System.out.println("插入成功：" + result);
			ps.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updatePerson(int id, Person p) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT", "root",
					"123456");
			PreparedStatement ps = conn.prepareStatement("update person set name=?, age=?, description=? where id=?");
			ps.setString(1, p.getName());
			ps.setInt(2, p.getAge());
			ps.setString(3, p.getDescription());
			ps.setInt(4, id);
			int result = ps.executeUpdate();
			System.out.println("修改成功：" + result);
			ps.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deletePerson(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT", "root",
					"123456");
			PreparedStatement ps = conn.prepareStatement("delete from person where id=?");
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			System.out.println("删除成功：" + result);
			ps.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void queryPerson(int startIndex, int count) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT", "root",
					"123456");
			PreparedStatement ps = conn.prepareStatement("select id,name,age,description from person limit ?,?");
			ps.setInt(1, startIndex);
			ps.setInt(2, count);
			// 执行查询，返回结果集
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				int id = result.getInt("id"); // 通过列名取
				String name = result.getString(2); // 通过列序号取（序号从1开始）
				int age = result.getInt(3);
				String description = result.getString(4);
				System.out.println("id=" + id + ", name=" + name + ", age=" + age + ", description=" + description);
			}
			System.out.println("查询成功");
			ps.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 事务
	public static void transaction() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT", "root",
					"123456");
			// 设置手动提交事务
			conn.setAutoCommit(false);
			String sql1 = "insert into person(name, age, description) values(?, ?, ?)";
			String sql2 = "update person set name=?, age=?, description=? where id=?";
			ps = conn.prepareStatement(sql1);
			ps.setString(1, "老黄");
			ps.setInt(2, 5);
			ps.setString(3, "老黄是条狗");
			ps.executeUpdate();
			ps = conn.prepareStatement(sql2);
			ps.setString(1, "大白");
			ps.setInt(2, 1);
			ps.setString(3, "大白是兔兔");
			ps.setInt(4, 2);
			ps.executeUpdate();
			// 提交事务
			conn.commit();
			System.out.println("事务提交成功");
			ps.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				// 有一条事务失败就回滚
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
