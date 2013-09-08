package jp.co.dk.datastoremanager.property;

import static org.junit.Assert.*;
import jp.co.dk.datastoremanager.DaoConstants;
import jp.co.dk.datastoremanager.DataAccessObjectFactory;

import org.junit.Test;

public class TestDataStoreManagerProperty {

	@Test
	public void getString() {
		// ＝＝＝＝＝共通設定の取得＝＝＝＝＝
		// 接続先データストア種別を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_TYPE.getString(), "mysql");
		// ORACLE接続先を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_URL.getString(), "oracleserver:5121");
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_SID.getString(), "oralcesid");
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_USER.getString(), "orausr");
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_PASSWORD.getString(), "oracle");
		// MYSQL接続先を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_URL.getString(), "mysqlserver:5121");
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_SID.getString(), "mysqlsid");
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_USER.getString(), "mysqlusr");
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_PASSWORD.getString(), "mysql");
		// POSTGRESQL接続先を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_URL.getString(), "postgresqlserver:5121");
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_SID.getString(), "postgresqlsid");
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_USER.getString(), "posusr");
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_PASSWORD.getString(), "postgresql");
		
		// ＝＝＝＝＝個別設定の取得＝＝＝＝＝
		// 接続先データストア種別を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_TYPE.getString(FakeDaiConstants.USERS), "mysql");
		// ORACLE接続先を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_URL.getString(FakeDaiConstants.USERS), "usersoracleserver:5121");
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_SID.getString(FakeDaiConstants.USERS), "usersoralcesid");
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_USER.getString(FakeDaiConstants.USERS), "usersorausr");
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_PASSWORD.getString(FakeDaiConstants.USERS), "usersoracle");
		// MYSQL接続先を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_URL.getString(FakeDaiConstants.USERS), "usersmysqlserver:5121");
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_SID.getString(FakeDaiConstants.USERS), "usersmysqlsid");
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_USER.getString(FakeDaiConstants.USERS), "usersmysqlusr");
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_PASSWORD.getString(FakeDaiConstants.USERS), "usersmysql");
		// POSTGRESQL接続先を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_URL.getString(FakeDaiConstants.USERS), "userspostgresqlserver:5121");
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_SID.getString(FakeDaiConstants.USERS), "userspostgresqlsid");
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_USER.getString(FakeDaiConstants.USERS), "usersposusr");
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_PASSWORD.getString(FakeDaiConstants.USERS), "userspostgresql");
		
		// ＝＝＝＝＝個別設定の取得＝＝＝＝＝
		// 接続先データストア種別を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_TYPE.getString(FakeDaiConstants.NO_USERS), "mysql");
		// ORACLE接続先を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_URL.getString(FakeDaiConstants.NO_USERS), "oracleserver:5121");
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_SID.getString(FakeDaiConstants.NO_USERS), "oralcesid");
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_USER.getString(FakeDaiConstants.NO_USERS), "orausr");
		assertEquals(DataStoreManagerProperty.DATASTORE_ORACLE_PASSWORD.getString(FakeDaiConstants.NO_USERS), "oracle");
		// MYSQL接続先を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_URL.getString(FakeDaiConstants.NO_USERS), "mysqlserver:5121");
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_SID.getString(FakeDaiConstants.NO_USERS), "mysqlsid");
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_USER.getString(FakeDaiConstants.NO_USERS), "mysqlusr");
		assertEquals(DataStoreManagerProperty.DATASTORE_MYSQL_PASSWORD.getString(FakeDaiConstants.NO_USERS), "mysql");
		// POSTGRESQL接続先を正常に取得できること
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_URL.getString(FakeDaiConstants.NO_USERS), "postgresqlserver:5121");
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_SID.getString(FakeDaiConstants.NO_USERS), "postgresqlsid");
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_USER.getString(FakeDaiConstants.NO_USERS), "posusr");
		assertEquals(DataStoreManagerProperty.DATASTORE_POSTGRESQL_PASSWORD.getString(FakeDaiConstants.NO_USERS), "postgresql");
		
	}

}

enum FakeDaiConstants implements DaoConstants{
	
	/** サンプルユーザテーブル */
	USERS("USERS", null),
	
	/** サンプルユーザテーブル */
	NO_USERS("NO_USERS", null),
	;
	
	private String name;
	
	private DataAccessObjectFactory factory;
	
	private FakeDaiConstants(String name, DataAccessObjectFactory factory) {
		this.name    = name;
		this.factory = factory;
	}
	
	@Override
	public DataAccessObjectFactory getDataAccessObjectFactory() {
		return this.factory;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
}