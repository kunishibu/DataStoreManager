package jp.co.dk.datastoremanager.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.datastoremanager.DaoConstants;
import jp.co.dk.datastoremanager.DataAccessObject;
import jp.co.dk.datastoremanager.DataStore;
import jp.co.dk.datastoremanager.DataStoreKind;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public class DataBaseDataStore implements DataStore {
	
	/** データベースアクセスパラメータ */
	protected DataBaseAccessParameter dataBaseAccessParameter;
	
	/** トランザクション */
	protected Transaction transaction;
	
	/** SQLリスト */
	protected List<Sql> sqlList = new ArrayList<Sql>();
	
	/**
	 * コンストラクタ<p/>
	 * 指定のデータベースアクセスパラメータを基にデータベースデータストアを生成します。
	 * 
	 * @param dataBaseAccessParameter データベースアクセスパラメータ
	 */
	DataBaseDataStore(DataBaseAccessParameter dataBaseAccessParameter) {
		this.dataBaseAccessParameter = dataBaseAccessParameter;
	}
	
	@Override
	public void startTransaction() throws DataStoreManagerException {
		this.transaction = Transaction.getTransaction(dataBaseAccessParameter);
	}

	@Override
	public DataAccessObject getDataAccessObject(DaoConstants daoConstants) throws DataStoreManagerException {
		if (this.transaction == null) throw new DataStoreManagerException(TRANSACTION_IS_NOT_START);
		DataStoreKind dataStoreKind = this.dataBaseAccessParameter.getDataStoreKind();
		return daoConstants.getDataAccessObjectFactory().getDataAccessObject(dataStoreKind, this);
	}

	@Override
	public void finishTransaction() throws DataStoreManagerException {
		if (this.transaction == null) throw new DataStoreManagerException(TRANSACTION_IS_NOT_START);
		this.transaction.commit();
		this.transaction = null;
	}
	
	@Override
	public boolean isTransaction() {
		if (this.transaction != null) return true;
		return false;
	}
	
	ResultSet execute(Sql sql) throws DataStoreManagerException {
		ResultSet result = this.transaction.execute(sql);
		this.sqlList.add(sql);
		return result;
	}
}
