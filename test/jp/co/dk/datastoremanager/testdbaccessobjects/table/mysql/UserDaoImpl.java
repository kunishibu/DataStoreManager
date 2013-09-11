package jp.co.dk.datastoremanager.testdbaccessobjects.table.mysql;

import jp.co.dk.datastoremanager.database.AbstractDataBase;
import jp.co.dk.datastoremanager.database.DataBaseAccessParameter;
import jp.co.dk.datastoremanager.database.DataBaseDriverConstants;
import jp.co.dk.datastoremanager.database.Sql;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.testdbaccessobjects.record.UsersRecord;
import jp.co.dk.datastoremanager.testdbaccessobjects.table.UsersDao;

public class UserDaoImpl extends AbstractDataBase implements UsersDao{

	protected UserDaoImpl(DataBaseDriverConstants driver, String url, String sid, String user, String password) throws DataStoreManagerException {
		super(driver, url, sid, user, password);
	}
	
	public UserDaoImpl(DataBaseAccessParameter parameter) throws DataStoreManagerException {
		super(parameter);
	}

	@Override
	public UsersRecord selectUser(String userid) throws DataStoreManagerException {
		Sql sql = new Sql("SELECT * FROM USERS WHERE NAME = ?");
		sql.setParameter(userid);
		return selectSingle(sql, new UsersRecord());
	}

}
