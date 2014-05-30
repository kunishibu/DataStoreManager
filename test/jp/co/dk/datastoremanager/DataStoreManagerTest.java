package jp.co.dk.datastoremanager;

import org.junit.Test;

import jp.co.dk.datastoremanager.database.DataBaseDataStore;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.property.DataStoreManagerProperty;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;
import static org.junit.Assert.*;

public class DataStoreManagerTest extends DataStoreManagerTestFoundation{
	
	@Test
	public void constractor() {
		// ==============================正常系==============================
		// 接続先プロパティの設定がマスターのみの場合
		try {
			DataStoreManager target = new DataStoreManager(new DataStoreManagerProperty("properties/test/datastoremanager/success/datastoremanager_accessable_001.properties"));
			assertNotNull(target.defaultDataStore);
			assertNotNull(target.dataStores);
			assertEquals (target.dataStores.size(), 0);
			assertNotNull(target.dataStoreManagerProperty);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), PROPERTY_IS_NOT_SET);
		}
		
		// 接続先プロパティの設定がマスター、個別の接続先に設定している場合
		try {
			DataStoreManager target = new DataStoreManager(new DataStoreManagerProperty("properties/test/datastoremanager/success/datastoremanager_accessable_002.properties"));
			assertNotNull(target.defaultDataStore);
			assertNotNull(target.dataStores);
			assertEquals (target.dataStores.size(), 1);
			assertNotNull(target.dataStores.get("USERS"));
			assertNotNull(target.dataStoreManagerProperty);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), PROPERTY_IS_NOT_SET);
		}
		
		// ==============================異常系==============================
		// コンストラクタにnullを指定した場合、例外が送出されること。
		try {
			new DataStoreManager(null);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), PROPERTY_IS_NOT_SET);
		}
	}
	
	@Test
	public void startTrunsaction() {
		// ==============================正常系==============================
		// 接続先プロパティの設定がマスターのみの場合、正常にトランザクション開始できること。
		try {
			DataStoreManager target = new DataStoreManager(new DataStoreManagerProperty("properties/test/datastoremanager/success/datastoremanager_accessable_001.properties"));
			target.startTrunsaction();
			DataBaseDataStore defaultDataBaseDataStore = (DataBaseDataStore)target.defaultDataStore;
			assertTrue(defaultDataBaseDataStore.isTransaction());
			assertNotNull(target.dataStores);
			assertEquals (target.dataStores.size(), 0);
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// 接続先プロパティの設定がマスター、個別の接続先を指定している場合、正常にトランザクション開始できること。
		try {
			DataStoreManager target = new DataStoreManager(new DataStoreManagerProperty("properties/test/datastoremanager/success/datastoremanager_accessable_002.properties"));
			target.startTrunsaction();
			DataBaseDataStore defaultDataBaseDataStore = (DataBaseDataStore)target.defaultDataStore;
			DataBaseDataStore usersDataBaseDataStore   = (DataBaseDataStore)target.dataStores.get("USERS");
			assertTrue(defaultDataBaseDataStore.isTransaction());
			assertTrue(usersDataBaseDataStore.isTransaction());
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// ==============================異常系==============================
		// 接続先プロパティの設定がマスターのみの場合で、接続先が不正な場合、正常にエラーが発生すること。
		try {
			DataStoreManager target = new DataStoreManager(new DataStoreManagerProperty("properties/test/datastoremanager/success/datastoremanager_access_fail_001.properties"));
			target.startTrunsaction();
			fail();
		} catch (DataStoreManagerException e) {
			if (e.getMessageObj() == FAILE_TO_CREATE_CONNECTION) ;
		}
		
		// 接続先プロパティの設定がマスター、個別の接続先を指定している場合で、接続先が不正な場合、正常にエラーが発生すること。
		try {
			DataStoreManager target = new DataStoreManager(new DataStoreManagerProperty("properties/test/datastoremanager/success/datastoremanager_access_fail_002.properties"));
			target.startTrunsaction();
			fail();
		} catch (DataStoreManagerException e) {
			if (e.getMessageObj() == FAILE_TO_CREATE_CONNECTION) ;
		}
	}
}
