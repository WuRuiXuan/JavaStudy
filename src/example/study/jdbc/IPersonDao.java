package example.study.jdbc;

import java.sql.SQLException;
import java.util.List;

public interface IPersonDao {
	public void insert(Person p) throws SQLException;
	public void delete(int id) throws SQLException;
	public void update(Person p) throws SQLException;
	public Person findById(int id) throws SQLException;
	public List<Person> findAll() throws SQLException;
}
