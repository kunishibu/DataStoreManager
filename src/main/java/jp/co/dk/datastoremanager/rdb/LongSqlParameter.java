package jp.co.dk.datastoremanager.rdb;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.AN_EXCEPTION_OCCURRED_WHEN_PERFORMING_THE_SET_PARAMETERS_TO_SQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

class LongSqlParameter extends SqlParameter{
	
	protected long parameter;
	
	LongSqlParameter(long parameter) {
		this.parameter = parameter;
	}

	@Override
	void set(int index, PreparedStatement statement) throws DataStoreManagerException {
		try {
			statement.setLong(index, this.parameter);
		} catch (SQLException e) {
			throw new DataStoreManagerException(AN_EXCEPTION_OCCURRED_WHEN_PERFORMING_THE_SET_PARAMETERS_TO_SQL, e);
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (!(object instanceof LongSqlParameter)) return false;
		LongSqlParameter thisClassObj = (LongSqlParameter) object;
		if (thisClassObj.hashCode() == this.hashCode()) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return (int) ((int)this.parameter * 17L);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.parameter).append("(long)");
		return sb.toString();
	}
}
