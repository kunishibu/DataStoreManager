package jp.co.dk.datastoremanager.database.rdb;

import jp.co.dk.datastoremanager.DaoConstants;
import jp.co.dk.datastoremanager.DataAccessObject;
import jp.co.dk.datastoremanager.DataStore;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

/**
 * 単一のリレーショナルデータベースのデータストア
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class RelationalDataBaseDataStore implements DataStore {

	@Override
	public void startTrunsaction() throws DataStoreManagerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataAccessObject getDataAccessObject(DaoConstants daoConstants) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void finishTrunsaction() throws DataStoreManagerException {
		// TODO Auto-generated method stub
		
	}

}
