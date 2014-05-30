package jp.co.dk.datastoremanager.testdbaccessobjects.table;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.testdbaccessobjects.record.UsersRecord;

/**
 * ユーザテーブルへのDAOインターフェース
 * 
 * @version 1.0
 * @author D.Kanno
 */
public interface UsersDao {
	
	public void createTable() throws DataStoreManagerException;
	
	public UsersRecord select(String stringData)throws DataStoreManagerException;
	
	public void insert(String stringData, int intData, long longData, Date dateData, Timestamp timestampData, byte[] byteDate, Serializable objectData, Object convertByteData)throws DataStoreManagerException;
	
	public void dropTable() throws DataStoreManagerException;
}
