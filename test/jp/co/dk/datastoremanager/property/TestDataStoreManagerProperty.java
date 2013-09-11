package jp.co.dk.datastoremanager.property;

import static org.junit.Assert.*;
import jp.co.dk.datastoremanager.DaoConstants;
import jp.co.dk.datastoremanager.DataAccessObjectFactory;
import jp.co.dk.datastoremanager.DataStoreKind;
import jp.co.dk.datastoremanager.DataStoreParameter;
import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;
import jp.co.dk.datastoremanager.database.DataBaseAccessParameter;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.message.DataStoreManagerMessage;

import org.junit.Test;

public class TestDataStoreManagerProperty extends TestDataStoreManagerFoundation{

	@Test
	public void getDataStoreParameter_success() {
		// ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝指定のDAOの設定の取得＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
		// ORACLEの設定値を正常に取得できること
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/success/oracle.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.USERS);
			assertTrue  (dataStoreParameter instanceof DataBaseAccessParameter);
			DataBaseAccessParameter dataBaseAccessParameter = (DataBaseAccessParameter)dataStoreParameter;
			assertEquals(dataStoreParameter.getDataStoreKind(), DataStoreKind.ORACLE);
			assertEquals(dataBaseAccessParameter.getUrl()     , "users_db_server");
			assertEquals(dataBaseAccessParameter.getSid()     , "users_sid");
			assertEquals(dataBaseAccessParameter.getUser()    , "users_user");
			assertEquals(dataBaseAccessParameter.getPassword(), "users_password");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// MYSQLの設定値を正常に取得できること
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/success/mysql.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.USERS);
			assertTrue  (dataStoreParameter instanceof DataBaseAccessParameter);
			DataBaseAccessParameter dataBaseAccessParameter = (DataBaseAccessParameter)dataStoreParameter;
			assertEquals(dataStoreParameter.getDataStoreKind(), DataStoreKind.MYSQL);
			assertEquals(dataBaseAccessParameter.getUrl()     , "users_db_server");
			assertEquals(dataBaseAccessParameter.getSid()     , "users_sid");
			assertEquals(dataBaseAccessParameter.getUser()    , "users_user");
			assertEquals(dataBaseAccessParameter.getPassword(), "users_password");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// POSTGRESQLの設定値を正常に取得できること
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/success/postgresql.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.USERS);
			assertTrue  (dataStoreParameter instanceof DataBaseAccessParameter);
			DataBaseAccessParameter dataBaseAccessParameter = (DataBaseAccessParameter)dataStoreParameter;
			assertEquals(dataStoreParameter.getDataStoreKind(), DataStoreKind.POSTGRESQL);
			assertEquals(dataBaseAccessParameter.getUrl()     , "users_db_server");
			assertEquals(dataBaseAccessParameter.getSid()     , "users_sid");
			assertEquals(dataBaseAccessParameter.getUser()    , "users_user");
			assertEquals(dataBaseAccessParameter.getPassword(), "users_password");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝共通設定の取得＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
		// 指定のDAO定数を設定しない場合、共通設定が取得できること
		// ORACLEの設定値を正常に取得できること
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/success/oracle.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter();
			assertTrue  (dataStoreParameter instanceof DataBaseAccessParameter);
			DataBaseAccessParameter dataBaseAccessParameter = (DataBaseAccessParameter)dataStoreParameter;
			assertEquals(dataStoreParameter.getDataStoreKind(), DataStoreKind.ORACLE);
			assertEquals(dataBaseAccessParameter.getUrl()     , "default_db_server");
			assertEquals(dataBaseAccessParameter.getSid()     , "default_sid");
			assertEquals(dataBaseAccessParameter.getUser()    , "default_user");
			assertEquals(dataBaseAccessParameter.getPassword(), "default_password");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// MYSQLの設定値を正常に取得できること
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/success/mysql.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter();
			assertTrue  (dataStoreParameter instanceof DataBaseAccessParameter);
			DataBaseAccessParameter dataBaseAccessParameter = (DataBaseAccessParameter)dataStoreParameter;
			assertEquals(dataBaseAccessParameter.getUrl()     , "default_db_server");
			assertEquals(dataBaseAccessParameter.getSid()     , "default_sid");
			assertEquals(dataBaseAccessParameter.getUser()    , "default_user");
			assertEquals(dataBaseAccessParameter.getPassword(), "default_password");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// POSTGRESQLの設定値を正常に取得できること
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/success/postgresql.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter();
			assertTrue  (dataStoreParameter instanceof DataBaseAccessParameter);
			DataBaseAccessParameter dataBaseAccessParameter = (DataBaseAccessParameter)dataStoreParameter;
			assertEquals(dataStoreParameter.getDataStoreKind(), DataStoreKind.POSTGRESQL);
			assertEquals(dataBaseAccessParameter.getUrl()     , "default_db_server");
			assertEquals(dataBaseAccessParameter.getSid()     , "default_sid");
			assertEquals(dataBaseAccessParameter.getUser()    , "default_user");
			assertEquals(dataBaseAccessParameter.getPassword(), "default_password");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// 指定のDAO定数がプロパティが存在しない場合、共通設定が取得できること
		// ORACLEの設定値を正常に取得できること
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/success/oracle.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.NO_USERS);
			assertTrue  (dataStoreParameter instanceof DataBaseAccessParameter);
			DataBaseAccessParameter dataBaseAccessParameter = (DataBaseAccessParameter)dataStoreParameter;
			assertEquals(dataStoreParameter.getDataStoreKind(), DataStoreKind.ORACLE);
			assertEquals(dataBaseAccessParameter.getUrl()     , "default_db_server");
			assertEquals(dataBaseAccessParameter.getSid()     , "default_sid");
			assertEquals(dataBaseAccessParameter.getUser()    , "default_user");
			assertEquals(dataBaseAccessParameter.getPassword(), "default_password");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// MYSQLの設定値を正常に取得できること
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/success/mysql.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.NO_USERS);
			assertTrue  (dataStoreParameter instanceof DataBaseAccessParameter);
			DataBaseAccessParameter dataBaseAccessParameter = (DataBaseAccessParameter)dataStoreParameter;
			assertEquals(dataBaseAccessParameter.getUrl()     , "default_db_server");
			assertEquals(dataBaseAccessParameter.getSid()     , "default_sid");
			assertEquals(dataBaseAccessParameter.getUser()    , "default_user");
			assertEquals(dataBaseAccessParameter.getPassword(), "default_password");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// POSTGRESQLの設定値を正常に取得できること
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/success/postgresql.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.NO_USERS);
			assertTrue  (dataStoreParameter instanceof DataBaseAccessParameter);
			DataBaseAccessParameter dataBaseAccessParameter = (DataBaseAccessParameter)dataStoreParameter;
			assertEquals(dataStoreParameter.getDataStoreKind(), DataStoreKind.POSTGRESQL);
			assertEquals(dataBaseAccessParameter.getUrl()     , "default_db_server");
			assertEquals(dataBaseAccessParameter.getSid()     , "default_sid");
			assertEquals(dataBaseAccessParameter.getUser()    , "default_user");
			assertEquals(dataBaseAccessParameter.getPassword(), "default_password");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
	
	@Test
	public void getDataStoreParameter_error() {
		// データストア種別が設定されていない場合、例外が発生すること。
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/datastore_type_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.DATASTORE_KIND_VALUE_IS_FAILE); 
		}
		
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/datastore_type_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.NO_USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.DATASTORE_KIND_VALUE_IS_FAILE); 
		}
		
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/datastore_type_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter();
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.DATASTORE_KIND_VALUE_IS_FAILE); 
		}
		
		// URLが設定されていない場合、例外が発生すること。
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/url_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.URL_IS_NOT_SET); 
		}
		
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/url_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.NO_USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.URL_IS_NOT_SET); 
		}
		
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/url_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter();
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.URL_IS_NOT_SET); 
		}
		
		
		// SIDが設定されていない場合、例外が発生すること。
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/sid_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.SID_IS_NOT_SET); 
		}
		
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/sid_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.NO_USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.SID_IS_NOT_SET); 
		}
		
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/sid_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter();
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.SID_IS_NOT_SET); 
		}
		
		
		// ユーザが設定されていない場合、例外が発生すること。
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/user_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.USER_IS_NOT_SET); 
		}
		
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/user_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.NO_USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.USER_IS_NOT_SET); 
		}
		
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/user_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter();
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.USER_IS_NOT_SET); 
		}
		
		// パスワードが設定されていない場合、例外が発生すること。
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/password_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.PASSWORD_IS_NOT_SET); 
		}
		
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/password_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter(FakeDaiConstants.NO_USERS);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.PASSWORD_IS_NOT_SET); 
		}
		
		try {
			DataStoreManagerProperty dataStoreManagerProperty = new DataStoreManagerProperty("properties/test/datastoremanager/error/password_not_set.properties");
			DataStoreParameter dataStoreParameter = dataStoreManagerProperty.getDataStoreParameter();
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DataStoreManagerMessage.PASSWORD_IS_NOT_SET); 
		}
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