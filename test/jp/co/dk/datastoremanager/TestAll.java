package jp.co.dk.datastoremanager;

import jp.co.dk.datastoremanager.database.TestDataBaseDataStore;
import jp.co.dk.datastoremanager.database.rdb.TestRelationalDatabaseDataStore;
import jp.co.dk.datastoremanager.exception.TestDataStoreManagerException;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestDataStoreKind.class,
	TestDataStoreManager.class,
	TestDataStoreParameter.class,
	TestDataStoreManagerException.class,
	
	TestDataBaseDataStore.class,
	TestRelationalDatabaseDataStore.class,
})
public class TestAll {}
 