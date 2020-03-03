package example.study.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 调用存储过程
 */

public class ProcedureDemo {

	public static void main(String[] args) {
		callProc("小强");
	}

	public static void callProc(String in) {
		String sql = "call proc(?,?)";
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = MyDbUtils.getConnection();
			cs = conn.prepareCall(sql);
			// 输入参数
			cs.setString(1, in);
			// 注册输出参数类型
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.execute();
			// 输出结果
			System.out.println(cs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cs != null) cs.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
