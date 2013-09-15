package jp.co.dk.datastoremanager.database;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.AN_EXCEPTION_OCCURRED_WHEN_PERFORMING_THE_SET_PARAMETERS_TO_SQL;
import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.SQL_PARAMETER_IS_NOT_SET;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

class DateSqlParameter extends SqlParameter{
	
	protected Date parameter;
	
	DateSqlParameter(Date parameter) throws DataStoreManagerException {
		if (parameter == null) throw new DataStoreManagerException(SQL_PARAMETER_IS_NOT_SET);
		this.parameter = parameter;
	}

	@Override
	void set(int index, PreparedStatement statement) throws DataStoreManagerException {
		try {
			statement.setDate(index, new java.sql.Date(this.parameter.getTime()));
		} catch (SQLException e) {
			throw new DataStoreManagerException(AN_EXCEPTION_OCCURRED_WHEN_PERFORMING_THE_SET_PARAMETERS_TO_SQL, e);
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (!(object instanceof DateSqlParameter)) return false;
		DateSqlParameter thisClassObj = (DateSqlParameter) object;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		sb.append(sdf.format(this.parameter).toString()).append("(date)");
		return sb.toString();
	}
}