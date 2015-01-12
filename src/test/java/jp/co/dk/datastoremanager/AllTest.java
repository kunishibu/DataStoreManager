package jp.co.dk.datastoremanager;

import jp.co.dk.datastoremanager.database.BytesSqlParameterTest;
import jp.co.dk.datastoremanager.database.DataBaseAccessParameterTest;
import jp.co.dk.datastoremanager.database.DataBaseDataStoreTest;
import jp.co.dk.datastoremanager.database.DataBaseDriverConstantsTest;
import jp.co.dk.datastoremanager.database.DataBaseRecordTest;
import jp.co.dk.datastoremanager.database.DateSqlParameterTest;
import jp.co.dk.datastoremanager.database.IntSqlParameterTest;
import jp.co.dk.datastoremanager.database.ObjectSqlParameterTest;
import jp.co.dk.datastoremanager.database.SqlTest;
import jp.co.dk.datastoremanager.database.StringSqlParameterTest;
import jp.co.dk.datastoremanager.database.TimestampSqlParameterTest;
import jp.co.dk.datastoremanager.database.TransactionTest;
import jp.co.dk.datastoremanager.exception.DataStoreManagerExceptionTest;
import jp.co.dk.datastoremanager.property.DataStoreManagerPropertyTest;
import jp.co.dk.datastoremanager.testdbaccessobjects.DataBaseAccessTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	DataStoreKindTest.class,
	DataStoreManagerTest.class,
	DataStoreParameterTest.class,
	
	BytesSqlParameterTest.class,
	DataBaseAccessParameterTest.class,
	DataBaseDataStoreTest.class,
	DataBaseDriverConstantsTest.class,
	DataBaseRecordTest.class,
	DateSqlParameterTest.class,
	IntSqlParameterTest.class,
	SqlTest.class,
	ObjectSqlParameterTest.class,
	StringSqlParameterTest.class,
	TimestampSqlParameterTest.class,
	TransactionTest.class,
	
	DataStoreManagerExceptionTest.class,
	
	DataStoreManagerPropertyTest.class,
	
	DataBaseAccessTest.class,
})
public class AllTest {}
 