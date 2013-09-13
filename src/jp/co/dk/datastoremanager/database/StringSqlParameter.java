package jp.co.dk.datastoremanager.database;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

class StringSqlParameter extends SqlParameter{
	
	protected String parameter;
	
	StringSqlParameter(String parameter) throws DataStoreManagerException {
		if (parameter == null || parameter.equals("")) throw new DataStoreManagerException(SQL_PARAMETER_IS_NOT_SET);
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
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (!(object instanceof StringSqlParameter)) return false;
		StringSqlParameter thisClassObj = (StringSqlParameter) object;
		if (thisClassObj.hashCode() == this.hashCode()) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.parameter.hashCode() * 17;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.parameter).append("(string)");
		return sb.toString();
	}
}