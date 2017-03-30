package jp.co.dk.datastoremanager;

public interface DataStoreFactory {
	
	DataStore createDataStore(DataStoreParameter dataStoreParameter);
}
