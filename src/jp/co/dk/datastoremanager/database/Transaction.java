package jp.co.dk.datastoremanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

class Transaction {
	
	/** コネクション */
	protected Connection connection;
	
	/** パラメータ */
	protected DataBaseAccessParameter dataBaseAccessParameter;
	
	/**
	 * コンストラクタ<p/>
	 * 指定のトランザクションのパラメータを元にトランザクションを開始する。
	 * 
	 * @param dataBaseAccessParameter トランザクションパラメータ
	 * @throws DataStoreManagerException トランザクションの開始に失敗した場合
	 */
	Transaction(DataBaseAccessParameter dataBaseAccessParameter) throws DataStoreManagerException{
		if (dataBaseAccessParameter == null) throw new DataStoreManagerException(PARAMETER_IS_NOT_SET);
		this.dataBaseAccessParameter = dataBaseAccessParameter;
		try {
			DataBaseDriverConstants driverConstants = dataBaseAccessParameter.getDriver();
			String driver   = driverConstants.getDriverClass();
			String url      = driverConstants.getUrl(dataBaseAccessParameter.getUrl(), dataBaseAccessParameter.getSid());
			String user     = dataBaseAccessParameter.getUser();
			String password = dataBaseAccessParameter.getPassword();
			Class.forName(driver);
			this.connection = DriverManager.getConnection(url, user, password);
			this.connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			throw new DataStoreManagerException(FAILE_TO_READ_DRIVER, e);
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_CREATE_CONNECTION, e);
		}
	}
	
	void createTable(Sql sql) throws DataStoreManagerException {
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql.getSql());
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_EXECUTE_SQL, e);
		}
	}
	
	void insert(Sql sql) throws DataStoreManagerException {
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql.getSql());
			List<SqlParameter> sqlPrameterList = sql.getParameterList();
			for (int index = 0; index < sqlPrameterList.size(); index++) {
				int tmpIndex = index + 1;
				sqlPrameterList.get(index).set(tmpIndex, statement);
			}
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_EXECUTE_SQL, e);
		}
	}
	
	int update(Sql sql) throws DataStoreManagerException {
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql.getSql());
			List<SqlParameter> sqlPrameterList = sql.getParameterList();
			for (int index = 0; index < sqlPrameterList.size(); index++) {
				int tmpIndex = index + 1;
				sqlPrameterList.get(index).set(tmpIndex, statement);
			}
			int result = statement.executeUpdate();
			statement.executeUpdate();
			statement.close();
			return result;
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_EXECUTE_SQL, e);
		}
	}
	
	int delete(Sql sql) throws DataStoreManagerException {
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql.getSql());
			List<SqlParameter> sqlPrameterList = sql.getParameterList();
			for (int index = 0; index < sqlPrameterList.size(); index++) {
				int tmpIndex = index + 1;
				sqlPrameterList.get(index).set(tmpIndex, statement);
			}
			int result = statement.executeUpdate();
			statement.executeUpdate();
			statement.close();
			return result;
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_EXECUTE_SQL, e);
		}
	}
	
	ResultSet select(Sql sql) throws DataStoreManagerException {
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql.getSql());
			List<SqlParameter> sqlPrameterList = sql.getParameterList();
			for (int index = 0; index < sqlPrameterList.size(); index++) {
				int tmpIndex = index + 1;
				sqlPrameterList.get(index).set(tmpIndex, statement);
			}
			return statement.executeQuery();
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_EXECUTE_SQL, e);
		}
	}
	
	void dropTable(Sql sql) throws DataStoreManagerException {
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql.getSql());
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_EXECUTE_SQL, e);
		}
	}
	
	void commit() throws DataStoreManagerException {
		try {
			this.connection.commit();
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_COMMIT, e);
		}
	}
	
	void rollback() throws DataStoreManagerException {
		try {
			this.connection.rollback();
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_ROLLBACK, e);
		}
	}
	
	void close() throws DataStoreManagerException {
		try {
			this.connection.close();
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_CLOSE, e);
		}
	}
	
	/**
	 * コネクションを取得します。
	 * 
	 * @return コネクション
	 */
	Connection getConnection() {
		return connection;
	}
	
	@Override
	public int hashCode() {
		return this.connection.hashCode() * this.dataBaseAccessParameter.hashCode() * 17;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (!(object instanceof Transaction)) return false;
		Transaction thisClassIbj = (Transaction)object;
		if (thisClassIbj.hashCode() == this.hashCode()) return true;
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CONNECTION_HASH=[").append(this.connection.hashCode()).append(']').append(',');
		sb.append(this.dataBaseAccessParameter.toString());
		return sb.toString();
	}
}
