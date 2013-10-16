package jp.co.dk.datastoremanager;

import jp.co.dk.datastoremanager.database.TestBytesSqlParameter;
import jp.co.dk.datastoremanager.database.TestDataBaseAccessParameter;
import jp.co.dk.datastoremanager.database.TestDataBaseDataStore;
import jp.co.dk.datastoremanager.database.TestDataBaseDriverConstants;
import jp.co.dk.datastoremanager.database.TestDataBaseRecord;
import jp.co.dk.datastoremanager.database.TestDateSqlParameter;
import jp.co.dk.datastoremanager.database.TestIntSqlParameter;
import jp.co.dk.datastoremanager.database.TestObjectSqlParameter;
import jp.co.dk.datastoremanager.database.TestSql;
import jp.co.dk.datastoremanager.database.TestStringSqlParameter;
import jp.co.dk.datastoremanager.database.TestTimestampSqlParameter;
import jp.co.dk.datastoremanager.database.TestTransaction;
import jp.co.dk.datastoremanager.exception.TestDataStoreManagerException;
import jp.co.dk.datastoremanager.property.TestDataStoreManagerProperty;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestDataStoreKind.class,
	TestDataStoreManager.class,
	TestDataStoreParameter.class,
	
	TestBytesSqlParameter.class,
	TestDataBaseAccessParameter.class,
	TestDataBaseDataStore.class,
	TestDataBaseDriverConstants.class,
	TestDataBaseRecord.class,
	TestDateSqlParameter.class,
	TestIntSqlParameter.class,
	TestSql.class,
	TestObjectSqlParameter.class,
	TestStringSqlParameter.class,
	TestTimestampSqlParameter.class,
	TestTransaction.class,
	
	TestDataStoreManagerException.class,
	
	TestDataStoreManagerProperty.class,
})
public class TestAll {}
 