package jp.co.dk.datastoremanager.database;

import jp.co.dk.datastoremanager.DataStore;
import jp.co.dk.datastoremanager.DataStoreKind;
import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import org.junit.Test;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;
import static org.junit.Assert.*;

public class TestDataBaseAccessParameter  extends TestDataStoreManagerFoundation{
	
	@Test
	public void constractor() {
		// ==============================正常系==============================
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, "255.255.255.255", "test_db", "test_user", "123456");
			assertEquals(target.getDataStoreKind(), DataStoreKind.ORACLE);
			assertEquals(target.getDriver(), DataBaseDriverConstants.ORACLE);
			assertEquals(target.getUrl(), "255.255.255.255");
			assertEquals(target.getSid(), "test_db");
			assertEquals(target.getUser(), "test_user");
			assertEquals(target.getPassword(), "123456");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// ==============================異常系==============================
		// データストア種別にnullが指定された場合、例外が送出されること。
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(null, DataBaseDriverConstants.ORACLE, "255.255.255.255", "test_db", "test_user", "123456");
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DATA_STORE_KIND_IS_NOT_SET);
		}
		
		// ドライバーにnullが指定された場合、例外が送出されること。
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, null, "255.255.255.255", "test_db", "test_user", "123456");
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DRIVER_IS_NOT_SET);
		}
		
		// URLにnullが指定された場合、例外が送出されること。
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, null, "test_db", "test_user", "123456");
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), URL_IS_NOT_SET);
		}
		
		// URLに空文字が指定された場合、例外が送出されること。
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, "", "test_db", "test_user", "123456");
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), URL_IS_NOT_SET);
		}
		
		// SIDにnullが指定された場合、例外が送出されること。
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, "255.255.255.255", null, "test_user", "123456");
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), SID_IS_NOT_SET);
		}
		
		// SIDに空文字が指定された場合、例外が送出されること。
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, "255.255.255.255", "", "test_user", "123456");
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), SID_IS_NOT_SET);
		}
		
		// ユーザにnullが指定された場合、例外が送出されること。
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, "255.255.255.255", "test_user", null, "123456");
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), USER_IS_NOT_SET);
		}
		
		// ユーザに空文字が指定された場合、例外が送出されること。
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, "255.255.255.255", "test_user", "", "123456");
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), USER_IS_NOT_SET);
		}
		
		// パスワードにnullが指定された場合、例外が送出されること。
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, "255.255.255.255", "test_user", "test_user", null);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), PASSWORD_IS_NOT_SET);
		}
		
		// パスワードに空文字が指定された場合、例外が送出されること。
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, "255.255.255.255", "test_user", "test_user", "");
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), PASSWORD_IS_NOT_SET);
		}
	}
	
	@Test
	public void createDataStore() {
		// ==============================正常系==============================
		try {
			DataBaseAccessParameter target = new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, "255.255.255.255", "test_db", "test_user", "123456");
			assertEquals(target.getDataStoreKind(), DataStoreKind.ORACLE);
			assertEquals(target.getDriver(), DataBaseDriverConstants.ORACLE);
			assertEquals(target.getUrl(), "255.255.255.255");
			assertEquals(target.getSid(), "test_db");
			assertEquals(target.getUser(), "test_user");
			assertEquals(target.getPassword(), "123456");
			
			DataStore dataStore = target.createDataStore();
			assertTrue(dataStore instanceof DataBaseDataStore);
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
}
