package jp.co.dk.datastoremanager.database;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.testdbaccessobjects.TestDataStoreDaoConstants;
import jp.co.dk.datastoremanager.testdbaccessobjects.table.UsersDao;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;
import static org.junit.Assert.*;

public class TestDataBaseDataStore extends TestDataStoreManagerFoundation{
	
	@Test
	public void constractor() {
		// ==============================正常系==============================
		// アクセス可能なデータアクセスパラメータを設定した場合、正常にインスタンス生成ができること
		try {
			DataBaseDataStore dataBaseDataStore = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			assertNotNull(dataBaseDataStore.dataBaseAccessParameter);
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// アクセス不可能なデータアクセスパラメータを設定した場合、正常にインスタンス生成ができること
		try {
			DataBaseDataStore dataBaseDataStore = new DataBaseDataStore(super.getAccessFaileDataBaseAccessParameter());
			assertNotNull(dataBaseDataStore.dataBaseAccessParameter);
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
	
	@Test
	public void startTrunsaction() {
		// ==============================正常系==============================
		// アクセス可能なデータアクセスパラメータを設定した場合、正常にインスタンス生成でき、トランザクションが開始できること
		try {
			DataBaseDataStore dataBaseDataStore = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			dataBaseDataStore.startTransaction();
			assertNotNull(dataBaseDataStore.transaction);
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// ==============================異常系==============================
		// アクセス不可能なデータアクセスパラメータを設定した場合、正常にインスタンス生成ができ、トランザクション開始時に例外が発生すること
		try {
			DataBaseDataStore dataBaseDataStore = new DataBaseDataStore(super.getAccessFaileDataBaseAccessParameter());
			dataBaseDataStore.startTransaction();
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_CREATE_CONNECTION);
		}
	}
	
	@Test
	public void getDataAccessObject() {
		// ==============================正常系==============================
		// アクセス可能なデータアクセスパラメータを設定した場合、正常にインスタンス生成ができること
		// トランザクション開始し、データアクセスオブジェクトを取得した場合、正常に取得できること。
		try {
			DataBaseDataStore dataBaseDataStore = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			dataBaseDataStore.startTransaction();
			UsersDao dao = (UsersDao)dataBaseDataStore.getDataAccessObject(TestDataStoreDaoConstants.USERS);
			assertTrue(dao instanceof UsersDao);
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// ==============================異常系==============================
		// アクセス可能なデータアクセスパラメータを設定した場合、正常にインスタンス生成ができること
		// トランザクション開始せずに、データアクセスオブジェクトを取得した場合、例外が発生されること
		try {
			DataBaseDataStore dataBaseDataStore = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			UsersDao dao = (UsersDao)dataBaseDataStore.getDataAccessObject(TestDataStoreDaoConstants.USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), TRANSACTION_IS_NOT_START);
		}
	}
	
	@Test
	public void finishTrunsaction() {
		// ==============================正常系==============================
		// アクセス可能なデータアクセスパラメータを設定した場合、正常にインスタンス生成でき、トランザクションが開始されている場合、
		// trueが返却されること。トランザクション終了後、トランザクション状態はfalseを返却されること。
		try {
			DataBaseDataStore dataBaseDataStore = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			dataBaseDataStore.startTransaction();
			assertTrue(dataBaseDataStore.isTransaction());
			dataBaseDataStore.finishTransaction();
			assertFalse(dataBaseDataStore.isTransaction());
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// ==============================異常系==============================
		// アクセス可能なデータアクセスパラメータを設定した場合、正常にインスタンス生成でき、トランザクションが開始前に
		// トランザクション終了が実行された場合、例外が送出されること。
		try {
			DataBaseDataStore dataBaseDataStore = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			dataBaseDataStore.finishTransaction();
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), TRANSACTION_IS_NOT_START
					);
		}
	}
	
	@Test
	public void isTransaction(){
		// ==============================正常系==============================
		// アクセス可能なデータアクセスパラメータを設定した場合、正常にインスタンス生成でき、トランザクションが開始されている場合、
		// trueが返却されること
		try {
			DataBaseDataStore dataBaseDataStore = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			dataBaseDataStore.startTransaction();
			assertTrue(dataBaseDataStore.isTransaction());
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// アクセス可能なデータアクセスパラメータを設定した場合、正常にインスタンス生成でき、トランザクションが開始されていない場合、
		// falseが返却されること
		try {
			DataBaseDataStore dataBaseDataStore = new DataBaseDataStore(super.getAccessFaileDataBaseAccessParameter());
			assertFalse(dataBaseDataStore.isTransaction());
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
	
	@Test
	public void test_equals() {
		try {
			// トランザクション開始前は同一のパラメータで有るため、hashcode、qualsは一致すること
			DataBaseDataStore target_01_01 = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			DataBaseDataStore target_01_02 = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			DataBaseDataStore target_01_03 = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			List<Object> faileList_01 = new ArrayList<Object>();
			DataBaseDataStore fail_01_01 = new DataBaseDataStore(super.getAccessFaileDataBaseAccessParameter());
			faileList_01.add(fail_01_01);
			testEquals(target_01_01, target_01_02, target_01_03, faileList_01);
			
			DataBaseDataStore target_02_01 = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			DataBaseDataStore target_02_02 = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			target_02_02.startTransaction();
			assertFalse(target_02_01.equals(target_02_02));
			
			DataBaseDataStore target_03_01 = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			target_03_01.startTransaction();
			DataBaseDataStore target_03_02 = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			assertFalse(target_03_01.equals(target_03_02));
			
			DataBaseDataStore target_04_01 = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			target_04_01.startTransaction();
			DataBaseDataStore target_04_02 = new DataBaseDataStore(super.getAccessableDataBaseAccessParameter());
			target_04_02.startTransaction();
			assertTrue(target_04_01.equals(target_04_01));
			assertFalse(target_04_01.equals(target_04_02));
			
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
	
	@Test
	public void test_tostring() {
		try {
			DataBaseAccessParameter param = super.getAccessableDataBaseAccessParameter();
			DataBaseDataStore target = new DataBaseDataStore(param);
			assertEquals(target.toString(), "CONNECTION_HASH=[Transaction has not been started]," + param.toString());
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
}
