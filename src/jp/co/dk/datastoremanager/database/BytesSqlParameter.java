package jp.co.dk.datastoremanager.database;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

class BytesSqlParameter extends SqlParameter{
	
	protected byte[] parameter;
	
	BytesSqlParameter(byte[] parameter) throws DataStoreManagerException {
		if (parameter == null) throw new DataStoreManagerException(SQL_PARAMETER_IS_NOT_SET);
		this.parameter = parameter;
	} 
	
	BytesSqlParameter(Object parameter) throws DataStoreManagerException {
		if (parameter == null) throw new DataStoreManagerException(SQL_PARAMETER_IS_NOT_SET);
		try {
			ByteArrayOutputStream baos= new ByteArrayOutputStream();  
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(parameter);
			this.parameter = baos.toByteArray();
			baos.close();
			oos.close();
		} catch (IOException e) {
			throw new DataStoreManagerException(FAILE_TO_AN_ATTEMPT_WAS_MADE_TO_CONVERT_TO_BYTE_ARRAY, e);
		}
	}

	@Override
	void set(int index, PreparedStatement statement) throws DataStoreManagerException {
		try {
			statement.setBytes(index, this.parameter);
		} catch (SQLException e) {
			throw new DataStoreManagerException(AN_EXCEPTION_OCCURRED_WHEN_PERFORMING_THE_SET_PARAMETERS_TO_SQL, e);
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (!(object instanceof BytesSqlParameter)) return false;
		BytesSqlParameter thisClassObj = (BytesSqlParameter) object;
		if (thisClassObj.hashCode() == this.hashCode()) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		for (int i=0; i<this.parameter.length; i++) {
			result*=this.parameter[i];
		}
		return result * 17;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i< this.parameter.length; i++) {
			sb.append(this.parameter[i]).append(',');
		}
		StringBuilder result = new StringBuilder(sb.substring(0, sb.length()-1));
		result.append("(byte[])");;
		return result.toString();
	}
}