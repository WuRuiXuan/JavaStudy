package example.study.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * 开源工具DbUtils
 * 下载地址：http://commons.apache.org/proper/commons-dbutils/download_dbutils.cgi
 */

public class DbUtilsDemo {

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
			QueryRunner qr = new QueryRunner();
			qr.update(MyDbUtils.getConnection(), sql, p.getName(), p.getAge(), p.getDescription());
		}

		@Override
		public void delete(int id) throws SQLException {
			String sql = "delete from person where id=?";
			QueryRunner qr = new QueryRunner();
			qr.update(MyDbUtils.getConnection(), sql, id);
		}

		@Override
		public void update(Person p) throws SQLException {
			String sql = "update person set name=?, age=?, description=? where id=?";
			QueryRunner qr = new QueryRunner();
			qr.update(MyDbUtils.getConnection(), sql, p.getName(), p.getAge(), p.getDescription(), p.getId());
		}

		@Override
		public Person findById(int id) throws SQLException {
			String sql = "select id,name,age,description from person where id=?";
			QueryRunner qr = new QueryRunner();
			Person p = qr.query(MyDbUtils.getConnection(), sql, new BeanHandler<Person>(Person.class), id);
			return p;
		}

		@Override
		public List<Person> findAll() throws SQLException {
			String sql = "select id,name,age,description from person";
			QueryRunner qr = new QueryRunner();
			List<Person> list = qr.query(MyDbUtils.getConnection(), sql, new BeanListHandler<Person>(Person.class));
			// 返回一个值
//			Object lastId = qr.query("select last_insert_id()", new ScalarHandler<>());
			return list;
		}
		
	}
}
