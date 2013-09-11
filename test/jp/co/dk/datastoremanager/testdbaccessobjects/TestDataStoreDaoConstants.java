package jp.co.dk.datastoremanager.testdbaccessobjects;

import jp.co.dk.datastoremanager.DaoConstants;
import jp.co.dk.datastoremanager.DataAccessObject;
import jp.co.dk.datastoremanager.DataAccessObjectFactory;
import jp.co.dk.datastoremanager.DataStore;
import jp.co.dk.datastoremanager.DataStoreKind;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.testdbaccessobjects.table.mysql.UserDaoImpl;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public enum TestDataStoreDaoConstants implements DaoConstants{
	USERS("USERS", new DataAccessObjectFactory() {
		@Override
		public DataAccessObject getDataAccessObject(DataStoreKind dataStoreKind, DataStore dataStore) throws DataStoreManagerException{
			switch (dataStoreKind) {
			case ORACLE:
			case MYSQL:
			case POSTGRESQL:
				return new UserDaoImpl(dataStore);
			default:
				throw new DataStoreManagerException(FAILE_TO_CREATE_DATA_ACCESS_OBJECT);
			}
		}
	}),
	;

	private String name;
	
	private DataAccessObjectFactory factory;
	
	private TestDataStoreDaoConstants(String name, DataAccessObjectFactory factory) {
		this.name = name;
		this.factory = factory;
	}
	
	@Override
	public DataAccessObjectFactory getDataAccessObjectFactory() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

}
