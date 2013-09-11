package jp.co.dk.datastoremanager.database;

import jp.co.dk.datastoremanager.DaoConstants;
import jp.co.dk.datastoremanager.DataAccessObject;
import jp.co.dk.datastoremanager.DataStore;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

public class DataBaseDataStore implements DataStore {
	
	/** トランザクション */
	private Transaction transaction;
	
	/** データベースアクセスパラメータ */
	private DataBaseAccessParameter dataBaseAccessParameter;
	
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
	public DataAccessObject getDataAccessObject(DaoConstants daoConstants) {
		return null;
	}

	@Override
	public void finishTrunsaction() throws DataStoreManagerException {
		
	}

}
