package jp.co.dk.datastoremanager.testdbaccessobjects.table.mysql;

import jp.co.dk.datastoremanager.DataStore;
import jp.co.dk.datastoremanager.database.AbstractDataBaseAccessObject;
import jp.co.dk.datastoremanager.database.DataBaseAccessParameter;
import jp.co.dk.datastoremanager.database.DataBaseDriverConstants;
import jp.co.dk.datastoremanager.database.Sql;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.testdbaccessobjects.record.UsersRecord;
import jp.co.dk.datastoremanager.testdbaccessobjects.table.UsersDao;

public class UserDaoImpl extends AbstractDataBaseAccessObject implements UsersDao{

	public UserDaoImpl(DataBaseAccessParameter dataBaseAccessParameter) throws DataStoreManagerException {
		super(dataBaseAccessParameter);
	}
	
	public UserDaoImpl(DataStore dataStore) throws DataStoreManagerException {
		super(dataStore);
	}
	
	@Override
	public UsersRecord selectUser(String userid) throws DataStoreManagerException {
		Sql sql = new Sql("SELECT * FROM USERS WHERE NAME = ?");
		sql.setParameter(userid);
		return selectSingle(sql, new UsersRecord());
	}

}
