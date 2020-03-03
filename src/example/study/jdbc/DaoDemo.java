package example.study.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO设计模式
 * Data Access Object数据访问接口
 */

public class DaoDemo {

	public static void main(String[] args) throws SQLException {
		IPersonDao dao = new IPersonDaoImpl();
//		dao.insert(new Person("小若", 18, "小若是一个妹子"));
//		dao.update(new Person(2, "小若若", 20, "小若若是一个女神"));
		
//		Person p = dao.findById(10);
//		System.out.println(p);
		
		List<Person> list = dao.findAll();
		for (Person person : list) {
			System.out.println(person);
		}
	}

	static class IPersonDaoImpl implements IPersonDao {

		@Override
		public void insert(Person p) throws SQLException {
			String sql = "insert into person(name, age, description) values(?, ?, ?)";
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = MyDbUtils.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, p.getName());
				ps.setInt(2, p.getAge());
				ps.setString(3, p.getDescription());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("添加数据失败！");
			} finally {
				MyDbUtils.close(null, ps, conn);
			}
		}

		@Override
		public void delete(int id) throws SQLException {
			String sql = "delete from person where id=?";
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = MyDbUtils.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("删除数据失败！");
			} finally {
				MyDbUtils.close(null, ps, conn);
			}
		}

		@Override
		public void update(Person p) throws SQLException {
			String sql = "update person set name=?, age=?, description=? where id=?";
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = MyDbUtils.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, p.getName());
				ps.setInt(2, p.getAge());
				ps.setString(3, p.getDescription());
				ps.setInt(4, p.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("修改数据失败！");
			} finally {
				MyDbUtils.close(null, ps, conn);
			}
		}

		@Override
		public Person findById(int id) throws SQLException {
			String sql = "select id,name,age,description from person where id=?";
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Person p = null;
			try {
				conn = MyDbUtils.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					p = new Person();
					p.setId(rs.getInt(1));
					p.setName(rs.getString(2));
					p.setAge(rs.getInt(3));
					p.setDescription(rs.getString(4));
				}
				return p;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("根据ID查询数据失败！");
			} finally {
				MyDbUtils.close(rs, ps, conn);
			}
		}

		@Override
		public List<Person> findAll() throws SQLException {
			String sql = "select id,name,age,description from person";
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<Person> list = new ArrayList<>();
			try {
				conn = MyDbUtils.getConnection();
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Person p = new Person();
					p.setId(rs.getInt(1));
					p.setName(rs.getString(2));
					p.setAge(rs.getInt(3));
					p.setDescription(rs.getString(4));
					list.add(p);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("根据ID查询数据失败！");
			} finally {
				MyDbUtils.close(rs, ps, conn);
			}
		}
		
	}
}
