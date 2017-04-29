package jp.co.dk.datastoremanager.rdb.oracle;

import static org.junit.Assert.*;
import jp.co.dk.datastoremanager.DataStoreManagerTestFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import org.junit.Before;
import org.junit.Test;

public class OracleTableMetaDataTest extends DataStoreManagerTestFoundation{
	
	protected OracleTableMetaData target;

	@Before
 	public void init() throws DataStoreManagerException {
		OracleDataBaseDataStore dbs = new OracleDataBaseDataStore(this.getAccessableDataBaseAccessParameterORACLE());
		dbs.startTransaction();
		target = (OracleTableMetaData)dbs.getTable("EMP");
	}
	
	@Test
	public void createTableMetaData() throws DataStoreManagerException {
		this.target.createHistoryTable();
		this.target.createTriggerHistoryTable();
		this.target.dropHistoryTrigger();
		this.target.dropHistoryTable();
	}
	
}