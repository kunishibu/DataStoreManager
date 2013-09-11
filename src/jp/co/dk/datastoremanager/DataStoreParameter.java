package jp.co.dk.datastoremanager;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public abstract class DataStoreParameter {
	
	/** データストア種別 */
	private DataStoreKind dataStoreKind;
	
	protected DataStoreParameter(DataStoreKind dataStoreKind) throws DataStoreManagerException {
		if (dataStoreKind == null) throw new DataStoreManagerException(DATA_STORE_KIND_IS_NOT_SET);
		this.dataStoreKind = dataStoreKind;
	}
	
	public DataStoreKind getDataStoreKind() {
		return this.dataStoreKind;
	}
	
	protected abstract DataStore getDataStore();
}
