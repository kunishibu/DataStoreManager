package jp.co.dk.datastoremanager;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class TestDataStoreParameter extends TestCaseTemplate {
	
	@Test
	public void constractor_success01() throws DataStoreManagerException{
		new FakeDataStoreParameter(DataStoreKind.ORACLE);
	}
	
	@Test
	public void constractor_error01(){
		try {
			new FakeDataStoreParameter(null);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DATA_STORE_KIND_IS_NOT_SET);
		}
	}
}

class FakeDataStoreParameter extends DataStoreParameter {

	protected FakeDataStoreParameter(DataStoreKind dataStoreKind) throws DataStoreManagerException {
		super(dataStoreKind);
	}

	@Override
	protected DataStore createDataStore() {
		return null;
	}
	
}