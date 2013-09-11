package jp.co.dk.datastoremanager.testdbaccessobjects;

import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.testdbaccessobjects.record.UsersRecord;
import jp.co.dk.datastoremanager.testdbaccessobjects.table.mysql.UserDaoImpl;

import org.junit.Test;

public class TestDataBaseAccess extends TestDataStoreManagerFoundation{

	@Test
	public void access() throws DataStoreManagerException {
		UserDaoImpl UserDaoImpl = new UserDaoImpl(super.getAccessableDataBaseAccessParameter());
		UsersRecord userRecord  = UserDaoImpl.selectUser("1234567890");
		assertEquals(userRecord.getName(), "1234567890");
		assertEquals(userRecord.getAge()   , 20);
	}

}
