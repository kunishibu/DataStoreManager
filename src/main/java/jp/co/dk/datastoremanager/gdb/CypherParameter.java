package jp.co.dk.datastoremanager.gdb;

import java.sql.PreparedStatement;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

abstract class CypherParameter {
	
	abstract void set(int index, PreparedStatement statement) throws DataStoreManagerException;
	
}
