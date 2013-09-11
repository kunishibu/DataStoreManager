package jp.co.dk.datastoremanager.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.datastoremanager.DaoConstants;
import jp.co.dk.datastoremanager.DataAccessObject;
import jp.co.dk.datastoremanager.DataStore;
import jp.co.dk.datastoremanager.DataStoreKind;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

public class DataBaseDataStore implements DataStore {
	
	/** トランザクション */
	protected Transaction transaction;
	
	/** データベースアクセスパラメータ */
	protected DataBaseAccessParameter dataBaseAccessParameter;
	
	/** SQLリスト */
	List<Sql> sqlList = new ArrayList<Sql>();
	
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
	public void startTrunsaction() throws DataStoreManagerException {
		this.transaction = Transaction.getTransaction(dataBaseAccessParameter);
	}

	@Override
	public DataAccessObject getDataAccessObject(DaoConstants daoConstants) throws DataStoreManagerException {
		DataStoreKind dataStoreKind = this.dataBaseAccessParameter.getDataStoreKind();
		daoConstants.getDataAccessObjectFactory().getDataAccessObject(dataStoreKind, this);
		return null;
	}

	@Override
	public void finishTrunsaction() throws DataStoreManagerException {
		this.transaction.commit();
	}
	
	public boolean isTrunsaction() {
		if (this.transaction != null) return true;
		return false;
	}
	
	ResultSet execute(Sql sql) throws DataStoreManagerException {
		ResultSet result = this.transaction.execute(sql);
		this.sqlList.add(sql);
		return result;
	}
}
