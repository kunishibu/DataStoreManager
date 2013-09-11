package jp.co.dk.datastoremanager.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public class Sql {
	
	/** SQL本文 */
	private StringBuilder sql = new StringBuilder();
	
	/** パラメータ */
	private List<SqlParameter> sqlParameter = new ArrayList<SqlParameter>();
	
	public Sql(String sql) {
		this.sql.append(sql);
	}
	
	public void setParameter(String parameter) {
		sqlParameter.add(new StringSqlParameter(parameter));
	}
	
	public void setParameter(int parameter) {
		sqlParameter.add(new IntSqlParameter(parameter));
	}
	
	public void setParameter(Date parameter) {
		sqlParameter.add(new DateSqlParameter(parameter));
	}
	
	List<SqlParameter> getParameterList() {
		return this.sqlParameter;
	}
	
	@Override
	public String toString() {
		return this.sql.toString();
	}
}

abstract class SqlParameter {
	
	abstract void set(int index, PreparedStatement statement) throws DataStoreManagerException;
	
}

class StringSqlParameter extends SqlParameter{
	
	private String parameter;
	
	StringSqlParameter(String parameter) {
		this.parameter = parameter;
	} 

	@Override
	void set(int index, PreparedStatement statement) throws DataStoreManagerException {
		try {
			statement.setString(index, this.parameter);
		} catch (SQLException e) {
			throw new DataStoreManagerException(AN_EXCEPTION_OCCURRED_WHEN_PERFORMING_THE_SET_PARAMETERS_TO_SQL, e);
		}
	}
}

class IntSqlParameter extends SqlParameter{
	
	private int parameter;
	
	IntSqlParameter(int parameter) {
		this.parameter = parameter;
	}

	@Override
	void set(int index, PreparedStatement statement) throws DataStoreManagerException {
		try {
			statement.setInt(index, this.parameter);
		} catch (SQLException e) {
			throw new DataStoreManagerException(AN_EXCEPTION_OCCURRED_WHEN_PERFORMING_THE_SET_PARAMETERS_TO_SQL);
		}
	}
}

class DateSqlParameter extends SqlParameter{
	
	private Date parameter;
	
	DateSqlParameter(Date parameter) {
		this.parameter = parameter;
	}

	@Override
	void set(int index, PreparedStatement statement) throws DataStoreManagerException {
		try {
			statement.setDate(index, (java.sql.Date) this.parameter);
		} catch (SQLException e) {
			throw new DataStoreManagerException(AN_EXCEPTION_OCCURRED_WHEN_PERFORMING_THE_SET_PARAMETERS_TO_SQL);
		}
	}
}