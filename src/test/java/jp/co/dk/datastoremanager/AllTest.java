package jp.co.dk.datastoremanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	DataStoreKindTest.class,
	DataStoreManagerTest.class,
	DataStoreParameterTest.class,
	
	jp.co.dk.datastoremanager.gdb.AllTest.class,
	jp.co.dk.datastoremanager.rdb.AllTest.class,
	
	jp.co.dk.datastoremanager.exception.AllTest.class,
	jp.co.dk.datastoremanager.property.AllTest.class,
})
public class AllTest {}
 