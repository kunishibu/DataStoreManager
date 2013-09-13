package jp.co.dk.datastoremanager.database;

import java.sql.PreparedStatement;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

abstract class SqlParameter {
	
	abstract void set(int index, PreparedStatement statement) throws DataStoreManagerException;
	
}
