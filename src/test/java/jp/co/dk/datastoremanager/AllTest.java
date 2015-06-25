package jp.co.dk.datastoremanager;

import jp.co.dk.datastoremanager.exception.DataStoreManagerExceptionTest;
import jp.co.dk.datastoremanager.property.DataStoreManagerPropertyTest;
import jp.co.dk.datastoremanager.rdb.BytesSqlParameterTest;
import jp.co.dk.datastoremanager.rdb.DataBaseAccessParameterTest;
import jp.co.dk.datastoremanager.rdb.DataBaseDataStoreTest;
import jp.co.dk.datastoremanager.rdb.DataBaseDriverConstantsTest;
import jp.co.dk.datastoremanager.rdb.DataBaseRecordTest;
import jp.co.dk.datastoremanager.rdb.DateSqlParameterTest;
import jp.co.dk.datastoremanager.rdb.IntSqlParameterTest;
import jp.co.dk.datastoremanager.rdb.ObjectSqlParameterTest;
import jp.co.dk.datastoremanager.rdb.SqlTest;
import jp.co.dk.datastoremanager.rdb.StringSqlParameterTest;
import jp.co.dk.datastoremanager.rdb.TimestampSqlParameterTest;
import jp.co.dk.datastoremanager.rdb.TransactionTest;
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
 