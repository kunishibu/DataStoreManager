package jp.co.dk.datastoremanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public class Transaction {
	
	/** トランザクションプール */
	private static Map<TransactionParam, Transaction> transactionPool = new HashMap<TransactionParam, Transaction>();
	
	/** コネクション */
	private Connection connection;
	
	/** パラメータ */
	private DataBaseAccessParameter dataBaseAccessParameter;
	
	/**
	 * 指定のデータベースアクセスパラメータから呼び出し元のスレッドが保持しているトランザクションを取得する。<p/>
	 * 呼び出し元のスレッドがトランザクションを保持していない場合、生成し返却する。
	 * 
	 * @param dataBaseAccessParameter データストアアクセスパラメータ
	 * @return トランザクション
	 * @throws DataStoreManagerException トランザクション生成に失敗した場合
	 */
	static Transaction getTransaction(DataBaseAccessParameter dataBaseAccessParameter) throws DataStoreManagerException {
		TransactionParam transactionParam = new TransactionParam(dataBaseAccessParameter);
		Transaction transaction = transactionPool.get(transactionParam);
		if (transaction == null) {
			transaction = new Transaction(transactionParam);
			transactionPool.put(transactionParam, transaction);
		}
		return transaction;
	}
	
	/**
	 * コンストラクタ<p/>
	 * 指定のトランザクションのパラメータを元にトランザクションを開始する。
	 * 
	 * @param transactionParam トランザクションパラメータ
	 * @throws DataStoreManagerException トランザクションの開始に失敗した場合
	 */
	Transaction(TransactionParam transactionParam) throws DataStoreManagerException{
		DataBaseAccessParameter dataAccessParameter = transactionParam.getDataBaseAccessParameter();
		try {
			DataBaseDriverConstants driverConstants = dataAccessParameter.getDriver();
			String driver   = driverConstants.getDriverClass();
			String url      = driverConstants.getUrl(dataAccessParameter.getUrl(), dataAccessParameter.getSid());
			String user     = dataAccessParameter.getUser();
			String password = dataAccessParameter.getPassword();
			Class.forName(driver);
			this.connection = DriverManager.getConnection(url, user, password);
			this.connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			throw new DataStoreManagerException(FAILE_TO_READ_DRIVER, e);
		} catch (SQLException e) {
			throw new DataStoreManagerException(FAILE_TO_CREATE_CONNECTION, e);
		}
	}
	
	ResultSet execute(Sql sql) throws DataStoreManagerException {
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql.toString());
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
		
	}
	
	/**
	 * コネクションを取得します。
	 * 
	 * @return コネクション
	 */
	Connection getConnection() {
		return connection;
	}
	
}

class TransactionParam {
	
	/** カレントスレッドID */
	private long correntThreadId;
	
	/** データストアアクセスパラメータ */
	private DataBaseAccessParameter dataBaseAccessParameter;
	
	/**
	 * コンストラクタ<p/>
	 * このインスタンスを作成したスレッドID、指定のデータストアアクセスパラメータを基にトランザクションパラメータを生成します。
	 * 
	 * @param dataBaseAccessParameter データストアアクセスパラメータ
	 */
	TransactionParam(DataBaseAccessParameter dataBaseAccessParameter) {
		this.correntThreadId = Thread.currentThread().getId();
		this.dataBaseAccessParameter = dataBaseAccessParameter;
	}
	
	/**
	 * カレントスレッドＩＤを取得する。
	 * 
	 * @return カレントスレッドＩＤ
	 */
	public long getCorrentThreadId() {
		return correntThreadId;
	}
	
	/**
	 * データストアアクセスパラメータを取得する。
	 * 
	 * @return データストアアクセスパラメータ
	 */
	public DataBaseAccessParameter getDataBaseAccessParameter() {
		return dataBaseAccessParameter;
	}
}